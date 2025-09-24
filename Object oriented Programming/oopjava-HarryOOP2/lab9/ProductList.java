// ProductList.java
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;  // Import for UTF-8 charset
import java.util.*;
import java.util.stream.*;

public class ProductList {
    private List<Product> products = new ArrayList<>();

    public ProductList(String filename) {
        try (Stream<String> lines = Files.lines(Path.of(filename), StandardCharsets.UTF_8)) {
            products = lines.skip(1)  // Skip the header
                    .map(line -> {
                        String[] fields = line.split(",");
                        try {
                            int id = fields[0].isEmpty() ? -1 : Integer.parseInt(fields[0]);
                            String name = fields[1];
                            String category = fields[2];
                            String subcat = fields[3];
                            double price = Double.parseDouble(fields[4]);
                            if (price < 0) throw new IllegalArgumentException("Invalid price");
                            return new Product(id, name, category, subcat, price);
                        } catch (Exception e) {
                            System.err.println("Error processing product with line: " + line);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}
