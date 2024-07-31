package com.skillstorm.inventory_mgmt.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_mgmt.dtos.UpdateWarehouseCapacityDto;
import com.skillstorm.inventory_mgmt.models.Warehouse;
import com.skillstorm.inventory_mgmt.services.WarehouseService;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin("http://localhost:5173")
public class WarehouseController {
    
    private WarehouseService service;

    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @GetMapping()
    public Iterable<Warehouse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable int id){
        Optional<Warehouse> warehouse = service.findById(id);
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
        return service.save(warehouse);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable int id, @RequestBody Warehouse warehouseToUpdate) {
        service.update(id, warehouseToUpdate);
    }

    @PatchMapping("/{id}")
    public void updateWarehouseCapacityById(@PathVariable int id, @RequestBody UpdateWarehouseCapacityDto capacityUpdate){
        service.updateCapacityById(id, capacityUpdate.getOperation(), capacityUpdate.getValue());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        service.deleteById(id);
    }
}
