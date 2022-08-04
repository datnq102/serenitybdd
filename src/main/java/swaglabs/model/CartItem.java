package swaglabs.model;

// public record CartItem(String title, String description, Double price) {}
public class CartItem {

    String title;
    String description;
    Double price;

    public CartItem(String title, String description, Double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
