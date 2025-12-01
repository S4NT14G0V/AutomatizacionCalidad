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

public class CreateOffice implements Task {

    private final Map<String, String> officeData;

    public CreateOffice(Map<String, String> officeData) {
        this.officeData = officeData;
    }

    public static CreateOffice withData(Map<String, String> officeData) {
        return Tasks.instrumented(CreateOffice.class, officeData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitFor.visible(MedicalDashboardPage.OFFICE_NAME_INPUT, 10),
                EnterText.valueInto(officeData.get("nombre"), MedicalDashboardPage.OFFICE_NAME_INPUT),
                WaitFor.sleep(1)
        );
        
        // Usar JavaScript para seleccionar los valores en los hidden selects
        String especialidad = officeData.get("especialidad");
        String sede = officeData.get("sede");
        String estado = officeData.get("estado");
        
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        
        // Ejecutar JavaScript para seleccionar especialidad
        js.executeScript(
            "var selects = document.querySelectorAll('select[aria-hidden=\"true\"]');" +
            "for(var i=0; i<selects.length; i++) {" +
            "  var options = selects[i].options;" +
            "  for(var j=0; j<options.length; j++) {" +
            "    if(options[j].value === arguments[0]) {" +
            "      selects[i].selectedIndex = j;" +
            "      selects[i].dispatchEvent(new Event('change', { bubbles: true }));" +
            "      break;" +
            "    }" +
            "  }" +
            "}", especialidad);
        
        actor.attemptsTo(WaitFor.sleep(1));
        
        // Ejecutar JavaScript para seleccionar sede
        js.executeScript(
            "var selects = document.querySelectorAll('select[aria-hidden=\"true\"]');" +
            "for(var i=0; i<selects.length; i++) {" +
            "  var options = selects[i].options;" +
            "  for(var j=0; j<options.length; j++) {" +
            "    if(options[j].value === arguments[0]) {" +
            "      selects[i].selectedIndex = j;" +
            "      selects[i].dispatchEvent(new Event('change', { bubbles: true }));" +
            "      break;" +
            "    }" +
            "  }" +
            "}", sede);
        
        actor.attemptsTo(WaitFor.sleep(1));
        
        // Ejecutar JavaScript para seleccionar estado
        js.executeScript(
            "var selects = document.querySelectorAll('select[aria-hidden=\"true\"]');" +
            "for(var i=0; i<selects.length; i++) {" +
            "  var options = selects[i].options;" +
            "  for(var j=0; j<options.length; j++) {" +
            "    if(options[j].value === arguments[0]) {" +
            "      selects[i].selectedIndex = j;" +
            "      selects[i].dispatchEvent(new Event('change', { bubbles: true }));" +
            "      break;" +
            "    }" +
            "  }" +
            "}", estado);
        
        actor.attemptsTo(
                WaitFor.sleep(2),
                ClickOn.target(MedicalDashboardPage.SAVE_OFFICE_BUTTON),
                WaitFor.sleep(3)
        );
    }
}
