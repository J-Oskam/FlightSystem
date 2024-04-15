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

public class Nhan_Nhu_Justin_PassengerSceneController extends Nhan_Nhu_Justin_InputValidator implements Initializable , IDisplayable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private  List<Nhan_Nhu_Justin_Passenger> passengerList  = new ArrayList<>();
    private static int currentIndex = -1;


    @FXML
    Label displayLabel;
    @FXML
    TextField PassportNoTextField;
    @FXML
    TextField FirstNameTextField;
    @FXML
    TextField LastNameTextField;
    @FXML
    TextField AgeTextField;
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    ListView<String> passengerListView;

    //----Methods ----//

    public void displayPassengerInfo(Nhan_Nhu_Justin_Passenger passenger){
        String text ="====================="  + "\n";
        text +=     "Passport Number: " +passenger.getPassportNumber() + "\n";
        text +=     "First Name: "+ passenger.getFirstName() +"\n";
        text +=     "Last Name: " +passenger.getLastName()+ "\n";
        text +=     "Age: " +passenger.getAge()+ "\n";
        if(passenger.getFlightNumber() != -1){text +="Flight Number: " +passenger.getFlightNumber()+ "\n";}
        text +=      "======================" +"\n";

        displayLabel.setText(text);
    }



    public void displayPassengerInfoByPassportNumber(int passportNumber) {
        Nhan_Nhu_Justin_Passenger foundPassenger = null;

        // Search for the passenger with the specified passport number
        for (Nhan_Nhu_Justin_Passenger passenger : passengerList) {
            if (passenger.getPassportNumber() == passportNumber) {
                foundPassenger = passenger;
                break;
            }
        }

        if (foundPassenger != null) {
            // Passenger with the specified passport number found, display their information
            String text = "=====================" + "\n";
            text += "Passport Number: " + foundPassenger.getPassportNumber() + "\n";
            text += "First Name: " + foundPassenger.getFirstName() + "\n";
            text += "Last Name: " + foundPassenger.getLastName() + "\n";
            text += "Age: " + foundPassenger.getAge() + "\n";
            text += "======================" + "\n";

            displayLabel.setText(text);
        } else {
            // Passenger with the specified passport number not found
            displayLabel.setText("Passenger with passport number " + passportNumber + " not found.");
        }
    }


    private void addPassenger(Nhan_Nhu_Justin_Passenger newPassenger){
        passengerList.add(newPassenger);
        announce("New Passenger Added.");
        clearAllPassengerTextFields();
    }

    private void deletePassengerByPassport(int passportNo) {

        Iterator<Nhan_Nhu_Justin_Passenger> iterator = passengerList.iterator();

        while (iterator.hasNext()) {
            Nhan_Nhu_Justin_Passenger passenger = iterator.next();
            if (passenger.getPassportNumber() == passportNo) {
                iterator.remove(); // Use the iterator's remove method to safely remove the element
                announce("Passenger Deleted.");
                displayLabel.setText(" ");
                clearAllPassengerTextFields();
                return; // Exit the method after deletion
            }

        }
        // If no matching passenger is found
        alert("Passenger not found and/or invalid passport input.");



    }

    private void clearAllPassengerTextFields() {
        PassportNoTextField.clear();
        FirstNameTextField.clear();
        LastNameTextField.clear();
        AgeTextField.clear();
    }

    public void viewPrevious() {
        if (currentIndex > 0) {
            currentIndex--;
            displayPassengerInfo(passengerList.get(currentIndex));

        } else {
            alert("No previous passenger available.");
        }
    }

    public void viewNext() {
        if (currentIndex < passengerList.size() - 1) {
            currentIndex++;
            displayPassengerInfo(passengerList.get(currentIndex));

        } else {
            alert("No next passenger available.");

        }
    }
    private ObservableList<Nhan_Nhu_Justin_Passenger> getPassengers() {
        ObservableList<Nhan_Nhu_Justin_Passenger> passengerOList = FXCollections.observableArrayList();

        // Adding all elements from passengerList to passengerOList
        passengerOList.addAll(passengerList);

        return passengerOList;
    }

    private Nhan_Nhu_Justin_Passenger validateAndCreatePassenger() {
        // Validate input and create a new passenger
        String firstName = validateStringInput(FirstNameTextField.getText());
        String lastName = validateStringInput(LastNameTextField.getText());
        int passportNo = validateIntInput(PassportNoTextField.getText());
        int age = validateIntInput(AgeTextField.getText());

        // Check if all validations passed
        if (passportNo != -1 && age != -1 && firstName != null && lastName != null) {
            if (isPassportNumberUnique(passportNo)) {
                // Passport number is unique, create and return the new passenger
                return new Nhan_Nhu_Justin_Passenger(passportNo, firstName, lastName, age);
            } else {
                alert("Passport Number must be unique.");
                return null; // Validation failed, return null
            }
        }
        return null;
    }
    private boolean isPassportNumberUnique ( int passportNumber){
        // Check if the passport number already exists in your list of passengers
        for (Nhan_Nhu_Justin_Passenger passenger : passengerList) {
            if (passenger.getPassportNumber() == passportNumber) {
                return false; // Passport number is not unique
            }
        }
        return true; // Passport number is unique
    }

    public ObservableList<String> getPassengerForListView(ObservableList<Nhan_Nhu_Justin_Passenger> passengers) {
        ObservableList<String> formattedPassengerList = FXCollections.observableArrayList();

        for (Nhan_Nhu_Justin_Passenger passenger : passengers) {
            String formattedPassenger = String.format(
                    "Passport Number: %d, First Name: %s, Last Name: %s, Age: %d",
                    passenger.getPassportNumber(), passenger.getFirstName(), passenger.getLastName(),
                    passenger.getAge()
            );

            formattedPassengerList.add(formattedPassenger);
        }
        return formattedPassengerList;
    }

    // Method to update the ListView with the latest list of passengers
    public void updateListView() {
        passengerListView.setItems(getPassengerForListView(getPassengers()));
    }
    private Nhan_Nhu_Justin_Passenger getSelectedPassenger() {
        int selectedIndex = passengerListView.getSelectionModel().getSelectedIndex();
        return (selectedIndex >= 0) ? passengerList.get(selectedIndex) : null;
    }

    //----Handle Buttons ----//


    @FXML
    public void onAddButtonClick() {
        // Validate input and create a new passenger
        Nhan_Nhu_Justin_Passenger newPassenger = validateAndCreatePassenger();

        // Check if the passenger creation was successful
        if (newPassenger != null) {
            // add Passenger and Display passenger information
            addPassenger(newPassenger);
            displayPassengerInfo(newPassenger);

            updateListView();

            // Clear all text fields after adding a new passenger
            clearAllPassengerTextFields();
        } else {
            alert("Validation failed. Please correct inputs.");
        }

        if(onPassengerListItemClicked()) {
            //Add the selected passenger to the passengerList
            Nhan_Nhu_Justin_Passenger selectedPassenger = getSelectedPassenger();
            if (selectedPassenger != null) {
                passengerList.add(selectedPassenger);
                updateListView();
            }
        }
    }




    public void onDeleteButtonClick() {
        int passportNo = -1;

        if(onPassengerListItemClicked() && passportNo == -1) {
            //Delete by selection - Remove the selected passenger from the passengerList
            Nhan_Nhu_Justin_Passenger selectedPassenger = getSelectedPassenger();
            if (selectedPassenger != null) {
                passengerList.remove(selectedPassenger);
                updateListView();
            }
        } else {
            passportNo = validateIntInput(PassportNoTextField.getText());
            //DELETE bY passport input
            deletePassengerByPassport(passportNo);
            // Update the ListView after deleting the passenger
            updateListView();
        }


    }

    public void onNextButtonClick() {
        viewNext();
    }
    public void onPreviousButtonClick() {
        viewPrevious();
    }
    @FXML
    public void onViewButtonClick() {
        // Validate input and delete flight
        int passportNo = validateIntInput(PassportNoTextField.getText());
        displayPassengerInfoByPassportNumber(passportNo);

        // Clear all text fields after deleting a flight
        clearAllPassengerTextFields();
    }


    //---- Switch Scenes----//
    public void switchToMainScene(ActionEvent actionEvent) {
        try{
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
            stage = (Stage) (((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToFlightScene(ActionEvent event)  {
        try{
            root = FXMLLoader.load(getClass().getResource("flight.fxml"));
            stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void switchToReservationScene(ActionEvent event)  {
        try{
            root = FXMLLoader.load(getClass().getResource("reservation.fxml"));
            stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     boolean onPassengerListItemClicked() {
        // Get the selected passenger index
        int selectedIndex = passengerListView.getSelectionModel().getSelectedIndex();

        // Check if an item is selected
        if (selectedIndex >= 0) {
            // Get the corresponding passenger object from the list
            Nhan_Nhu_Justin_Passenger selectedPassenger = passengerList.get(selectedIndex);

            // Display or do something with the selected passenger
            displayPassengerInfo(selectedPassenger);
        }
        return true;
    }
    // --- Initialization ----//
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up event handlers for buttons

        addButton.setOnAction(e -> onAddButtonClick());
        deleteButton.setOnAction(e -> onDeleteButtonClick());


        // Initialize the ListView with three initial flights
        Nhan_Nhu_Justin_Passenger initialP1 = new Nhan_Nhu_Justin_Passenger(1,  "John", "Doe", 25);
        Nhan_Nhu_Justin_Passenger initialP2 = new Nhan_Nhu_Justin_Passenger(2,  "Jane", "Smith", 30);
        Nhan_Nhu_Justin_Passenger initialP3 = new Nhan_Nhu_Justin_Passenger(3,  "Bob", "Johnson", 22);

        passengerList.add(initialP1);
        passengerList.add(initialP2);
        passengerList.add(initialP3);

        List<String> initialPassengerValues = Arrays.asList(
                "Passport Number: 1, First Name: John, Last Name: Doe, Age: 25",
                "Passport Number: 2, First Name: Jane, Last Name: Smith, Age: 30",
                "Passport Number: 3, First Name: Bob, Last Name: Johnson, Age: 22"
        );

        ObservableList<String> formattedPassengers = FXCollections.observableArrayList(initialPassengerValues);

        passengerListView.setItems(formattedPassengers);

        // Set up event handlers for passengerListView
        passengerListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onPassengerListItemClicked();
            }
        });

    }
}
