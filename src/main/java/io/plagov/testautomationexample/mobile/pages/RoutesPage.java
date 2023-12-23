package io.plagov.testautomationexample.mobile.pages;

import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.className;
import static io.plagov.testautomationexample.mobile.pages.CommonElements.elementByResourceId;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.appium.SelenideAppiumElement;

public class RoutesPage {

    private final SelenideAppiumElement searchInput = elementByResourceId("poi-search-input");

    private final SelenideAppiumElement firstSearchResult = elementByResourceId("poi:search-result-item:0");

    private final SelenideAppiumElement originPicker = elementByResourceId("poi:origin-picker");

    private final SelenideAppiumElement destinationPicker = elementByResourceId("poi:destination-picker");

    public SelenideElement getRecommendedTicketButton() {
        return elementByResourceId("ROUTES:buy-recommended-ticket-button")
                .shouldBe(visible)
                .find(className("android.widget.TextView"));
    }

    public RoutesPage setOrigin(String origin) {
        originPicker.tap();
        searchInput
                .shouldBe(visible)
                .sendKeys(origin);
        firstSearchResult.tap();
        return this;
    }

    public RoutesPage setDestination(String destination) {
        destinationPicker.tap();
        searchInput
                .shouldBe(visible)
                .sendKeys(destination);
        firstSearchResult.tap();
        return this;
    }
}
