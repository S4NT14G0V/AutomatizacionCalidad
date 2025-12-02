package org.calidadsoftware.tasks;

import java.time.Duration;

import org.calidadsoftware.interactions.ClickOfficeButton;
import org.calidadsoftware.interactions.WaitAndClick;
import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

/**
 * Task representing the business process of deleting an office
 * Uses proper Serenity Screenplay pattern with robust synchronization
 */
public class DeleteOffice implements Task {

    private final String officeName;

    public DeleteOffice(String officeName) {
        this.officeName = officeName;
    }

    public static DeleteOffice named(String officeName) {
        return Tasks.instrumented(DeleteOffice.class, officeName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Click the delete button for the specific office
        actor.attemptsTo(
                ClickOfficeButton.toDelete(officeName)
        );

        // Wait for delete confirmation dialog to appear
        actor.attemptsTo(
                WaitUntil.the(MedicalDashboardPage.DELETE_DIALOG, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10))
        );

        // Confirm deletion and wait for operation to complete
        // Wait for dashboard to be visible again (dialog closes after delete)
        actor.attemptsTo(
                WaitAndClick.on(MedicalDashboardPage.CONFIRM_DELETE_BUTTON)
        );

        // Give React time to process the delete and update the DOM
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
