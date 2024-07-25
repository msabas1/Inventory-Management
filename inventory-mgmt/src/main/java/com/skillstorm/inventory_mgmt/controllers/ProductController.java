package com.skillstorm.inventory_mgmt.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_mgmt.models.Product;
import com.skillstorm.inventory_mgmt.repositories.ProductRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository repo;

    public ProductController(ProductRepository repo){
        this.repo = repo;
    }
    
    @GetMapping()
    public Iterable<Product> findAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable int id){
        Optional<Product> product = repo.findById(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return repo.save(product);
    }

    @PutMapping("/{id}")
    public Product updateById(@PathVariable int id, @RequestBody Product updatedProduct) {
        return repo.findById(id)
        .map(product -> {
            product.setProductName(updatedProduct.getProductName());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            return repo.save(product);
        })
        .orElseGet(() -> {
            return repo.save(updatedProduct);
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        repo.deleteById(id);
    }
}
