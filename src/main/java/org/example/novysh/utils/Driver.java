package org.example.novysh.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();

           /* ((ChromeDriver)driver).executeCdpCommand("Network.setCookie", Map.of(
                    "name", "CookieConsent", // Укажите реальное имя куки сайта
                    "value", "{stamp:'5yLe6qXJO/3YItBbPvxrXgxX+xxUta1oHLH9NORDdT0/TsXUS/D0yA==',necessary:true,preferences:true,statistics:true,marketing:true,method:'explicit',ver:1,utc:1791887711100,region:'pl'}",
                    "domain", "innowise.com",
                    "path", "/"
            ));*/
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigReader.getTimeout()));
        }
        return wait;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}
