package org.calidadsoftware.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.calidadsoftware.interfaces.InventoryPage;
import org.calidadsoftware.interactions.ClickOn;

public class ViewCart implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickOn.target(InventoryPage.CART_LINK));
    }

    public static Performable now() {
        return Tasks.instrumented(ViewCart.class);
    }
}
