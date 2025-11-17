package org.calidadsoftware.stepdefinitions;

import io.cucumber.java.en.*;
import org.calidadsoftware.questions.CartItemsVisible;
import org.calidadsoftware.tasks.AddToCart;
import org.calidadsoftware.tasks.ViewCart;
import org.calidadsoftware.interactions.WaitFor;
import org.calidadsoftware.interfaces.CartPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class VerCarritoStepDefinitions {

    @Given("ha agregado productos al carrito")
    public void ha_agregado_productos() {
        CommonStepDefinitions.actor.attemptsTo(AddToCart.products(1));
    }

    @When("accede a la vista del carrito")
    public void accede_vista_carrito() {
        CommonStepDefinitions.actor.attemptsTo(ViewCart.now());
    }

    @Then("debe visualizar la lista de productos agregados")
    public void visualiza_lista_productos() {
        CommonStepDefinitions.actor.attemptsTo(
                WaitFor.visible(CartPage.CART_LIST, 5)
        );
        CommonStepDefinitions.actor.should(seeThat(CartItemsVisible.are(), is(true)));
    }
}
