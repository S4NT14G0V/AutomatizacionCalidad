// Question to verify if user is successfully logged in
package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class TheLoginStatus implements Question<Boolean> {

    public static TheLoginStatus isSuccessful() {
        return new TheLoginStatus();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        // Check if the user email is visible on the dashboard
        return Visibility.of(MedicalDashboardPage.USER_EMAIL).answeredBy(actor);
    }
}
