package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtils {

    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;


    public static Object[][] getData(String FilePath, String SheetName) throws Exception {

        FileInputStream ExcelFile = new FileInputStream(FilePath);
        ExcelWBook = new XSSFWorkbook(ExcelFile);
        ExcelWSheet = ExcelWBook.getSheet(SheetName);

        Object[][] data = new Object[ExcelWSheet.getLastRowNum()][ExcelWSheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < ExcelWSheet.getLastRowNum(); i++) {
            for (int k = 0; k < ExcelWSheet.getRow(0).getLastCellNum(); k++) {
                if (ExcelWSheet.getRow(i+1).getCell(k).getCellType()==Cell.CELL_TYPE_NUMERIC){
                    data[i][k]=Integer.toString((int) ExcelWSheet.getRow(i+1).getCell(k).getNumericCellValue());
                }
                if (ExcelWSheet.getRow(i+1).getCell(k).getCellType()==Cell.CELL_TYPE_STRING){
                    data[i][k]=ExcelWSheet.getRow(i+1).getCell(k).getStringCellValue();
                }

                System.out.println(data[i][k]);
                }
            }
        return data;
    }
}