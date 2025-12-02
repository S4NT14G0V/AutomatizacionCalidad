package org.calidadsoftware.tasks;

import java.time.Duration;
import java.util.Map;

import org.calidadsoftware.interactions.ClickOfficeButton;
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
 * Task representing the business process of modifying an existing office
 * Uses proper Serenity Screenplay pattern with robust synchronization
 */
public class ModifyOffice implements Task {

    private final String officeName;
    private final Map<String, String> updatedData;

    public ModifyOffice(String officeName, Map<String, String> updatedData) {
        this.officeName = officeName;
        this.updatedData = updatedData;
    }

    public static ModifyOffice named(String officeName, Map<String, String> updatedData) {
        return Tasks.instrumented(ModifyOffice.class, officeName, updatedData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Click the modify button for the specific office
        actor.attemptsTo(
                ClickOfficeButton.toModify(officeName)
        );

        // Wait for edit dialog to open
        actor.attemptsTo(
                WaitUntil.the(MedicalDashboardPage.EDIT_DIALOG, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10))
        );

        // Update name if provided
        if (updatedData.containsKey("nombre")) {
            actor.attemptsTo(
                    WaitAndEnterText.inField(
                            updatedData.get("nombre"),
                            MedicalDashboardPage.EDIT_OFFICE_NAME_INPUT
                    )
            );
        }

        // Update specialty if provided
        if (updatedData.containsKey("especialidad")) {
            actor.attemptsTo(
                    SelectFromDropdown.option(
                            updatedData.get("especialidad"),
                            MedicalDashboardPage.EDIT_OFFICE_SPECIALTY_SELECT
                    )
            );
        }

        // Update location if provided
        if (updatedData.containsKey("sede")) {
            actor.attemptsTo(
                    SelectFromDropdown.option(
                            updatedData.get("sede"),
                            MedicalDashboardPage.EDIT_OFFICE_LOCATION_SELECT
                    )
            );
        }

        // Update status if provided
        if (updatedData.containsKey("estado")) {
            actor.attemptsTo(
                    SelectFromDropdown.option(
                            updatedData.get("estado"),
                            MedicalDashboardPage.EDIT_OFFICE_STATUS_SELECT
                    )
            );
        }

        // Click save and wait for operation to complete
        // Wait for dashboard to be visible again (dialog closes after save)
        actor.attemptsTo(
                WaitAndClick.on(MedicalDashboardPage.EDIT_SAVE_BUTTON)
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
