package net.opentrends.thinko.jlchavezlab;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class ImportToHubspotAndThinko {

    private static final String PHP_DB = "/home/jlchavez/php.xlsx";
    private static final String HUBSPOT_DB = "/home/jlchavez/hubspot.xlsx";


    @Test
    public void compare() throws IOException, InvalidFormatException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        List<String> lista = IOUtils.readLines(new FileInputStream("/home/jlchavez/all.txt"), "UTF-8");

        FileInputStream excelFile = new FileInputStream(new File(PHP_DB));
        FileOutputStream outFile = new FileOutputStream(new File(HUBSPOT_DB));
        Workbook workbook = new XSSFWorkbook(excelFile);
        XSSFWorkbook hubspot = new XSSFWorkbook();
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();
        XSSFSheet sheet = hubspot.createSheet("hubspot");
        int rowNum = 1;
        Row rowP = sheet.createRow(0);
        rowP.createCell(0).setCellValue("First Name");
        rowP.createCell(1).setCellValue("Last Name");
        rowP.createCell(2).setCellValue("Email Address");
        rowP.createCell(3).setCellValue("Phone Number");
        rowP.createCell(4).setCellValue("Thinkö - Registro");
        rowP.createCell(5).setCellValue("Thinkö - Registro Fecha");
        rowP.createCell(6).setCellValue("Thinkö - Registro Hora");
        iterator.next();
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Cell cell_mail = currentRow.getCell(1);
            String mail = cell_mail.getStringCellValue();
            boolean containsSearchStr = lista.stream().anyMatch(mail::equalsIgnoreCase);
            if(!containsSearchStr){
                String name = getValue(currentRow.getCell(3));
                String pwd = getValue(currentRow.getCell(2));
                String city = getValue(currentRow.getCell(5));
                String phone = getValue(currentRow.getCell(10));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String data = sdf.format(Calendar.getInstance().getTime());
                String camp = "campaign_anna_fores";
                String encodedPassword = passwordEncoder.encode(pwd);
                System.out.println("INSERT INTO `users`(`created_at`, `updated_at`, `active`, `email`, "
                        + "`last_name`, `link_date`, `linkurl`, `mail_sended`, "
                        + "`name`, `newsletter_subscription`, `password`, `profile`, "
                        + "`recovery_date`, `recovery_link`, `sur_name`, `usertype`, `zipcode`, `profile_completed`,`beta_tester`,`city`,`phonenumber`) VALUES (" + "'" + data
                        + "','" + data + "','1','" + mail + "','" + "" + "',NULL,'" + camp + "','1','" + name + "','0','"
                        + encodedPassword + "','TEACHER'," + "NULL,NULL,'" + "" + "',NULL,NULL,0,0,'" + city + "','" + phone + "');");

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(name);
                row.createCell(2).setCellValue(mail);
                row.createCell(3).setCellValue(phone);
                row.createCell(4).setCellValue("Sí");
                row.createCell(5).setCellValue("02/12/2021");
                row.createCell(6).setCellValue("21:40");
            }
        }
        hubspot.write(outFile);
        hubspot.close();
        workbook.close();
    }

    private String getValue(Cell cell) {
        String valor = "";
        try {
            if (cell.getCellTypeEnum() == CellType.STRING) {
                valor = cell.getStringCellValue();
            } else {
                double test = cell.getNumericCellValue();
                DecimalFormat df = new DecimalFormat("#");
                df.setMaximumFractionDigits(0);
                valor = df.format(test);
            }

        } catch (Exception e) {
        } finally {
            return valor;
        }

    }



}
