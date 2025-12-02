package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

/**
 * Interaction to set value on input field with proper waiting and React event triggering
 * Ensures the field is ready before setting value
 */
public class WaitAndEnterText implements Interaction {

    private final String text;
    private final Target target;
    private final int timeoutSeconds;

    private WaitAndEnterText(String text, Target target, int timeoutSeconds) {
        this.text = text;
        this.target = target;
        this.timeoutSeconds = timeoutSeconds;
    }

    public static WaitAndEnterText inField(String text, Target target) {
        return new WaitAndEnterText(text, target, 10);
    }

    public static WaitAndEnterText inField(String text, Target target, int timeoutSeconds) {
        return new WaitAndEnterText(text, target, timeoutSeconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Wait for field to be visible and enabled
        actor.attemptsTo(
                WaitUntil.the(target, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(timeoutSeconds)),
                WaitUntil.the(target, isEnabled())
                        .forNoMoreThan(Duration.ofSeconds(timeoutSeconds))
        );

        // Use JavaScript to set value and trigger React events
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        js.executeScript(
            "var input = arguments[0];" +
            "if (input) {" +
            "  input.focus();" +
            "  var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "  nativeInputValueSetter.call(input, arguments[1]);" +
            "  input.dispatchEvent(new Event('input', { bubbles: true }));" +
            "  input.dispatchEvent(new Event('change', { bubbles: true }));" +
            "  input.blur();" +
            "}",
            target.resolveFor(actor).getElement(),
            text
        );
    }
}
