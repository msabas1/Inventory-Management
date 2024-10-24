package com.skillstorm.Utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class Navigator {
    // #region Static fields
    public static final String PGNAME_HOME = "Home";
    public static final String PGNAME_WAREHOUSES = "Warehouses";

    public static final String URL = System.getProperty("frontendUrl", "http://localhost:5173");
    public static final String URL_HOME = URL;
    public static final String URL_WAREHOUSES = URL + "/warehouses";
    // #endregion

    Map<String, String> pageUrlMap = new HashMap<>();
    private WebDriver driver;

    public Navigator(WebDriver driver) {
        this.driver = driver;
        pageUrlMap.put(PGNAME_WAREHOUSES, URL_WAREHOUSES);
    }

    /**
     * Resolves a page name into a navigation method. The page specified
     * should always be loaded after this method is called.
     * 
     * @param pageName Name of the page to navigate to. Must be one of the static
     *                 PGNAME Strings.
     */
    public void navigateTo(String pageName) {
        switch (pageName) {
            case PGNAME_WAREHOUSES:
                navigateToWarehouses();
                break;
            default:
                throw new IllegalArgumentException("Page '" + pageName + "' does not exist.");
        }
    }

    public String getURL(String pageName) {
        return pageUrlMap.get(pageName);
    }
    private void navigateToWarehouses() {
        driver.get(URL_WAREHOUSES);
    }
}