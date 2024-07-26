package com.skillstorm.inventory_mgmt.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_mgmt.models.User;
import com.skillstorm.inventory_mgmt.repositories.UserRepository;

@Service
public class UserService {
    
    private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public Iterable<User> findAll() {
        return repo.findAll();
    }

    public Optional<User> findById(int id) {
        return repo.findById(id);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public void update(int id, User user) {
        if (!repo.existsById(id)) 
            throw new NoSuchElementException("Inventory item with id " + id + " does not exist.");
            user.setUserId(id);
        repo.save(user);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
