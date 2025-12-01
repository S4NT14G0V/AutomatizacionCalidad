// Task to perform login to the Medical Admin application
package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interfaces.MedicalLoginPage;
import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.utils.WaitFor;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class LoginToMedicalAdmin implements Task {

    private final String email;
    private final String password;

    public LoginToMedicalAdmin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static LoginToMedicalAdmin withCredentials(String email, String password) {
        return Tasks.instrumented(LoginToMedicalAdmin.class, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitFor.visible(MedicalLoginPage.EMAIL_INPUT, 30),
                EnterText.valueInto(email, MedicalLoginPage.EMAIL_INPUT),
                EnterText.valueInto(password, MedicalLoginPage.PASSWORD_INPUT),
                ClickOn.target(MedicalLoginPage.LOGIN_BUTTON),
                // Wait for login attempt to complete
                WaitFor.sleep(5));
    }
}
