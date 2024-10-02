package com.org.Pages;

import com.org.Base.BaseTest;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class SearchFlights extends BaseTest {

    WebDriver driver;
    Logger log;
    String fullDepartureDate;
    String fullArrivalDate;
    private final By txtBox_departurePoint = By.xpath("(//input[@class='js-field-input field__input js-dropdown-open field__input--active'])[1]");
    private final By txtBox_arrivalPoint = By.xpath("//input[@class='js-field-input field__input js-dropdown-open field__input--active'][@name='Arrival airport']");
    private final By btn_continue = By.xpath("//button[contains(@class,'cta cta--large cta--primary js-continue-search-flight search-flight__continue--cta')]");
    private final By txtBox_departureField = By.xpath("//input[@id='search-flight-date-picker--depart']");
    WebElement cabinType;

    public SearchFlights(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;

    }

    @Test
    public String setDepartureAirport(String departureCity) {

        log.info("Start setDepartureAirport ");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement dpt = driver.findElement(txtBox_departurePoint);
        dpt.click();
        dpt.clear();
        dpt.sendKeys(departureCity);
        dpt.click();
        dpt.sendKeys(Keys.ENTER);
        return departureCity;


    }


    @Test
    public String setArrivalAirport(String arrivalCity) {

        log.info("Start setArrivalAirport ");
        WebElement arr = driver.findElement(txtBox_arrivalPoint);
        arr.click();
        arr.clear();
        arr.sendKeys(arrivalCity);
        arr.click();
        arr.sendKeys(Keys.ENTER);
        return arrivalCity;

    }


    @Test
    public void clickContinueBtn() {

        try {
            driver.findElement(btn_continue).click();
        } catch (Exception e) {
            log.error(e.getMessage());
        }


    }

    @Test
    public void clickDepartureSelection() {
        log.info("start clickDepartureSelection");
        try {
            WebElement ds = driver.findElement(txtBox_departureField);
            ds.click();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public String enterDepartureDate(String depDate) throws InterruptedException {

        log.info("Start enterDepartureDate");
        String[] dateParts = depDate.split("/");

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        StringBuilder sb = new StringBuilder();
        sb.append(day).append(month).append(year);

        fullDepartureDate = sb.toString();
        By btn_departureDate = By.xpath("//button[@data-string='" + fullDepartureDate + "']");
        WebElement dep = driver.findElement(btn_departureDate);
        Thread.sleep(100);
        dep.click();
        System.out.println(fullDepartureDate);

        return depDate;


    }

    @Test
    public void enterArrivalDate(String arrDate) {

        log.info("Start enterArrivalDate ");

        String fullArrDate = arrDate.replaceAll("/", "");
        System.out.println(fullArrDate);
        By fullArrivalDate = By.xpath("//button[@data-string='" + fullArrDate + "']");
        try {
            WebElement arrivalDate = driver.findElement(fullArrivalDate);
            arrivalDate.click();
        } catch (Exception e) {
            log.error(e.getMessage());

        }


    }

    @Test
    public void clickPassengersBox() {
        By passengers = By.xpath("//input[@name='Passengers']");
        driver.findElement(passengers).click();
    }

    @Test
    public int setAdultPaxCount(int noOfAdults) {

        log.info("Start setAdultPaxCount");

        WebElement initialAdults = null;
        try {
            WebElement adults = driver.findElement(By.xpath("//div[@class='increment-field js-increment-field increment-field--subtract-disabled']//span[@class='icon icon-plus']"));
            initialAdults = driver.findElement(By.xpath("//span[@class='increment-field__value js-increment-value'][normalize-space()='1']"));
            int adultCount = Integer.parseInt(initialAdults.getText());


            if (noOfAdults > 1) {
                for (int i = 1; i < noOfAdults; i++) {
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                    wait.until(ExpectedConditions.elementToBeClickable(adults)).click();

                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        assert initialAdults != null;
        return Integer.parseInt(initialAdults.getText());
    }

    @Test
    public void clickCabinClass()
    {
        log.info("Start clickCabinClass ");

        try {
            cabinType = driver.findElement(By.xpath("//input[@id='field-search-flight-class']"));
            cabinType.click();
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
    }

    @Test
    public String setCabinClass(String cabinClass)
    {
        WebElement cabin = driver.findElement(By.xpath("//p[normalize-space()='"+ cabinClass +"']"));
        cabin.click();
        System.out.println(cabinType.getText());

        return cabinType.getText();
    }

    @Test
    public void clickSearchButton()
    {
        WebElement search = driver.findElement(By.xpath("//form[@method='post']//button[@type='submit']"));
        search.click();
    }
}
