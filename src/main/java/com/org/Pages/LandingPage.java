package com.org.Pages;// LoginPage.java
import com.org.Base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage extends BaseTest {

     WebDriver driver;
     Logger log;

    private final By lbl_SearchFlights = By.xpath("//li[@id='tab0']//div[@class='widget__tab__content']");




    public LandingPage(WebDriver driver,Logger log ) {
        this.driver = driver;
        this.log = log;

         // Inheriting the driver from BaseTest
    }




    public void clickSearchFlights()
    {
        log.info("clickSearchFlights");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lbl_SearchFlights))).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            log.error(e.getMessage());
        }


    }


}
