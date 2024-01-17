package tech.techiewolf.tests.base;

import com.codeborne.selenide.Configuration;
import tech.techiewolf.config.ConfigurationFactory;
import tech.techiewolf.config.EnvironmentConfig;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class WebTestSetup {

    public static final EnvironmentConfig ENV_CONFIG = ConfigurationFactory.getEnvironmentConfig();

    @BeforeEach
    void browserSetup(){
        Configuration.headless = false;
    }
}
