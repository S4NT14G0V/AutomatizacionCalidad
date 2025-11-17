package org.calidadsoftware.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.calidadsoftware.interfaces.LoginPage;
import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.interactions.ClickOn;

public class Login  {

    public static Performable with(String username, String password) {
        return Task.where("{0} intenta iniciar sesión",
                EnterText.valueInto(username, LoginPage.USERNAME),
                EnterText.valueInto(password, LoginPage.PASSWORD),
                ClickOn.target(LoginPage.LOGIN_BUTTON)
        );
    }
}
