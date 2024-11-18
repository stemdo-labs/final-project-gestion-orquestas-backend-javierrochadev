package com.gruposei.gestion_orquestas.utils;


import com.google.zxing.WriterException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFGenerator {

    public ByteArrayOutputStream generatePDF(String ticket, String show, Date fecha) throws DocumentException, IOException, WriterException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("QRTicket.pdf"));
        PdfWriter.getInstance(document, bos);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        Chunk chunk = new Chunk("Usted a comprado exitosamente su entrada!", font);
        document.add(chunk);

        Paragraph saltoDeLinea = new Paragraph("");
        document.add(saltoDeLinea);

        chunk = new Chunk("Show: " + show, font);
        document.add(chunk);

        saltoDeLinea = new Paragraph("");
        document.add(saltoDeLinea);

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        chunk = new Chunk("Fecha: " + sd.format(fecha), font);
        document.add(chunk);

        saltoDeLinea = new Paragraph("");
        document.add(saltoDeLinea);

        saltoDeLinea = new Paragraph("");
        document.add(saltoDeLinea);

        QRCodeGenerator qr = new QRCodeGenerator();

        Image img = Image.getInstance(qr.getQRCodeImage(ticket,250,250));
        document.add(img);

        document.close();
        document.close();

        return bos;
    }
}
