package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Interaction to scroll to an element on the page
 */
public class ScrollToElement implements Interaction {

    private final String elementSelector;
    private final ScrollPosition position;

    public enum ScrollPosition {
        TOP, CENTER, BOTTOM
    }

    public ScrollToElement(String elementSelector, ScrollPosition position) {
        this.elementSelector = elementSelector;
        this.position = position;
    }

    public static ScrollToElement located(String elementSelector) {
        return Tasks.instrumented(ScrollToElement.class, elementSelector, ScrollPosition.CENTER);
    }

    public static ScrollToElement locatedAtTop(String elementSelector) {
        return Tasks.instrumented(ScrollToElement.class, elementSelector, ScrollPosition.TOP);
    }

    public static ScrollToElement locatedAtBottom(String elementSelector) {
        return Tasks.instrumented(ScrollToElement.class, elementSelector, ScrollPosition.BOTTOM);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        String block = position == ScrollPosition.TOP ? "start" : 
                      position == ScrollPosition.BOTTOM ? "end" : "center";
        
        js.executeScript(
            "var element = document.querySelector(arguments[0]);" +
            "if (element) {" +
            "  element.scrollIntoView({ behavior: 'smooth', block: '" + block + "' });" +
            "}",
            elementSelector);
    }
}
