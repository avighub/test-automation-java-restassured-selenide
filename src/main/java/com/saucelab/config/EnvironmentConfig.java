package com.saucelab.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:src/main/resources/environments/${environment}.properties",
})
public interface EnvironmentConfig extends Config {

    @Key("loginPageUrl")
    String loginPageUrl();

}
