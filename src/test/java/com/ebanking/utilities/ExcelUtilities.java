package com.ebanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	FileInputStream fis;
	FileOutputStream fos;	
	XSSFWorkbook workBook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	XSSFCellStyle style;
	String path;
	
	
	public ExcelUtilities(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fis = new FileInputStream(path);
		workBook = new XSSFWorkbook(fis);
		sheet = workBook.getSheet(sheetName);
		int totalRows = sheet.getLastRowNum();
		workBook.close();
		fis.close();
		return totalRows;
	}
	
	public int getCellCount(String sheetName, int rowNo) throws IOException
	{
		fis=new FileInputStream(path);
		workBook = new XSSFWorkbook(fis);
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		int cellCount = row.getLastCellNum();
		workBook.close();
		fis.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowNo, int collNo) throws IOException
	{
		fis=new FileInputStream(path);
	    workBook = new XSSFWorkbook(fis);
	    sheet = workBook.getSheet(sheetName);
	    row = sheet.getRow(rowNo);
	    cell  = row.getCell(collNo);
	    
	    DataFormatter formatter = new DataFormatter();
	    String data;
	    try {
	    	data = formatter.formatCellValue(cell);
	    }catch(Exception e)
	    {
	    	data="";
	    }
	    
	    workBook.close();
	    fis.close();
	    return data;
	}
	
	public void setCellData(String sheetName, int rowNo, int collNo, String data) throws IOException
	{
		fis = new FileInputStream(path);
		workBook = new XSSFWorkbook(fis);
		
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		cell = row.createCell(collNo);
		cell.setCellValue(data);
		
		fos = new FileOutputStream(path);
		workBook.write(fos);
		workBook.close();
		fis.close();
		fos.close();
	}	
	
	public void fillGreenColor(String sheetName, int rollNo, int collNo) throws IOException
	{
		fis = new FileInputStream(path);
		workBook = new XSSFWorkbook(fis);
		sheet = workBook.getSheet(sheetName);
		
		row = sheet.getRow(collNo);
		cell= row.getCell(collNo);
		
		
		style = cell.getCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		workBook.write(fos);
		workBook.close();
		fis.close();
		fos.close();
				
	}
}

