package org.calidadsoftware.stepdefinitions;

import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.calidadsoftware.drivers.DriverFactory;
import org.calidadsoftware.tasks.Login;
import org.calidadsoftware.tasks.OpenTheApplication;
import org.openqa.selenium.WebDriver;

public class CommonStepDefinitions {

    public static WebDriver browser;
    public static Actor actor = Actor.named("usuario");

    @Given("que el usuario ha iniciado sesion")
    public void usuario_ha_iniciado_sesion() {
        browser = DriverFactory.firefox();
        actor.can(BrowseTheWeb.with(browser));
        actor.attemptsTo(OpenTheApplication.on("https://www.saucedemo.com"));
        actor.attemptsTo(Login.with("standard_user", "secret_sauce"));
    }

    @Given("se encuentra en el catálogo de productos")
    public void se_encuentra_en_catalogo() {
        // ya está despues de login
    }

    public static Actor getActor() {
        return actor;
    }

    public static WebDriver getBrowser() {
        return browser;
    }
}
