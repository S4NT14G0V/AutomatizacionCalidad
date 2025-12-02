package org.calidadsoftware.tasks;

import java.time.Duration;
import java.util.Map;

import org.calidadsoftware.interactions.WaitAndClick;
import org.calidadsoftware.interactions.WaitAndEnterText;
import org.calidadsoftware.interactions.SelectFromDropdown;
import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

/**
 * Task representing the business process of creating a new office
 * Uses proper Serenity Screenplay pattern with waits and interactions
 */
public class CreateOffice implements Task {

    private final Map<String, String> officeData;

    public CreateOffice(Map<String, String> officeData) {
        this.officeData = officeData;
    }

    public static CreateOffice withData(Map<String, String> officeData) {
        return Tasks.instrumented(CreateOffice.class, officeData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Wait for the registration form to be visible
        actor.attemptsTo(
                WaitUntil.the(MedicalDashboardPage.REGISTRATION_FORM, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10))
        );

        // Fill in office name/number
        if (officeData.containsKey("nombre")) {
            actor.attemptsTo(
                    WaitAndEnterText.inField(
                            officeData.get("nombre"),
                            MedicalDashboardPage.OFFICE_NAME_INPUT
                    )
            );
        }

        // Select specialty
        if (officeData.containsKey("especialidad")) {
            actor.attemptsTo(
                    SelectFromDropdown.option(
                            officeData.get("especialidad"),
                            MedicalDashboardPage.OFFICE_SPECIALTY_SELECT
                    )
            );
        }

        // Select location/sede
        if (officeData.containsKey("sede")) {
            actor.attemptsTo(
                    SelectFromDropdown.option(
                            officeData.get("sede"),
                            MedicalDashboardPage.OFFICE_LOCATION_SELECT
                    )
            );
        }

        // Select status
        if (officeData.containsKey("estado")) {
            actor.attemptsTo(
                    SelectFromDropdown.option(
                            officeData.get("estado"),
                            MedicalDashboardPage.OFFICE_STATUS_SELECT
                    )
            );
        }

        // Click save button and wait for operation to complete
        // Wait for dashboard to be visible again (form closes after save)
        actor.attemptsTo(
                WaitAndClick.on(MedicalDashboardPage.SAVE_OFFICE_BUTTON)
        );

        // Give React time to process the save and update the DOM
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }

        // Verify we're back on the dashboard
        actor.attemptsTo(
                WaitUntil.the(MedicalDashboardPage.DASHBOARD_CONTAINER, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10))
        );
    }
}
