package com.org.Base;// BaseTest.java

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class BaseTest {

    protected static WebDriver driver;
    protected static Logger log;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    private final By acceptCookies = By.xpath("//button[@id='onetrust-accept-btn-handler']");


    @BeforeSuite
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=old");
        options.addArguments("--window-size=1920,1080");
        //options.add_arguments("--headless=old");
        driver = new ChromeDriver(options);
       // driver = new ChromeDriver();
        driver.get("https://www.emirates.com/lk/english/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(acceptCookies).click();
        log = LogManager.getLogger(BaseTest.class);
        log.info("Driver set up is successful");

        // Set up ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports.html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Your Name");
    }


    @BeforeClass
    public void logClassName() {
        String className = getClass().getSimpleName();
        log.info("Start Class", className);
        test = extent.createTest(className);
    }


    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
            try {
                String screenshotPath = getScreenshot(driver, result.getName());
                test.addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
                log.info(e.getMessage());
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else {
            test.skip("Test Skipped");
        }
    }

    public String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Reports/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileHandler.copy(source, finalDestination);
        return destination;
    }

   @AfterSuite
    public void tearDown() {
      driver.quit();
        log.info("Driver quit");
       extent.flush();
  }
}







