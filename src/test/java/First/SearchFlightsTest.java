package First;

import com.org.Base.BaseTest;

import com.org.Pages.SearchFlights;
import com.org.Utility.RetryAnalyzer;
import com.org.Utility.Utilities;
import org.testng.annotations.Test;

public class SearchFlightsTest extends BaseTest {


    @Test(dataProviderClass = Utilities.class, dataProvider = "dataProvider2", retryAnalyzer = RetryAnalyzer.class)
    public void setDepartureAirport(String data) throws InterruptedException {
        SearchFlights sf = new SearchFlights(driver, log);

        Utilities util = new Utilities(driver, log);
        util.scrollUi();
        String[] tripInfo = data.split(",");


        sf.setDepartureAirport(tripInfo[0]);
        sf.setArrivalAirport(tripInfo[1]);

//            wait(1000);
        sf.clickContinueBtn();
        sf.clickDepartureSelection();
        util.scrollUi();
//            sf.enterDepartureDate(tripInfo[2]);
        util.scrollUi();

        sf.setDepartureDate(tripInfo[2]);

        sf.setDepartureDate(tripInfo[3]);
        util.scrollUi();
        sf.clickPassengersBox();
        sf.setAdultPaxCount(tripInfo[6]);
        sf.clickCabinClass();
        String cabin = sf.setCabinClass(tripInfo[4]);
        //           Assert.assertEquals(cabin,"Business Class");
        sf.clickSearchButton();
        driver.get("https://www.emirates.com/lk/english/");


    }


}
