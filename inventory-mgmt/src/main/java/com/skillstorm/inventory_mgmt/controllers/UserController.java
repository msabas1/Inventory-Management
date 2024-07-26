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

import com.skillstorm.inventory_mgmt.models.User;
import com.skillstorm.inventory_mgmt.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository repo;

    public UserController(UserRepository repo){
        this.repo = repo;
    }
    
    @GetMapping()
    public Iterable<User> findAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable int id){
        Optional<User> user = repo.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return repo.save(user);
    }

    @PutMapping("/{id}")
    public User updateById(@PathVariable int id, @RequestBody User updatedUser) {
        return repo.findById(id)
        .map(user -> {
            user.setName(updatedUser.getName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            return repo.save(user);
        })
        .orElseGet(() -> {
            return repo.save(updatedUser);
        });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id){
        repo.deleteById(id);
    } 
}
