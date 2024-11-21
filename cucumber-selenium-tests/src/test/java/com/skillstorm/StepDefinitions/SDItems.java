package com.skillstorm.StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.skillstorm.WebDriverSingleton;
import com.skillstorm.PageObjects.ItemsPage;
import com.skillstorm.Utilities.Navigator;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SDItems {
    private WebDriver driver;
    private ItemsPage itemsPage;
    private Navigator navigator;
    private Actions actions;

    @Before()
    public void setUp() {
        driver = WebDriverSingleton.getDriver();
        navigator = new Navigator(driver);
        itemsPage = new ItemsPage(driver);
        actions = new Actions(driver);
    }

    @After()
    public void tearDown() {
        WebDriverSingleton.quitDriver();
    }

    @Given("I am on the Items page")
    public void givenIAmOnTheItemsPage() {
        itemsPage = new ItemsPage(driver);
        navigator.navigateTo(Navigator.PGNAME_ITEMS);
    }

    @When("I click on the add item button")
    public void whenIClickOnTheAddItemButton(){
        itemsPage.clickButton(ItemsPage.BTN_ADDITEM_NAME);
    }

    @When("I select the Items Sort by dropdown option with text {string}")
    public void whenISelectTheItemsSortByDropdownOption(String selectOption) {
        this.itemsPage.selectSortBy(selectOption);
    }

    @Then("I can see the add item form modal")
    public void thenICanSeeTheAddItemFormModal(){
        Assert.assertTrue(driver.findElement(By.id("add-item-form-modal")).isDisplayed());
    }

    @Then("I can see the item list ordered by {string}")
    public void thenIShouldSeeTheOrderedItemList(String selectOption) {
        Assert.assertTrue(itemsPage.isOrdered(selectOption));
    }

    @Then("I can see the Add Item button")
    public void thenICanSeeTheAddItemButton(){
        Assert.assertTrue(driver.findElement(By.id("add-item-btn")).isDisplayed());
    }

    @And("I can see the Items Sort By dropdown")
    public void thenICanSeeTheItemsSortByDropdown(){
        Assert.assertTrue(driver.findElement(By.id("sort-items")).isDisplayed());
    }

    @And("I can see the Items table")
    public void thenICanSeeTheItemsTable(){
        Assert.assertTrue(driver.findElement(By.id("items-table")).isDisplayed());
    }
}
