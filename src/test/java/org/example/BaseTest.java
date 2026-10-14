package org.example;

import org.example.novysh.utils.Driver;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {
    @AfterEach
    public void tearDown() {
        Driver.quitDriver();
    }
}
