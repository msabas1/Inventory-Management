package com.skillstorm.StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.WarehousesPage;
import com.skillstorm.Utilities.Navigator;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDWarehouses {
    private WebDriver driver;
    private WarehousesPage warehousesPage;
    private Navigator navigator;

    @Before("@warehouses-page")
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        navigator = new Navigator(driver);
        warehousesPage = new WarehousesPage(driver);
    }

    @After("@warehouses-page")
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }

    @Given("I am on the Warehouses page")
    public void givenIAmOnTheWarehousesPage() {
        warehousesPage.get();
        navigator.navigateTo(Navigator.PGNAME_WAREHOUSES);
    }

    @When("I click on the add warehouse button")
    public void whenIClickOnTheAddWarehouseButton(){
        warehousesPage.clickButton(WarehousesPage.BTN_ADDWAREHOUSE_NAME);
    }

    @Then("I can see the add warehouse form modal")
    public void thenICanSeeTheAddWarehouseFormModal(){
        Assert.assertTrue(driver.findElement(By.id("add-warehouse-form-modal")).isDisplayed());
    }
}
