package tech.techiewolf.config;

import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;

public final class ConfigurationFactory {

    private ConfigurationFactory() {
    }

    public static FrameworkConfig getFrameworkConfig() {
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }

    public static EnvironmentConfig getEnvironmentConfig() {
        ConfigFactory.setProperty("environment", getFrameworkConfig().environment());
        return ConfigCache.getOrCreate(EnvironmentConfig.class);
    }
}
