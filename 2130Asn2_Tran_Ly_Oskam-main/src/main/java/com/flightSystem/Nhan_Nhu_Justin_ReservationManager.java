package com.flightSystem;

import javafx.scene.control.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Nhan_Nhu_Justin_ReservationManager extends Nhan_Nhu_Justin_InputValidator{
        private  List<Nhan_Nhu_Justin_Reservation> reservations = new ArrayList<>();
        private  List<Nhan_Nhu_Justin_Passenger> passengers = new ArrayList<>();
        private  List<Nhan_Nhu_Justin_Reservation> flights = new ArrayList<>();



    private static int currentIndex = -1;
    private DatePicker DateField;

    public void addReservation() {


            int flightNumber = validateIntInput("Enter flight number:");
            String source = validateStringInput("Enter source:");
            String destination = validateStringInput("Enter destination:");
            double flightFare =validateDoubleInput("Enter flight fare:");
            double tax = validateDoubleInput("Enter flight tax:");
            String firstName = validateStringInput("Enter passenger's first name:");
            String lastName = validateStringInput("Enter passenger's last name:");
            int age = validateIntInput("Enter passenger's age:");
            //Date travelDate = parseDate("Enter travel date (yyyy-MM-dd):");
            Date travelDate = validateDatePicker(DateField);

            Nhan_Nhu_Justin_Flight flight = new Nhan_Nhu_Justin_Flight(flightNumber, source, destination, flightFare);
            Nhan_Nhu_Justin_Passenger passenger = new Nhan_Nhu_Justin_Passenger(1,firstName, lastName, age, flightNumber);
            Nhan_Nhu_Justin_Reservation reservation = new Nhan_Nhu_Justin_Reservation(flight, passenger, travelDate);

            reservations.add(reservation);
            currentIndex = reservations.size() - 1;

            alert("Reservation added successfully!");
        }

        public void displayReservation() {
            if (currentIndex >= 0 && currentIndex < reservations.size()) {
                Nhan_Nhu_Justin_Reservation currentReservation = reservations.get(currentIndex);
                System.out.println("===== Reservation =====" + currentIndex++);
                displayReservationDetails(currentReservation);
            } else {
                System.out.println("No reservation to display.");
            }
        }

    public void displayReservationsForFlightAndDate(int flightNumber, Date date) {
        boolean found = false;

        for (Nhan_Nhu_Justin_Reservation reservation : reservations) {
            if (reservation.getFlight().getFlightNumber() == flightNumber &&
                    reservation.getTravelDate().equals(date) ){
                if (!found) {
                    System.out.println("===== Reservations for Flight " + flightNumber +
                            " on " + date + " =====");
                    found = true;
                }
                displayReservationDetails(reservation);
                System.out.println("----------------------------------");
            }
        }
        if (!found) {
            System.out.println("No reservations found for Flight " + flightNumber + " on " + date + ".");
        }
    }





    //Calculates total fare for a flight
    public double calculateTotalFare() {
        int matchPassengers =0;
        // Check if the passenger's flight number matches the reservation's flight number
        for (Nhan_Nhu_Justin_Reservation reservation : reservations) {
            if (reservation.getPassenger().getFlightNumber() == reservation.getFlight().getFlightNumber()) {
                matchPassengers++;
            }
            return reservation.getFlight().getFlightFare() * matchPassengers;
        }
        return 0;

    }

    // Return all passengers in the same flight
    public List<Nhan_Nhu_Justin_Passenger> getPassengersForFlight(Nhan_Nhu_Justin_Flight flight){

        List<Nhan_Nhu_Justin_Passenger> passengersForFlight = new ArrayList<>();

        for (Nhan_Nhu_Justin_Reservation reservation : reservations) {
            if (reservation.getFlight().getFlightNumber() == flight.getFlightNumber()) {
                passengersForFlight.add(reservation.getPassenger());
            }
        }

        return passengersForFlight;
    }

    public String displayReservationDetails(Nhan_Nhu_Justin_Reservation reservation){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return   "Flight number: " + reservation.getFlight().getFlightNumber()
                +"Source: " + reservation.getFlight().getSource()
                +"Destination: " + reservation.getFlight().getDestination()
                +"Date of travel: " + dateFormat.format(reservation.getTravelDate()
                +"Passenger: " + reservation.getPassenger().getFirstName() + " "
                +reservation.getPassenger().getLastName() + ", Age: " + reservation.getPassenger().getAge()
                +"Total Fare Amount: " + reservation.calculateTotalFare());

    }

    public String viewPrevious() {
        if (currentIndex > 0) {
            currentIndex--;
            displayReservationDetails(reservations.get(currentIndex));
            return "hi";
        } else {
            alert("No previous reservation available.");
            return "no hi";
        }
    }

    public String viewNext() {
        if (currentIndex < reservations.size() - 1) {
            currentIndex++;
            displayReservationDetails(reservations.get(currentIndex));
            return "hi";
        } else {
            alert("No previous reservation available.");
            return " no hi";
        }
    }



}
