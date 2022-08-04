package swaglabs.cart;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import swaglabs.actions.CartActions;
import swaglabs.actions.LoginActions;
import swaglabs.model.CartItem;
import swaglabs.pageobjects.CartPage;
import swaglabs.pageobjects.ProductList;
import swaglabs.pageobjects.ShoppingCartIcon;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static swaglabs.model.User.STANDARD_USER;

@ExtendWith(SerenityJUnit5Extension.class)
public class AddMultipleItemsToCartTests {

    @Managed(driver = "chrome")
    public WebDriver driver;

    @Steps
    LoginActions login;

    @Steps
    CartActions cart;

    ProductList productList;
    ShoppingCartIcon shoppingCartIcon;


    @BeforeEach
    public void login() {
        login.as(STANDARD_USER);
    }

    @Test
    public void allItemsShouldAppearInTheCart() {
        // Add several items to the cart
        List<String> selectedItems = firstThreeProductTitlesDisplayed();
        cart.addItems(selectedItems);

        // Should see correct number if items in the cart
        String cartBadgeCount = Integer.toString(selectedItems.size());
        Serenity.reportThat("The shopping cart badge should new be " + cartBadgeCount,
                () -> assertThat(shoppingCartIcon.badgeCount()).isEqualTo(cartBadgeCount)
        );

        // Open cart page
        cart.openCart();

        // Should see all the added items
        Serenity.reportThat("Should see all selected items",
                () -> assertThat(cart.displayedItems()).containsExactlyElementsOf(selectedItems)
        );
    }

    CartPage cartPage;

    @Test
    public void pricesForEachItemShouldBeShownInTheCart() {
        // add items to the shopping cart
        cart.addItems(firstThreeProductTitlesDisplayed());

        // Open cart page
        cartPage.open();

        // Check that each item in the cart has a price
        List<CartItem> items = cartPage.items();
        assertThat(items).hasSize(3).allMatch(item -> item.getPrice() > 0.0);

    }

    private List<String> firstThreeProductTitlesDisplayed() {
        return productList.titles().subList(0, 3);
    }
}
