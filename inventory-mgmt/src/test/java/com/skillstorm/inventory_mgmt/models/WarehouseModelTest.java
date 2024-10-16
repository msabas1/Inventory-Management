package com.skillstorm.inventory_mgmt.models;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WarehouseModelTest {
    private Warehouse warehouse;
    private List<Item> itemList;

    // Set up instance of Items into a list and sets the instance of warehouse
    // to testing values for its properties
    @BeforeMethod
    public void setUp() {
        warehouse = new Warehouse();

        Item item1 = new Item();
        item1.setItemId(1);
        item1.setQuantity(30);

        Item item2 = new Item();
        item2.setItemId(2);
        item2.setQuantity(20);

        itemList = Arrays.asList(item1, item2);

        warehouse.setWarehouseId(1);
        warehouse.setWarehouseName("testName");
        warehouse.setCapacity(300);
        warehouse.setItemList(itemList);
    }
    // Tear down after tests
    @AfterTest
    public void teardown() throws Exception{
        warehouse = null;
    }

    // The below methods assert that the warehouse's getters & setters are successfully retrieved and set
    // for the following properties: id, capacity, and warehouseName
    @Test
    public void idGetterSetter() {
        Assert.assertEquals(warehouse.getWarehouseId(), 1);
        warehouse.setWarehouseId(2);
        Assert.assertEquals(warehouse.getWarehouseId(), 2);
    }
    
    @Test
    public void capacityGetterSetter() {
        Assert.assertEquals(warehouse.getCapacity(), 300);
        warehouse.setCapacity(400);
        Assert.assertEquals(warehouse.getCapacity(), 400);
    }

    @Test
    public void nameGetterSetter() {
        Assert.assertEquals(warehouse.getWarehouseName(), "testName");
        warehouse.setWarehouseName("testName2");
        Assert.assertEquals(warehouse.getWarehouseName(), "testName2");
    }

    // Tests if the warehouse has expected items list
    // and if updating that list with set() methods still returns expected values
    @Test
    public void itemListGetterSetter() {
        Assert.assertEquals(warehouse.getItemList(), itemList);

        Item itemToAdd = new Item();
        itemToAdd.setItemId(4);
        itemToAdd.setQuantity(13);

        List<Item> productInventories2 = Arrays.asList(itemToAdd);
        warehouse.setItemList(productInventories2);
        Assert.assertEquals(warehouse.getItemList(), productInventories2);
    }

    // Tests .toString() for our test Warehouse instance
    @Test
    public void testToString() {
        System.out.println(warehouse);
        String expectedToString = "Warehouse [id=1" + ", itemList=" + itemList + ", capacity=300" + ", warehouseName=testName" + "]";
        System.out.println(expectedToString);
        Assert.assertEquals(warehouse.toString(), expectedToString);
    }
}
