// Main.java
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        // Create ProductList instance with file
        ProductList productList = new ProductList("lab9_products.csv");

        // Check if products were loaded correctly
        System.out.println("Loaded products: " + productList.getProducts());

        // Convert prices from Euro to USD (Assuming exchange rate: 1 EUR = 1.1 USD)
        List<Product> convertedProducts = productList.getProducts().stream()
                .map(p -> new Product(p.id(), p.name(), p.category(), p.subcat(), p.price() * 1.1))
                .collect(Collectors.toList());

        // Check if products were correctly converted
        System.out.println("Converted products: " + convertedProducts);

        // Write the products with updated prices into a new CSV file
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("updated_products.csv"))) {
            writer.write("id,name,category,subcat,price\n");
            for (Product p : convertedProducts) {
                writer.write(String.format("%d,%s,%s,%s,%.2f\n", p.id(), p.name(), p.category(), p.subcat(), p.price()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calculate average prices by category
        Map<String, Double> avgPricesByCategory = convertedProducts.stream()
                .collect(Collectors.groupingBy(Product::category,
                        Collectors.averagingDouble(Product::price)));

        // Sort categories by average price, highest to lowest
        avgPricesByCategory.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
