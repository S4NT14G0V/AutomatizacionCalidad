package org.calidadsoftware.tasks;

import java.util.Map;

import org.calidadsoftware.interactions.ClickOn;
import org.calidadsoftware.interactions.EnterText;
import org.calidadsoftware.interfaces.MedicalDashboardPage;
import org.calidadsoftware.utils.WaitFor;
import org.openqa.selenium.JavascriptExecutor;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Clear;

public class ModifyOffice implements Task {

    private final String officeName;
    private final Map<String, String> updatedData;

    public ModifyOffice(String officeName, Map<String, String> updatedData) {
        this.officeName = officeName;
        this.updatedData = updatedData;
    }

    public static ModifyOffice named(String officeName, Map<String, String> updatedData) {
        return Tasks.instrumented(ModifyOffice.class, officeName, updatedData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        actor.attemptsTo(WaitFor.sleep(3)); // Wait for the office list to update after creation

        // Scroll to bottom first
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        actor.attemptsTo(WaitFor.sleep(2));

        // Find the button and click it with full event simulation
        js.executeScript(
                "var h3 = Array.from(document.querySelectorAll('h3')).find(h => h.textContent.includes('Consultorio " + officeName + "'));" +
                "if (h3) {" +
                "  var buttonContainer = h3.closest('.flex.items-start');" +
                "  if (buttonContainer) {" +
                "    var modifyButton = Array.from(buttonContainer.querySelectorAll('button')).find(b => b.textContent.includes('Modificar'));" +
                "    if (modifyButton) {" +
                "      modifyButton.scrollIntoView({behavior: 'instant', block: 'center'});" +
                "      var events = ['mouseenter', 'mouseover', 'mousedown', 'mouseup', 'click'];" +
                "      events.forEach(eventType => {" +
                "        var event = new MouseEvent(eventType, {" +
                "          bubbles: true," +
                "          cancelable: true," +
                "          view: window" +
                "        });" +
                "        modifyButton.dispatchEvent(event);" +
                "      });" +
                "    }" +
                "  }" +
                "}");

        actor.attemptsTo(WaitFor.sleep(2)); // Wait for modal to open
        actor.attemptsTo(WaitFor.visible(MedicalDashboardPage.OFFICE_NAME_INPUT, 10));

        // Update fields based on provided data
        if (updatedData.containsKey("nombre")) {
            String newNumber = updatedData.get("nombre");
            // Use JavaScript to set the value since React controls it
            js.executeScript(
                "var input = document.querySelector('#number, #name');" +
                "if (input) {" +
                "  var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                "  nativeInputValueSetter.call(input, arguments[0]);" +
                "  var event = new Event('input', { bubbles: true });" +
                "  input.dispatchEvent(event);" +
                "}", newNumber);
            actor.attemptsTo(WaitFor.sleep(1));
        }

        if (updatedData.containsKey("especialidad")) {
            String especialidad = updatedData.get("especialidad");
            js.executeScript(
                    "var dialog = document.querySelector('[role=\"dialog\"]');" +
                    "if (dialog) {" +
                    "  var typeButton = dialog.querySelector('#type');" +
                    "  if (typeButton) {" +
                    "    var select = typeButton.previousElementSibling;" +
                    "    if (select && select.tagName === 'SELECT') {" +
                    "      for(var i=0; i<select.options.length; i++) {" +
                    "        if(select.options[i].value === arguments[0]) {" +
                    "          select.selectedIndex = i;" +
                    "          select.dispatchEvent(new Event('change', { bubbles: true }));" +
                    "          break;" +
                    "        }" +
                    "      }" +
                    "    }" +
                    "  }" +
                    "}",
                    especialidad);
            actor.attemptsTo(WaitFor.sleep(1));
        }

        if (updatedData.containsKey("sede")) {
            String sede = updatedData.get("sede");
            js.executeScript(
                    "var dialog = document.querySelector('[role=\"dialog\"]');" +
                    "if (dialog) {" +
                    "  var locationButton = dialog.querySelector('#location');" +
                    "  if (locationButton) {" +
                    "    var select = locationButton.previousElementSibling;" +
                    "    if (select && select.tagName === 'SELECT') {" +
                    "      for(var i=0; i<select.options.length; i++) {" +
                    "        if(select.options[i].value === arguments[0]) {" +
                    "          select.selectedIndex = i;" +
                    "          select.dispatchEvent(new Event('change', { bubbles: true }));" +
                    "          break;" +
                    "        }" +
                    "      }" +
                    "    }" +
                    "  }" +
                    "}",
                    sede);
            actor.attemptsTo(WaitFor.sleep(1));
        }

        // Click Guardar button simulating real user interaction
        actor.attemptsTo(WaitFor.sleep(1));
        js.executeScript(
            "var dialog = document.querySelector('[role=\"dialog\"]');" +
            "if (dialog) {" +
            "  var saveButton = Array.from(dialog.querySelectorAll('button')).find(b => b.textContent.includes('Guardar'));" +
            "  if (saveButton) {" +
            "    saveButton.focus();" +
            "    saveButton.dispatchEvent(new MouseEvent('mouseenter', { bubbles: true, cancelable: true }));" +
            "    saveButton.dispatchEvent(new MouseEvent('mouseover', { bubbles: true, cancelable: true }));" +
            "    saveButton.dispatchEvent(new MouseEvent('mousedown', { bubbles: true, cancelable: true }));" +
            "    saveButton.dispatchEvent(new MouseEvent('mouseup', { bubbles: true, cancelable: true }));" +
            "    saveButton.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true }));" +
            "    saveButton.dispatchEvent(new KeyboardEvent('keydown', { key: 'Enter', code: 'Enter', keyCode: 13, bubbles: true }));" +
            "    saveButton.dispatchEvent(new KeyboardEvent('keypress', { key: 'Enter', code: 'Enter', keyCode: 13, bubbles: true }));" +
            "    saveButton.dispatchEvent(new KeyboardEvent('keyup', { key: 'Enter', code: 'Enter', keyCode: 13, bubbles: true }));" +
            "  }" +
            "}");
        actor.attemptsTo(WaitFor.sleep(2));
    }
}
