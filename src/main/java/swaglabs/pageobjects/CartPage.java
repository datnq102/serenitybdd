package swaglabs.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import swaglabs.model.CartItem;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@DefaultUrl("https://www.saucedemo.com/cart.html")
public class CartPage extends PageObject {

    @FindBy(id = "#checkout")
    WebElementFacade checkoutButton;

    @FindBy(css = ".title")
    WebElementFacade title;

    // @FindBy(className = "cart_item")
    // List<WebElementFacade> cartItemElements;

    private static By CART_ITEMS = By.className("cart_item");

    public void checkout() {
        checkoutButton.click();
    }

    public String getTitleText() {
        return title.getText();
    }

    public List<CartItem> items() {
        // List<CartItem> cartItems = new ArrayList<>();
        // for (WebElementFacade cartItemElement : cartItemElements) {
        //     String name = cartItemElement.findBy(".inventory_item_name").getText();
        //     String description = cartItemElement.findBy(".inventory_item_desc").getText();
        //     Double price = priceFrom(cartItemElement.findBy(".inventory_item_price").getText());
        //     cartItems.add(new CartItem(name, description, price));
        // }
        // return cartItems;
        return $$(CART_ITEMS).map(
                item -> new CartItem(
                        item.findBy(".inventory_item_name").getText(),
                        item.findBy(".inventory_item_desc").getText(),
                        priceFrom(item.findBy(".inventory_item_price").getText())
                )
        );
    }

    private Double priceFrom(String priceString) {
        return Double.parseDouble(priceString.replace("$", ""));
    }
}
