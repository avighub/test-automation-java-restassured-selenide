package com.saucelab.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${environment}.properties",
})
public interface EnvironmentConfig extends Config {

    @Key("loginPageUrl")
    String loginPageUrl();

}
