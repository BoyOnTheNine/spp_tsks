package by.bsuir.spp.documents;

import by.bsuir.spp.entities.User;
import by.bsuir.spp.entities.UserOrder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Component
public class XLSDocument {

    private static String[] columns = {"Order name", "Date", "Price", "Description"};
    private static String[] userInfoColumns = {"First name", "Last name", "Email", "Phone number"};

    public static ByteArrayInputStream buildXLSDocument(UserOrder order) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Order");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(order.getOffer().getName());
        row.createCell(1).setCellValue(order.getOffer().getDate().toString());
        row.createCell(2).setCellValue(order.getOffer().getPrice());
        row.createCell(3).setCellValue(order.getOffer().getDescription());

        Row customerHeaderRow = sheet.createRow(2);
        {
            Cell cell = customerHeaderRow.createCell(0);
            cell.setCellValue("Customer info");
            cell.setCellStyle(headerCellStyle);
        }
        customerHeaderRow = sheet.createRow(3);
        for (int i = 0; i < userInfoColumns.length; i++) {
            Cell cell = customerHeaderRow.createCell(i);
            cell.setCellValue(userInfoColumns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        customerHeaderRow = sheet.createRow(4);
        customerHeaderRow.createCell(0).setCellValue(order.getOffer().getCustomer().getFirstName());
        customerHeaderRow.createCell(1).setCellValue(order.getOffer().getCustomer().getLastName());
        customerHeaderRow.createCell(2).setCellValue(order.getOffer().getCustomer().getEmail());
        customerHeaderRow.createCell(3).setCellValue(order.getOffer().getCustomer().getPhoneNumber());

        Row userHeaderRow = sheet.createRow(5);
        {
            Cell cell = userHeaderRow.createCell(0);
            cell.setCellValue("Workers info");
            cell.setCellStyle(headerCellStyle);
        }
        userHeaderRow = sheet.createRow(6);
        for (int i = 0; i < userInfoColumns.length; i++) {
            Cell cell = userHeaderRow.createCell(i);
            cell.setCellValue(userInfoColumns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        rowNum = 7;
        for (User user : order.getWorkers()) {
            Row userRow = sheet.createRow(rowNum++);
            userRow.createCell(0).setCellValue(user.getFirstName());
            userRow.createCell(1).setCellValue(user.getLastName());
            userRow.createCell(2).setCellValue(user.getEmail());
            userRow.createCell(3).setCellValue(user.getPhoneNumber());
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(out);

        return new ByteArrayInputStream(out.toByteArray());
    }
}
