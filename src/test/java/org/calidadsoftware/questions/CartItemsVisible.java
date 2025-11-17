package org.calidadsoftware.questions;

import net.serenitybdd.screenplay.Question;
import org.calidadsoftware.interfaces.CartPage;

public class CartItemsVisible {

    public static Question<Boolean> are() {
        return actor -> CartPage.CART_LIST.resolveFor(actor).isVisible();
    }
}
