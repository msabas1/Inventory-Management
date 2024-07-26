package com.skillstorm.inventory_mgmt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skillstorm.inventory_mgmt.models.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
    @Query(value = "select i from Inventory i ORDER BY price", nativeQuery = false)
    List<Inventory> getSortedInventory();
}
