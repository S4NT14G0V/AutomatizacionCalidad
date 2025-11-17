package org.calidadsoftware.questions;

import net.serenitybdd.screenplay.Question;
import org.calidadsoftware.interfaces.LoginPage;

public class LoginError {

    public static Question<Boolean> isVisible() {
        return actor -> LoginPage.ERROR_MESSAGE.resolveFor(actor).isVisible();
    }
}

