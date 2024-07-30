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

import com.skillstorm.inventory_mgmt.models.Item;
import com.skillstorm.inventory_mgmt.services.ItemService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/item")
public class ItemController {
    private ItemService service;

    public ItemController(ItemService service){
        this.service = service;
    }
    
    @GetMapping()
    public Iterable<Item> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable int id){
        Optional<Item> item = service.findById(id);
        if(item.isPresent()){
            return ResponseEntity.ok(item.get());
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Item create(@RequestBody Item item){
        return service.save(item);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable int id, @RequestBody Item itemToUpdate) {
        service.updateItemById(id, itemToUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        service.deleteById(id);
    }
}
