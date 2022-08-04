package swaglabs.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;

public class ProductList extends PageObject {

    public String getHeading() {
        return $(".title").getText();
    }

    public List<String> titles() {
        return findAll(".inventory_item_name").texts();
    }

    public static By productDetailsLinkFor(String productName) {
        return By.linkText(productName);
    }

    public String imageTextForProduct(String productName) {
        return $(String.format("//div[@class='inventory_item'][contains(.,'%s')]//img", productName)).getAttribute("Alt");
    }

    public static By addToCartButtonFor(String productName) {
        return By.xpath(String.format("//div[@class='inventory_item'][contains(.,'%s')]//button", productName));
    }
}
