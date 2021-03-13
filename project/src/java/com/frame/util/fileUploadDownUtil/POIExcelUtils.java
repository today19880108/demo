package com.frame.util.fileUploadDownUtil;



import java.text.DecimalFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.frame.util.date.DateFormat;


public class POIExcelUtils {    

	/**
	 * 读取 office 2003 excelCell
	 */
	public String read2003ExcelCell(HSSFRow row,int cellNum) {
		String returnValue = null;
		HSSFCell cell = row.getCell((short) cellNum);
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				DecimalFormat df = new DecimalFormat("0");
				returnValue = df.format(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				boolean booleanTxt = cell.getBooleanCellValue();
				returnValue = booleanTxt + "";
				break;
			case Cell.CELL_TYPE_BLANK:
				returnValue = "";
				break;
			case Cell.CELL_TYPE_FORMULA:
				returnValue = cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_STRING:
				returnValue = cell.getStringCellValue();
				break;
			}
		}else{
			returnValue = "";
		}
		return returnValue;
	}
	


	/**
	 * 读取 office 2007 excelCell
	 */
	public String read2007ExcelCell(XSSFRow row,int cellNum) {
		String returnValue = null;
		XSSFCell cell = row.getCell((short) cellNum);
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				DecimalFormat df = new DecimalFormat("0");
				returnValue = df.format(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				boolean booleanTxt = cell.getBooleanCellValue();
				returnValue = booleanTxt + "";
				break;
			case Cell.CELL_TYPE_BLANK:
				returnValue = "";
				break;
			case Cell.CELL_TYPE_FORMULA:
				returnValue = cell.getCellFormula();
				break;
			case Cell.CELL_TYPE_STRING:
				returnValue = cell.getStringCellValue();
				break;
			}
		}else{
			returnValue ="";
		}
		return returnValue;
	}
	
	/**
	 * 读取 office 2007 excelCell
	 */
	public Date read2007ExcelDateCellStr(XSSFRow row,int cellNum) {
		Date returnValue = null;
		XSSFCell cell = row.getCell((short) cellNum);
		if (cell != null) {
			switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_STRING:
	            String tempStr = cell.getStringCellValue();
	            returnValue=DateFormat.strToDate(tempStr);
	            break;
	        case HSSFCell.CELL_TYPE_NUMERIC:
	            returnValue = cell.getDateCellValue();
	            break;
			}
		}
		return returnValue;
	}
	
	/**
	 * 读取 office 2007 excelCell
	 */
	public Date read2003ExcelDateCellStr(HSSFRow row,int cellNum) {
		Date returnValue = null;
		HSSFCell cell = row.getCell((short) cellNum);
		if (cell != null) {
			switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_STRING:
	            String tempStr = cell.getStringCellValue();
	            returnValue=DateFormat.strToDate(tempStr);
	            break;
	        case HSSFCell.CELL_TYPE_NUMERIC:
	            returnValue = cell.getDateCellValue();
	            break;
			}
		}
		return returnValue;
	}
	
}
