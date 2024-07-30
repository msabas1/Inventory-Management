package com.skillstorm.inventory_mgmt.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_mgmt.models.Item;
import com.skillstorm.inventory_mgmt.repositories.ItemRepository;

@Service
public class ItemService {

    private ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public Iterable<Item> findAll() {
        return repo.findAll();
    }

    public Optional<Item> findById(int id) {
        return repo.findById(id);
    }

    public Item save(Item item) {
        return repo.save(item);
    }

    public Item updateItemById(int id, Item item) {
        item.setItemId(id);
        return repo.save(item);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
