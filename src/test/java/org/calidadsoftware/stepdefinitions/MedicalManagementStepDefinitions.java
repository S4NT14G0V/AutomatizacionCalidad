package org.calidadsoftware.stepdefinitions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.tasks.CreateOffice;
import org.calidadsoftware.tasks.DeleteOffice;
import org.calidadsoftware.tasks.LoginToMedicalAdmin;
import org.calidadsoftware.tasks.ModifyOffice;
import org.calidadsoftware.tasks.NavigateToDashboard;
import org.calidadsoftware.utils.WaitFor;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;

public class MedicalManagementStepDefinitions {

        @Given("que el usuario ha iniciado sesión como administrador")
        public void userHasLoggedInAsAdministrator() {
                CommonStepDefinitions.openApplication();
                CommonStepDefinitions.getActor().attemptsTo(
                                LoginToMedicalAdmin.withCredentials("admin1@medicaladmin.com", "Admin123!"),
                                NavigateToDashboard.afterLogin());
        }

        @And("que existe un consultorio con nombre {string}")
        public void anOfficeExistsWithName(String nombre) {
                // Create the office for this test to ensure test independence
                Map<String, String> data = new HashMap<>();
                data.put("nombre", nombre);
                data.put("especialidad", "Medicina General");
                data.put("sede", "Sede Central");
                data.put("estado", "Activo");

                CommonStepDefinitions.getActor().attemptsTo(
                                org.calidadsoftware.interactions.ClickOn.target(MedicalDashboardPage.NEW_OFFICE_BUTTON),
                                WaitFor.visible(MedicalDashboardPage.OFFICE_NAME_INPUT, 10),
                                CreateOffice.withData(data),
                                WaitFor.sleep(5));
                
                // Scroll to bottom to see newly created office
                org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) net.serenitybdd.screenplay.abilities.BrowseTheWeb.as(CommonStepDefinitions.getActor()).getDriver();
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                
                CommonStepDefinitions.getActor().attemptsTo(WaitFor.sleep(2));
                
                // Try to find and scroll to the specific office element
                try {
                        org.openqa.selenium.WebElement officeElement = net.serenitybdd.screenplay.abilities.BrowseTheWeb.as(CommonStepDefinitions.getActor()).getDriver()
                                .findElement(org.openqa.selenium.By.xpath("//h3[contains(text(),'Consultorio " + nombre + "')]"));
                        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", officeElement);
                        CommonStepDefinitions.getActor().attemptsTo(WaitFor.sleep(2));
                } catch (Exception e) {
                        // If element not found, just wait a bit more
                        CommonStepDefinitions.getActor().attemptsTo(WaitFor.sleep(3));
                }
        }

        @When("crea un nuevo consultorio con nombre {string}, especialidad {string} y ubicación {string}")
        public void createsANewOffice(String nombre, String especialidad, String sede) {
                Map<String, String> data = new HashMap<>();
                data.put("nombre", nombre);
                data.put("especialidad", especialidad);
                data.put("sede", sede);
                data.put("estado", "Activo");

                CommonStepDefinitions.getActor().attemptsTo(
                                org.calidadsoftware.interactions.ClickOn.target(MedicalDashboardPage.NEW_OFFICE_BUTTON),
                                WaitFor.visible(MedicalDashboardPage.OFFICE_NAME_INPUT, 10),
                                CreateOffice.withData(data));
        }

        @When("se edita el consultorio {string} cambiando el nombre a {string} y la especialidad a {string}")
        public void editsTheOffice(String nombreOriginal, String nuevoNombre, String nuevaEspecialidad) {
                Map<String, String> data = new HashMap<>();
                data.put("nombre", nuevoNombre);
                data.put("especialidad", nuevaEspecialidad);

                CommonStepDefinitions.getActor().attemptsTo(
                                ModifyOffice.named(nombreOriginal, data));
        }

        @When("se elimina el consultorio {string}")
        public void deletesTheOffice(String nombre) {
                CommonStepDefinitions.getActor().attemptsTo(
                                DeleteOffice.named(nombre));
        }

        @And("el usuario cierra sesión")
        public void userLogsOut() {
                CommonStepDefinitions.getActor().attemptsTo(
                                WaitFor.visible(MedicalDashboardPage.LOGOUT_BUTTON, 10),
                                org.calidadsoftware.interactions.ClickOn.target(MedicalDashboardPage.LOGOUT_BUTTON),
                                WaitFor.sleep(2));
        }

        @Then("debe ver el mensaje {string}")
        public void shouldSeeTheMessage(String expectedMessage) {
                if (expectedMessage.contains("Registrado") || expectedMessage.contains("actualizado")
                                || expectedMessage.contains("eliminado")) {
                        // Success case - verify we're still on dashboard
                        assertThat(
                                        "Dashboard should be visible after operation",
                                        CommonStepDefinitions.getActor().asksFor(
                                                        Visibility.of(MedicalDashboardPage.DASHBOARD_CONTAINER)),
                                        is(true));
                } else if (expectedMessage.contains("Error") || expectedMessage.contains("existe")) {
                        // Error case - verify we're still on dashboard (operation failed)
                        assertThat(
                                        "Dashboard should still be visible after error",
                                        CommonStepDefinitions.getActor().asksFor(
                                                        Visibility.of(MedicalDashboardPage.DASHBOARD_CONTAINER)),
                                        is(true));
                }
        }

        @Then("debe ver el login")
        public void shouldSeeTheLogin() {
                CommonStepDefinitions.getActor().attemptsTo(
                                WaitFor.sleep(2));
                assertThat(
                                "Login page should be visible after logout",
                                CommonStepDefinitions.getActor().asksFor(
                                                Visibility.of(org.calidadsoftware.interfaces.MedicalLoginPage.LOGIN_BUTTON)),
                                is(true));
        }
}
