package utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

	static String projectPath;
	static XSSFWorkbook workBook;
	static XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName) throws IOException {

		workBook = new XSSFWorkbook(excelPath);
		sheet = workBook.getSheet(sheetName);
	}

	public static void main(String[] args)  {
		getRowCount();
		getCellDataString(0,0);
		
	}

	public static int getRowCount()  {	


		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println("Number of rows :"+ rowCount);
		return rowCount;
	}
	
	public static int getColCount()  {	


		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Number of columns :"+ colCount);
		return colCount;
		
	}

	
	
	
	public static String getCellDataString(int rowNum, int colNum)  {	
		DataFormatter formatter = new DataFormatter();

		String cellData ;
		Cell cell = sheet.getRow(rowNum).getCell(colNum);
		cellData = formatter.formatCellValue(cell);
		
		System.out.println(cellData);
		return cellData;
	}

	
	
	
	

}
