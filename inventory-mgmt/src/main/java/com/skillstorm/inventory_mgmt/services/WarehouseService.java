package com.skillstorm.inventory_mgmt.services;

import java.util.NoSuchElementException;
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

    public Warehouse save(Warehouse movie) {
        return repo.save(movie);
    }

    public void update(int id, Warehouse warehouse) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("Warehouse with id " + id + " does not exist.");
            warehouse.setWarehouseId(id);
        repo.save(warehouse);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
