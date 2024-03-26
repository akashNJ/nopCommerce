package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name="dp")
	public String[][] dataProvider() throws IOException {
		ExcelUtility excel=new ExcelUtility();
		String path=".//testdata//OpenCart.xlsx";
		int row=excel.getRowCount(path, "sheet1");
		int cell=excel.getCellCount(path, "sheet1", 0);
		
		String[][] logindata = new String[row+1][cell];
		for(int r=0;r<=row;r++) {
			for(int c=0;c<cell;c++) {
				logindata[r][c]=excel.getCellData(path, "sheet1", r, c);
			}
		}
		return logindata;
	}

}
