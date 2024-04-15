package com.flightSystem;

import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.Date;

public class Nhan_Nhu_Justin_InputValidator {


    public static String validateStringInput(String  stringInput) {

        if (stringInput.isEmpty()) {
            alert("Empty String Input Field. Please enter related information");
            return null;
        }
        return stringInput;
    }



    public static double validateDoubleInput( String stringInput ) {

        double doubleValue = 0.0;

        if (!stringInput.isEmpty()) {
            try {
                // Attempt to parse the input as a double
                doubleValue=  Double.parseDouble(stringInput);
                return doubleValue;
            } catch (NumberFormatException e) {
                alert("Invalid input. Please enter a valid number.");
                return -1;
            }
        } else {
            alert("Empty Number Input Field. Please enter a number");
            return -1;
        }


    }

    public static int validateIntInput(String stringInput) {

        int intValue = 0 ;


        if (stringInput.isEmpty()) {
            alert("Empty Number Input Field. Please enter a number");
            return -1;
        }
        else{
            try {
                // Attempt to parse the input as a double
                intValue=  Integer.parseInt(stringInput);
                return intValue;
            } catch (NumberFormatException e) {
                alert("Invalid input. Please enter a valid number.");
                return -1;
            }
        }


    }
    public static Date convertToLocalDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static Date validateDatePicker(DatePicker datePicker) {
        LocalDate localDate = datePicker.getValue();

        if (localDate != null) {
            try {
                // Convert the formatted string to java.sql.Date
                Date date = convertToLocalDateViaSqlDate(localDate);

                return date;
            } catch (IllegalArgumentException e) {
                // Handle the case when conversion fails
                alert("Error converting date. Please pick a valid date.");
                return null;
            }
        } else {
            alert("Error empty date input. Please pick a date.");
            return null;
        }
    }



    static void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    static void announce(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
