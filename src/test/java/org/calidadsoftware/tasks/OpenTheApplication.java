package org.calidadsoftware.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenTheApplication {

    public static Performable on(String url) {
        return Task.where("{0} abre la aplicación",
                net.serenitybdd.screenplay.actions.Open.url(url)
        );
    }
}