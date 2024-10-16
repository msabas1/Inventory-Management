package com.skillstorm.inventory_mgmt.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.skillstorm.inventory_mgmt.models.Item;
import com.skillstorm.inventory_mgmt.repositories.ItemRepository;

public class ItemServiceTest {
     //creates a mock instance of the ItemRepository class
    @Mock
    private ItemRepository itemRepository;

    //injects the ItemRepository mock object into the ItemService
    @InjectMocks
    private ItemService itemService;
    private AutoCloseable closeable;

    //initializes the mock objects to ensure they are ready before tests are run
    @BeforeTest
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    //closes the closeable resource to ensure that the mock objects are cleaned up properly - prevents memory leaks
    @AfterTest
    public void teardown() throws Exception{
        closeable.close();
    }

    // Tests if the mocked item repo successfully return expected response
    // after service calls findAll() with expected values
    @Test
    public void findAllItemsTest() {
        //Given
        List<Item> expectedItems = Arrays.asList(new Item(), new Item());

        //When (stubbing)
        when(itemRepository.findAll())
        
        //Then
        .thenReturn(expectedItems);

        List<Item> response = itemService.findAll();

        Assert.assertEquals(response, expectedItems);
    }

    // Tests if the mocked item repo successfully return expected response
    // after service calls findById() for a item id that does exist 
    @Test
    public void findItemByIdTest() {
        int itemId = 1;
        Item inputItem = new Item();
        Optional<Item> expectedItem = Optional.of(inputItem);
        
        when(itemRepository.findById(itemId))
        .thenReturn(expectedItem);

        Optional<Item> response = itemService.findById(itemId);

        Assert.assertEquals(response, expectedItem);
    }

    // Tests if the mocked item repo successfully return expected response
    // after service calls save() to create a item
    @Test
    public void createItemTest() {
        Item inputItem = new Item();
        Item savedItem = new Item();

        when(itemRepository.save(inputItem))
        .thenReturn(savedItem);

        Item response = itemService.save(inputItem);

        Assert.assertEquals(response, savedItem);
    }

    // Tests if the mocked item repo successfully return expected response
    // after service calls update()
    @Test
    public void updateItemTest() {
        Item inputItem = new Item(1, "itemName", "itemDescription", 30,10);
        Item savedItem =  new Item(1, "itemName", "itemDescription", 30,10);

        when(itemRepository.save(inputItem))
        .thenReturn(savedItem);

        when(itemRepository.existsById(inputItem.getItemId()))
        .thenReturn(true);
        
        System.out.println(savedItem);

        int response = itemService.updateItemById(inputItem.getItemId(), inputItem);

        Assert.assertEquals(response, inputItem.getItemId());
    }

    // Tests if the mocked item repo successfully return expected EXCEPTION
    // after service calls update()
    @Test
    public void updateItemInvalidTest() {
        Item inputItem = new Item(1, "itemName", "itemDescription", 30,10);

        when(itemRepository.save(inputItem))
        .thenReturn(inputItem);

        when(itemRepository.existsById(2))
        .thenReturn(false);
        
        Assert.assertThrows(NoSuchElementException.class, () -> {
            itemService.updateItemById(2, inputItem);
        });
    }

    // Tests if the mocked itemInventory repo successfully deletes item with matching id after service calls deleteById()
    @Test
    public void deleteItemTest() {
        Item inputItem = new Item(1, "itemName", "itemDescription", 30,10);
        Optional<Item> expectedItem = Optional.of(inputItem);

        when(itemRepository.findById(inputItem.getItemId()))
        .thenReturn(expectedItem);
        
        itemRepository.deleteById(inputItem.getItemId());
        verify(itemRepository, times(1)).deleteById(inputItem.getItemId());
    
        Assert.assertEquals(inputItem.getItemId(), itemService.deleteById(1));
    }
}
