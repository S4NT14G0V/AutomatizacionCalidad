package org.calidadsoftware.questions;

import net.serenitybdd.screenplay.Question;
import org.calidadsoftware.interfaces.ProductDetailPage;

public class ProductDetailsVisible {

    public static Question<Boolean> are() {
        return actor -> ProductDetailPage.PRODUCT_NAME.resolveFor(actor).isVisible();
    }
}
