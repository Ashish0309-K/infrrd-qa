package com.qa.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;


public class FlightsPage {
private WebDriver driver;


// Generic locators - change according to site
private By sourceInput = By.cssSelector("input[placeholder*='From'], input[aria-label*='From']");
private By destInput = By.cssSelector("input[placeholder*='To'], input[aria-label*='To']");
private By calendarOpen = By.xpath("//input[contains(@placeholder,'Depart') or contains(@aria-label,'Depart') or contains(@class,'date')]");
private By calendarNext = By.xpath("//button[contains(@aria-label,'Next') or contains(., 'Next')]");
private By dateCells = By.xpath("//td[not(contains(@class,'disabled'))]//button | //div[contains(@class,'day')]//button");
private By searchBtn = By.xpath("//button[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'search') or contains(., 'Search')]");


public FlightsPage(WebDriver driver) {
    this.driver = driver;
}


public void enterSource(String source) {
    WebElement src = driver.findElement(sourceInput);
    src.clear();
    src.sendKeys(source);
    // wait/selection logic might be needed per site
    src.sendKeys(Keys.ENTER);
}


public void enterDestination(String dest) {
    WebElement d = driver.findElement(destInput);
    d.clear();
    d.sendKeys(dest);
    d.sendKeys(Keys.ENTER);
}


public void selectDateNextMonth(int dayOfMonth) {
// Open calendar then click next month then choose day
    try { driver.findElement(calendarOpen).click(); } catch (Exception e) { /* some sites auto-open */ }
        driver.findElement(calendarNext).click();
        List<?> cells = driver.findElements(dateCells);
        for (Object o : cells) {
            WebElement cell = (WebElement) o;
            if (cell.getText().trim().equals(String.valueOf(dayOfMonth))) {
                cell.click();
                break;
            }
        }
    }


    public void clickSearch() {
        driver.findElement(searchBtn).click();
    }
}