package org.calidadsoftware.questions;

import net.serenitybdd.screenplay.Question;
import org.calidadsoftware.interfaces.CheckoutPage;

public class ConfirmationMessageVisible {

    public static Question<Boolean> is() {
        return actor -> CheckoutPage.CONFIRMATION_MESSAGE.resolveFor(actor).isVisible();
    }
}
