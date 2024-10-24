package com.skillstorm.PageObjects.Components.Navbar;

import org.openqa.selenium.WebDriver;

import com.skillstorm.PageObjects.Interfaces.ButtonContainer;
import com.skillstorm.PageObjects.Interfaces.Component;

public abstract class Navbar implements Component, ButtonContainer {
    protected WebDriver driver;

    public Navbar(WebDriver driver){
        this.driver = driver;
    }
}