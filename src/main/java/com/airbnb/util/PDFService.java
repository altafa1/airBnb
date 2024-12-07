package com.airbnb.util;

import com.airbnb.entity.Booking;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class PDFService {

    private EmailService emailService;

    public PDFService(EmailService emailService) {
        this.emailService = emailService;
    }

    private final Logger logger=LoggerFactory.getLogger(PDFService.class);

    public void generatePdf(Booking booking){
        try{

            logger.info("generating pdf"+new Date());
            String filePath = "D://bnbBooking//" + booking.getId() + "_bookingConfirm.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            addRows(table);

            document.add(table);
            document.close();
            logger.info("pdf generated"+new Date());
            emailService.sendEmailWithAttachment(booking.getEmail(),
                    "Booking Confirmation",
                    "Here is your booking confirmation.",
                    new File(filePath));

        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

}

