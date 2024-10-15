package org.example.tp3;


import exception.ProductAlreadyExistsException;
import exception.ProductNotFoundException;
import model.Product;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import service.ProductService;
import service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class CliRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Create User");
            System.out.println("2. Display Products");
            System.out.println("3. Fetch Product by ID");
            System.out.println("4. Add New Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Update Product");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    fetchProductById(scanner);
                    break;
                case 4:
                    addNewProduct(scanner);
                    break;
                case 5:
                    deleteProduct(scanner);
                    break;
                case 6:
                    updateProduct(scanner);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createUser(Scanner scanner) {
        System.out.println("Enter user details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(password);

        User createdUser = userService.createUser(user);
        System.out.println("User created successfully with ID: " + createdUser.getId());
    }

    private void displayProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Products:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private void fetchProductById(Scanner scanner) {
        System.out.print("Enter product ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        try {
            Product product = productService.getProductById(id);
            System.out.println("Product found: " + product);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addNewProduct(Scanner scanner) {
        System.out.println("Enter product details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine(); // Consume newline
        System.out.print("Expiration date (YYYY-MM-DD): ");
        LocalDate expirationDate = LocalDate.parse(scanner.nextLine());

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setExpirationDate(expirationDate);

        try {
            Product createdProduct = productService.createProduct(product);
            System.out.println("Product created successfully with ID: " + createdProduct.getId());
        } catch (ProductAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProduct(Scanner scanner) {
        System.out.print("Enter product ID to delete: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        try {
            productService.deleteProduct(id);
            System.out.println("Product deleted successfully.");
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateProduct(Scanner scanner) {
        System.out.print("Enter product ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        try {
            Product existingProduct = productService.getProductById(id);

            System.out.println("Enter new details (press enter to keep current value):");
            System.out.print("Name (" + existingProduct.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                existingProduct.setName(name);
            }

            System.out.print("Price (" + existingProduct.getPrice() + "): ");
            String priceStr = scanner.nextLine();
            if (!priceStr.isEmpty()) {
                existingProduct.setPrice(new BigDecimal(priceStr));
            }

            System.out.print("Expiration date (" + existingProduct.getExpirationDate() + "): ");
            String dateStr = scanner.nextLine();
            if (!dateStr.isEmpty()) {
                existingProduct.setExpirationDate(LocalDate.parse(dateStr));
            }

            Product updatedProduct = productService.updateProduct(id, existingProduct);
            System.out.println("Product updated successfully: " + updatedProduct);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
