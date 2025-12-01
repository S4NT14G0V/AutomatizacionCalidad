// Question to get the user name displayed in the dashboard
package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheUserName implements Question<String> {

    public static TheUserName displayed() {
        return new TheUserName();
    }

    @Override
    public String answeredBy(Actor actor) {
        return actor.asksFor(Text.of(MedicalDashboardPage.USER_NAME)).trim();
    }
}
