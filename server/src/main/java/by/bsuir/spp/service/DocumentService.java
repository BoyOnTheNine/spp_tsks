package by.bsuir.spp.service;

import by.bsuir.spp.documents.CSVDocument;
import by.bsuir.spp.documents.XLSDocument;
import by.bsuir.spp.documents.PDFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;

@Service
@Transactional
public class DocumentService {

    @Autowired
    private UserOrderService service;

    public ByteArrayInputStream getOrderPDF(Long orderId) {
        try {
            return PDFDocument.buildPdfDocument(service.getById(orderId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ByteArrayInputStream getOrderXLS(Long orderId) {
        try {
            return XLSDocument.buildXLSDocument(service.getById(orderId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ByteArrayInputStream getOrderCSV(Long orderId) {
        try {
            return CSVDocument.buildCSVDocument(service.getById(orderId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
