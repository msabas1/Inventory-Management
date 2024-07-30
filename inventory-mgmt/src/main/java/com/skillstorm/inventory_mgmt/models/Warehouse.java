package com.skillstorm.inventory_mgmt.models;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 1)
    @NotNull
    private int capacity;

    @Column(name = "warehouse_name", length = 100)
    @NotBlank
    private String warehouseName;

    @OneToMany(mappedBy = "warehouse")
    @Cascade(CascadeType.ALL)
    @JsonBackReference
    List<Item> itemList;
    
    public Warehouse(){}

    public Warehouse(int id, @Min(1) @NotNull int capacity, @NotBlank String warehouseName) {
        this.id = id;
        this.capacity = capacity;
        this.warehouseName = warehouseName;
    }

    public int getWarehouseId() {
        return id;
    }

    public void setWarehouseId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", itemList=" + itemList + ", capacity=" + capacity
                + ", warehouseName=" + warehouseName + "]";
    }
}