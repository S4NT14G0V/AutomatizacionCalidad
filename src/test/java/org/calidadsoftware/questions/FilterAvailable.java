// Question to check if filter is available
package org.calidadsoftware.questions;

import org.calidadsoftware.interfaces.MedicalDashboardPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.targets.Target;

public class FilterAvailable implements Question<Boolean> {

    private final String filterType;

    public FilterAvailable(String filterType) {
        this.filterType = filterType;
    }

    public static FilterAvailable ofType(String filterType) {
        return new FilterAvailable(filterType);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Target filterButton = getFilterButton(filterType);
        return actor.asksFor(Visibility.of(filterButton));
    }

    private Target getFilterButton(String filterType) {
        switch (filterType.toLowerCase()) {
            case "sede":
            case "location":
                return MedicalDashboardPage.LOCATION_FILTER;
            case "especialidad":
            case "specialty":
                return MedicalDashboardPage.SPECIALTY_FILTER;
            case "estado":
            case "status":
                return MedicalDashboardPage.STATUS_FILTER;
            default:
                throw new IllegalArgumentException("Unknown filter type: " + filterType);
        }
    }
}
