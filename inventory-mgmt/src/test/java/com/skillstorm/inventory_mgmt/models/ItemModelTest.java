package com.skillstorm.inventory_mgmt.models;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemModelTest {
    private Item item;

    @BeforeMethod
    public void setUp() {
        item = new Item();

        item.setItemId(1);
        item.setName("testName");
        item.setDescription("testDescription");
        item.setPrice(20);
        item.setQuantity(12);
    }
    // Tear down after tests
    @AfterTest
    public void teardown() throws Exception {
        item = null;
    }

    // The below methods assert that the item's getters & setters are successfully retrieved and set
    // for the following properties: id, name, description, price, & quantity
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
}
