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
 * Interaction to select an option from a React/Shadcn select dropdown
 * Handles the hidden select pattern used by modern UI libraries
 */
public class SelectFromDropdown implements Interaction {

    private final String optionValue;
    private final Target selectTrigger;
    private final int timeoutSeconds;

    private SelectFromDropdown(String optionValue, Target selectTrigger, int timeoutSeconds) {
        this.optionValue = optionValue;
        this.selectTrigger = selectTrigger;
        this.timeoutSeconds = timeoutSeconds;
    }

    public static SelectFromDropdown option(String optionValue, Target selectTrigger) {
        return new SelectFromDropdown(optionValue, selectTrigger, 10);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Wait for select trigger to be visible
        actor.attemptsTo(
                WaitUntil.the(selectTrigger, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(timeoutSeconds))
        );

        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        // Find and interact with the hidden select element
        js.executeScript(
            "var trigger = arguments[0];" +
            "var optionValue = arguments[1];" +
            "if (trigger) {" +
            "  var container = trigger.closest('div');" +
            "  var select = container.querySelector('select[aria-hidden=\"true\"]');" +
            "  if (select) {" +
            "    for(var i = 0; i < select.options.length; i++) {" +
            "      if(select.options[i].value === optionValue || select.options[i].text === optionValue) {" +
            "        select.selectedIndex = i;" +
            "        select.value = select.options[i].value;" +
            "        select.dispatchEvent(new Event('change', { bubbles: true }));" +
            "        select.dispatchEvent(new Event('input', { bubbles: true }));" +
            "        console.log('Selected option:', optionValue);" +
            "        return true;" +
            "      }" +
            "    }" +
            "    console.error('Option not found:', optionValue);" +
            "    return false;" +
            "  } else {" +
            "    console.error('Select element not found for trigger');" +
            "    return false;" +
            "  }" +
            "}",
            selectTrigger.resolveFor(actor).getElement(),
            optionValue
        );

        // Small wait for React to update
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
