package com.qa.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {
    private WebDriver driver;
    private By flightsTab = By.xpath("//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'flight') or contains(., 'Flights')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToFlights() {
        WebElement flights = driver.findElement(flightsTab);
        flights.click();
    }
}