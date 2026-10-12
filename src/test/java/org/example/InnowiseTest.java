package org.example;

import org.example.novysh.Driver;
import org.example.novysh.Utils;
import org.example.novysh.InnowiseMainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InnowiseTest {
    InnowiseMainPage innowiseMainPage;

    @BeforeEach
    public void setUp() {
        innowiseMainPage = new InnowiseMainPage();
        innowiseMainPage.openBaseUrl();
    }

    @Test
    public void verifyCompanySloganText() {

        String actualSloganText = Utils.getText(innowiseMainPage.getSloganText());
        String expectedSloganText = "Building scalable, high-quality software on time and on budget.";

        Assertions.assertEquals(expectedSloganText, actualSloganText);
    }

    @Test
    public void verifyOpenContactModalWhenContactUsButtonClicked() {
        InnowiseMainPage innowiseMainPage = new InnowiseMainPage();
        innowiseMainPage.clickButtonContactUs();

        Utils.assertElementIsDisplayed(innowiseMainPage.getContactForm());
    }

    @AfterEach
    public void tearDown() {
       Driver.quitDriver();
    }
}
