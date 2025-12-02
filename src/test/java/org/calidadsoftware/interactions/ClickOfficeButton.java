package org.calidadsoftware.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

/**
 * Interaction to click a button associated with a specific office
 * Uses data-testid for reliable element location
 */
public class ClickOfficeButton implements Interaction {

    private final String officeName;
    private final ButtonType buttonType;

    public enum ButtonType {
        MODIFY, DELETE
    }

    public ClickOfficeButton(String officeName, ButtonType buttonType) {
        this.officeName = officeName;
        this.buttonType = buttonType;
    }

    public static ClickOfficeButton toModify(String officeName) {
        return Tasks.instrumented(ClickOfficeButton.class, officeName, ButtonType.MODIFY);
    }

    public static ClickOfficeButton toDelete(String officeName) {
        return Tasks.instrumented(ClickOfficeButton.class, officeName, ButtonType.DELETE);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Wait for office list to be present
        actor.attemptsTo(
                WaitUntil.the(MedicalDashboardPage.OFFICE_ITEMS, isPresent())
                        .forNoMoreThan(Duration.ofSeconds(10))
        );

        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        // Find the office by its visible name text and click the appropriate button
        // Frontend displays office names as "Consultorio {officeNumber}"
        // We search for this exact text and then find the correct button
        String fullOfficeName = "Consultorio " + officeName;
        boolean isModify = buttonType == ButtonType.MODIFY;
        
        Boolean clicked = (Boolean) js.executeScript(
            "var fullOfficeName = arguments[0];" +
            "var isModify = arguments[1];" +
            "var allCards = document.querySelectorAll('[data-testid^=\"office-item-\"]');" +
            "for (var i = 0; i < allCards.length; i++) {" +
            "  var card = allCards[i];" +
            "  var heading = card.querySelector('h3');" +
            "  if (heading && heading.textContent.trim() === fullOfficeName) {" +
            "    var buttons = card.querySelectorAll('button');" +
            "    for (var j = 0; j < buttons.length; j++) {" +
            "      var button = buttons[j];" +
            "      if (isModify) {" +
            "        if (button.textContent && button.textContent.includes('Modificar')) {" +
            "          button.scrollIntoView({ behavior: 'instant', block: 'center' });" +
            "          button.click();" +
            "          console.log('Clicked Modify button for office:', fullOfficeName);" +
            "          return true;" +
            "        }" +
            "      } else {" +
            "        if (button.querySelector('svg')) {" +
            "          button.scrollIntoView({ behavior: 'instant', block: 'center' });" +
            "          button.click();" +
            "          console.log('Clicked Delete button for office:', fullOfficeName);" +
            "          return true;" +
            "        }" +
            "      }" +
            "    }" +
            "  }" +
            "}",
            fullOfficeName,
            isModify
        );

        if (!Boolean.TRUE.equals(clicked)) {
            throw new RuntimeException("Failed to click " + buttonType + " button for office: " + officeName);
        }

        // Small wait for UI to respond
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
