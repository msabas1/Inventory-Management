package com.skillstorm.inventory_mgmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.inventory_mgmt.models.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{
    
}
