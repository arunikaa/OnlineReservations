package com.org.Utility;

import com.org.Base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import java.util.List;


public class Utilities extends BaseTest {
    WebDriver driver;
     Logger log;


    public Utilities(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;

    }
/*
Scroll the UI when required
 */

    public void scrollUi() {

        log.info("Start scrollUi ");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");

    }
    /**
     * DataProvider for flight search tests
     * Returns TripData objects from JSON file
     */
    @DataProvider(name = "tripDataProvider")
    public static Object[][] tripDataProvider() {
        String filePath = "src/main/resources/FlightAvailability.json";
        List<TripData> tripDataList = JasonDataReader.readTripData(filePath);

        // Convert List to 2D Object array for TestNG DataProvider
        Object[][] dataArray = new Object[tripDataList.size()][1];
        for (int i = 0; i < tripDataList.size(); i++) {
            dataArray[i][0] = tripDataList.get(i);
        }

        return dataArray;
    }
}
