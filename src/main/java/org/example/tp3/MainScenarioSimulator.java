package org.example.tp3;

import model.Product;
import model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import service.ProductService;
import service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class MainScenarioSimulator {
    private static final Logger logger = LoggerFactory.getLogger(MainScenarioSimulator.class);
    private static final Random random = new Random();
    private static UserService userService;
    private static ProductService productService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainScenarioSimulator.class, args);
        userService = context.getBean(UserService.class);
        productService = context.getBean(ProductService.class);

        logger.info("Simulating scenarios...\n");
        simulateScenarios(5, 20);
        logger.info("Scenarios simulation completed.\n");

    }

    private static void simulateScenarios(int userCount, int scenariosPerUser) {
        for (int i = 1; i <= userCount; i++) {
            String userId = "user" + i;
            logger.info("   Simulating scenarios for user: {}", userId);
            for (int j = 0; j < scenariosPerUser; j++) {
                executeRandomScenario(userId);
            }
        }
    }

    private static void executeRandomScenario(String userId) {
        int scenario = random.nextInt(6);
        switch (scenario) {
            case 0:
                createUserScenario(userId);
                break;
            case 1:
                displayProductsScenario(userId);
                break;
            case 2:
                fetchProductByIdScenario(userId);
                break;
            case 3:
                addNewProductScenario(userId);
                break;
            case 4:
                deleteProductScenario(userId);
                break;
            case 5:
                updateProductScenario(userId);
                break;
        }
    }

    private static void createUserScenario(String userId) {
        logger.info("User {} is creating a new user", userId);
        String name = "Name" + random.nextInt(100);
        int age = random.nextInt(80) + 18;
        String email = "email" + random.nextInt(10000) + "@example.com"; // Augmentation de la plage
        String password = "password" + random.nextInt(1000);
        User newUser = new User(null, name, age, email, password);
        try {
            User createdUser = userService.createUser(newUser);
            logger.info("New user created: {}", createdUser.getName());
        } catch (DataIntegrityViolationException e) {
            logger.warn("Failed to create user with email {}. Email already exists.", email);
            // Vous pouvez choisir de réessayer avec un nouvel email ici si nécessaire
        }
    }


    private static void displayProductsScenario(String userId) {
        logger.info("User {} is displaying all products", userId);
        productService.getAllProducts().forEach(product ->
                logger.info("Product: {}, Price: {}", product.getName(), product.getPrice()));
    }

    private static void fetchProductByIdScenario(String userId) {
        long productId = random.nextLong(100) + 1;
        logger.info("User {} is fetching product with ID: {}", userId, productId);
        try {
            Product product = productService.getProductById(productId);
            logger.info("Fetched product: {}", product.getName());
        } catch (Exception e) {
            logger.info("Product with ID {} not found", productId);
        }
    }

    private static void addNewProductScenario(String userId) {
        logger.info("User {} is adding a new product", userId);
        String name = "Product" + random.nextInt(1000);
        BigDecimal price = BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
        LocalDate expirationDate = LocalDate.now().plusDays(random.nextInt(365));
        Product newProduct = new Product(null, name, price, expirationDate);
        productService.createProduct(newProduct);
        logger.info("New product added: {}", newProduct.getName());
    }

    private static void deleteProductScenario(String userId) {
        long productId = random.nextLong(100) + 1;
        logger.info("User {} is deleting product with ID: {}", userId, productId);
        try {
            productService.deleteProduct(productId);
            logger.info("Product with ID {} deleted", productId);
        } catch (Exception e) {
            logger.info("Product with ID {} not found or could not be deleted", productId);
        }
    }

    private static void updateProductScenario(String userId) {
        long productId = random.nextLong(100) + 1;
        logger.info("User {} is updating product with ID: {}", userId, productId);
        try {
            Product existingProduct = productService.getProductById(productId);
            String updatedName = "UpdatedProduct" + random.nextInt(1000);
            BigDecimal updatedPrice = BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP);
            LocalDate updatedExpirationDate = LocalDate.now().plusDays(random.nextInt(365));

            existingProduct.setName(updatedName);
            existingProduct.setPrice(updatedPrice);
            existingProduct.setExpirationDate(updatedExpirationDate);

            productService.updateProduct(productId, existingProduct);
            logger.info("Product updated: {}", existingProduct.getName());
        } catch (Exception e) {
            logger.info("Product with ID {} not found or could not be updated", productId);
        }
    }
}
