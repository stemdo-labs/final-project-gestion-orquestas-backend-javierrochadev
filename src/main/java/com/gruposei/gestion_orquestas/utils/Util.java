package com.gruposei.gestion_orquestas.utils;

//import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

public class Util {

    public static boolean validEmail(String email) {

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

//    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
//        ByteArrayOutputStream stream = QRCode
//                .from(barcodeText)
//                .withSize(250, 250)
//                .stream();
//        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());
//
//        return ImageIO.read(bis);
//    }
}
