package com.skillstorm.inventory_mgmt.controllers;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.inventory_mgmt.models.Item;
import com.skillstorm.inventory_mgmt.services.ItemService;

public class ItemControllerTest {

    // creates a mock instance of the ItemService class
    @Mock
    private ItemService itemService;

    // injects the ItemService mock object into the ItemController
    @InjectMocks
    private ItemController itemController;
    private AutoCloseable closeable;

    // initializes the mock objects to ensure they are ready before tests are run
    @BeforeTest
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    // closes the closeable resource to ensure that the mock objects are cleaned up properly - prevents memory leaks
    @AfterTest
    public void teardown() throws Exception {
        closeable.close();
    }

    // Tests if the mocked item service successfully HTTP 200 OK after controller calls findAll() with expected values
    @Test
    public void findAllItemsTest() {

        // Given
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());

        // When (stubbing)
        when(itemService.findAll())
        
        // Then
        .thenReturn(expectedItems);

        ResponseEntity<List<Item>> response = itemController.findAll();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), expectedItems);
        Assert.assertEquals(response.getBody().size(), 2);
    }

    // Tests if the mocked item service successfully HTTP 200 OK after controller calls findById()
    // for a item id that does exist 
    @Test
    public void findItemByIdTest() {
        int itemId = 1;
        Item inputItem = new Item();
        Optional<Item> expectedItem = Optional.of(inputItem);
        
        when(itemService.findById(itemId))
        .thenReturn(expectedItem);

        ResponseEntity<Item> response = itemController.findById(itemId);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // Tests if the mocked item service gets HTTP 404 NOT FOUND after controller calls findById()
    // for a item id that doesn't exist 
    @Test
    public void findItemByIdInvalidTest() {
        int itemId = 1;
        Item inputItem = new Item();
        Optional<Item> expectedItem = Optional.of(inputItem);
        
        when(itemService.findById(itemId))
        .thenReturn(expectedItem);

        ResponseEntity<Item> response = itemController.findById(2);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    // Tests if the mocked item service successfully HTTP 201 CREATED after controller calls create() to create a item
    @Test
    public void createItemTest() {
        Item inputItem = new Item();
        Item savedItem = new Item();
        
        when(itemService.save(inputItem))
        .thenReturn(savedItem);

        ResponseEntity<Item> response = itemController.create(inputItem);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    // Tests if the mocked item service successfully HTTP 200 OK after controller calls update()
    @Test
    public void updateItemTest() {
        Item inputItem = new Item();
        when(itemService.updateItemById(inputItem.getItemId(), inputItem))
        .thenReturn(inputItem.getItemId());

        ResponseEntity<Integer> response = itemController.updateById(inputItem.getItemId(), inputItem);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    // Tests if the mocked item service successfully deletes item with matching id after controller calls deleteById()
    @Test
    public void deleteItemTest() {
        Item inputItem = new Item();
        when(itemService.deleteById(inputItem.getItemId()))
        .thenReturn(inputItem.getItemId());

        ResponseEntity<Integer> response = itemController.deleteById(inputItem.getItemId());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
