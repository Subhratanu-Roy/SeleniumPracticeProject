package utilities;



import org.testng.annotations.DataProvider;

import base.Base;

public class DataProviders extends Base{

	@DataProvider(name = "allData")
	public Object[][] allData(){
		int rowno = excelLibrary.getTotalNoOfRows(); //2
		int colno = excelLibrary.getTotalNoOfCols(); //5
		Object[][] data = new Object[rowno][colno];
		for (int i = 1; i <= rowno; i++) {
			for (int j = 0; j < colno; j++) {
				data[i-1][j] = excelLibrary.getCellData(i,j);
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(data[i][j]+" ");
			}
			System.out.println();
		}
		return data;
		
		
	}
	
	
}
