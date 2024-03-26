package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	
	public static FileInputStream file;
	public static FileOutputStream file1;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public int getRowCount(String fileName,String sheetName) throws IOException {
		file=new FileInputStream(fileName);
		workbook=new XSSFWorkbook(file);
		sheet=workbook.getSheet(sheetName);
		int rowCount= sheet.getLastRowNum();
		workbook.close();
		file.close();
		return rowCount;
	}

	public int getCellCount(String fileName, String sheetName, int rowNum) throws IOException {

		file=new FileInputStream(fileName);
		workbook=new XSSFWorkbook(file);
		sheet=workbook.getSheet(sheetName);
		int count=sheet.getRow(rowNum).getLastCellNum();
		workbook.close();
		file.close();
		return count;
	}

	public String getCellData(String fileName, String sheetName, int rowNum, int cellNum) throws IOException {
		file=new FileInputStream(fileName);
		workbook=new XSSFWorkbook(file);
		sheet=workbook.getSheet(sheetName);
		XSSFCell cell=sheet.getRow(rowNum).getCell(cellNum);
		String data;
		try {
			DataFormatter format=new DataFormatter();
			data = format.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		workbook.close();
		file.close();
		return data;
	}
}
