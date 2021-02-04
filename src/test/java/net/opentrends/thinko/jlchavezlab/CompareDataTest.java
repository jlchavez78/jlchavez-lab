package net.opentrends.thinko.jlchavezlab;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

public class CompareDataTest {

    private static final String THINKO_DB = "/home/jlchavez/database.xlsx";
    private static final String MIXPANEL_DB = "/home/jlchavez/mixpanel.xlsx";
    private static final String HUBSPOT_DB = "/home/jlchavez/hubspot.xlsx";

    private static final int THINKO_MAIL_COL = 4;
    private static final int MIXPANEL_MAIL_COL = 0;
    private static final int HUBSPOT_MAIL_COL = 3;

    @Test
    public void compare() throws IOException, InvalidFormatException {
        try {

            FileInputStream excelFile = new FileInputStream(new File(THINKO_DB));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Cell cell_mail = currentRow.getCell(THINKO_MAIL_COL);
                String mail = cell_mail.getStringCellValue();
                if (!StringUtils.containsIgnoreCase(mail, "thinko")
                        && !StringUtils.containsIgnoreCase(mail, "opentrends")
                        && !StringUtils.containsIgnoreCase(mail, "poblet")
                        && !StringUtils.containsIgnoreCase(mail, "chavez")
                        && !StringUtils.containsIgnoreCase(mail, "lobato")
                        && !StringUtils.containsIgnoreCase(mail, "failfast")) {

                    boolean finded = searchMailInDB(mail, MIXPANEL_DB, MIXPANEL_MAIL_COL);
                    if (!finded) {
                        Double active = currentRow.getCell(3).getNumericCellValue();
                        Double bt = currentRow.getCell(27).getNumericCellValue();
                        String d = currentRow.getCell(1).getStringCellValue();
                        System.out.println(mail + " " + String.valueOf(active.intValue()) + "  " + String.valueOf(bt.intValue()) + "  " + d);
                    }

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean searchMailInDB(String mail, String db, int col) {
        boolean finded = false;
        try {

            FileInputStream excelFile = new FileInputStream(new File(db));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext() && !finded) {
                Row currentRow = iterator.next();
                Cell cell_mail = currentRow.getCell(col);
                String mail_hubspot = cell_mail.getStringCellValue();
                finded = StringUtils.equalsIgnoreCase(mail, mail_hubspot);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return finded;
        }
    }


}
