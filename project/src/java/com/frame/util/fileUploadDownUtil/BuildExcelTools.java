package com.frame.util.fileUploadDownUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.frame.util.date.DateFormat;

/**
 * 构建excel文档工具类，并有I/O流输出
 * 
 * @author yushp
 * @version 2014-09-25 version-0
 * */

public class BuildExcelTools {
	
	/**
	 * 构建excel方法
	 * 
	 * @param request
	 * @param response
	 * @param exportData 要导出的数据
	 * @param exportInfo 对应的要导出数据的信息
	 * @param excelName 要导出的文件名
	 * 
	 * */
	public static void buildExcel(HttpServletRequest request,
			HttpServletResponse response, ArrayList<Object> exportData,
			ArrayList<Object> exportInfo, String excelName) {
		try {
			String agent = (String)request.getHeader("USER-AGENT");
			ServletOutputStream os = response.getOutputStream(); // 获得输出流
			response.reset(); // 清空输出流
			response.setContentType("APPLICATION/msexcel");// vnd.ms-excel/msexcel
			response.setHeader("Content-Disposition", "attachment; "
					+ FileDownUtil.getFileNameByBrowser(agent,excelName));;
			// 构建workBook
			HSSFWorkbook workbook = null;
			if (exportData != null && exportInfo != null) {
				workbook = createWorkBook(exportData, exportInfo);
			}
			// 输出excel
			try {
				workbook.write(os);
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
				os.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建workBook
	 * 
	 * @param exportData 要导出的数据
	 * @param exportInfo 对应的要导出数据的信息
	 * @return HSSFWorkbook 文件对象
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HSSFWorkbook createWorkBook(ArrayList<Object> exportData,
			ArrayList<Object> exportInfo) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCell cell = null;
		for (int i = 0; i < exportData.size(); i++) {
			Map<String, Object> dataInfo = (Map<String, Object>) exportInfo
					.get(i);
			String sheetName = dataInfo.get("SHEET_NAME").toString();
			String sheetTitle = dataInfo.get("SHEET_TITLE").toString();
			// 列头
			LinkedHashMap<String, String> bodyInfo = (LinkedHashMap<String, String>) dataInfo
					.get("BODY_TITLE");
			// 构建第一页
			HSSFSheet sheet1 = workbook.createSheet(sheetName);
			// 标题
			HSSFCellStyle titleCellstyle = getTitleStyle(workbook, "1L-Title");
			Region region = new Region((short)0,(short)0,(short)0,(short)(bodyInfo.size()-1));   
			sheet1.addMergedRegion(region);   
			// 得到所有区域   
			sheet1.getNumMergedRegions();
			myCreateCell(0, sheetTitle, sheet1.createRow(0), cell,
					titleCellstyle);
			HSSFCellStyle titleCellstyle2 = getTitleStyle(workbook, "2L-Title");
			Region region1 = new Region((short)1,(short)0,(short)1,(short)(bodyInfo.size()-1));   
			sheet1.addMergedRegion(region1);   
			// 得到所有区域   
			sheet1.getNumMergedRegions();
			myCreateCell(0, DateFormat.getStringCurrentDateShort(), sheet1
					.createRow(1), cell, titleCellstyle2);

			
			HSSFCellStyle CTitleCellstyle = getTitleStyle(workbook, "C-Title");
			HSSFCellStyle CCellstyle = getTitleStyle(workbook, "C-Cell");
			Iterator iterator = bodyInfo.keySet().iterator();
			int j = 0;
			HSSFRow row = sheet1.createRow(2);
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				String value = bodyInfo.get(key);
				myCreateCell(j, value, row, cell,CTitleCellstyle);
				j++;
			}
			// 数据区
			List<Map<String, ?>> iteList = (List<Map<String, ?>>) exportData.get(i);
			for (int k = 0; k < iteList.size(); k++) {
				Map<String, ?> itmMap = iteList.get(k);
				int l=0;
				HSSFRow rowData = sheet1.createRow(3+k);
				Iterator iteData = bodyInfo.keySet().iterator();
				while (iteData.hasNext()) {
					String key = (String) iteData.next();
					Object value = itmMap.get(key)==null?"":itmMap.get(key);
					myCreateCell(l, value.toString(), rowData, cell,CCellstyle);
					l++;
				}
			} 
//			sheet1.setColumnWidth((short)6, (short)(30*256));
			for(int columnIndex=0;columnIndex<bodyInfo.size();columnIndex++){
//				sheet1.autoSizeColumn((short)columnIndex);
				sheet1.setColumnWidth((short)columnIndex, (short)(124*256/bodyInfo.size()));
			}
		}

		return workbook;
	}

	/**
	 * 为每一个cell赋值
	 * 
	 * @param cellnum 位置从0开始
	 * @param value 填充cell的值
	 * @param row 行对象
	 * @param cell 列对象
	 * @param style 样式
	 * */
	private static void myCreateCell(int cellnum, String value, HSSFRow row,
			HSSFCell cell, HSSFCellStyle style) throws Exception {
		cell = row.createCell((short) cellnum,
				HSSFCell.ENCODING_COMPRESSED_UNICODE);
//		cell.setEncoding(HSSFCell.ENCODING_UTF_16);// 设置cell中文编码；
		cell.setCellValue(new HSSFRichTextString(value));
		cell.setCellStyle(style);
	}

	/**
	 * 设置cell样式
	 * 
	 * @param workbook 文件对象
	 * @param styleType 样式类型（一级标题，二级标题等）
	 * @return HSSFCellStyle 样式对象
	 * */
	private static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook, String styleType)
			throws Exception {
		// 设置字体;
		HSSFFont font = workbook.createFont();
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置字体大小 & 背景颜色
		if (styleType.equals("1L-Title")) {
			font.setFontHeightInPoints((short) 13);
			style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		} else if (styleType.equals("2L-Title")) {
			font.setFontHeightInPoints((short) 10);
			style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		} else if (styleType.equals("C-Title")) {
			font.setFontHeightInPoints((short) 10);
			style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		} else if (styleType.equals("C-Cell")) {
			font.setFontHeightInPoints((short) 10);
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		} else {
			font.setFontHeightInPoints((short) 10);
		}
		if(!styleType.equals("1L-Title")&&!styleType.equals("2L-Title")){
			// 设置底边框;
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// 设置底边框颜色;
			style.setBottomBorderColor(HSSFColor.BLACK.index);
			// 设置左边框;
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			// 设置左边框颜色;
			style.setLeftBorderColor(HSSFColor.BLACK.index);
			// 设置右边框;
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			// 设置右边框颜色;
			style.setRightBorderColor(HSSFColor.BLACK.index);
			// 设置顶边框;
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			// 设置顶边框颜色;
			style.setTopBorderColor(HSSFColor.BLACK.index);
		}
		// 设置字体名字;
		font.setFontName("宋体");
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(true);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}
	
	/**
	 * 构建excel输出到物理路径
	 * 
	 * @param request
	 * @param response
	 * @param exportData 要导出的数据
	 * @param exportInfo 对应的要导出数据的信息
	 * @param excelName 要导出的文件名
	 * 
	 * */
	public static void exportExcel(HttpServletRequest request,ArrayList<Object> exportData,
			ArrayList<Object> exportInfo, String savePathExcelName) {
		try {
			// 构建workBook
			HSSFWorkbook workbook = null;
			if (exportData != null && exportInfo != null) {
				workbook = createWorkBook(exportData, exportInfo);
			}
			// 输出excel到物理路径
			File file = new File(savePathExcelName);
			OutputStream out = new FileOutputStream(file);
			
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
