package org.calidadsoftware.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.calidadsoftware.interfaces.InventoryPage;
import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interactions.WaitFor;

public class Logout implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitFor.visible(InventoryPage.LOGOUT_LINK, 10),
                ClickOn.target(InventoryPage.LOGOUT_LINK)
        );
    }

    public static Performable now() {
        return Tasks.instrumented(Logout.class);
    }
}
