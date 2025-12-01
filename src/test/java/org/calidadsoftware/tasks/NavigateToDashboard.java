// Task to navigate to the Medical Admin dashboard after login
package org.calidadsoftware.tasks;

import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.utils.WaitFor;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class NavigateToDashboard implements Task {

    public static NavigateToDashboard afterLogin() {
        return Tasks.instrumented(NavigateToDashboard.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitFor.visible(MedicalDashboardPage.SIDEBAR_LOGO, 10),
                WaitFor.visible(MedicalDashboardPage.ADMIN_OFFICES_MENU, 10));
    }
}
