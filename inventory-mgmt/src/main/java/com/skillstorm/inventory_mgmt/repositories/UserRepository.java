package com.skillstorm.inventory_mgmt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillstorm.inventory_mgmt.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}
