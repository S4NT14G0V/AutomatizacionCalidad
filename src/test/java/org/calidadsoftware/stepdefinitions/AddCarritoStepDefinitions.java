package org.calidadsoftware.stepdefinitions;

import io.cucumber.java.en.*;
import org.calidadsoftware.interfaces.InventoryPage;
import org.calidadsoftware.questions.CartItemCount;
import org.calidadsoftware.tasks.AddToCart;
import org.calidadsoftware.interactions.WaitFor;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class AddCarritoStepDefinitions {

    @When("agrega productos al carrito")
    public void agrega_productos_al_carrito() {
        CommonStepDefinitions.actor.attemptsTo(AddToCart.products(1));
    }

    @Then("el contador del carrito debe aumentar según la cantidad agregada")
    public void contador_aumenta() {
        CommonStepDefinitions.actor.attemptsTo(
                WaitFor.visible(InventoryPage.CART_BADGE, 5),
                WaitFor.containsText(InventoryPage.CART_BADGE, "1", 5)
        );
        CommonStepDefinitions.actor.should(seeThat(CartItemCount.value(), equalTo(1)));
    }
}
