package com.skillstorm.PageObjects.Interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface Component {

    List<WebElement> getWebElements();
    WebElement getWebElement(String name);
}