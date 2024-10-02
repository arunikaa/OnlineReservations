package First;

import com.org.Base.BaseTest;
import com.org.Pages.SearchFlights;
import com.org.Utility.Utilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchFlightsTest extends BaseTest {


    @Test(dataProviderClass = Utilities.class,dataProvider = "dataProvider2")
    public void setDepartureAirport(String data) throws InterruptedException {
        SearchFlights sf = new SearchFlights(driver,log);
        Utilities util = new Utilities(driver,log);
        util.scrollUi();
        String[] tripInfo = data.split(",");


            sf.setDepartureAirport(tripInfo[0]);
            sf.setArrivalAirport(tripInfo[1]);
            sf.clickContinueBtn();
            sf.clickDepartureSelection();
            sf.enterDepartureDate(tripInfo[2]);
            sf.enterArrivalDate(tripInfo[3]);
            util.scrollUi();
            sf.clickPassengersBox();
            int adultCount = sf.setAdultPaxCount(7);
           // Assert.assertEquals(adultCount, 7);
            sf.clickCabinClass();
            String cabin = sf.setCabinClass(tripInfo[4]);
           // Assert.assertEquals(cabin,"Business Class");


        sf.clickCabinClass();
        sf.clickSearchButton();
        driver.get("https://www.emirates.com/lk/english/");





    }



}
