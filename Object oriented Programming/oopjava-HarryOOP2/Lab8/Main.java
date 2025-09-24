import java.util.*;
import java.util.function.Function;
import java.util.regex.*;

// Abstract class Product
abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    
    public abstract String getDescription();
}


class Book extends Product {
    private String author;

    public Book(String name, double price, String author) {
        super(name, price);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getDescription() {
        return "Book: " + name + " by " + author + ", Price: $" + price;
    }
}


class Electronic extends Product {
    private String brand;

    public Electronic(String name, double price, String brand) {
        super(name, price);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String getDescription() {
        return "Electronic: " + name + " by " + brand + ", Price: $" + price;
    }
}


class Catalog {
    private List<Product> products = new ArrayList<>();

    // Add a product to the catalog
    public void addProduct(Product p) {
        products.add(p);
    }

    
    public void addProductsFromFile(String filePath) {
        addProduct(new Book("The Catcher in the Rye", 10.99, "J.D. Salinger"));
        addProduct(new Electronic("iPhone", 999.99, "Apple"));
    }

    
    public List<Product> selectProducts(String regex) {
        List<Product> selectedProducts = new ArrayList<>();
        for (Product p : products) {
            if (Pattern.matches(regex, p.getName())) {
                selectedProducts.add(p);
            }
        }
        return selectedProducts;
    }

    
    public void transformProducts(Function<Product, Product> transformer) {
        for (int i = 0; i < products.size(); i++) {
            products.set(i, transformer.apply(products.get(i)));
        }
    }

    
    public void printProducts() {
        for (Product p : products) {
            System.out.println(p.getDescription());
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();

        
        catalog.addProduct(new Book("1984", 12.99, "George Orwell"));
        catalog.addProduct(new Electronic("Samsung TV", 499.99, "Samsung"));
        catalog.addProduct(new Book("Brave New World", 8.99, "Aldous Huxley"));

        
        System.out.println("All Products:");
        catalog.printProducts();

        
        System.out.println("\nSelected Products (Names containing '1984'):");
        List<Product> selectedProducts = catalog.selectProducts(".*1984.*");
        for (Product p : selectedProducts) {
            System.out.println(p.getDescription());
        }

        
        System.out.println("\nTransformed Products (Price increased by 10%):");
        catalog.transformProducts(p -> {
            double newPrice = p.getPrice() * 1.10; 
            if (p instanceof Book) {
                return new Book(p.getName(), newPrice, ((Book) p).getAuthor());
            } else if (p instanceof Electronic) {
                return new Electronic(p.getName(), newPrice, ((Electronic) p).getBrand());
            } else {
                return p;
            }
        });

    
        catalog.printProducts();
    }
}
