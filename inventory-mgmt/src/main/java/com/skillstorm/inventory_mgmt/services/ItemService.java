package com.skillstorm.inventory_mgmt.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_mgmt.models.Item;
import com.skillstorm.inventory_mgmt.repositories.ItemRepository;

import jakarta.transaction.Transactional;

@Service
public class ItemService {

    private ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public List<Item> findAll() {
        return repo.findAll();
    }

    public Optional<Item> findById(int id) {
        return repo.findById(id);
    }

    public Item save(Item item) {
        return repo.save(item);
    }

    @Transactional
    public int updateItemById(int id, Item item) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("Item with id " + id + " does not exist");
        item.setItemId(id);
        repo.save(item);
        return id;
    }

    @Transactional
    public int deleteById(int id) {
        repo.deleteById(id);
        return id;
    }
}
