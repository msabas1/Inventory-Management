package com.skillstorm.inventory_mgmt.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class UpdateWarehouseCapacityDto {
    @NotBlank
    private String operation;

    @Min(value = 1)
    private int value = 1;

    public UpdateWarehouseCapacityDto() {
    }

    public UpdateWarehouseCapacityDto(@NotBlank String operation, @Min(1) int value) {
        this.operation = operation;
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
