package com.qa.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

import java.util.UUID;

public class HelperGeneric {

    private HelperGeneric() {
        throw new IllegalStateException("Utility class can not be instantiated, Use it statically.");
    }

    static Logger log = HelperLog.getLogger();

    public static String generateRandomString(int numOfChar) {
        String randomString = RandomStringUtils.randomAlphabetic(numOfChar);
        log.info(randomString);
        return randomString;
    }

    public static String generateRandomAlphaNumericString(int numOfChar) {
        String randomString = RandomStringUtils.randomAlphanumeric(numOfChar);
        log.info(randomString);
        return randomString;
    }

    public static String generateRandomNumericString(int numOfChar) {
        String randomNumString = RandomStringUtils.randomNumeric(numOfChar);
        log.info(randomNumString);
        return randomNumString;
    }

    public static String generateGUID() {
        UUID uuid = UUID.randomUUID();
        String guid = uuid.toString();
        log.info(guid);
        return guid;
    }

    public static String getEncryptedValue(String encryptionPassword, String textToEncrypt) {
        /*
         * Get the encryption password from system variable ( For security reasons, should not be kept in SVN)
         * Get the text to encrypt via param
         * Store the encrypted values in properties file instead of actual sensitive data
         */
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptionPassword);
        encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());
        log.info("== Encryption performed successfully...");
        return encryptor.encrypt(textToEncrypt);
    }


    public static String getDecryptedValue(String encryptionPassword, String textToDecrypt) {
        /*
         * Get the encryption password from system variable ( For security reasons, should not be kept in SVN)
         * Get the text to decrypt via param
         */
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptionPassword);
        encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());
        log.info("== Decryption performed successfully...");
        return encryptor.decrypt(textToDecrypt);

    }

}
