package org.example.novysh;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    @FindBy(xpath = "//button[text()='Allow all']")
    private WebElement acceptCookiesButton;

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public WebElement waitForVisibility(WebElement element) {
        scrollToElement(element);

        return Driver.getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForVisibility(By locator) {
        return Driver.getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(1, 500));
            WebElement button = shortWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));

            try {
                button.click();

                shortWait.until(ExpectedConditions.invisibilityOf(acceptCookiesButton));
            } catch (Exception e) {
                ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", button);
            }
        } catch (Exception ignored) {
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver())
                .executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'instant'});", element);
    }

    public void waitForDisappear(WebElement element) {
        try {
            WebDriverWait shortWait = new WebDriverWait(Driver.getDriver(), Duration.ofMillis(1500));
            shortWait.until(ExpectedConditions.visibilityOf(element));

            Driver.getWait().until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException ignored) {
        }
    }

    public void clickWithScroll(WebElement element) {
        /*scrollToElement(element);
        WebElement clickableElement = Driver.getWait()
                .until(ExpectedConditions.elementToBeClickable(element));
        try {
            // clickableElement.sendKeys(Keys.ENTER);
            //clickableElement.click();
         //  Thread.sleep(10000);
            new Actions(Driver.getDriver())
                    .moveToElement(clickableElement)
                    .click()
                    .perform();
        } catch (ElementClickInterceptedException exception) {
            ((JavascriptExecutor) Driver.getDriver())
                    .executeScript("arguments[0].click();", clickableElement);
        }*/

        // 2. Отключаем дефолтную перезагрузку страницы у родительской формы (event.preventDefault)
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "var form = arguments[0].form || arguments[0].closest('form');" +
                        "if (form) {" +
                        "  form.onsubmit = function(e) { e.preventDefault(); };" +
                        "}", element);

        // 3. Выполняем нативный клик по кнопке (теперь он вызовет AJAX-валидацию без перезагрузки)
        WebElement clickableElement = Driver.getWait()
                .until(ExpectedConditions.elementToBeClickable(element));

        clickableElement.click();
    }

    public void clickSendWithoutReload(WebElement sendButton) {
        scrollToElement(sendButton);

        // Генерируем чистое событие click без вызова нативного submit браузера
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "var event = new MouseEvent('click', { 'view': window, 'bubbles': true, 'cancelable': true });" +
                        "arguments[0].dispatchEvent(event);", sendButton
        );
    }

    public void waitForPageToReload(WebElement elementThatWillDisappear) {
        try {
            Driver.getWait().until(ExpectedConditions.stalenessOf(elementThatWillDisappear));
        } catch (Exception ignored) {
        }

        Driver.getWait().until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public void typeText(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);

        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                element
        );
    }
}
