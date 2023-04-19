package framework.util;

import framework.util.logger.LoggerManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ReadExcel {
    private static XSSFSheet sheet;

    public ReadExcel(String pathToExcelFile, String sheetName) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(pathToExcelFile);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException ioException){
            LoggerManager.logError(ioException.toString());
        }
    }
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }
    public int getColCount() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }
    public String getStringCellData(int row, int col) {
        return sheet.getRow(row).getCell(col).getStringCellValue();
    }
    public int getNumberCellData(int row, int col) {
        return (int) sheet.getRow(row).getCell(col).getNumericCellValue();
    }
    public int getCellType(int row, int col){
        return sheet.getRow(row).getCell(col).getCellType();
    }
    public String getCellData(int row, int col){
        if(getCellType(row, col) == 0){
            int intValue = getNumberCellData(row, col);
            return Integer.toString(intValue);
        } else return getStringCellData(row, col);
    }
}
