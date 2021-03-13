package com.frame.util.fileUploadDownUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.frame.business.project.model.project;
import com.frame.util.Base64;
import com.frame.util.UUIDUtil;

/**
 * 
 * <p>[文件下载工具类]<p>
 * @author yushp
 *
 */
public class FileDownUtil {
	/**
	 * 
	 * 1.  IE浏览器，采用URLEncoder编码
	 * 2.  Opera浏览器，采用filename*方式
	 * 3.  Safari浏览器，采用ISO编码的中文输出
	 * 4.  Chrome浏览器，采用Base64编码或ISO编码的中文输出
	 * 5.  FireFox浏览器，采用Base64或filename*或ISO编码的中文输出
	 * 
	 * <p>[根据不同的浏览器返回不同的附件的中文编码]</p>
	 *
	 * @param userAgent
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getFileNameByBrowser(String userAgent, String fileName) throws UnsupportedEncodingException{
		String new_filename = URLEncoder.encode(fileName, "UTF8");  
		String rtn = "filename=\"" + new_filename + "\"";  
		if (userAgent != null) { 
		     userAgent = userAgent.toLowerCase(); 
		     // IE浏览器，只能采用URLEncoder编码 
		     if (userAgent.indexOf("msie") != -1) { 
		    	 rtn = "filename=\"" + new_filename + "\""; 
		     } 
		     // Opera浏览器只能采用filename* 
		     else if (userAgent.indexOf("opera") != -1) { 
		    	 rtn = "filename*=UTF-8''" + new_filename; 
		     } 
		     // Safari浏览器，只能采用ISO编码的中文输出 
		     else if (userAgent.indexOf("safari") != -1 ) { 
		    	 rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"),"ISO8859-1") + "\""; 
		     } 
		     // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出 
		     else if (userAgent.indexOf("applewebkit") != -1 ) { 
		    	 new_filename = "=?UTF-8?B?" + (new String(Base64.encode(fileName.getBytes("UTF-8")))) + "?=";  
		         rtn = "filename=\"" + new_filename + "\""; 
		     } 
		     // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出 
		     else if (userAgent.indexOf("mozilla") != -1) { 
		         rtn = "filename*=UTF-8''" + new_filename; 
		     } 
		}  
		
		return rtn ;
	}
	
	/**
	 * @function保存文件 
	 * @param files
	 * @param sysPath
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> saveFile(List<MultipartFile> files,
			String sysPath) throws Exception {
		Map<String,String> re=new HashMap<String,String>();
		// 得到文件上传路径
		System.out.print("上传文件，配置路径："+sysPath);
		// 判断上传路径是否存在
		File fpath = new File(sysPath);
		if (!fpath.exists()) {
			fpath.mkdirs();
		}
		try{
			for (int i = 0; i < files.size(); i++) {
				CommonsMultipartFile mf = (CommonsMultipartFile) files.get(i);
				if (mf.getSize() > 0) {
					if (mf.getSize() > 10485760) {
						throw new Exception("保存失败！上传文件过大！");
					}
					int index = mf.getOriginalFilename().lastIndexOf(".");
					// 文件名
					String filename = ""+System.currentTimeMillis()+"_"+mf.getOriginalFilename();
					// 文件后缀
					String extendname = mf.getOriginalFilename().substring(index+1);				
					File file = new File(sysPath + File.separator + filename+ "."+ extendname);
					
					// 判断是否有重名的
					while (!file.exists()) {
						file = new File(sysPath + File.separator + filename + "."+ extendname);
						FileOutputStream output = new FileOutputStream(file);
						FileCopyUtils.copy(mf.getBytes(), output);
						output.close(); 
						System.out.print("上传文件成功，文件保存路径："+ sysPath + File.separator + filename + "."+ extendname);
						re.put("path", filename);
						re.put("result", "success");
						re.put("filename", filename);
					}
				}
				else{
					re.put("result", "false");
				}
			}
		}catch(Exception ex){
			re.put("result", "false");
		}
		return re;
	}
	
	
	/**
	 * @function文件下载
	 * @param attachFileInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void dowloadFile(String aa, String filePath, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sysPath = request.getSession().getServletContext().getRealPath("/");
		
		// 检查文件是否存在
		File obj = new File(sysPath+ File.separator + aa + File.separator + filePath);
		if (!obj.exists()) {
			response.setContentType("text/html;charset=GBK");
			System.out.print("指定文件不存在:" + obj.getAbsolutePath());
		} else {
			FileInputStream in = new FileInputStream(obj);
			OutputStream out = response.getOutputStream();
			try {
				String agent = (String)request.getHeader("USER-AGENT");
				response.setContentType("multipart/form-data");
				response.setContentLength((int) obj.length());
				response.setHeader("Content-Disposition", "attachment;"
						+ FileDownUtil.getFileNameByBrowser(agent, filePath));
				byte[] bb = new byte[1024];
				int a = -1;
				while ((a = in.read(bb)) != -1) {
					out.write(bb, 0, a);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				in.close();
				out.close();
			}
		}
	}
	
	/**
	 * @function文件下载直接打开
	 * @param attachFileInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public static void dowloadFileToOpen(String aa, String filePath, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String sysPath = request.getSession().getServletContext().getRealPath("/");
		
		// 检查文件是否存在
		File obj = new File(sysPath+ File.separator + aa + File.separator + filePath);
		if (!obj.exists()) {
			response.setContentType("text/html;charset=GBK");
			System.out.print("指定文件不存在:" + obj.getAbsolutePath());
		} else {
			FileInputStream in = new FileInputStream(obj);
			OutputStream out = response.getOutputStream();
			try {
				String agent = (String)request.getHeader("USER-AGENT");
//				response.setContentType("multipart/form-data");
				response.setContentLength((int) obj.length());
				response.setHeader("Content-Disposition", "inline;"
						+ FileDownUtil.getFileNameByBrowser(agent, filePath));
				byte[] bb = new byte[1024];
				int a = -1;
				while ((a = in.read(bb)) != -1) {
					out.write(bb, 0, a);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				in.close();
				out.close();
			}
		}
	}
	
	public static List<project> parseAllBill(File operateFile, String extension) throws Exception {
		List<project> allList = new ArrayList<project>();
		FileInputStream fileIn = new FileInputStream(operateFile);
		try{
			if ("xls".equals(extension.toLowerCase())) {
				HSSFWorkbook hwb = new HSSFWorkbook(fileIn);
				HSSFSheet sheet = hwb.getSheetAt(0);
				POIExcelUtils poiExcel = new POIExcelUtils();
				for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
					HSSFRow row = sheet.getRow(i);
					project billInfo = new project();
					String projectid2= UUIDUtil.getUUID();
					String projectname=poiExcel.read2003ExcelCell(row, 0);	//项目名称
					String yearplan=poiExcel.read2003ExcelCell(row, 1);	//年度大计划
					String cityleaders=poiExcel.read2003ExcelCell(row, 2);	//分管市领导
					String managementoffice=poiExcel.read2003ExcelCell(row, 3);	//责任处室
					String responsibilityunit=poiExcel.read2003ExcelCell(row, 4);	//责任单位
					String insteadunit=poiExcel.read2003ExcelCell(row, 5);	//代建单位
					String localgoverment=poiExcel.read2003ExcelCell(row, 6);	//辖区政府
					String projecttype=poiExcel.read2003ExcelCell(row, 7);//	项目类型
					String constructionnature=poiExcel.read2003ExcelCell(row, 8);	//建设性质
					String projectstage=poiExcel.read2003ExcelCell(row, 9);	//建设阶段
					String starttime=poiExcel.read2003ExcelCell(row, 10);		//开始日期
					String endtime=poiExcel.read2003ExcelCell(row, 11);		//结束日期
					String constructioncontent=poiExcel.read2003ExcelCell(row, 12);	//建设内容
					String remark=poiExcel.read2003ExcelCell(row, 13);	//备注
					
					billInfo.setProjectid(projectid2);
					billInfo.setProjectname(projectname);
					billInfo.setPlanyear(yearplan);
					billInfo.setCityleaders(cityleaders);
					billInfo.setManagementoffice(managementoffice);
					billInfo.setResponsibilityunit(responsibilityunit);
					billInfo.setInsteadunit(insteadunit);
					billInfo.setLocalgoverment(localgoverment);
					billInfo.setProjecttype(projecttype);
					billInfo.setConstructionnature(constructionnature);
					billInfo.setProjectstage(projectstage);
					billInfo.setStarttime(starttime);
					billInfo.setEndtime(endtime);
					billInfo.setConstructioncontent(constructioncontent);
					billInfo.setRemark(remark);
					
					allList.add(billInfo);
				}
			} else if ("xlsx".equals(extension.toLowerCase())) {
				XSSFWorkbook hwb = new XSSFWorkbook(fileIn);
				XSSFSheet sheet = hwb.getSheetAt(0);
				POIExcelUtils poiExcel = new POIExcelUtils();
				for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
					XSSFRow row = sheet.getRow(i);
					project billInfo = new project();
					String projectid2= UUIDUtil.getUUID();
					String projectname=poiExcel.read2007ExcelCell(row, 0);	//项目名称
					String yearplan=poiExcel.read2007ExcelCell(row, 1);	//年度大计划
					String cityleaders=poiExcel.read2007ExcelCell(row, 2);	//分管市领导
					String managementoffice=poiExcel.read2007ExcelCell(row, 3);	//责任处室
					String responsibilityunit=poiExcel.read2007ExcelCell(row, 4);	//责任单位
					String insteadunit=poiExcel.read2007ExcelCell(row, 5);	//代建单位
					String localgoverment=poiExcel.read2007ExcelCell(row, 6);	//辖区政府
					String projecttype=poiExcel.read2007ExcelCell(row, 7);//	项目类型
					String constructionnature=poiExcel.read2007ExcelCell(row, 8);	//建设性质
					String projectstage=poiExcel.read2007ExcelCell(row, 9);	//建设阶段
					String starttime=poiExcel.read2007ExcelCell(row, 10);		//开始日期
					String endtime=poiExcel.read2007ExcelCell(row, 11);		//结束日期
					String constructioncontent=poiExcel.read2007ExcelCell(row, 12);	//建设内容
					String remark=poiExcel.read2007ExcelCell(row, 13);	//备注
					
					billInfo.setProjectid(projectid2);
					billInfo.setProjectname(projectname);
					billInfo.setPlanyear(yearplan);
					billInfo.setCityleaders(cityleaders);
					billInfo.setManagementoffice(managementoffice);
					billInfo.setResponsibilityunit(responsibilityunit);
					billInfo.setInsteadunit(insteadunit);
					billInfo.setLocalgoverment(localgoverment);
					billInfo.setProjecttype(projecttype);
					billInfo.setConstructionnature(constructionnature);
					billInfo.setProjectstage(projectstage);
					billInfo.setStarttime(starttime);
					billInfo.setEndtime(endtime);
					billInfo.setConstructioncontent(constructioncontent);
					billInfo.setRemark(remark);
					
					allList.add(billInfo);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			fileIn.close();
		}
		
		return allList;
	}
}
