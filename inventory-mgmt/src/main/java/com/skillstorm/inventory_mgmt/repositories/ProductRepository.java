package com.skillstorm.inventory_mgmt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skillstorm.inventory_mgmt.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query(value = "select p from Product p ORDER BY price", nativeQuery = false)
    List<Product> getSortedProducts();
}
