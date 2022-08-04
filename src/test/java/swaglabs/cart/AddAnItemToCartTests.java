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
import swaglabs.pageobjects.ShoppingCartIcon;

import static org.assertj.core.api.Assertions.assertThat;
import static swaglabs.model.User.STANDARD_USER;

@ExtendWith(SerenityJUnit5Extension.class)
public class AddAnItemToCartTests {

    @Managed(driver = "chrome")
    public WebDriver driver;

    @Steps
    LoginActions login;

    @Steps
    CartActions cart;

    ShoppingCartIcon shoppingCartIcon;


    @BeforeEach
    public void login() {
        login.as(STANDARD_USER);
    }

    @Test
    public void theCorrectItemShouldBeShown() {
        // Check that shopping cart is empty
        Serenity.reportThat("The shopping cart badge should be empty",
                () -> assertThat(shoppingCartIcon.badgeCount()).isEmpty()
        );

        // Add 1 item to the cart
        cart.addItem("Sauce Labs Backpack");

        // The shopping cart badge should be "1"
        Serenity.reportThat("The shopping cart badge should be '1'",
                () -> assertThat(shoppingCartIcon.badgeCount()).isEqualTo("1")
        );
    }

}
