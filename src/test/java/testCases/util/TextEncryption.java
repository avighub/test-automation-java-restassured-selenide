package testCases.util;

import com.qa.helper.HelperGeneric;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextEncryption {
    @Test(groups = {"encryption"})
    public void testEncryption() {
        String pass = System.getenv("encryptionPassphrase");
        System.out.println(HelperGeneric.getEncryptedValue(pass, "test@example.com"));
//        System.out.println(HelperGeneric.getEncryptedValue(pass, ""));
        System.out.println(HelperGeneric.getDecryptedValue(pass, "uMWH4Pqs2OBl6bH/pgrCXajQ10Wjeo/5kS0vd3BMsT9jZnm+LhWNDhUtWTrhipoiy1lteppP2kVxohJOZSJ5WA=="));
    }



}
