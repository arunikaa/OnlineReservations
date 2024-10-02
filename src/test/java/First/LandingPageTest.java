package First;


import com.org.Pages.LandingPage;
import com.org.Base.BaseTest;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseTest {


 @Test
    public void testLogin()
 {
     LandingPage lp = new LandingPage(driver,log);
          lp.clickSearchFlights();





 }



}
