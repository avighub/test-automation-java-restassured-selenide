package com.saucelab.utils;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.URL;

public final class FileUtils {
    private FileUtils() {
    }

    static Logger log = LoggerUtils.getLogger();


    public static JSONObject parseFileToJson(String filePath) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(filePath));
            jsonObject = (JSONObject) obj;

            log.info("Successfully read json file, Path:" + filePath);
        } catch (Exception e) {
            log.warn("Unable to read json file... Path:" + filePath);
            e.printStackTrace();
        }
        return jsonObject;
    }

    /*
     * Extract content in a pdf file from byteArray Need PDFBox library
     */

//    public static String byteArrayToPDF(byte[] byteArrayOfPdf) throws IOException {
//        log.debug("<<< Save PDF Byte array to PDF Document Object >>>");
//        PDDocument document = null;
//        try {
//            document = PDDocument.load(new ByteArrayInputStream(byteArrayOfPdf));
//            log.debug("<<< Extract required text from PDF document Object >>>");
//            return new PDFTextStripper().getText(document);
//        } finally {
//            log.debug("<<< Closing PDF Object >>>");
//            document.close();
//        }
//    }

    public static void savePDFFromUrl(String pdfUrl, String path) throws IOException {
        byte[] fileContent = IOUtils.toByteArray(new URL(pdfUrl));

        try {
            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(path), fileContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
