package org.example;

import org.example.novysh.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    @BeforeEach
    public void setUp() {
        Driver.getDriver();
    }

    @AfterEach
    public void tearDown() {
        Driver.quitDriver();
    }
}
