package com.flightSystem;

import java.util.Date;

public class Nhan_Nhu_Justin_Reservation {
    private Nhan_Nhu_Justin_Flight flight;
    private Nhan_Nhu_Justin_Passenger passenger;
    private Date travelDate;



    public Nhan_Nhu_Justin_Reservation(Nhan_Nhu_Justin_Flight flight, Nhan_Nhu_Justin_Passenger passenger, Date travelDate) {
        this.flight = flight;
        this.passenger = passenger;
        this.travelDate = travelDate;
    }


    public Nhan_Nhu_Justin_Flight getFlight() {
        return flight;
    }

    public Nhan_Nhu_Justin_Passenger getPassenger() {
        return passenger;
    }


    public Date getTravelDate() {
        return travelDate;
    }

    //Calculates total fare for a reservation
    public double calculateTotalFare() {

        // Check if the passenger's flight number matches the reservation's flight number
        if (passenger.getFlightNumber() == flight.getFlightNumber()) {
            return flight.getFlightFare();
        }
        return 0;
    }
}


