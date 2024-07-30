package com.skillstorm.inventory_mgmt.models;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "item")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_name")
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Warehouse warehouse;

    public Item(){}
    
    public Item(
        int id,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull int price,
        @NotNull int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getItemId() {
        return id;
    }

    public void setItemId(int id) {
        this.id = id;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", warehouse=" + warehouse + ", name=" + name + ", description=" + description
                + ", price=" + price + ", quantity=" + quantity + "]";
    }
}
