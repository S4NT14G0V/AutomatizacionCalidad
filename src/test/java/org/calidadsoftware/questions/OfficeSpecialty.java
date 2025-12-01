// Question to get the specialty of a specific office
package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class OfficeSpecialty implements Question<String> {

    private final String officeName;

    public OfficeSpecialty(String officeName) {
        this.officeName = officeName;
    }

    public static OfficeSpecialty forOffice(String officeName) {
        return new OfficeSpecialty(officeName);
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            String fullText = actor.asksFor(Text.of(MedicalDashboardPage.officeSpecialty(officeName)));
            // Extract specialty from text like "Odontología, Envigado Urgencias,"
            if (fullText != null && fullText.contains(",")) {
                return fullText.split(",")[0].trim();
            }
            return fullText;
        } catch (Exception e) {
            return "";
        }
    }
}
