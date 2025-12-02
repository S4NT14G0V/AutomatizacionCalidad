// Task to perform login to the Medical Admin application
package org.calidadsoftware.tasks;

import java.time.Duration;

import org.calidadsoftware.interactions.WaitAndClick;
import org.calidadsoftware.interactions.WaitAndEnterText;
import org.calidadsoftware.interfaces.MedicalLoginPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

/**
 * Task to perform login to the Medical Admin application
 * Uses robust synchronization and proper Screenplay pattern
 */
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
        // Wait for login page to be ready
        actor.attemptsTo(
                WaitUntil.the(MedicalLoginPage.LOGIN_PAGE, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(30))
        );

        // Enter credentials
        actor.attemptsTo(
                WaitAndEnterText.inField(email, MedicalLoginPage.EMAIL_INPUT),
                WaitAndEnterText.inField(password, MedicalLoginPage.PASSWORD_INPUT)
        );

        // Submit login form
        actor.attemptsTo(
                WaitAndClick.on(MedicalLoginPage.LOGIN_BUTTON)
        );
    }
}
