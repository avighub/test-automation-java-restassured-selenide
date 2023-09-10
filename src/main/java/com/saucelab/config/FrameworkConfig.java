package com.saucelab.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:frameworkconfig.properties",
})
public interface FrameworkConfig extends Config {
    @Key("test.environment")
    @DefaultValue("dev")
    String environment();

    @Key("test.browser")
    @DefaultValue("firefox")
    String browser();

    @Key("test.browserMode")
    @DefaultValue("headless")
    String browserMode();
}
