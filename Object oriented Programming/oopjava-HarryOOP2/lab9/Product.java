// Product.java
public record Product(int id, String name, String category, String subcat, double price) {
    public Product {
        if (price < 0) {
            throw new IllegalArgumentException("Invalid price for product ID " + id);
        }
    }
}
