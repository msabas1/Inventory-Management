package com.skillstorm.PageObjects;

import org.openqa.selenium.WebDriver;

import com.skillstorm.PageObjects.Components.Navbar.Navbar;
import com.skillstorm.PageObjects.Interfaces.ButtonContainer;
import com.skillstorm.PageObjects.Interfaces.Component;

public abstract class Page implements Component, ButtonContainer{
    protected WebDriver driver;
    protected Navbar navbar;

    public Page(WebDriver driver){
        this.driver = driver;
    }
}