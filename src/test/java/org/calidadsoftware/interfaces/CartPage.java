package org.calidadsoftware.interfaces;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class CartPage extends PageObject {

    public static final Target CART_LIST = Target.the("lista del carrito")
            .located(By.className("cart_list"));

    public static final Target CART_ITEM = Target.the("ítem del carrito")
            .located(By.className("cart_item"));

    public static final Target REMOVE_BUTTON = Target.the("botón remover")
            .located(By.cssSelector(".btn_secondary"));

    public static final Target CHECKOUT_BUTTON = Target.the("botón checkout")
            .located(By.id("checkout"));

    public static final Target CART_BADGE = Target.the("contador del carrito")
            .located(By.className("shopping_cart_badge"));
}
