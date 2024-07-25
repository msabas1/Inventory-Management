package com.skillstorm.inventory_mgmt.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_mgmt.models.Product;
import com.skillstorm.inventory_mgmt.repositories.ProductRepository;

@Service
public class ProductService {

    private ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Iterable<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(int id) {
        return repo.findById(id);
    }

    public Product save(Product movie) {
        return repo.save(movie);
    }

    public void update(int id, Product product) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("Product with id " + id + " does not exist.");
            product.setProductId(id);
        repo.save(product);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
