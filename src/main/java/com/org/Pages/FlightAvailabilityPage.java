package com.org.Pages;

import com.org.Base.BaseTest;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FlightAvailabilityPage extends BaseTest {
    WebDriver driver;
    Logger log;

     public FlightAvailabilityPage(WebDriver driver, Logger log) {
       this.driver = driver;
       this.log = log;
    }

    public String viewOrigin(String origin)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement flightOrigin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='search-details__ond search-details']//span[contains(normalize-space(), '"+origin+"')]")));
        return flightOrigin.getText();


    }








}
