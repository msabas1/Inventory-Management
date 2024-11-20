package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WarehousesPage extends Page {
    //#region Static Fields
    
    // Names
    public static final String BTN_ADDWAREHOUSE_NAME = "Add Warehouse Button";
    public static final String SELECT_SORTWAREHOUSES_NAME = "Sort Warehouses Dropdown";
    public static final String TABLE_WAREHOUSESLIST_NAME = "Warehouses List Table";

    // IDs
    public static final String BTN_ADDWAREHOUSE_ID = "add-warehouse-btn";
    public static final String SELECT_SORTWAREHOUSES_ID = "sort-warehouses";
    public static final String TABLE_WAREHOUSESLIST_ID = "warehouses-table";

    //#endregion

    @FindBy(id = BTN_ADDWAREHOUSE_ID)
    private WebElement btnAddWarehouse;

    @FindBy(id = SELECT_SORTWAREHOUSES_ID)
    private WebElement selectSortWarehouses;

    @FindBy(id = TABLE_WAREHOUSESLIST_ID)
    private WebElement tableWarehousesList;

    @FindBy(id = TABLE_WAREHOUSESLIST_ID)
    private List<WebElement> tableElements;

    /**
     * Initializes the driver and sets an implicit wait 
     */
    public WarehousesPage(WebDriver driver){
        super(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Helper method to wait for element visibility
    private WebElement waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * select sort by warehouse
     * pause execution for 1000 mili sec before navigating
     */
    public void selectSortBy(String selectOption) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Select select = new Select(selectSortWarehouses);
        select.selectByVisibleText(selectOption);
    }

    public boolean isOrdered(String sortOption) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(tableElements.size() == 1)
        {
            return true;
        }
        // sort by ID
        boolean found = true;
        int prev = 0;
        if(sortOption.equals("ID"))
        {
            for(WebElement list:tableElements){
                WebElement wID = list.findElement(By.id("warehouse-table-id"));
                int current = Integer.parseInt(wID.getText());
                System.out.println("Current id is: " + current);
                if(current <= prev){
                    return false;
                }
                else{
                    prev = current;
                }
            }
        }
        // sort by Name
        else if(sortOption.equals("Name"))
        {
            for(WebElement list:tableElements){
                WebElement wCapacity = list.findElement(By.id("warehouse-table-name"));
                int current = Integer.parseInt(wCapacity.getText());
                System.out.println("Current name is: " + current);
                if(current < prev){
                    return false;
                }
                else{
                    prev = current;
                }
            }
        }
        // sort by Capacity
        else if(sortOption.equals("Capacity"))
        {
            for(WebElement list:tableElements){
                WebElement wCapacity = list.findElement(By.id("warehouse-table-capacity"));
                int current = Integer.parseInt(wCapacity.getText());
                System.out.println("Current capacity is: " + current);
                if(current < prev){
                    return false;
                }
                else{
                    prev = current;
                }
            }
        }
        
        return found;
    }

    public void tabTo(Actions actions, WebElement elementToTabTo){
        WebElement focusedElement = driver.switchTo().activeElement();

        for (int i = 0; i < 10; i += 1) {
            if (elementToTabTo.equals(focusedElement)) {
                break;
            }
            actions.sendKeys(Keys.TAB).pause(Duration.ofMillis(200)).perform();
            focusedElement = driver.switchTo().activeElement();
        }

        Assert.assertEquals(elementToTabTo, focusedElement);
    }

    public WebElement getWarehousesSortDropdown() {
        return selectSortWarehouses;
    }

    public void pressArrowKeyNTimes(Actions actions, Keys key, int n) {
        for (int t = 0; t < n; t += 1) {
            actions.sendKeys(key).pause(Duration.ofMillis(200)).perform();
        }
    }

    @Override
    public List<WebElement> getWebElements() {
        List<WebElement> webElements = new ArrayList<WebElement>();

        // Add this Component's web elements
        webElements.add(btnAddWarehouse);
        webElements.add(selectSortWarehouses);
        webElements.add(tableWarehousesList);

        return webElements;
    }

    @Override
    public WebElement getWebElement(String name) {
        switch (name) {
            case BTN_ADDWAREHOUSE_NAME:
                return btnAddWarehouse;
            case SELECT_SORTWAREHOUSES_NAME:
                return selectSortWarehouses;
            case TABLE_WAREHOUSESLIST_NAME:
                return tableWarehousesList;
        }

        return null;
    }

    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnAddWarehouse);
    }

    @Override
    public void clickButton(String name) {
        WebElement button = getWebElement(name);

        if (button != null) {
            waitForElement(button, 10).click();
        } else {
            throw new IllegalArgumentException("Button '" + name + "' does not exist.");
        }
    }
}
