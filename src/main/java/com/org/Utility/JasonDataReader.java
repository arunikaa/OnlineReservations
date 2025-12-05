package com.org.Utility;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JasonDataReader {
    /**
     * Reads trip data from JSON file and returns a list of TripData objects
     * @param filePath Path to JSON file
     * @return List of TripData objects
     */
    public static List<TripData> readTripData(String filePath) {
        List<TripData> tripDataList = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray tripDetails = (JSONArray) jsonObject.get("trip details");

            for (Object tripObj : tripDetails) {
                JSONObject tripJson = (JSONObject) tripObj;

                TripData tripData = new TripData(
                        (String) tripJson.get("Origin"),
                        (String) tripJson.get("Destination"),
                        (String) tripJson.get("DepartureDate"),
                        (String) tripJson.get("ArrivalDate"),
                        (String) tripJson.get("CabinClass"),
                        (String) tripJson.get("TripType"),
                        (String) tripJson.get("AdultPax")
                );

                tripDataList.add(tripData);
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
                  }

        return tripDataList;
    }
}
