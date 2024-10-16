package com.skillstorm.inventory_mgmt.models;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemModelTest {
    private Item item;
    private Warehouse testWarehouse;

    @BeforeMethod
    public void setUp() {
        item = new Item();
        testWarehouse = new Warehouse(1, 100, "testWarehouse");

        item.setItemId(1);
        item.setName("testName");
        item.setDescription("testDescription");
        item.setPrice(20);
        item.setQuantity(12);
        item.setWarehouse(testWarehouse);
    }
    // Tear down after tests
    @AfterTest
    public void teardown() throws Exception {
        item = null;
    }

    // The below methods assert that the item's getters & setters are successfully retrieved and set
    // for the following properties: id, name, description, price, quantity, & warehouse
    @Test
    public void idGetterSetter() {
        Assert.assertEquals(item.getItemId(), 1);
        item.setItemId(2);
        Assert.assertEquals(item.getItemId(), 2);
    }

    @Test
    public void nameGetterSetter() {
        Assert.assertEquals(item.getName(), "testName");
        item.setName("testName2");
        Assert.assertEquals(item.getName(), "testName2");
    }

    @Test
    public void descriptionGetterSetter() {
        Assert.assertEquals(item.getDescription(), "testDescription");
        item.setDescription("testDescription2");
        Assert.assertEquals(item.getDescription(), "testDescription2");
    }
    
    @Test
    public void priceGetterSetter() {
        Assert.assertEquals(item.getPrice(), 20);
        item.setPrice(40);
        Assert.assertEquals(item.getPrice(), 40);
    }

    @Test
    public void quantityGetterSetter() {
        Assert.assertEquals(item.getQuantity(), 12);
        item.setQuantity(100);
        Assert.assertEquals(item.getQuantity(), 100);
    }

    @Test
    public void warehouseGetterSetter() {
        Assert.assertEquals(item.getWarehouse(), testWarehouse);
        Warehouse newWarehouse = new Warehouse(2, 200, "newWarehouse");
        item.setWarehouse(newWarehouse);
        Assert.assertEquals(item.getWarehouse(), newWarehouse);
    }
}
