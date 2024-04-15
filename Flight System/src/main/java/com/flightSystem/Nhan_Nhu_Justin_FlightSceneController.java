package com.flightSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Nhan_Nhu_Justin_FlightSceneController extends Nhan_Nhu_Justin_InputValidator implements Initializable , IDisplayable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    private List<Nhan_Nhu_Justin_Flight> flightList = new ArrayList<>();
    private static int currentIndex = -1;
    @FXML
    Label displayLabel;
    @FXML
    TextField FlightNumberTextField;
    @FXML
    TextField MaxPassengersTextField;
    @FXML
    TextField SourceTextField;
    @FXML
    TextField FlightFareTextField;
    @FXML
    TextField DestinationTextField;

    @FXML
    DatePicker TravelDatePicker;
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    ListView<String> flightListView;

    // ----- Methods ------//

    public void displayFlightInfo(Nhan_Nhu_Justin_Flight flight) {
        String text = "=====================" + "\n";
        text += "Flight Number: " + flight.getFlightNumber() + "\n";
        text += "Capacity: " + flight.getMaxPassengers() + "\n";
        text += "Source: " + flight.getSource() + "\n";
        text += "Destination: " + flight.getDestination() + "\n";
        text += "Flight Fare: " + flight.getFlightFare() + "\n";
        text += "Tax: " + flight.getTax() + "\n";
        text += "Total fare: " + flight.getTotalFlightFare() + "\n";


        text += "======================" + "\n";
        displayLabel.setText(text);
    }

    public void displayFlightInfoByNumber(int flightNumber) {
        Nhan_Nhu_Justin_Flight foundFlight = null;

        // Search for the flight with the specified flight number
        for (Nhan_Nhu_Justin_Flight flight : flightList) {
            if (flight.getFlightNumber() == flightNumber) {
                foundFlight = flight;
                break;
            }
        }

        if (foundFlight != null) {
            // Flight with the specified number found, display its information
            String text = "=====================" + "\n";
            text += "Flight Number: " + foundFlight.getFlightNumber() + "\n";
            text += "Capacity: " + foundFlight.getMaxPassengers() + "\n";
            text += "Source: " + foundFlight.getSource() + "\n";
            text += "Destination: " + foundFlight.getDestination() + "\n";
            text += "Flight Fare: " + foundFlight.getFlightFare() + "\n";
            text += "Tax: " + foundFlight.getTax() + "\n";
            text += "Total fare: " + foundFlight.getTotalFlightFare() + "\n";
            text += "======================" + "\n";

            displayLabel.setText(text);
        } else {
            // Flight with the specified number not found
            displayLabel.setText("Flight with number " + flightNumber + " not found.");
        }
    }

    private void addFlight(Nhan_Nhu_Justin_Flight newFlight) {
        flightList.add(newFlight);
        announce("New Flight Added.");
        clearAllFlightTextFields();
    }

    private void deleteFlightByFlightNumber(int flightNo) {
        Iterator<Nhan_Nhu_Justin_Flight> iterator = flightList.iterator();

        while (iterator.hasNext()) {
            Nhan_Nhu_Justin_Flight flight = iterator.next();
            if (flight.getFlightNumber() == flightNo) {
                iterator.remove(); // Use the iterator's remove method to safely remove the element
                announce("Flight" + flightNo + " Deleted.");
                displayLabel.setText(" ");
                clearAllFlightTextFields();
                return; // Exit the method after deletion
            }
        }

        // If no matching passenger is found
        alert("Flight not found or invalid Flight Number input.");
    }

    private void clearAllFlightTextFields() {
        FlightNumberTextField.clear();
        MaxPassengersTextField.clear();
        SourceTextField.clear();
        DestinationTextField.clear();
        FlightFareTextField.clear();
    }

    public void viewPrevious() {
        if (currentIndex > 0) {
            currentIndex--;
            displayFlightInfo(flightList.get(currentIndex));

        } else {
            alert("No previous reservation available.");
        }
    }

    public void viewNext() {
        if (currentIndex < flightList.size() - 1) {
            currentIndex++;
            displayFlightInfo(flightList.get(currentIndex));

        } else {
            alert("No next reservation available.");

        }
    }
    // -- ListView ---//

    private ObservableList<Nhan_Nhu_Justin_Flight> getFlights() {
        ObservableList<Nhan_Nhu_Justin_Flight> flightOList = FXCollections.observableArrayList();
        // Adding all elements from flightList to flightOList
        flightOList.addAll(flightList);
        return flightOList;
    }

    private Nhan_Nhu_Justin_Flight validateAndCreateFlight() {

        int flightNo = validateIntInput(FlightNumberTextField.getText());
        int maxPassengers = validateIntInput(MaxPassengersTextField.getText());
        String source = validateStringInput(SourceTextField.getText());
        String destination = validateStringInput(DestinationTextField.getText());
        double flightFare = validateDoubleInput(FlightFareTextField.getText());
        Date travelDate = validateDatePicker(TravelDatePicker);


        // Check if all validations passed
        if (flightNo != -1 && maxPassengers != -1 && source != null && destination != null && travelDate != null && flightFare != -1) {
            if (isFlightNumberUnique(flightNo)) {
                return new Nhan_Nhu_Justin_Flight(flightNo, maxPassengers, source, destination, travelDate, flightFare);
            } else {
                alert("Flight Number must be unique.");
                return null; // Validation failed, return null
            }
        }
        return null;
    }
    private boolean isFlightNumberUnique ( int flightNumber){
        // Check if the passport number already exists in your list of passengers
        for (Nhan_Nhu_Justin_Flight flight : flightList) {
            if (flight.getFlightNumber() == flightNumber) {
                return false; // Passport number is not unique
            }
        }
        return true; // Flight number is unique
    }

    public ObservableList<String> getFlightForListView(ObservableList<Nhan_Nhu_Justin_Flight> flights) {
        ObservableList<String> formattedFlightList = FXCollections.observableArrayList();

        for (Nhan_Nhu_Justin_Flight flight : flights) {
            String formattedFlight = String.format(
                    "Flight Number: %d, Source: %s, Destination: %s, Max Passengers: %d, Travel date: %s, Flight fare: %.2f, Tax: %.2f, Total Fare: %.2f",
                    flight.getFlightNumber(), flight.getSource(), flight.getDestination(),
                    flight.getMaxPassengers(), flight.getTravelDate(), flight.getFlightFare(), flight.getTax(), flight.getTotalFlightFare()
            );

            formattedFlightList.add(formattedFlight);
        }
        return formattedFlightList;
    }

    // Method to update the ListView with the latest list of flights
    public void updateListView() {
        flightListView.setItems(getFlightForListView(getFlights()));
    }
    private Nhan_Nhu_Justin_Flight getSelectedFlight() {
        int selectedIndex = flightListView.getSelectionModel().getSelectedIndex();
        return (selectedIndex >= 0) ? flightList.get(selectedIndex) : null;
    }

    // ------- Handle Buttons ------//
    @FXML
    public void onAddButtonClick() {
        // Validate input and create a new flight
        Nhan_Nhu_Justin_Flight newFlight = validateAndCreateFlight();

        // Check if the flight creation was successful
        if (newFlight != null) {
            // add Flight and Display flight information
            addFlight(newFlight);
            displayFlightInfo(newFlight);

            updateListView();

            // Clear all text fields after adding a new flight
            clearAllFlightTextFields();
        } else {
            alert("Validation failed. Please correct inputs.");
        }

        //Add the selected flight to the flightList
        if(onFlightListItemClicked()) {
            Nhan_Nhu_Justin_Flight selectedFlight = getSelectedFlight();
            if (selectedFlight != null) {
                flightList.add(selectedFlight);
                updateListView();
            }
        }
    }

    @FXML
    public void onDeleteButtonClick() {
        int flightNumber =-1;

        if(onFlightListItemClicked() && flightNumber == -1) {
            //Remove the selected flight from the flightList
            Nhan_Nhu_Justin_Flight selectedFlight = getSelectedFlight();
            if (selectedFlight != null) {
                flightList.remove(selectedFlight);
                updateListView();
            }
        }
        else {
            flightNumber = validateIntInput(FlightNumberTextField.getText());
            //delete by flight num input
            deleteFlightByFlightNumber(flightNumber);
            // Update the ListView after deleting the flight
            updateListView();
        }

    }

    @FXML
    public void onViewButtonClick() {
        // Validate input and display flight
        int flightNumber = validateIntInput(FlightNumberTextField.getText());
        displayFlightInfoByNumber(flightNumber);

        // Clear all text fields after displaying a flight
        clearAllFlightTextFields();
    }

    public void onNextButtonClick() {
        viewNext();
    }

    public void onPreviousButtonClick() {
        viewPrevious();
    }


    // ------- Switch Scenes ------//
    @FXML
    private void switchToPassengerScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("passenger.fxml"));
            stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToReservationScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("reservation.fxml"));
            stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void switchToMainScene(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
            stage = (Stage) (((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatFlight(Nhan_Nhu_Justin_Flight flight) {
        return String.format(
                "Flight Number: %d, Source: %s, Destination: %s, Max Passengers: %d, Travel date: %s, Flight fare: %.2f, Tax: %.2f, Total Fare: %.2f",
                flight.getFlightNumber(), flight.getSource(), flight.getDestination(),
                flight.getMaxPassengers(), flight.getTravelDate(), flight.getFlightFare(), flight.getTax(), flight.getTotalFlightFare()
        );
    }


    private boolean onFlightListItemClicked() {
        // Get the selected flight index
        int selectedIndex = flightListView.getSelectionModel().getSelectedIndex();

        // Check if an item is selected
        if (selectedIndex >= 0) {
            // Get the corresponding flight object from the list
            Nhan_Nhu_Justin_Flight selectedFlight = flightList.get(selectedIndex);

            // Display or do something with the selected flight
            displayFlightInfo(selectedFlight);
        }
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addButton.setOnAction(e -> onAddButtonClick());
        deleteButton.setOnAction(e -> onDeleteButtonClick());

        // Initialize the ListView with three initial flights
        Nhan_Nhu_Justin_Flight initialFlight1 = new Nhan_Nhu_Justin_Flight(1, 150, "City A", "City B", new Date(), 150);
        Nhan_Nhu_Justin_Flight initialFlight2 = new Nhan_Nhu_Justin_Flight(2, 200, "City C", "City D", new Date(), 250);
        Nhan_Nhu_Justin_Flight initialFlight3 = new Nhan_Nhu_Justin_Flight(3, 180, "City E", "City F", new Date(), 350);

        flightList.add(initialFlight1);
        flightList.add(initialFlight2);
        flightList.add(initialFlight3);

        // Format flights as strings
        List<String> initialFlightValues = Arrays.asList(
                formatFlight(initialFlight1),
                formatFlight(initialFlight2),
                formatFlight(initialFlight3)
        );

        ObservableList<String> formattedFlights = FXCollections.observableArrayList(initialFlightValues);

        flightListView.setItems(formattedFlights);

        // Set up event handlers for flightListView
        flightListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onFlightListItemClicked();
            }
        });
    }
}