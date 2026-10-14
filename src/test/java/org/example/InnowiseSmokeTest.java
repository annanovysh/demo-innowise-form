package org.example;

import org.example.novysh.utils.Driver;
import org.example.novysh.utils.Utils;
import org.example.novysh.page.InnowiseMainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InnowiseSmokeTest {
    InnowiseMainPage innowiseMainPage;

    @BeforeEach
    public void setUp() {
        innowiseMainPage = new InnowiseMainPage();
        innowiseMainPage.navigate();
    }

    @Test
    public void verifyCompanySloganText() {
        String actualSloganText = Utils.getText(innowiseMainPage.getSlogan());
        String expectedSloganText = "Building scalable, high-quality software on time and on budget.";

        Assertions.assertEquals(expectedSloganText, actualSloganText);
    }

    @Test
    public void verifyOpenContactModalWhenContactUsButtonClicked() {
        innowiseMainPage.clickButtonContactUsWithWait();

        Utils.assertElementIsDisplayed(innowiseMainPage.getContactForm());
    }

    @AfterEach
    public void tearDown() {
       Driver.quitDriver();
    }
}
