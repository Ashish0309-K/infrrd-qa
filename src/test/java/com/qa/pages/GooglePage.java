package com.qa.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GooglePage {
    private WebDriver driver;
    private By searchBox = By.name("q");


    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }


    public void search(String query) {
        WebElement box = driver.findElement(searchBox);
        box.sendKeys(query);
        box.sendKeys(Keys.ENTER);
    }
}