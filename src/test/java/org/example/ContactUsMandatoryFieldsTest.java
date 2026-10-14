package org.example;

import org.example.novysh.page.ContactUsPage;
import org.example.novysh.page.InnowiseMainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactUsMandatoryFieldsTest extends BaseTest{
    private InnowiseMainPage homePage;
    private ContactUsPage contactUsPage;
    private final String errorMesssage = "Error message should be displayed when %s field is empty";

    @BeforeEach
    public void setUp() {
        homePage = new InnowiseMainPage();
        homePage.navigate();
        contactUsPage = homePage.clickButtonContactUsWithWait();
    }

    @Test
    @DisplayName("Check validation when Name field is empty")
    public void testSubmitFormWithEmptyName() {
        contactUsPage.fillForm(null, "Innowise", "+1234567890", "test@innowise.com", "Project details")
                .clickSend();

        Assertions.assertTrue(contactUsPage.isNameErrorDisplayed(),
                String.format(errorMesssage,"Name"));
    }

    @Test
    @DisplayName("Check validation when Company field is empty")
    public void testSubmitFormWithEmptyCompany() {
        contactUsPage.fillForm("John Doe", null, "+1234567890", "test@innowise.com", "Project details")
                .clickSend();

        Assertions.assertTrue(contactUsPage.isCompanyErrorDisplayed(),
                String.format(errorMesssage,"Company"));

    }

    @Test
    @DisplayName("Check validation when Phone field is empty")
    public void testSubmitFormWithEmptyPhone() {
        contactUsPage.fillForm("John Doe", "Innowise", null, "test@innowise.com", "Project details")
                .clickSend();

        Assertions.assertTrue(contactUsPage.isPhoneErrorDisplayed(),
                String.format(errorMesssage,"Phone"));
    }

    @Test
    @DisplayName("Check validation when Corporate Email field is empty")
    public void testSubmitFormWithEmptyEmail() {
        contactUsPage.fillForm("John Doe", "Innowise", "+1234567890", null, "Project details")
                .clickSend();

        Assertions.assertTrue(contactUsPage.isEmailErrorDisplayed(),
                String.format(errorMesssage, "Corporate Email"));
    }

    @Test
    @DisplayName("Check validation when Describe in details field is empty")
    public void testSubmitFormWithEmptyDescription() {
        contactUsPage.fillForm("John Doe", "Innowise", "+1234567890", "test@innowise.com", null)
                .clickSend();

        Assertions.assertTrue(contactUsPage.isDescriptionErrorDisplayed(),
                String.format(errorMesssage, "Description"));

    }
}
