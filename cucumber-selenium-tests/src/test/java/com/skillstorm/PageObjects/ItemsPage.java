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

public class ItemsPage extends Page {
    //#region Static Fields
    
    // Names
    public static final String BTN_ADDITEM_NAME = "Add Item Button";
    public static final String SELECT_SORTITEMS_NAME = "Sort Items Dropdown";
    public static final String TABLE_ITEMSLIST_NAME = "Items List Table";

    // IDs
    public static final String BTN_ADDITEM_ID = "add-item-btn";
    public static final String SELECT_SORTITEMS_ID = "sort-items";
    public static final String TABLE_ITEMSLIST_ID = "items-table";

    //#endregion

    @FindBy(id = BTN_ADDITEM_ID)
    private WebElement btnAddItem;

    @FindBy(id = SELECT_SORTITEMS_ID)
    private WebElement selectSortItems;

    @FindBy(id = TABLE_ITEMSLIST_ID)
    private WebElement tableItemsList;

    @FindBy(id = TABLE_ITEMSLIST_ID)
    private List<WebElement> tableElements;

    /**
     * Initializes the driver and sets an implicit wait 
     */
    public ItemsPage(WebDriver driver){
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
     * select sort by item
     * pause execution for 1000 mili sec before navigating
     */
    public void selectSortBy(String selectOption) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Select select = new Select(selectSortItems);
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
        // sort by Name
        boolean found = true;
        int prev = 0;
        if(sortOption.equals("Name"))
        {
            for(WebElement list:tableElements){
                WebElement itemName = list.findElement(By.id("item-table-name"));
                int current = Integer.parseInt(itemName.getText());
                System.out.println("Current item name is: " + current);
                if(current <= prev){
                    return false;
                }
                else{
                    prev = current;
                }
            }
        }
        // sort by Price
        else if(sortOption.equals("Price"))
        {
            for(WebElement list:tableElements){
                WebElement itemPrice = list.findElement(By.id("item-table-price"));
                int current = Integer.parseInt(itemPrice.getText());
                System.out.println("Current item price is: " + current);
                if(current < prev){
                    return false;
                }
                else{
                    prev = current;
                }
            }
        }
        // sort by Capacity
        else if(sortOption.equals("Quantity"))
        {
            for(WebElement list:tableElements){
                WebElement itemQuantity = list.findElement(By.id("item-table-quantity"));
                int current = Integer.parseInt(itemQuantity.getText());
                System.out.println("Current item quantity is: " + current);
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

        for (int i = 0; i < 10; i++) {
            if (elementToTabTo.equals(focusedElement)) {
                break;
            }
            actions.sendKeys(Keys.TAB).pause(Duration.ofMillis(300)).perform();
            focusedElement = driver.switchTo().activeElement();
        }

        Assert.assertEquals(elementToTabTo, focusedElement);
    }

    public WebElement getItemsSortDropdown() {
        return selectSortItems;
    }

    public void pressArrowKeyNTimes(Actions actions, Keys key, int n) {
        for (int t = 0; t < n; t++) {
            actions.sendKeys(key).pause(Duration.ofMillis(300)).perform();
        }
    }

    @Override
    public List<WebElement> getWebElements() {
        List<WebElement> webElements = new ArrayList<WebElement>();

        // Add this Component's web elements
        webElements.add(btnAddItem);
        webElements.add(selectSortItems);
        webElements.add(tableItemsList);

        return webElements;
    }

    @Override
    public WebElement getWebElement(String name) {
        switch (name) {
            case BTN_ADDITEM_NAME:
                return btnAddItem;
            case SELECT_SORTITEMS_NAME:
                return selectSortItems;
            case TABLE_ITEMSLIST_NAME:
                return tableItemsList;
        }

        return null;
    }

    @Override
    public List<WebElement> getButtons() {
        return Arrays.asList(btnAddItem);
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
