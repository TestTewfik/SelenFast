package utils;

import java.io.IOException;

public class ExcelUtilDemo {

	public static void main(String[] args) throws IOException {
		
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		
		ExcelUtils excel = new ExcelUtils(projectPath+"/src/test/resources/excel/data.xlsx", "Sheet1");
		excel.getRowCount();
		excel.getCellDataString(0, 0);
		excel.getCellDataString(1, 1);
		//excel.getCellDataNumber(1, 1);

	}

}
