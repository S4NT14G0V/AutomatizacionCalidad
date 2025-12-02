package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.utils.WaitFor;
import org.calidadsoftware.interfaces.CheckoutPage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

// tarea para completar la compra ingresando los datos requeridos
public class CompletePurchase implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickOn.target(CheckoutPage.FINISH_BUTTON));
        actor.attemptsTo(WaitFor.sleep(2));
    }

    public static Performable now() {
        return Tasks.instrumented(CompletePurchase.class);
    }
}
