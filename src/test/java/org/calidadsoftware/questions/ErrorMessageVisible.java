package org.calidadsoftware.questions;

import net.serenitybdd.screenplay.Question;
import org.calidadsoftware.interfaces.CheckoutPage;

public class ErrorMessageVisible {

    public static Question<Boolean> is() {
        return actor -> CheckoutPage.ERROR_MESSAGE.resolveFor(actor).isVisible();
    }
}
