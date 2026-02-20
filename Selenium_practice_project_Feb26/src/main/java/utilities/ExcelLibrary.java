package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary {

	XSSFWorkbook wb = null;
	XSSFSheet sheet = null;
	FileInputStream fis = null;

	public ExcelLibrary(String path, String sheetname) throws IOException {
		fis = new FileInputStream(new File(path));
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetname);
	}

	public int getTotalNoOfRows() {
		return sheet.getLastRowNum();
	}

	public int getTotalNoOfCols() {
		return sheet.getRow(0).getLastCellNum();
	}

	public String getCellData(int rowno, int colno) {

		if (rowno == -1)
			return "";
		if (colno == -1)
			return "";
		XSSFRow row = sheet.getRow(rowno);
		if (row == null) {
			return "";
		}
		XSSFCell cell = row.getCell(colno);
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BLANK:
			return "";
		default:
			return "";

		}
	}
	
	public String getCellData(int rowno, String colname) {
		XSSFRow row = sheet.getRow(0);
		int colno = 0;
		for (int i = 0; i < getTotalNoOfCols(); i++) {
			if (row.getCell(i).getStringCellValue().equals(colname)) {
				System.out.println("Col no: "+ i);
				colno = i;
			}
		}
		
		return getCellData(rowno, colno);
	}

}
