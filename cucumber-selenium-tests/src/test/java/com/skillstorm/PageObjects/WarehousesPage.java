package com.skillstorm.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillstorm.PageObjects.Interfaces.Component;

public class WarehousesPage extends Page {
    //#region Static Fields

    // URLs
    private static final String url = "http://localhost:5173/warehouses";

    // Names
    public static final String BTN_ADDWAREHOUSE_NAME = "Add Warehouse Button";
    public static final String SELECT_SORTWAREHOUSES_NAME = "Sort Warehouses Dropdown";
    public static final String TABLE_WAREHOUSESLIST_NAME = "Warehouses List Table";

    // IDs
    public static final String BTN_ADDWAREHOUSE_ID = "add-warehouse-btn";
    public static final String SELECT_SORTWAREHOUSES_ID = "select-sort-warehouses";
    public static final String TABLE_WAREHOUSESLIST_ID = "warehouses-table";

    //#endregion

    @FindBy(id = BTN_ADDWAREHOUSE_ID)
    private WebElement btnAddWarehouse;

    @FindBy(id = SELECT_SORTWAREHOUSES_ID)
    private WebElement selectSortWarehouses;

    @FindBy(id = TABLE_WAREHOUSESLIST_ID)
    private WebElement tableWarehousesList;

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
     * navigating to the warehouse page
     * pause execution for 1000 mili sec before navigating
     */
    public void get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
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
