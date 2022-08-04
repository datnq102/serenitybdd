package swaglabs.authentication;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import swaglabs.actions.LoginActions;
import swaglabs.pageobjects.ProductList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static swaglabs.model.User.STANDARD_USER;

@RunWith(SerenityRunner.class)
public class LoginTests {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    LoginActions login;

    ProductList productList;

    @Test
    public void userCanLoginViaTheHomePage() {
        login.as(STANDARD_USER);
        Serenity.reportThat("The inventory page should be displayed with a correct title",
                () -> assertThat(productList.getHeading()).isEqualTo("PRODUCTS")
        );
    }

}
