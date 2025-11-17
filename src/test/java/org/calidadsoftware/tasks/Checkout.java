package org.calidadsoftware.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.calidadsoftware.interfaces.CartPage;
import org.calidadsoftware.interactions.ClickOn;

public class Checkout implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickOn.target(CartPage.CHECKOUT_BUTTON));
    }

    public static Performable now() {
        return Tasks.instrumented(Checkout.class);
    }
}
