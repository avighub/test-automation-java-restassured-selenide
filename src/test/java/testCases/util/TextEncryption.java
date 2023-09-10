package testCases.util;

import com.saucelab.helper.HelperGeneric;
import org.testng.annotations.Test;

public class TextEncryption {
    @Test(groups = {"encryption"})
    public void testEncryption() {
        String pass = System.getenv("encryptionPassphrase");
        System.out.println(HelperGeneric.getEncryptedValue(pass, "test@example.com"));
//        System.out.println(HelperGeneric.getEncryptedValue(pass, ""));
        System.out.println(HelperGeneric.getDecryptedValue(pass, "uMWH4Pqs2OBl6bH/pgrCXajQ10Wjeo/5kS0vd3BMsT9jZnm+LhWNDhUtWTrhipoiy1lteppP2kVxohJOZSJ5WA=="));
    }



}
