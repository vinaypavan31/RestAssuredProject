package ExcelDataDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public static void main(String[] args) throws IOException {

	}

	public List<String> getData(String sheetName,String testcaseName) throws IOException {
		List<String> al = new ArrayList();
		// fileInputstream obj

		FileInputStream fis = new FileInputStream("C:\\Users\\KC\\Documents\\TestExcelSheet.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		int sheetCount = wb.getNumberOfSheets();

		for (int i = 0; i < sheetCount; i++) {
			if (wb.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = wb.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				int k = 0;
				int column = 0;
				Iterator<Cell> cells = firstRow.cellIterator();
				while (cells.hasNext()) {
					Cell value = cells.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {

						column = k;
					}
					k++;
				}

				System.out.println(column);

				while (rows.hasNext()) {
					Row rowValue = rows.next();
					if (rowValue.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> cell = rowValue.cellIterator();

						while (cell.hasNext()) {
							Cell c = cell.next();
							if (c.getCellType() == CellType.STRING) {

								al.add(c.getStringCellValue());
							} else {
								al.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}
				}
			}
		}
		return al;

	}

}
