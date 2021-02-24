package com.amazon.config;

import java.io.File;
import java.io.FileInputStream;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserConfiguration {
	XSSFWorkbook workbook;
	private String path;
	FileInputStream fis;
	public UserConfiguration(String fileName) {
		path = System.getProperty("user.dir");
		path += "/Configurations/"+fileName;
	}
	public String[][] getUserData(int sheetNo){
		String[][] data = null;
		try {
			fis = new FileInputStream(new File(path));
			workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(sheetNo);
			int rows = sheet.getLastRowNum();
			Row row = sheet.getRow(0);
			int cols = row.getLastCellNum();
			
			data = new String[rows+1][cols+1];
			Iterator<Row> rowIterator = sheet.iterator();
			int r=0, c=0;
			while(rowIterator.hasNext()) {
				row = rowIterator.next();
				Iterator<Cell> cellIter = row.cellIterator();
				while(cellIter.hasNext()) {
					Cell cell = cellIter.next();
					addToCell(r, c, cell,data);
					c++;
				}
				c=0;
				r++;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return data;
	}
	private void addToCell(int r, int c, Cell cell, String[][] data) {
		String val;
		if(cell.getCellType() == CellType.NUMERIC) {
			DataFormatter format = new DataFormatter();
			val = format.formatCellValue(cell);
			data[r][c] = val;
			
		}
		else if(cell.getCellType() == CellType.STRING) {
			val = cell.getStringCellValue();
			data[r][c] = val;
		}
		
	}
	private void printData(String[][] data) {
		if(data == null) return;
		for(String[] v: data) {
			for(String c: v) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
		
	}
	
}
