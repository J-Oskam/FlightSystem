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

public class Nhan_Nhu_Justin_ReservationSceneController extends Nhan_Nhu_Justin_ReservationManager {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private  List<Nhan_Nhu_Justin_Reservation> reservationList  = new ArrayList<>();
    private static int currentIndex = -1;

    @FXML
    Label displayLabel;
    @FXML
    TextField FlightNumberTextField;
    @FXML
    TextField SourceTextField;
    @FXML
    TextField DestinationField;
    @FXML
    TextField FareField;
    @FXML
    TextField FirstNameTextField;
    @FXML
    TextField LastNameTextField;
    @FXML
    TextField AgeTextField;
    @FXML
    DatePicker DateField;
    @FXML
    Button addButton;
    @FXML
    ListView<String> reservationListView;


    // Initialize the ListView with three initial flights
    Nhan_Nhu_Justin_Flight flight1 = new Nhan_Nhu_Justin_Flight(1, 150, "City A", "City B", new Date(), 150);
    Nhan_Nhu_Justin_Flight flight2 = new Nhan_Nhu_Justin_Flight(2, 200, "City C", "City D", new Date(), 250);
    Nhan_Nhu_Justin_Flight flight3 = new Nhan_Nhu_Justin_Flight(3, 180, "City E", "City F", new Date(), 350);

    Nhan_Nhu_Justin_Passenger passenger1 = new Nhan_Nhu_Justin_Passenger(1,"Bob","Murphy",47);
    Nhan_Nhu_Justin_Passenger passenger2 = new Nhan_Nhu_Justin_Passenger(2,"Joy","Des Lauriers",85);
    Nhan_Nhu_Justin_Passenger passenger3 = new Nhan_Nhu_Justin_Passenger(3,"Kevin","Jones",18);

    Nhan_Nhu_Justin_Reservation reservation1 = new Nhan_Nhu_Justin_Reservation(flight1,passenger1,new Date());
    Nhan_Nhu_Justin_Reservation reservation2 = new Nhan_Nhu_Justin_Reservation(flight2,passenger2,new Date());
    Nhan_Nhu_Justin_Reservation reservation3 = new Nhan_Nhu_Justin_Reservation(flight3,passenger3,new Date());



    public void displayReservationInfo(Nhan_Nhu_Justin_Reservation reservation){
        String text = "=====================" + "\n";
        text += "Flight: " + reservation.getFlight() + "\n";
        text += "Passenger: " + reservation.getPassenger() + "\n";
        text += "Travel Date: " + reservation.getTravelDate() + "\n";
        text += "======================" + "\n";
        displayLabel.setText(text);
    }

    public void onPreviousButtonClick(){
        viewPrevious();
    }

    public void onNextButtonClick(){
        viewNext();
    }

    public void onAddButtonClick(){
//        addReservation();
    }

    public void onViewButtonClick(){

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
    private void switchToFlightScene(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("flight.fxml"));
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

}
