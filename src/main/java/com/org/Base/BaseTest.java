package com.org.Base;// BaseTest.java

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected static WebDriver driver;
    protected static Logger log;

    private final By acceptCookies = By.xpath("//button[@id='onetrust-accept-btn-handler']");


    @BeforeSuite
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.emirates.com/lk/english/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(acceptCookies).click();
        log = LogManager.getLogger(BaseTest.class);
        log.info("Driver set up is successful");
    }

    @BeforeClass
    public void logClassName() {
        String className = getClass().getSimpleName();
        log.info("Start Class", className);
    }


   @AfterSuite
    public void tearDown() {
      driver.quit();
        log.info("Driver quit");
  }
}







