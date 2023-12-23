package io.plagov.testautomationexample.mobile;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.time.Duration;

import io.plagov.testautomationexample.mobile.pages.WelcomePage;

public class MobileBaseTest {

    @BeforeAll
    public static void configureSelenide() {
        Configuration.timeout = Duration.ofSeconds(10).toMillis();
    }

    @BeforeEach
    void setUp() {
        closeWebDriver();
        Configuration.browserSize = null;
        Configuration.browser = DriverConfiguration.class.getName();
        WebDriverRunner.addListener(new WebDriverListener() {
            @Override
            public void beforeClick(WebElement element) {
                WebDriverListener.super.beforeClick(element);
            }
        });
        open();
        new WelcomePage().skipThroughWelcomeScreens();
    }
}
