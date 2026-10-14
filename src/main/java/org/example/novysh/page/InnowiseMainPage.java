package org.example.novysh.page;

import org.example.novysh.utils.ConfigReader;
import org.example.novysh.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InnowiseMainPage extends BasePage{
    WebDriver driver;
    WebDriverWait wait;
    private final String buttonContactLocator = "//a[contains(@class, 'button-contact') and not(contains(@class, 'mob'))]/span";

    @FindBy(xpath = "//div[@class='elementor-widget-container']/p")
    private WebElement slogan;

    // @FindBy(css = ".new-menu .title-section.tit-about_us")
    @FindBy(xpath = "//div[@class='new-menu']//div[@class='new-block-ul']/div[contains(@class,'about')]")
    private WebElement menuAbout;

    @FindBy(css = ".new-menu .title-section.tit-service")
    private WebElement menuService;

    @FindBy(css = ".new-menu .title-section.tit-technologies")
    private WebElement menuTech;

    @FindBy(css = ".new-menu .title-section.tit-cases")
    private WebElement menuPortfolio;

    @FindBy(css = ".tit-hire-span-mob")
    private WebElement menuHireIt;

    @FindBy(xpath = "//a[contains(@class, 'button-contact') and not(contains(@class, 'mob'))]/span")
    private WebElement buttonContact;

    @FindBy(css = ".new-block-lang.tit")
    private WebElement linkLang;

    @FindBy(css = "[data-id='0a4b605']")
    private WebElement buttonAllServices;

    public WebElement getContactForm() {
        return contactForm;
    }

    @FindBy(xpath = "//div[@id='spinner']")
    private WebElement contactForm;

    public InnowiseMainPage() {
        driver = Driver.getDriver();
        wait = Driver.getWait();
        PageFactory.initElements(driver, this);
    }

    public WebElement getSlogan() {
        return slogan;
    }

    public ContactUsPage clickButtonContactUs() {
        buttonContact.click();
        return new ContactUsPage();
    }

    public ContactUsPage clickButtonContactUsWithWait() {
        WebElement button = Driver.getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(buttonContactLocator)
        ));

        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", button);
        return new ContactUsPage();
    }

    public void navigate() {
        Driver.getDriver().get(ConfigReader.getBaseUrl());
        acceptCookiesIfPresent();
    }
}
