package org.example.novysh.utils;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Utils {
    public static String getText(WebElement element) {
        if (element == null) {
            return "";
        }
        try {
            return element.getText().strip();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public static void assertElementIsDisplayed(WebElement element, String message) {
        try {
            WebElement visibleElement = Driver.getWait()
                    .until(ExpectedConditions.visibilityOf(element));

            Assertions.assertTrue(visibleElement.isDisplayed(), message);
        } catch (TimeoutException e) {
            Assertions.fail(message + " (Element did not appear within the allocated time)", e);
        }
    }

    public static void assertElementIsDisplayed(WebElement element) {
        assertElementIsDisplayed(element, "Element should be displayed on the page");
    }

}
