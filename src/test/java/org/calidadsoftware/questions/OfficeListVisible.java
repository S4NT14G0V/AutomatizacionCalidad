// Question to verify if the office list is visible
package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class OfficeListVisible implements Question<Boolean> {

    public static OfficeListVisible onDashboard() {
        return new OfficeListVisible();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return actor.asksFor(Visibility.of(MedicalDashboardPage.OFFICE_LIST));
    }
}
