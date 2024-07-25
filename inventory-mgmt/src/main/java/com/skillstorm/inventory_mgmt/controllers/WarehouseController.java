package com.skillstorm.inventory_mgmt.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_mgmt.models.Warehouse;
import com.skillstorm.inventory_mgmt.repositories.WarehouseRepository;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {
    
    private WarehouseRepository repo;

    public WarehouseController(WarehouseRepository repo) {
        this.repo = repo;
    }

    @GetMapping()
    public Iterable<Warehouse> findAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable int id){
        Optional<Warehouse> warehouse = repo.findById(id);
        if(warehouse.isPresent()){
            return ResponseEntity.ok(warehouse.get());
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Warehouse create(@RequestBody Warehouse warehouse){
        return repo.save(warehouse);
    }

    @PutMapping("/{id}")
    public Warehouse updateById(@PathVariable int id, @RequestBody Warehouse updatedWarehouse) {
        return repo.findById(id)
        .map(warehouse -> {
            warehouse.setCapacity(updatedWarehouse.getCapacity());
            return repo.save(warehouse);
        })
        .orElseGet(() -> {
            return repo.save(updatedWarehouse);
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        repo.deleteById(id);
    }
}
