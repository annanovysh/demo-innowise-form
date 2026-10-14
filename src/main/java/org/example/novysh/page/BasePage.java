package org.example.novysh.page;

import org.example.novysh.utils.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private final By acceptCookiesButtonLocator = By.xpath(
            "//button[contains(translate(text(), 'ALLOW ALL', 'allow all'), 'allow all') or text()='Accept']"
    );

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

    /*public void acceptCookiesIfPresent() {
        try {

            WebDriverWait shortWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3));
            WebElement button = shortWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));

            try {
                button.click();
            } catch (Exception e) {
                // Если баннер перекрыт другим элементом — кликаем через JS
                ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", button);
            }
            // Ждем, пока баннер исчезнет, чтобы он не перекрывал другие элементы
            shortWait.until(ExpectedConditions.invisibilityOf(acceptCookiesButton));
        } catch (Exception ignored) {
            // Если баннера нет — просто идем дальше
        }
    }*/

    /*protected void acceptCookiesIfPresent() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

            // 1. Принудительно триггерим событие скролла, чтобы Cookiebot отрендерился
           // js.executeScript("window.scrollTo(0, 2000);");
           // js.executeScript("window.scrollTo(0, 0);");
          //  js.executeScript("window.scrollTo({top: 1000, behavior: 'smooth'});");
          //  Thread.sleep(5000);
          //  js.executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");
            js.executeScript("window.dispatchEvent(new Event('scroll'));");
            // 2. Ждем появление кнопки Allow all (до 3 секунд)
            WebDriverWait shortWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement button = shortWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));

            // 3. Кликаем по кнопке кук
            js.executeScript("arguments[0].click();", button);
           // button.click();
            // 4. Ждем, пока баннер полностью удалится из DOM
            shortWait.until(ExpectedConditions.invisibilityOf(button));

        } catch (Exception ignored) {
            // Если баннер не появился или уже был закрыт — продолжаем
        }
    }*/

    /*protected void acceptCookiesIfPresent() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(5000);
            // js.executeScript("window.dispatchEvent(new Event('scroll'));");
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(5000);
            WebDriverWait shortWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement button = shortWait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));

            js.executeScript("arguments[0].click();", button);

            shortWait.until(ExpectedConditions.invisibilityOf(button));

        } catch (Exception ignored) {
            // Если баннера нет — просто идем дальше
        }
    }*/

    protected void acceptCookiesIfPresent() {
        WebDriver driver = Driver.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // 1. Плавный скролл вниз, чтобы активировать триггеры/lazy-load
            js.executeScript("window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});");

            // 2. Ждем, пока кнопка станет КЛИКАБЕЛЬНОЙ (а не просто появится в DOM)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButtonLocator));

            // 3. Прокручиваем к самой кнопке перед кликом для надежности
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

            // 4. Пробуем обычный клик, если не проходит — кликаем через JS
            try {
                button.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", button);
            }

            // 5. Ждем исчезновения кнопки/баннера
            wait.until(ExpectedConditions.invisibilityOf(button));

        } catch (Exception ignored) {
            // Баннера нет или он не появился — игнорируем
        } finally {
            // 6. Возвращаем скролл наверх
            try {
                js.executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");
            } catch (Exception ignored) {}
        }
    }

    public BasePage scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver())
                .executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        return this;
    }

    public void waitForDisappear(WebElement element) {
        try {
            WebDriverWait shortWait = new WebDriverWait(Driver.getDriver(), Duration.ofMillis(1500));
            shortWait.until(ExpectedConditions.visibilityOf(element));

            Driver.getWait().until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException ignored) {
        }
    }

    public void clickSendWithoutReload(WebElement sendButton) {
        scrollToElement(sendButton);

        /*// Генерируем чистое событие click без вызова нативного submit браузера
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "var event = new MouseEvent('click', { 'view': window, 'bubbles': true, 'cancelable': true });" +
                        "arguments[0].dispatchEvent(event);", sendButton
        );*/
        sendButton.click();
    }

    public void waitForPageToReload() {
        Driver.getWait().until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public void clickWithScroll(WebElement element) {
        scrollToElement(element);

        WebElement clickableElement = Driver.getWait()
                .until(ExpectedConditions.elementToBeClickable(element));

        try {
            clickableElement.click();
        } catch (ElementClickInterceptedException e) {
            try {
                new Actions(Driver.getDriver()).moveToElement(clickableElement).click().perform();
            } catch (Exception ex) {
                ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", clickableElement);
            }
        }
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
