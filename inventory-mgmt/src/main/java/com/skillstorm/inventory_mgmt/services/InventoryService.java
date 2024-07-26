package com.skillstorm.inventory_mgmt.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_mgmt.models.Inventory;
import com.skillstorm.inventory_mgmt.repositories.InventoryRepository;

@Service
public class InventoryService {

    private InventoryRepository repo;

    public InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }

    public Iterable<Inventory> findAll() {
        return repo.findAll();
    }

    public Optional<Inventory> findById(int id) {
        return repo.findById(id);
    }

    public Inventory save(Inventory item) {
        return repo.save(item);
    }

    public void update(int id, Inventory item) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("Inventory item with id " + id + " does not exist.");
            item.setInventoryId(id);
        repo.save(item);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
