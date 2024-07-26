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

import com.skillstorm.inventory_mgmt.models.Inventory;
import com.skillstorm.inventory_mgmt.repositories.InventoryRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private InventoryRepository repo;

    public InventoryController(InventoryRepository repo){
        this.repo = repo;
    }
    
    @GetMapping()
    public Iterable<Inventory> findAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> findById(@PathVariable int id){
        Optional<Inventory> inventory = repo.findById(id);
        if(inventory.isPresent()){
            return ResponseEntity.ok(inventory.get());
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Inventory create(@RequestBody Inventory item){
        return repo.save(item);
    }

    @PutMapping("/{id}")
    public Inventory updateById(@PathVariable int id, @RequestBody Inventory updatedItem) {
        return repo.findById(id)
        .map(item -> {
            item.setItemName(updatedItem.getItemName());
            item.setPrice(updatedItem.getPrice());
            item.setQuantity(updatedItem.getQuantity());
            return repo.save(item);
        })
        .orElseGet(() -> {
            return repo.save(updatedItem);
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        repo.deleteById(id);
    }
}
