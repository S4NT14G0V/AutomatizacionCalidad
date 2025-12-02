// Task to navigate to the Medical Admin dashboard after login
package org.calidadsoftware.tasks;

import java.time.Duration;

import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

/**
 * Task to verify navigation to dashboard after login
 * Waits for key dashboard elements to be visible
 */
public class NavigateToDashboard implements Task {

    public static NavigateToDashboard afterLogin() {
        return Tasks.instrumented(NavigateToDashboard.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Wait for dashboard to load
        actor.attemptsTo(
                WaitUntil.the(MedicalDashboardPage.DASHBOARD_CONTAINER, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(15)),
                WaitUntil.the(MedicalDashboardPage.SIDEBAR_LOGO, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(10))
        );
    }
}
