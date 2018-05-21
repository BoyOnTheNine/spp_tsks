package by.bsuir.spp.documents;

import by.bsuir.spp.entities.User;
import by.bsuir.spp.entities.UserOrder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.stereotype.Component;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Component
public class PDFDocument {

    public static ByteArrayInputStream buildPdfDocument(UserOrder userOrder) throws Exception {

        //This is not well way to fix issue with cyrillic, but it's works
        BaseFont helvetica = BaseFont.createFont("c:/Windows/Fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(helvetica, 10, Font.NORMAL);

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();
        document.addTitle("Order document");

        document.addCreationDate();
        document.add(new Paragraph(""));
        document.add(new Paragraph("Order name : " + userOrder.getOffer().getName(), font));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Date : " + userOrder.getOffer().getDate().toString(), font));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Price : " + userOrder.getOffer().getPrice(), font));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Description : " + userOrder.getOffer().getDescription(), font));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Customer : "));
        document.add(new Paragraph("First name : " + userOrder.getOffer().getCustomer().getFirstName(), font));
        document.add(new Paragraph("Last name : " + userOrder.getOffer().getCustomer().getLastName(), font));
        document.add(new Paragraph("Email : " + userOrder.getOffer().getCustomer().getEmail(), font));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Workers"));

        PdfPTable table = new PdfPTable(4);
        PdfPCell header1 = new PdfPCell(new Phrase("First name", font));
        PdfPCell header2 = new PdfPCell(new Phrase("Last name", font));
        PdfPCell header3 = new PdfPCell(new Phrase("Email", font));
        PdfPCell header4 = new PdfPCell(new Phrase("Phone number", font));
        header1.setHorizontalAlignment(Element.ALIGN_LEFT);
        header2.setHorizontalAlignment(Element.ALIGN_LEFT);
        header3.setHorizontalAlignment(Element.ALIGN_LEFT);
        header4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);
        table.addCell(header4);

        for (User user : userOrder.getWorkers()) {
            table.addCell(new Phrase(user.getFirstName(), font));
            table.addCell(new Phrase(user.getLastName(), font));
            table.addCell(new Phrase(user.getEmail(), font));
            table.addCell(new Phrase(user.getPhoneNumber(), font));
        }

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
