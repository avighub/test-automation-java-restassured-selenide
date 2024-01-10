package tests.base;

import com.codeborne.selenide.Configuration;
import com.saucelab.config.ConfigurationFactory;
import com.saucelab.config.EnvironmentConfig;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class WebTestSetup {

    private static final EnvironmentConfig ENV_CONFIG = ConfigurationFactory.getEnvironmentConfig();
    @BeforeEach
    void browserSetup(){
        Configuration.headless = false;
        open(ENV_CONFIG.loginPageUrl());
    }
}
