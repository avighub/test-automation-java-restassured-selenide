package tech.techiewolf.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:frameworkconfig.properties",
})
public interface FrameworkConfig extends Config {
    @Key("test.environment")
    @DefaultValue("dev")
    String environment();

}
