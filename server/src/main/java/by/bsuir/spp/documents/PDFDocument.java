package by.bsuir.spp.documents;

import by.bsuir.spp.entities.User;
import by.bsuir.spp.entities.UserOrder;
import com.itextpdf.text.Paragraph;
import org.springframework.stereotype.Component;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Component
public class PDFDocument {

    public static ByteArrayInputStream buildPdfDocument(UserOrder userOrder) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();
        document.addTitle("Order document");
        document.addCreationDate();
        document.add(new Paragraph(""));
        document.add(new Paragraph("Order name " + userOrder.getOffer().getName()));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Description " + userOrder.getOffer().getDescription()));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Customer " + userOrder.getOffer().getCustomer()));
        document.add(new Paragraph(""));

        document.add(new Paragraph("Workers"));
        PdfPTable table = new PdfPTable(4);
        PdfPCell header1 = new PdfPCell(new Phrase("First name"));
        PdfPCell header2 = new PdfPCell(new Phrase("Last name"));
        PdfPCell header3 = new PdfPCell(new Phrase("Email"));
        PdfPCell header4 = new PdfPCell(new Phrase("Phone number"));
        header1.setHorizontalAlignment(Element.ALIGN_LEFT);
        header2.setHorizontalAlignment(Element.ALIGN_LEFT);
        header3.setHorizontalAlignment(Element.ALIGN_LEFT);
        header4.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);
        table.addCell(header4);

        for (User user : userOrder.getWorkers()) {
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            table.addCell(user.getEmail());
            table.addCell(user.getPhoneNumber());
        }

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
