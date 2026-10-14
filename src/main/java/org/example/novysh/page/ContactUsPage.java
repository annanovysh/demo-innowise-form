package org.example.novysh.page;

import org.example.novysh.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUsPage extends BasePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "contact-name")
    private WebElement nameInput;

    @FindBy(id = "contact-company")
    private WebElement companyInput;

    @FindBy(id = "contact-phone")
    private WebElement phoneInput;

    @FindBy(id = "contact-email")
    private WebElement emailInput;

    @FindBy(id = "contact-message")
    private WebElement descriptionInput;

    public WebElement getSendButton() {
        return sendButton;
    }

    @FindBy(id = "contact-send-button")
    private WebElement sendButton;

    @FindBy(xpath = "//input[@id='contact-name']/following::span[1]")
    private WebElement nameError;

    @FindBy(xpath = "//input[@id='contact-company']/following::span[1]")
    private WebElement companyError;

    @FindBy(xpath = "//input[@id='contact-phone']/following::span[1]")
    private WebElement phoneError;

    @FindBy(xpath = "//input[@id='contact-email']/following::span[1]")
    private WebElement emailError;

    @FindBy(xpath = "//textarea[@id='contact-message']/following::span[1]")
    private WebElement descriptionError;

    @FindBy(xpath = "")
    private WebElement loaderSendButton;

    public ContactUsPage(){
        driver = Driver.getDriver();
        wait = Driver.getWait();
        PageFactory.initElements(driver, this);
    }

    public ContactUsPage fillName(String name) {
        typeText(nameInput, name);
        return this;
    }

    public ContactUsPage fillCompany(String company) {
        typeText(companyInput, company);
        return this;
    }

    public ContactUsPage fillPhone(String phone) {
        typeText(phoneInput,phone);
        return this;
    }

    public ContactUsPage fillEmail(String email) {
        typeText(emailInput, email);
        return this;
    }

    public ContactUsPage fillDescription(String description) {
        typeText(descriptionInput, description);
        return this;
    }

    public ContactUsPage fillForm(String name, String company, String phone, String email, String description) {
        if (name != null) fillName(name);
        if (company != null) fillCompany(company);
        if (phone != null) fillPhone(phone);
        if (email != null) fillEmail(email);
        if (description != null) fillDescription(description);
        return this;
    }

    public ContactUsPage clickSend() {
        clickWithScroll(sendButton);
        return this;
    }

    public boolean isNameErrorDisplayed() {
        return waitForVisibility(nameError).isDisplayed();
    }

    public boolean isCompanyErrorDisplayed() {
        return waitForVisibility(companyError).isDisplayed();
    }

    public boolean isPhoneErrorDisplayed() {
        return waitForVisibility(phoneError).isDisplayed();
    }

    public boolean isEmailErrorDisplayed() {
        return waitForVisibility(emailError).isDisplayed();
    }

    public boolean isDescriptionErrorDisplayed() {
        return waitForVisibility(descriptionError).isDisplayed();
    }
}
