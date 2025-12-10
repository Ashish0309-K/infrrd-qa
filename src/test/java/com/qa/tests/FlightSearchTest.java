package com.qa.tests;
import com.qa.base.BaseTest;
import com.qa.pages.FlightsPage;
import com.qa.pages.GooglePage;
import com.qa.pages.HomePage;
import com.qa.pages.ResultsPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;


import java.util.List;


public class FlightSearchTest extends BaseTest {


    @Test
    public void flightSearchFlow() throws InterruptedException {
        String baseUrl = "https://www.cleartrip.com/"; // update if you choose another site
        driver.get(baseUrl);


        HomePage home = new HomePage(driver);
        try { home.goToFlights(); } catch (Exception ignored) {}


        FlightsPage flights = new FlightsPage(driver);
        flights.enterSource("Bengaluru");
        flights.enterDestination("Mumbai");


        // Example: pick 10th of next month
        flights.selectDateNextMonth(10);
        flights.clickSearch();


        Thread.sleep(5000); // replace with explicit waits in production


        ResultsPage resultsPage = new ResultsPage(driver);
        List<ResultsPage.Flight> sorted = resultsPage.getSortedFlightsByPrice();


        if (sorted.size() >= 1) {
            System.out.println("Cheapest: " + sorted.get(0));
        }
        if (sorted.size() >= 2) {
        System.out.println("Second Cheapest: " + sorted.get(1));
        }


        // open new tab and go to Google
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
        var handles = driver.getWindowHandles().toArray();
        driver.switchTo().window(handles[handles.length - 1].toString());
        driver.get("https://www.google.com");


        GooglePage google = new GooglePage(driver);
        google.search("QA AI");


        Thread.sleep(3000);
    }
}