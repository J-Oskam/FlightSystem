package com.flightSystem;

import java.util.Date;

public class Nhan_Nhu_Justin_Flight {
    private int flightNumber;
    private int maxPassengers;
    private String source;
    private String destination;

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    private Date travelDate;
    private double flightFare;
    private double tax = .13;

    public Nhan_Nhu_Justin_Flight(int flightNumber, int maxPassengers, String source, String destination, Date travelDate, double flightFare) {
        this.flightNumber = flightNumber;
        this.maxPassengers = maxPassengers;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.flightFare = flightFare;
        this.tax = tax *flightFare;
    }

    public Nhan_Nhu_Justin_Flight(int flightNumber, String source, String destination, double flightFare){
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.flightFare = flightFare;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getMaxPassengers() {return maxPassengers;}
    public void setMaxPassengers(int maxPassengers){ this.maxPassengers = maxPassengers;};

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFlightFare(double flightFare) {
        this.flightFare = flightFare;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }


    public double getFlightFare() {
        return flightFare;
    }
    public double getTotalFlightFare() {
        return flightFare +tax;
    }
    // Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
    // then press Enter. You can now see whitespace characters in your code.

}
