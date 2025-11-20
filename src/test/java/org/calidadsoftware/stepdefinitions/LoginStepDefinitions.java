package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.drivers.DriverFactory;
import org.calidadsoftware.questions.LoginError;
import org.calidadsoftware.tasks.Login;
import org.calidadsoftware.tasks.OpenTheApplication;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginStepDefinitions {

    @Given("que el usuario está en la página de login")
    public void usuario_esta_en_pagina_login() {
        WebDriver browser = DriverFactory.firefox();
        CommonStepDefinitions.browser = browser;
        CommonStepDefinitions.actor.can(BrowseTheWeb.with(browser));
        String appUrl = getAppUrl();
        CommonStepDefinitions.actor.attemptsTo(OpenTheApplication.on(appUrl));
    }

    @When("ingresa su usuario {string} y contraseña {string}")
    public void usuario_ingresa_credenciales(String usuario, String contrasena) {
        CommonStepDefinitions.actor.attemptsTo(Login.with(usuario, contrasena));
    }

    @Then("el sistema debe mostrar {string}")
    public void el_sistema_debe_mostrar(String resultado) {

        switch (resultado.toLowerCase()) {
            case "success":
                assertTrue(
                        "se redirigió al inventario",
                        CommonStepDefinitions.browser.getCurrentUrl().contains("inventory"));
                break;

            case "error":
                assertTrue(
                        "El mensaje de error no se está mostrando",
                        LoginError.isVisible().answeredBy(CommonStepDefinitions.actor));
                break;

            default:
                throw new IllegalArgumentException("Resultado no reconocido: " + resultado);
        }
    }

    private String getAppUrl() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("serenity.properties")) {
            if (input != null) {
                props.load(input);
                return props.getProperty("app.url", "https://www.saucedemo.com");
            }
        } catch (IOException e) {
            // error
        }
        return "https://www.saucedemo.com";
    }
}
