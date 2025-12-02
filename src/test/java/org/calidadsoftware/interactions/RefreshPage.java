package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

/**
 * Interaction to refresh the current page
 * Useful to ensure state consistency after operations
 */
public class RefreshPage implements Interaction {

    public static RefreshPage now() {
        return Tasks.instrumented(RefreshPage.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().navigate().refresh();
    }
}
