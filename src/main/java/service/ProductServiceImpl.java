package service;


import exception.ProductAlreadyExistsException;
import exception.ProductNotFoundException;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        if (product.getId() != null && productRepository.existsById(product.getId())) {
            throw new ProductAlreadyExistsException("Product already exists with id: " + product.getId());
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id); // This will throw ProductNotFoundException if not found
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setExpirationDate(productDetails.getExpirationDate());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id); // This will throw ProductNotFoundException if not found
        productRepository.delete(product);
    }
}
