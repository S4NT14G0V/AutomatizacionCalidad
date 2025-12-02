package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Interaction to select an option from a hidden select element
 * Common in React applications using shadcn/ui or similar component libraries
 */
public class SelectHiddenOption implements Interaction {

    private final String optionValue;
    private final String fieldId;

    public SelectHiddenOption(String optionValue, String fieldId) {
        this.optionValue = optionValue;
        this.fieldId = fieldId;
    }

    public static SelectHiddenOption withValue(String optionValue, String fieldId) {
        return Tasks.instrumented(SelectHiddenOption.class, optionValue, fieldId);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        // Find the label with for=fieldId, then find the associated hidden select
        // Structure: <label for="X"> ... <button> ... <select aria-hidden="true">
        js.executeScript(
            "var label = document.querySelector('label[for=\"' + arguments[1] + '\"]');" +
            "if (label) {" +
            "  var container = label.parentElement;" +
            "  var select = container.querySelector('select[aria-hidden=\"true\"]');" +
            "  if (select) {" +
            "    var found = false;" +
            "    for(var i = 0; i < select.options.length; i++) {" +
            "      if(select.options[i].value === arguments[0] || select.options[i].text === arguments[0]) {" +
            "        select.selectedIndex = i;" +
            "        select.value = select.options[i].value;" +
            "        select.dispatchEvent(new Event('change', { bubbles: true }));" +
            "        select.dispatchEvent(new Event('input', { bubbles: true }));" +
            "        found = true;" +
            "        break;" +
            "      }" +
            "    }" +
            "    if (!found) {" +
            "      console.error('Option not found: ' + arguments[0] + ' in select with options:', Array.from(select.options).map(o => o.value + '=' + o.text));" +
            "    }" +
            "  } else {" +
            "    console.error('Select not found for label with for=' + arguments[1]);" +
            "  }" +
            "} else {" +
            "  console.error('Label not found with for=' + arguments[1]);" +
            "}",
            optionValue, fieldId);
    }
}
