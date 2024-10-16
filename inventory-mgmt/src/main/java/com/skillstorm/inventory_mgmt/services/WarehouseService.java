package com.skillstorm.inventory_mgmt.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_mgmt.models.Warehouse;
import com.skillstorm.inventory_mgmt.repositories.WarehouseRepository;

import jakarta.transaction.Transactional;

@Service
public class WarehouseService {
    
    private WarehouseRepository repo;

    public WarehouseService(WarehouseRepository repo) {
        this.repo = repo;
    }

    public List<Warehouse> findAll() {
        return repo.findAll();
    }

    public Optional<Warehouse> findById(int id) {
        return repo.findById(id);
    }

    public Warehouse save(Warehouse warehouse) {
        return repo.save(warehouse);
    }

    @Transactional
    public int update(int id, Warehouse warehouse) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("Warehouse with id " + id + " does not exist");
        warehouse.setWarehouseId(id);
        repo.save(warehouse);
        return id;
    }

    @Transactional
     public int updateCapacityById(int id, String operation, int value) {
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

            repo.save(warehouse);
            return id;
        } else {
            throw new RuntimeException("Warehouse with id: " + id + " not found.");
        }
    }

    @Transactional
    public int deleteById(int id) {
        repo.deleteById(id);
        return id;
    }
}
