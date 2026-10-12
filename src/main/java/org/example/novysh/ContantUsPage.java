package org.example.novysh;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContantUsPage extends InnowiseMainPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "contact-email")
    private WebElement nameInput;

    @FindBy(id = "contact-company")
    private WebElement companyInput;

    @FindBy(id = "contact-phone")
    private WebElement phoneInput;

    @FindBy(id = "contact-email")
    private WebElement emailInput;

    @FindBy(id = "contact-message")
    private WebElement descriptionInput;

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

    public ContantUsPage(){
        driver = Driver.getDriver();
        wait = Driver.getWait();
        PageFactory.initElements(driver, this);
    }

    public void fillName(String name) {
        typeText(nameInput, name);
    }

    public void fillCompany(String company) {
        typeText(companyInput, company);
    }

    public void fillPhone(String phone) {
        typeText(phoneInput,phone);
    }

    public void fillEmail(String email) {
        typeText(emailInput, email);
    }

    public void fillDescription(String description) {
        typeText(descriptionInput, description);
    }

    public void clickSend() {
        WebElement currentSendButton = sendButton;
        acceptCookiesIfPresent();
       // clickWithScroll(sendButton);
        clickSendWithoutReload(sendButton);
        //waitForDisappear(loaderSendButton);
       // waitForPageToReload(currentSendButton);
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
