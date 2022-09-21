package test;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.APIutils.Specifications;
import configuration.SettingsVkConfig;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(SettingsVkConfig.getUrl());
        browser.waitForPageToLoad();

        RestAssured.requestSpecification = Specifications.requestSpecification(SettingsVkConfig.getBaseMethodApi());
    }
    
    @AfterMethod
    public void tearDown() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
