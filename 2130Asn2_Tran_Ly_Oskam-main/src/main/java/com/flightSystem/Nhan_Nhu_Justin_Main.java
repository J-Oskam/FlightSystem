package com.flightSystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class Nhan_Nhu_Justin_Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Nhan_Nhu_Justin_Main.class.getResource("main.fxml"));
        Scene passengerScene = new Scene(loader.load(), 800, 600);

        stage.setTitle("Travel Reservation System");
        stage.setScene(passengerScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    private void switchToFlightScene(ActionEvent event) {
        switchScene("flight.fxml", event);
    }

    @FXML
    private void switchToPassengerScene(ActionEvent event) {
        switchScene("passenger.fxml", event);
    }

    @FXML
    private void switchToReservationScene(ActionEvent event){
        switchScene("reservation.fxml", event);
    }

    private void switchScene(String sceneName, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) (((MenuItem) event.getSource()).getParentPopup().getOwnerWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        Platform.exit();
    }

}