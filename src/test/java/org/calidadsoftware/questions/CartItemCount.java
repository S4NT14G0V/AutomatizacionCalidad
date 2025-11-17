package org.calidadsoftware.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import org.calidadsoftware.interfaces.InventoryPage;

public class CartItemCount {

    public static Question<Integer> value() {
        return actor -> {
            String badgeText = Text.of(InventoryPage.CART_BADGE).answeredBy(actor);
            return badgeText.isEmpty() ? 0 : Integer.parseInt(badgeText);
        };
    }
}
