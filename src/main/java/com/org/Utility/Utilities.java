package com.org.Utility;

import com.org.Base.BaseTest;
import com.org.Pages.Pojo.FlightDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        js.executeScript("window.scrollBy(0,250)", "");
        System.out.println("i am here");
    }

    @DataProvider
    public  static Object[] dataProvider2()
    {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        // Read JSON file
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("src/main/resources/FlightAvailability.json"));
        } catch (IOException | ParseException e) {
            e.getMessage();
        }
        jsonObject = (JSONObject) obj;

        // Extract array data from JSONObject
        assert jsonObject != null;
        JSONArray tripDetails = (JSONArray) jsonObject.get("trip details");

        // String array to store JSONArray data
        String[] dataArray = new String[tripDetails.size()];



        // JSONObject to read each JSONArray object
        JSONObject formInfoData;
        String origin,destination,departureDate,arrivalDate,cabinClass,tripType;
        // Get data from JSONArray and store it in String array
        for (int i = 0; i < tripDetails.size(); i++) {
            formInfoData = (JSONObject) tripDetails.get(i);
            origin = (String) formInfoData.get("Origin");
            destination = (String) formInfoData.get("Destination");
            departureDate = (String) formInfoData.get("DepartureDate");
            arrivalDate = (String) formInfoData.get("ArrivalDate");
            cabinClass = (String) formInfoData.get("CabinClass");
            tripType = (String) formInfoData.get("TripType");

            dataArray[i] = origin + "," + destination + "," + departureDate + "," + arrivalDate + "," + cabinClass + "," + tripType;
        }
        return  dataArray;

    }


}
