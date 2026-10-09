package org.example.novysh;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InnowiseMainPage extends BasePage{
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[@class='elementor-widget-container']/p")
    private WebElement sloganText;

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

    @FindBy(css = ".button-contact.mob")
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

    public WebElement getSloganText() {
        return sloganText;
    }

    public void clickButtonContactUs() {
        buttonContact.click();
    }
}
