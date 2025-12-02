package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Interaction to set a value on a React-controlled input field
 * Uses JavaScript to properly trigger React's synthetic events
 */
public class SetInputValue implements Interaction {

    private final String value;
    private final String inputId;

    public SetInputValue(String value, String inputId) {
        this.value = value;
        this.inputId = inputId;
    }

    public static SetInputValue inField(String value, String inputId) {
        return Tasks.instrumented(SetInputValue.class, value, inputId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        // Set value on React-controlled input field
        js.executeScript(
            "var input = document.querySelector('#' + arguments[1]);" +
            "if (input) {" +
            "  var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "  nativeInputValueSetter.call(input, arguments[0]);" +
            "  var event = new Event('input', { bubbles: true });" +
            "  input.dispatchEvent(event);" +
            "}",
            value, inputId);
    }
}
