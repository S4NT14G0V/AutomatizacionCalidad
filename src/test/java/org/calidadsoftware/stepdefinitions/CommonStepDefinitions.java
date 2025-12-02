package org.calidadsoftware.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.calidadsoftware.drivers.DriverFactory;
import org.calidadsoftware.tasks.LoginToMedicalAdmin;
import org.calidadsoftware.utils.OpenTheApplication;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Common step definitions shared across multiple features
 * Manages test lifecycle and browser setup/teardown for test independence
 */
public class CommonStepDefinitions {

    public static WebDriver browser;
    public static Actor actor = Actor.named("usuario");

    /**
     * Runs before each scenario to ensure clean state
     */
    @Before
    public void setupBrowser() {
        if (browser != null) {
            try {
                browser.quit();
            } catch (Exception e) {
                // Ignore errors during cleanup
            }
            browser = null;
        }
        actor = Actor.named("usuario");
    }

    /**
     * Runs after each scenario to clean up resources
     */
    @After
    public void tearDown() {
        if (browser != null) {
            try {
                browser.quit();
            } catch (Exception e) {
                // Ignore errors during cleanup
            }
            browser = null;
        }
    }

    /**
     * Initializes the browser and configures the actor, without login
     * Ensures fresh browser instance for each test
     */
    public static void openApplication() {
        if (browser != null) {
            try {
                browser.quit();
            } catch (Exception e) {
                // Ignore
            }
        }
        browser = DriverFactory.firefox();
        actor.can(BrowseTheWeb.with(browser));
        String appUrl = getAppUrl();
        actor.attemptsTo(OpenTheApplication.on(appUrl));
    }

    /**
     * Initializes the browser, configures the actor and performs login
     */
    @Given("que el usuario ha iniciado sesion")
    public void usuario_ha_iniciado_sesion() {
        openApplication();
        actor.attemptsTo(LoginToMedicalAdmin.withCredentials("demo@medical.com", "demo123"));
    }

    private static String getAppUrl() {
        Properties props = new Properties();
        try (InputStream input = CommonStepDefinitions.class.getClassLoader()
                .getResourceAsStream("serenity.properties")) {
            if (input != null) {
                props.load(input);
                return props.getProperty("app.url");
            }
        } catch (IOException e) {
            // Log error if needed
        }
        return "blank";
    }

    @Given("se encuentra en el catálogo de productos")
    public void se_encuentra_en_catalogo() {
        // Already in catalog after login
    }

    public static Actor getActor() {
        return actor;
    }

    public static WebDriver getBrowser() {
        return browser;
    }
}
