// Question to retrieve the displayed user email
package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheUserEmail implements Question<String> {

    public static TheUserEmail displayed() {
        return new TheUserEmail();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(MedicalDashboardPage.USER_EMAIL).answeredBy(actor);
    }
}
