package org.example.novysh;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage {
    public WebElement waitForVisibility(WebElement element) {
        return Driver.getWait()
                .until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForVisibility(By locator) {
        return Driver.getWait()
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
