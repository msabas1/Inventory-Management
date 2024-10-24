package com.skillstorm.StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.WarehousesPage;
import com.skillstorm.Utilities.Navigator;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDWarehouses {
    private WebDriver driver;
    private WarehousesPage warehousesPage;
    private Navigator navigator;

    @Before()
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        navigator = new Navigator(driver);
        warehousesPage = new WarehousesPage(driver);
    }

    @After()
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    @Given("I am on the Warehouses page")
    public void givenIAmOnTheWarehousesPage() {
        warehousesPage = new WarehousesPage(driver);
        navigator.navigateTo(Navigator.PGNAME_WAREHOUSES);
    }

    @When("I click on the add warehouse button")
    public void whenIClickOnTheAddWarehouseButton(){
        warehousesPage.clickButton(WarehousesPage.BTN_ADDWAREHOUSE_NAME);
    }

    @When("I select the Sort by dropdown option with text {string}")
    public void whenISelectTheSortByDropdownOption(String selectOption) {
        this.warehousesPage.selectSortBy(selectOption);
    }

    @Then("I can see the add warehouse form modal")
    public void thenICanSeeTheAddWarehouseFormModal(){
        Assert.assertTrue(driver.findElement(By.id("add-warehouse-form-modal")).isDisplayed());
    }

    @Then("I can see the warehouse list ordered by {string}")
    public void thenIShouldSeeTheOrderedWarehouseList(String selectOption) {
        Assert.assertTrue(warehousesPage.isOrdered(selectOption));
    }

    @Then("I can see the Add Warehouse button")
    public void thenICanSeeTheAddWarehouseButton(){
        Assert.assertTrue(driver.findElement(By.id("add-warehouse-btn")).isDisplayed());
    }

    @And("I can see the Sort By dropdown")
    public void thenICanSeeTheSortByDropdown(){
        Assert.assertTrue(driver.findElement(By.id("select-sort-warehouses")).isDisplayed());
    }

    @And("I can see the Warehouses table")
    public void thenICanSeeTheWarehousesTable(){
        Assert.assertTrue(driver.findElement(By.id("warehouses-table")).isDisplayed());
    }
}
