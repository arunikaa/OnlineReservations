package com.org.Utility;

public class TripData {
    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;
    private String cabinClass;
    private String tripType;
    private String adultPax;

    public TripData(String origin, String destination, String departureDate,
                    String arrivalDate, String cabinClass, String tripType, String adultPax) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.cabinClass = cabinClass;
        this.tripType = tripType;
        this.adultPax = adultPax;
    }

    // Getters
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getDepartureDate() { return departureDate; }
    public String getArrivalDate() { return arrivalDate; }
    public String getCabinClass() { return cabinClass; }
    public String getTripType() { return tripType; }
    public String getAdultPax() { return adultPax; }

    @Override
    public String toString() {
        return String.format("Trip: %s -> %s | Departure: %s | Arrival: %s | Class: %s | Passengers: %s",
                origin, destination, departureDate, arrivalDate, cabinClass, adultPax);
    }
}
