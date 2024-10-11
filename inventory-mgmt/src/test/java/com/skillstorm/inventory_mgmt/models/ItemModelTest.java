package com.skillstorm.inventory_mgmt.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(item.getItemId(), 1);
        item.setItemId(2);
        assertEquals(item.getItemId(), 2);
    }

    @Test
    public void nameGetterSetter() {
        assertEquals(item.getName(), "testName");
        item.setName("testName2");
        assertEquals(item.getName(), "testName2");
    }

    @Test
    public void descriptionGetterSetter() {
        assertEquals(item.getDescription(), "testDescription");
        item.setDescription("testDescription2");
        assertEquals(item.getDescription(), "testDescription2");
    }
    
    @Test
    public void priceGetterSetter() {
        assertEquals(item.getPrice(), 20);
        item.setPrice(40);
        assertEquals(item.getPrice(), 40);
    }
}
