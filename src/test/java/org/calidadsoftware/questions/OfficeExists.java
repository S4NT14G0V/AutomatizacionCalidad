// Question to verify if a specific office exists with expected details
package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class OfficeExists implements Question<Boolean> {

    private final String officeName;

    public OfficeExists(String officeName) {
        this.officeName = officeName;
    }

    public static OfficeExists withName(String officeName) {
        return new OfficeExists(officeName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return actor.asksFor(Visibility.of(MedicalDashboardPage.officeByName(officeName)));
        } catch (Exception e) {
            return false;
        }
    }
}
