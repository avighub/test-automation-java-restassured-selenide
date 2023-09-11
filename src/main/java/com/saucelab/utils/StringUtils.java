package com.saucelab.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;

import java.util.UUID;

public final class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class can not be instantiated, Use it statically.");
    }

    static Logger log = LoggerUtils.getLogger();

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


}
