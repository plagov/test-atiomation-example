package io.plagov.testautomationexample.mobile.pages;

import static com.codeborne.selenide.appium.AppiumSelectors.byText;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.className;
import static io.plagov.testautomationexample.mobile.pages.CommonElements.elementByResourceId;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumElement;

public class TicketsPage {

    private final SelenideAppiumElement yourTicketsTab = $(byText("Your tickets"));

    public final SelenideElement ticketsListMessage = elementByResourceId("your-tickets-list")
            .find(className("android.widget.TextView"));

    public TicketsPage openYourTicketsTab() {
        yourTicketsTab.tap();
        return this;
    }
}
