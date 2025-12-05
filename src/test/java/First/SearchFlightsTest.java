package First;

import com.org.Base.BaseTest;

import com.org.Pages.FlightAvailabilityPage;
import com.org.Pages.SearchFlightsPage;
import com.org.Utility.RetryAnalyzer;
import com.org.Utility.TripData;
import com.org.Utility.Utilities;
import org.testng.annotations.Test;

public class SearchFlightsTest extends BaseTest {

    /**
     * Test flight search functionality with data-driven approach
     * @param tripData Trip information from JSON file
     */
    @Test(dataProviderClass = Utilities.class,
            dataProvider = "tripDataProvider",
            retryAnalyzer = RetryAnalyzer.class,
            description = "Search for flights with various trip configurations")

    public void testFlightSearch(TripData tripData) throws InterruptedException {
        log.info("Starting flight search test: {}");

        // Initialize page objects and utilities
        SearchFlightsPage searchFlights = new SearchFlightsPage(driver,log);
        FlightAvailabilityPage avail = new FlightAvailabilityPage(driver,log);
        Utilities util = new Utilities(driver, log);

        try {
            // Step 1: Set departure airport

            util.scrollUi();
            searchFlights.setDepartureAirport(tripData.getOrigin());

            // Step 2: Set arrival airport
            log.info("Setting arrival airport: " + tripData.getDestination());
            searchFlights.setArrivalAirport(tripData.getDestination());

            // Step 3: Continue to date selection
            log.info("Clicking continue button");
            searchFlights.clickContinueBtn();
            searchFlights.clickDepartureSelection();
            util.scrollUi();

            // Step 4: Set departure date

            searchFlights.setDepartureDate(tripData.getDepartureDate());
            util.scrollUi();

            // Step 5: Set return/arrival date
           // log.info("Setting arrival date: " + tripData.getArrivalDate());
            searchFlights.setDepartureDate(tripData.getArrivalDate());
            util.scrollUi();

            // Step 6: Set passenger count
            //log.info("Setting passenger count: " + tripData.getAdultPax());
            searchFlights.clickPassengersBox();
            searchFlights.setAdultPaxCount(tripData.getAdultPax());

            // Step 7: Select cabin class
           // log.info("Setting cabin class: " + tripData.getCabinClass());
            searchFlights.clickCabinClass();
            String selectedCabin = searchFlights.setCabinClass(tripData.getCabinClass());

            // Validation: Verify cabin class selection
            // Assert.assertEquals(selectedCabin, tripData.getCabinClass(),
            //         "Cabin class mismatch");

            // Step 8: Search for flights
            log.info("Clicking search button");
            searchFlights.clickSearchButton();
            System.out.println(avail.viewOrigin(tripData.getOrigin()));
            //System.out.println(avail.viewDestination());

            // Add validation for search results if needed
            // Assert.assertTrue(searchFlights.areResultsDisplayed(), "Search results not displayed");

            log.info("Flight search test completed successfully");

        } catch (Exception e) {
            log.error("Test failed with exception: " + e.getMessage());
            throw e;
        } finally {
            // Navigate back to home page for next iteration
            log.info("Navigating back to home page");
            driver.get("https://www.emirates.com/lk/english/");
        }
        }
    }

