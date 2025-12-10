package com.qa.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ResultsPage {
    private WebDriver driver;


// Example locators - change per site structure
    private By priceElements = By.xpath("//div[contains(@class,'price') or contains(@class,'fare') or //*[contains(text(),'₹')]]");
    private By flightBlocks = By.xpath("//div[contains(@class,'flight') or contains(@class,'result')]");


    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }


    public List<Flight> getSortedFlightsByPrice() {
        List<Flight> flights = new ArrayList<>();
        List<WebElement> blocks = driver.findElements(flightBlocks);
        for (WebElement b : blocks) {
            try {
                String name = b.getText().split("\n")[0];
                String priceText = b.getText().replaceAll("[^0-9]", " ").trim();
                String[] parts = priceText.split("\\s+");
                if (parts.length == 0) continue;
                    int price = Integer.parseInt(parts[parts.length - 1]);
                    flights.add(new Flight(name, price));
                } catch (Exception e) {
                    // ignore parsing errors
                }
            }
        return flights.stream().sorted(Comparator.comparingInt(f -> f.price)).collect(Collectors.toList());
    }


    public static class Flight {
        public String name;
        public int price;


        public Flight(String name, int price) {
            this.name = name;
            this.price = price;
        }


        @Override
        public String toString() {
            return "Flight{name='" + name + "', price=" + price + "}";
        }
    }
}