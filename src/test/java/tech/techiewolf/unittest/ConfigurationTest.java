package tech.techiewolf.unittest;

import tech.techiewolf.annotations.UnitTest;
import tech.techiewolf.config.EnvironmentConfig;
import tech.techiewolf.config.FrameworkConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@UnitTest
class ConfigurationTest {

  @Test
  void testFrameworkConfiguration()
    {
      FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

      Assertions.assertNotNull(frameworkConfig.environment());
    }

  @Test
  void testEnvironmentConfiguration()
    {
      FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);
      ConfigFactory.setProperty("environment", frameworkConfig.environment());
      EnvironmentConfig environmentConfig = ConfigFactory.create(EnvironmentConfig.class);

      Assertions.assertNotNull(environmentConfig.loginPageUrl());
    }
}
