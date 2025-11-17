package org.calidadsoftware.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver chrome() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        return new EdgeDriver(options);
    }

    public static WebDriver edge() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    public static WebDriver firefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1280", "--height=800");
        return new FirefoxDriver(options);
    }
}
