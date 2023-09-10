package unittest;

import com.saucelab.config.EnvironmentConfig;
import com.saucelab.config.FrameworkConfig;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigurationTest {

    @Test
    void testFrameworkConfiguration() {
        FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

        Assert.assertNotNull(frameworkConfig.environment());
    }

    @Test
    void testEnvironmentConfiguration() {
        FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);
        ConfigFactory.setProperty("environment", frameworkConfig.environment());
        EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

        Assert.assertNotNull(environmentConfig.baseUrl());
    }
}
