package swaglabs.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import swaglabs.pageobjects.ProductList;

public class ViewProductDetailsActions extends UIInteractionSteps {

    @Step("View product details for product '{0}'")
    public void forProductWithName(String productName) {
        $(ProductList.productDetailsLinkFor(productName)).click();
    }

}
