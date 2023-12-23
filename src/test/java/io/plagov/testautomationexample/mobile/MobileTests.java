package io.plagov.testautomationexample.mobile;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.textCaseSensitive;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import io.plagov.testautomationexample.mobile.pages.HomePage;

@Tag("android-tests")
public class MobileTests extends MobileBaseTest {

    private final HomePage homePage = new HomePage();

    @Test
    void givenUserDidNotBuyTickets_thenYourTicketsListIsEmpty() {
        homePage
                .openTicketsPage()
                .openYourTicketsTab()
                .ticketsListMessage
                .shouldHave(text("You have no valid tickets"));
    }

    @Test
    void whenUserFindsRoute_thenUserCanSeeBuyTicketButton() {
        homePage
                .openRoutesPage()
                .setOrigin("West Terminal 1")
                .setDestination("Helsinki Central Railway Station")
                .getRecommendedTicketButton()
                .shouldHave(text("Buy a ticket"));
    }

    @Test
    void whenUserOpensLogInPage_thenUserCanSeeForgotYourPasswordLink() {
        homePage
                .openLoginPage()
                .forgotPasswordLink
                .shouldHave(textCaseSensitive("Forgot Your Password?"));
    }
}
