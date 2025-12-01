// Question to verify if login error alert is visible
package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.MedicalLoginPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class LoginErrorAlertVisible implements Question<Boolean> {

    public static LoginErrorAlertVisible onLoginPage() {
        return new LoginErrorAlertVisible();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        // Check if the error alert with role='alert' and destructive styling is visible
        return Visibility.of(MedicalLoginPage.ERROR_ALERT).answeredBy(actor);
    }
}
