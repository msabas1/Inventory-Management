package com.skillstorm.inventory_mgmt.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_mgmt.models.Warehouse;
import com.skillstorm.inventory_mgmt.repositories.WarehouseRepository;

@Service
public class WarehouseService {
    
    private WarehouseRepository repo;

    public WarehouseService(WarehouseRepository repo) {
        this.repo = repo;
    }

    public Iterable<Warehouse> findAll() {
        return repo.findAll();
    }

    public Optional<Warehouse> findById(int id) {
        return repo.findById(id);
    }

    public Warehouse save(Warehouse warehouse) {
        return repo.save(warehouse);
    }

    public Warehouse update(int id, Warehouse warehouse) {
        warehouse.setWarehouseId(id);
        return repo.save(warehouse);
    }

     public Warehouse updateCapacityById(int id, String operation, int value) {
        Optional<Warehouse> optionalWarehouse = repo.findById(id);

        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();
            if (operation.equals("add")) {
                warehouse.setCapacity(warehouse.getCapacity() + value);
            } else if (operation.equals("subtract")) {
                warehouse.setCapacity(warehouse.getCapacity() - value);
            } else {
                throw new IllegalArgumentException("Invalid capacity update operation: " + operation);
            }

            return repo.save(warehouse);
        } else {
            throw new RuntimeException("Warehouse with id: " + id + " not found.");
        }
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
