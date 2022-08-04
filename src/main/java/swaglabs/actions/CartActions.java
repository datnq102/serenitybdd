package swaglabs.actions;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;
import swaglabs.pageobjects.ProductList;
import swaglabs.pageobjects.ShoppingCartIcon;

import java.util.List;

public class CartActions extends UIInteractions {

    @Step("Add item '{0}' to the cart")
    public void addItem(String productName) {
        $(ProductList.addToCartButtonFor(productName)).click();
    }

    @Step("Add items '{0}' to the cart")
    public void addItems(List<String> productNames) {
        productNames.forEach(this::addItem);
    }

    @Step("Open the shopping cart")
    public void openCart() {
        $(ShoppingCartIcon.link()).click();
    }

    public List<String> displayedItems() {
        return findAll(".inventory_item_name").texts();
    }
}
