package com.skillstorm.inventory_mgmt.dtos;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateWarehouseCapacityDtoTest {
    private UpdateWarehouseCapacityDto updateWarehouseCapacityDto;

    @BeforeMethod
    public void setUp() {
        updateWarehouseCapacityDto = new UpdateWarehouseCapacityDto();

        updateWarehouseCapacityDto.setOperation("update");
        updateWarehouseCapacityDto.setValue(1);
    }
    // Tear down after tests
    @AfterTest
    public void teardown() throws Exception {
        updateWarehouseCapacityDto = null;
    }

    @Test
    public void testConstructor() {
        UpdateWarehouseCapacityDto updateWarehouseCapacityDto = new UpdateWarehouseCapacityDto("update", 1);
        Assert.assertEquals(updateWarehouseCapacityDto.getOperation(), "update");
        Assert.assertEquals(updateWarehouseCapacityDto.getValue(), 1);
    }

    // The below methods assert that the dto's getters & setters are successfully retrieved and set
    // for the following properties: operation & value
    @Test
    public void operationGetterSetter() {
        Assert.assertEquals(updateWarehouseCapacityDto.getOperation(), "update");
        updateWarehouseCapacityDto.setOperation("delete");
        Assert.assertEquals(updateWarehouseCapacityDto.getOperation(), "delete");
    }

    @Test
    public void valueGetterSetter() {
        Assert.assertEquals(updateWarehouseCapacityDto.getValue(), 1);
        updateWarehouseCapacityDto.setValue(2);
        Assert.assertEquals(updateWarehouseCapacityDto.getValue(), 2);
    }
}
