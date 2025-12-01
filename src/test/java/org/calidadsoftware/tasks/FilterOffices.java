// Task to apply filters to the office list
package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.utils.WaitFor;
import org.openqa.selenium.By;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;

public class FilterOffices implements Task {

    private final String filterType;
    private final String filterValue;

    public FilterOffices(String filterType, String filterValue) {
        this.filterType = filterType;
        this.filterValue = filterValue;
    }

    public static FilterOffices by(String filterType, String filterValue) {
        return Tasks.instrumented(FilterOffices.class, filterType, filterValue);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Target filterButton = getFilterButton(filterType);
        
        actor.attemptsTo(
                WaitFor.visible(filterButton, 10),
                ClickOn.target(filterButton),
                WaitFor.sleep(1) // Wait for dropdown to appear
        );

        // Click on the filter value option
        Target filterOption = Target.the("filter option: " + filterValue)
                .located(By.xpath("//button[contains(text(),'" + filterValue + "') or .//span[contains(text(),'" + filterValue + "')]]"));

        actor.attemptsTo(
                WaitFor.visible(filterOption, 5),
                ClickOn.target(filterOption),
                WaitFor.sleep(1) // Wait for filter to apply
        );
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
