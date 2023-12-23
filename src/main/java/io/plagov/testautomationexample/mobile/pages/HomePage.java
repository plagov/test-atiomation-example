package io.plagov.testautomationexample.mobile.pages;

import static com.codeborne.selenide.appium.AppiumSelectors.byText;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.plagov.testautomationexample.mobile.pages.CommonElements.elementByResourceId;

import com.codeborne.selenide.appium.SelenideAppiumElement;

public class HomePage {

    private final SelenideAppiumElement ticketsButton = $(byText("TICKETS"));

    private final SelenideAppiumElement routesButton = $(byText("ROUTES"));

    private final SelenideAppiumElement loginButton = elementByResourceId("home:greetings-login-button");

    public TicketsPage openTicketsPage() {
        ticketsButton.tap();
        return new TicketsPage();
    }

    public RoutesPage openRoutesPage() {
        routesButton.tap();
        return new RoutesPage();
    }

    public LoginPage openLoginPage() {
        loginButton.tap();
        return new LoginPage().load();
    }
}
