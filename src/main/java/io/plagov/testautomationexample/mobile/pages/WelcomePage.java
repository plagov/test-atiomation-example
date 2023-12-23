package io.plagov.testautomationexample.mobile.pages;

import static com.codeborne.selenide.appium.AppiumSelectors.byText;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

import com.codeborne.selenide.appium.SelenideAppiumElement;

public class WelcomePage {

    private SelenideAppiumElement buttonByText(String text) {
        return $(byText(text));
    }

    public void skipThroughWelcomeScreens() {
        buttonByText("Continue").tap();
        buttonByText("Continue").tap();
        buttonByText("Yes, I give my consent").tap();
        buttonByText("I live abroad").tap();
        $(accessibilityId("Close the notification")).tap();
    }
}
