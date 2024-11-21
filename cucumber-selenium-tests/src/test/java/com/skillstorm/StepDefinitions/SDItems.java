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

    @And("I am on the Items Sort dropdown")
    public void andITabToTheItemsSortDropdown(){
        this.itemsPage.tabTo(actions, driver.findElement(By.id("sort-items")));
    }

    @When("I click on the add item button")
    public void whenIClickOnTheAddItemButton(){
        itemsPage.clickButton(ItemsPage.BTN_ADDITEM_NAME);
    }

    @When("I select the Items Sort by dropdown option with text {string}")
    public void whenISelectTheItemsSortByDropdownOption(String selectOption) {
        this.itemsPage.selectSortBy(selectOption);
    }

    @When("I tab to the Items Sort dropdown")
    public void whenITabToTheItemsSortDropdown(){
        this.itemsPage.tabTo(actions, driver.findElement(By.id("sort-items")));
    }

    @When("I tab to click the Add Items button")
    public void whenITabToTheAddItemsButton(){
        this.itemsPage.tabTo(actions, driver.findElement(By.id("add-item-btn")));
        driver.findElement(By.id("add-item-btn")).sendKeys(Keys.ENTER);
    }

    @When("I tab to Navigate to a item")
    public void whenITabToNavigateToAItem(){
        this.itemsPage.tabTo(actions, driver.findElement(By.id("get-item-link")));
        driver.findElement(By.id("get-item-link")).sendKeys(Keys.ENTER);
    }

    @When("I press the arrow key down two times and up one time on the Items Sort dropdown")
    public void whenIPressTheArrowKeyDownOneTimeOnTheItemsSortDropdown(){
        this.itemsPage.pressArrowKeyNTimes(actions, Keys.ARROW_DOWN, 2);
        this.itemsPage.pressArrowKeyNTimes(actions, Keys.ARROW_UP, 1);
    }

    @Then("the items sort dropdown will be focused")
    public void thenTheItemsSortDropdownWillBeFocused() {
        WebElement itemsSortDropdown = itemsPage.getItemsSortDropdown();
        WebElement focusedElement = driver.switchTo().activeElement();

        Assert.assertEquals(itemsSortDropdown, focusedElement);
    }

    @Then("I can see the Update Item button")
    public void thenICanSeeTheUpdateItemButton(){
        Assert.assertTrue(driver.findElement(By.id("update-item-btn")).isDisplayed());
    }

    @Then("I can see the add item form modal")
    public void thenICanSeeTheAddItemFormModal(){
        Assert.assertTrue(driver.findElement(By.id("add-item-form-modal")).isDisplayed());
    }

    @Then("I can see the item list ordered by {string}")
    public void thenIShouldSeeTheOrderedItemList(String selectOption) {
        Assert.assertTrue(itemsPage.isOrdered(selectOption));
    }

    @Then("I should see the second item in the Items Sort dropdown highlighted")
    public void thenIShouldSeeTheSecondItemInTheItemSortDropdownHighlighted() {
        WebElement itemsSortDropdown = itemsPage.getItemsSortDropdown();
        WebElement focusedElement = driver.switchTo().activeElement();

        Assert.assertEquals(itemsSortDropdown, focusedElement);
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
