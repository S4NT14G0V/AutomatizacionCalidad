package org.calidadsoftware.questions;

import net.serenitybdd.screenplay.Question;
import org.calidadsoftware.interfaces.LoginPage;

public class RedirectedToLogin {

    public static Question<Boolean> is() {
        return actor -> LoginPage.LOGIN_BUTTON.resolveFor(actor).isVisible();
    }
}
