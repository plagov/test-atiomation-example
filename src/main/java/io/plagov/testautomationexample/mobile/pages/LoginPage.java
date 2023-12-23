package io.plagov.testautomationexample.mobile.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;
import static io.plagov.testautomationexample.mobile.pages.CommonElements.elementByResourceId;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumElement;

public class LoginPage {

    private final SelenideAppiumElement username = elementByResourceId("username");

    private final SelenideAppiumElement password = elementByResourceId("password");

    public final SelenideElement forgotPasswordLink = $(accessibilityId("Forgot your password?"))
            .find(className("android.widget.TextView"));

    public LoginPage load() {
        username.shouldBe(visible);
        password.shouldBe(visible);
        return this;
    }
}
