package org.calidadsoftware.questions;

import net.serenitybdd.screenplay.Question;
import org.calidadsoftware.interfaces.InventoryPage;

public class ProductListVisible {

    public static Question<Boolean> is() {
        return actor -> InventoryPage.PRODUCT_LIST.resolveFor(actor).isVisible();
    }
}
