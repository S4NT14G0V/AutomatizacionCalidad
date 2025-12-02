package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Interaction to click a button by its text content
 * Simulates complete mouse event sequence for React event handlers
 */
public class ClickButton implements Interaction {

    private final String buttonText;
    private final String containerSelector;

    public ClickButton(String buttonText, String containerSelector) {
        this.buttonText = buttonText;
        this.containerSelector = containerSelector;
    }

    public static ClickButton withText(String buttonText) {
        return Tasks.instrumented(ClickButton.class, buttonText, "");
    }

    public static ClickButton withTextInContainer(String buttonText, String containerSelector) {
        return Tasks.instrumented(ClickButton.class, buttonText, containerSelector);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        String containerPart = containerSelector.isEmpty() ? "document" : 
            "document.querySelector(\"" + containerSelector + "\")";
        
        js.executeScript(
            "var container = " + containerPart + ";" +
            "if (container) {" +
            "  var button = Array.from(container.querySelectorAll('button')).find(b => b.textContent.trim() === arguments[0]);" +
            "  if (button) {" +
            "    button.disabled = false;" +
            "    button.focus();" +
            "    button.scrollIntoView({ behavior: 'instant', block: 'center' });" +
            "    ['mouseenter', 'mouseover', 'mousedown', 'mouseup', 'click'].forEach(eventType => {" +
            "      button.dispatchEvent(new MouseEvent(eventType, {" +
            "        bubbles: true," +
            "        cancelable: true," +
            "        view: window" +
            "      }));" +
            "    });" +
            "    console.log('Button clicked:', arguments[0]);" +
            "  } else {" +
            "    console.error('Button not found:', arguments[0]);" +
            "  }" +
            "} else {" +
            "  console.error('Container not found:', arguments[1]);" +
            "}",
            buttonText, containerSelector);
    }
}
