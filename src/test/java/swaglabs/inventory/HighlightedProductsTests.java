package swaglabs.inventory;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import swaglabs.actions.LoginActions;
import swaglabs.actions.ViewProductDetailsActions;
import swaglabs.pageobjects.ProductDetails;
import swaglabs.pageobjects.ProductList;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static swaglabs.model.User.STANDARD_USER;

@ExtendWith(SerenityJUnit5Extension.class)
public class HighlightedProductsTests {

    @Managed(driver = "chrome")
    public WebDriver driver;

    @Steps
    LoginActions login;

    @Steps
    ViewProductDetailsActions viewProductDetails;

    ProductList productList;
    ProductDetails productDetails;

    @Test
    public void shouldDisplayHighlightedProductsOnTheWelcomePage() {
        login.as(STANDARD_USER);
        Serenity.reportThat("Should display highlighted products",
                () -> assertThat(productList.titles()).hasSize(6).contains("Sauce Labs Backpack")
        );
    }

    @Test
    public void shouldDisplayCorrectProductDetailsPage() {
        login.as(STANDARD_USER);

        String firstItemName = productList.titles().get(0);
        viewProductDetails.forProductWithName(firstItemName);

        Serenity.reportThat("The product should be correctly displayed", () -> {
            assertThat(productDetails.productName()).isEqualTo(firstItemName);
            productDetails.productImageWithAlt(firstItemName).shouldBeVisible();
        });
    }

    @Test
    public void highlightedProductsShouldDisplayTheCorrespondingImages() {
        login.as(STANDARD_USER);
        List<String> productsOnDisplay = productList.titles();

        SoftAssertions softly = new SoftAssertions();
        productsOnDisplay.forEach(
                productName -> softly.assertThat(productList.imageTextForProduct(productName)).isEqualTo(productName)
        );
        softly.assertAll();
    }
}
