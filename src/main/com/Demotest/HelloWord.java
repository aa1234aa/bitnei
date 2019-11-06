package Demotest;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * xx模块
 */
public class HelloWord {
    public static void main(String[] args) {
        HSSFWorkbook workbook= new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet();
        HSSFRow row=sheet.createRow(0);


    }
}
