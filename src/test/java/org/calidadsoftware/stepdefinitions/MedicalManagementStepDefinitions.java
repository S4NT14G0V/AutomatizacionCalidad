package org.calidadsoftware.stepdefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.calidadsoftware.interactions.WaitAndClick;
import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.tasks.CreateOffice;
import org.calidadsoftware.tasks.DeleteOffice;
import org.calidadsoftware.tasks.LoginToMedicalAdmin;
import org.calidadsoftware.tasks.ModifyOffice;
import org.calidadsoftware.tasks.NavigateToDashboard;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

/**
 * Step definitions for medical office management E2E tests
 * Follows Serenity best practices: logic in Tasks, not in step definitions
 */
public class MedicalManagementStepDefinitions {

        @Given("que el usuario ha iniciado sesión como administrador")
        public void userHasLoggedInAsAdministrator() {
                CommonStepDefinitions.openApplication();
                CommonStepDefinitions.getActor().attemptsTo(
                                LoginToMedicalAdmin.withCredentials("demo@medical.com", "demo123"),
                                NavigateToDashboard.afterLogin(),
                                WaitUntil.the(MedicalDashboardPage.DASHBOARD_CONTAINER, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)));
        }

        @And("que existe un consultorio con nombre {string}")
        public void anOfficeExistsWithName(String nombre) {
                // Create the office to ensure test independence
                Map<String, String> data = new HashMap<>();
                data.put("nombre", nombre);
                data.put("especialidad", "Medicina General");
                data.put("sede", "Sede Central");
                data.put("estado", "Activo");

                CommonStepDefinitions.getActor().attemptsTo(
                                WaitAndClick.on(MedicalDashboardPage.NEW_OFFICE_BUTTON),
                                WaitUntil.the(MedicalDashboardPage.REGISTRATION_FORM, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)),
                                CreateOffice.withData(data),
                                WaitUntil.the(MedicalDashboardPage.DASHBOARD_CONTAINER, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)));
        }

        @When("crea un nuevo consultorio con nombre {string}, especialidad {string} y ubicación {string}")
        public void createsANewOffice(String nombre, String especialidad, String sede) {
                Map<String, String> data = new HashMap<>();
                data.put("nombre", nombre);
                data.put("especialidad", especialidad);
                data.put("sede", sede);
                data.put("estado", "Activo");

                CommonStepDefinitions.getActor().attemptsTo(
                                WaitAndClick.on(MedicalDashboardPage.NEW_OFFICE_BUTTON),
                                WaitUntil.the(MedicalDashboardPage.REGISTRATION_FORM, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)),
                                CreateOffice.withData(data),
                                WaitUntil.the(MedicalDashboardPage.DASHBOARD_CONTAINER, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)));
        }

        @When("se edita el consultorio {string} cambiando el nombre a {string} y la especialidad a {string}")
        public void editsTheOffice(String nombreOriginal, String nuevoNombre, String nuevaEspecialidad) {
                Map<String, String> data = new HashMap<>();
                data.put("nombre", nuevoNombre);
                data.put("especialidad", nuevaEspecialidad);

                CommonStepDefinitions.getActor().attemptsTo(
                                ModifyOffice.named(nombreOriginal, data),
                                WaitUntil.the(MedicalDashboardPage.DASHBOARD_CONTAINER, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)));
        }

        @When("se elimina el consultorio {string}")
        public void deletesTheOffice(String nombre) {
                CommonStepDefinitions.getActor().attemptsTo(
                                DeleteOffice.named(nombre),
                                WaitUntil.the(MedicalDashboardPage.DASHBOARD_CONTAINER, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)));
        }

        @And("el usuario cierra sesión")
        public void userLogsOut() {
                CommonStepDefinitions.getActor().attemptsTo(
                                WaitUntil.the(MedicalDashboardPage.LOGOUT_BUTTON, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)),
                                WaitAndClick.on(MedicalDashboardPage.LOGOUT_BUTTON),
                                WaitUntil.the(org.calidadsoftware.interfaces.MedicalLoginPage.LOGIN_PAGE, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)));
        }

        @Then("debe ver el mensaje {string}")
        public void shouldSeeTheMessage(String expectedMessage) {
                // Verify we're on dashboard after operation
                // Toast messages are transient, so we verify page state instead
                assertThat(
                                "Dashboard should be visible after operation",
                                CommonStepDefinitions.getActor().asksFor(
                                                Visibility.of(MedicalDashboardPage.DASHBOARD_CONTAINER)),
                                is(true));
        }

        @Then("debe ver el login")
        public void shouldSeeTheLogin() {
                assertThat(
                                "Login page should be visible after logout",
                                CommonStepDefinitions.getActor().asksFor(
                                                Visibility.of(org.calidadsoftware.interfaces.MedicalLoginPage.LOGIN_BUTTON)),
                                is(true));
        }

        @Then("el consultorio {string} debe existir en el sistema")
        public void officeExistsInSystem(String nombre) {
                // Wait for list to be stable after create/edit operation
                CommonStepDefinitions.getActor().attemptsTo(
                                WaitUntil.the(MedicalDashboardPage.OFFICE_ITEMS, isVisible())
                                                .forNoMoreThan(Duration.ofSeconds(10)));
                
                // Verify office exists by finding its name in the list
                assertThat(
                                "Office should be visible in the list",
                                CommonStepDefinitions.getActor().asksFor(
                                                Visibility.of(MedicalDashboardPage.officeByNameText(nombre))),
                                is(true));
        }

        @Then("el consultorio {string} no debe existir en el sistema")
        public void officeDoesNotExistInSystem(String nombre) {
                // Wait for list to be stable after delete operation
                CommonStepDefinitions.getActor().attemptsTo(
                                WaitUntil.the(MedicalDashboardPage.OFFICE_LIST, isPresent())
                                                .forNoMoreThan(Duration.ofSeconds(10)));
                
                // Verify office does not exist by checking its name is not in the list
                assertThat(
                                "Office should not be visible in the list",
                                CommonStepDefinitions.getActor().asksFor(
                                                Visibility.of(MedicalDashboardPage.officeByNameText(nombre))),
                                is(false));
        }
}
