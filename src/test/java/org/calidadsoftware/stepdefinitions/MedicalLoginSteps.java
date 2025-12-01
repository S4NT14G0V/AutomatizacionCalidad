// Step definitions for Medical Admin login scenarios
package org.calidadsoftware.stepdefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.calidadsoftware.interfaces.MedicalLoginPage;
import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.questions.LoginErrorAlertVisible;
import org.calidadsoftware.tasks.LoginToMedicalAdmin;
import org.calidadsoftware.utils.WaitFor;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.questions.Visibility;

public class MedicalLoginSteps {

        @Given("está en la página de login")
        public void estaEnLaPaginaDeLogin() {
                CommonStepDefinitions.openApplication();
        }

        @When("inicia sesión con usuario {string} y contraseña {string}")
        public void iniciaSesionConUsuarioYContrasena(String email, String password) {
                CommonStepDefinitions.getActor().attemptsTo(
                                LoginToMedicalAdmin.withCredentials(email, password));
        }

        @Then("el usuario debe ver el mensaje {string}")
        public void elUsuarioDebeVerElMensaje(String expectedMessage) {
                if (expectedMessage.contains("Exitoso")) {
                        // Verify successful login by checking dashboard visibility
                        assertThat(
                                        "Dashboard should be visible after successful login",
                                        CommonStepDefinitions.getActor().asksFor(
                                                        Visibility.of(MedicalDashboardPage.DASHBOARD_CONTAINER)),
                                        is(true));
                } else if (expectedMessage.contains("Error")) {
                        // Wait for error alert to appear and verify it's visible
                        CommonStepDefinitions.getActor().attemptsTo(
                                        WaitFor.sleep(2));
                        assertThat(
                                        "Error alert should be visible after failed login",
                                        CommonStepDefinitions.getActor().asksFor(
                                                        Visibility.of(MedicalLoginPage.ERROR_ALERT)),
                                        is(true));
                }
        }
}
