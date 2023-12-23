package io.plagov.testautomationexample.mobile.pages;

import static com.codeborne.selenide.appium.SelenideAppium.$;

import com.codeborne.selenide.appium.AppiumSelectors;
import com.codeborne.selenide.appium.SelenideAppiumElement;

public class CommonElements {

    public static SelenideAppiumElement elementByResourceId(String resourceIdValue) {
        return $(AppiumSelectors.byAttribute("resource-id", resourceIdValue));
    }
}
