package org.example;

import org.example.novysh.ContantUsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactUsMandatoryFieldsTest extends BaseTest{
    private ContantUsPage contactUsPage;

    @BeforeEach
    public void setUp() {
        contactUsPage = new ContantUsPage();
        contactUsPage.openBaseUrl().clickButtonContactUsWithWait();
    }

    @Test
    @DisplayName("Check validation when Name field is empty")
    public void testSubmitFormWithEmptyName() {
        contactUsPage.fillCompany("Innowise");
        contactUsPage.fillPhone("+1234567890");
        contactUsPage.fillEmail("test@innowise.com");
        contactUsPage.fillDescription("Project request details");

        contactUsPage.clickSend();

        assertTrue(contactUsPage.isNameErrorDisplayed(),
                "Error message should be displayed when Name field is empty");
    }

    @Test
    @DisplayName("Check validation when Company field is empty")
    public void testSubmitFormWithEmptyCompany() {
        contactUsPage.fillName("John Doe");
        contactUsPage.fillPhone("+1234567890");
        contactUsPage.fillEmail("test@innowise.com");
        contactUsPage.fillDescription("Project request details");

        contactUsPage.clickSend();

        assertTrue(contactUsPage.isCompanyErrorDisplayed(),
                "Error message should be displayed when Company field is empty");
    }

    @Test
    @DisplayName("Check validation when Phone field is empty")
    public void testSubmitFormWithEmptyPhone() {
        contactUsPage.fillName("John Doe");
        contactUsPage.fillCompany("Innowise");
        contactUsPage.fillEmail("test@innowise.com");
        contactUsPage.fillDescription("Project request details");

        contactUsPage.clickSend();
       // Thread.sleep(10000);
        assertTrue(contactUsPage.isPhoneErrorDisplayed(),
                "Error message should be displayed when Phone field is empty");
    }

    @Test
    @DisplayName("Check validation when Corporate Email field is empty")
    public void testSubmitFormWithEmptyEmail() {
        contactUsPage.fillName("John Doe");
        contactUsPage.fillCompany("Innowise");
        contactUsPage.fillPhone("+1234567890");
        contactUsPage.fillDescription("Project request details");

        contactUsPage.clickSend();

        assertTrue(contactUsPage.isEmailErrorDisplayed(),
                "Error message should be displayed when Corporate Email field is empty");
    }

    @Test
    @DisplayName("Check validation when Describe in details field is empty")
    public void testSubmitFormWithEmptyDescription() {
        contactUsPage.fillName("John Doe");
        contactUsPage.fillCompany("Innowise");
        contactUsPage.fillPhone("+1234567890");
        contactUsPage.fillEmail("test@innowise.com");

        contactUsPage.clickSend();

        assertTrue(contactUsPage.isDescriptionErrorDisplayed(),
                "Error message should be displayed when Description field is empty");
    }
}
