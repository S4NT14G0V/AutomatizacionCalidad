// Task to delete a medical office
package org.calidadsoftware.tasks;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.utils.WaitFor;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

public class DeleteOffice implements Task {

    private final String officeName;

    public DeleteOffice(String officeName) {
        this.officeName = officeName;
    }

    public static DeleteOffice named(String officeName) {
        return Tasks.instrumented(DeleteOffice.class, officeName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        actor.attemptsTo(WaitFor.sleep(3)); // Wait for the office list to update

        // Scroll to bottom first
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        actor.attemptsTo(WaitFor.sleep(2));

        // Find the delete button and click it with full event simulation
        js.executeScript(
                "var h3 = Array.from(document.querySelectorAll('h3')).find(h => h.textContent.includes('Consultorio " + officeName + "'));" +
                "if (h3) {" +
                "  var buttonContainer = h3.closest('.flex.items-start');" +
                "  if (buttonContainer) {" +
                "    var deleteButton = Array.from(buttonContainer.querySelectorAll('button')).find(b => b.querySelector('svg.lucide-trash2'));" +
                "    if (deleteButton) {" +
                "      deleteButton.scrollIntoView({behavior: 'instant', block: 'center'});" +
                "      var events = ['mouseenter', 'mouseover', 'mousedown', 'mouseup', 'click'];" +
                "      events.forEach(eventType => {" +
                "        var event = new MouseEvent(eventType, {" +
                "          bubbles: true," +
                "          cancelable: true," +
                "          view: window" +
                "        });" +
                "        deleteButton.dispatchEvent(event);" +
                "      });" +
                "    }" +
                "  }" +
                "}");

        actor.attemptsTo(WaitFor.sleep(2)); // Wait for confirmation dialog to appear
        
        // Click Eliminar button simulating real user interaction
        js.executeScript(
            "var alertDialog = document.querySelector('[role=\"alertdialog\"]');" +
            "if (alertDialog) {" +
            "  var deleteButton = Array.from(alertDialog.querySelectorAll('button')).find(b => b.textContent.trim() === 'Eliminar');" +
            "  if (deleteButton) {" +
            "    deleteButton.focus();" +
            "    deleteButton.dispatchEvent(new MouseEvent('mouseenter', { bubbles: true, cancelable: true }));" +
            "    deleteButton.dispatchEvent(new MouseEvent('mouseover', { bubbles: true, cancelable: true }));" +
            "    deleteButton.dispatchEvent(new MouseEvent('mousedown', { bubbles: true, cancelable: true }));" +
            "    deleteButton.dispatchEvent(new MouseEvent('mouseup', { bubbles: true, cancelable: true }));" +
            "    deleteButton.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true }));" +
            "    deleteButton.dispatchEvent(new KeyboardEvent('keydown', { key: 'Enter', code: 'Enter', keyCode: 13, bubbles: true }));" +
            "    deleteButton.dispatchEvent(new KeyboardEvent('keypress', { key: 'Enter', code: 'Enter', keyCode: 13, bubbles: true }));" +
            "    deleteButton.dispatchEvent(new KeyboardEvent('keyup', { key: 'Enter', code: 'Enter', keyCode: 13, bubbles: true }));" +
            "  }" +
            "}");
        
        actor.attemptsTo(WaitFor.sleep(2)); // Wait for deletion to complete
    }
}
