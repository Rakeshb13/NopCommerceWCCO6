package genericlibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataUtility {

	public FileInputStream fis;
	public Properties pobj;
	public Workbook book;
	public Sheet sheet;

	public String getDataFromProperties(String key) throws IOException {
		fis = new FileInputStream("./src/test/resources/TestData/PropertiesFile.properties");
		pobj = new Properties();
		pobj.load(fis);
		return pobj.getProperty(key);
	}

	public String getDataFromExcel(String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./src/test/resources/TestData/ExcelFile.xlxs");
		book = WorkbookFactory.create(fis);
		sheet = book.getSheet(sheetName);
		return sheet.getRow(rowNum).getCell(cellNum).toString();
	}

	public Object[][] getAllDataFromExcel(String sheetName) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream("./src/test/resources/TestData/ExcelFile.xlxs");
		book = WorkbookFactory.create(fis);
		sheet = book.getSheet(sheetName);
		int row_count = sheet.getPhysicalNumberOfRows();
		int col_count = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[row_count - 1][col_count];

		for (int r = 1; r < row_count; r++) {
			Row row = sheet.getRow(r);
			for (int c = 0; c < row.getLastCellNum(); c++) {
				data[r - 1][c] = row.getCell(c).toString();
			}
		}
		return data;
	}
}
