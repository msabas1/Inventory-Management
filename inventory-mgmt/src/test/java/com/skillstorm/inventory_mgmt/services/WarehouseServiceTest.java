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

import com.skillstorm.inventory_mgmt.models.Warehouse;
import com.skillstorm.inventory_mgmt.repositories.WarehouseRepository;

public class WarehouseServiceTest {

    //creates a mock instance of the WarehouseRepository class
    @Mock
    private WarehouseRepository warehouseRepository;

    //injects the WarehouseRepository mock object into the WarehouseService
    @InjectMocks
    private WarehouseService warehouseService;
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

    // Tests if the mocked warehouse repo successfully return expected response
    // after service calls findAll() with expected values
    @Test
    public void findAllWarehousesTest() {
        //Given
        List<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse());

        //When (stubbing)
        when(warehouseRepository.findAll())
        
        //Then
        .thenReturn(expectedWarehouses);

        List<Warehouse> response = warehouseService.findAll();

        Assert.assertEquals(response, expectedWarehouses);
    }

    // Tests if the mocked warehouse repo successfully return expected response
    // after service calls findById() for a warehouse id that does exist 
    @Test
    public void findWarehouseByIdTest() {
        int warehouseId = 1;
        Warehouse inputWarehouse = new Warehouse();
        Optional<Warehouse> expectedWarehouse = Optional.of(inputWarehouse);
        
        when(warehouseRepository.findById(warehouseId))
        .thenReturn(expectedWarehouse);

        Optional<Warehouse> response = warehouseService.findById(warehouseId);

        Assert.assertEquals(response, expectedWarehouse);
    }

    // Tests if the mocked warehouse repo successfully return expected response
    // after service calls save() to create a warehouse
    @Test
    public void createWarehouseTest() {
        Warehouse inputWarehouse = new Warehouse();

        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        Warehouse response = warehouseService.save(inputWarehouse);

        Assert.assertEquals(response, inputWarehouse);
    }

    // Tests if the mocked warehouse repo successfully return expected response
    // after service calls update()
    @Test
    public void updateWarehouseTest() {
        Warehouse inputWarehouse = new Warehouse(1, 111, "testName");
        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        when(warehouseRepository.existsById(inputWarehouse.getWarehouseId()))
        .thenReturn(true);
        
        System.out.println(inputWarehouse);

        int response = warehouseService.update(inputWarehouse.getWarehouseId(), inputWarehouse);

        Assert.assertEquals(response, inputWarehouse.getWarehouseId());
    }

    // Tests if the mocked warehouse repo successfully return expected EXCEPTION
    // after service calls update()
    @Test
    public void updateWarehouseInvalidTest() {
        Warehouse inputWarehouse = new Warehouse(1, 111, "testName");
        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        when(warehouseRepository.existsById(2))
        .thenReturn(false);
        
        Assert.assertThrows(NoSuchElementException.class, () -> {
            warehouseService.update(2, inputWarehouse);
        });
    }

    // Tests if the mocked warehouse repo successfully return expected response
    // after service calls updateCapacityById() add
    @Test
    public void updateCapacityByIdWarehouseTestAdd() {
        Warehouse inputWarehouse = new Warehouse(1, 111, "testName");
        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        when(warehouseRepository.existsById(inputWarehouse.getWarehouseId()))
        .thenReturn(true);
        
        System.out.println(inputWarehouse);

        int response = warehouseService.updateCapacityById(inputWarehouse.getWarehouseId(), "add", 100);

        Assert.assertEquals(response, inputWarehouse.getWarehouseId());
    }

    // Tests if the mocked warehouse repo successfully return expected response
    // after service calls updateCapacityById() subtract
    @Test
    public void updateCapacityByIdWarehouseTestSubtract() {
        Warehouse inputWarehouse = new Warehouse(1, 111, "testName");
        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        when(warehouseRepository.existsById(inputWarehouse.getWarehouseId()))
        .thenReturn(true);
        
        System.out.println(inputWarehouse);

        int response = warehouseService.updateCapacityById(inputWarehouse.getWarehouseId(), "subtract", 5);

        Assert.assertEquals(response, inputWarehouse.getWarehouseId());
    }

    // Tests if the mocked warehouse repo successfully return expected response
    // after service calls updateCapacityById() subtract
    @Test
    public void updateCapacityByIdWarehouseTestInvalidOperation() {
        Warehouse inputWarehouse = new Warehouse(1, 111, "testName");
        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        when(warehouseRepository.existsById(inputWarehouse.getWarehouseId()))
        .thenReturn(true);

        Assert.assertThrows(IllegalArgumentException.class, () -> {
            warehouseService.updateCapacityById(inputWarehouse.getWarehouseId(), "invalidOperation", 5);
        });
    }

    // Tests if the mocked warehouse repo successfully throws exception
    // after service calls updateCapacityById() for non-existing warehouse
    @Test
    public void updateCapacityByIdWarehouseTestInvalidWarehouse() {
        Warehouse inputWarehouse = new Warehouse(1, 111, "testName");
        when(warehouseRepository.save(inputWarehouse))
        .thenReturn(inputWarehouse);

        when(warehouseRepository.existsById(2))
        .thenReturn(false);
        
        Assert.assertThrows(RuntimeException.class, () -> {
            warehouseService.updateCapacityById(2, "subtract", 5);
        });
    }

    // Tests if the mocked warehouse repo successfully deletes product with matching id after service calls deleteById()
    @Test
    public void deleteWarehouseTest() {
        Warehouse inputWarehouse = new Warehouse(1, 111, "testName");
        Optional<Warehouse> expectedWarehouse = Optional.of(inputWarehouse);

        when(warehouseRepository.findById(inputWarehouse.getWarehouseId()))
        .thenReturn(expectedWarehouse);
        
        warehouseRepository.deleteById(inputWarehouse.getWarehouseId());
        verify(warehouseRepository, times(1)).deleteById(inputWarehouse.getWarehouseId());
    
        Assert.assertEquals(inputWarehouse.getWarehouseId(), warehouseService.deleteById(1));
    }
}
