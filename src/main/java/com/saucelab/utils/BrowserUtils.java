package com.saucelab.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

public class BrowserUtils {
    private BrowserUtils(){}

    public static void setCookie(String name, String value, String path){
        Cookie cookie = new Cookie(name,value,path);
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        Selenide.refresh();
    }
}
