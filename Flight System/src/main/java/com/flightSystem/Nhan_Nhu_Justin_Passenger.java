package com.flightSystem;

public class Nhan_Nhu_Justin_Passenger {
    private int passportNumber;
    private String firstName;
    private String lastName;
    private int age;
    private int flightNumber;

    public Nhan_Nhu_Justin_Passenger(int passportNo, String firstName, String lastName, int age) {
        this.passportNumber = passportNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public Nhan_Nhu_Justin_Passenger(int passportNo, String firstName, String lastName, int age, int flightNumber) {
        this.passportNumber = passportNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.flightNumber = flightNumber;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getFlightNumber() {
        return flightNumber;
    }




    //passenger table
//    TableColumn<Nhan_Nhu_Justin_Passenger, Integer> passportNumberColumn = new TableColumn<>("Passport No");
//    passportNumberColumn.setMinWidth(200);
//    passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("passportNumber"));
//
//    TableColumn<Nhan_Nhu_Justin_Passenger, String>  firstNameColumn = new TableColumn<>("First name");
//        passportNumberColumn.setMinWidth(200);
//        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//
//    TableColumn<Nhan_Nhu_Justin_Passenger, String>  lastNameColumn = new TableColumn<>("Last name");
//        passportNumberColumn.setMinWidth(200);
//        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//
//
//    TableColumn<Nhan_Nhu_Justin_Passenger, Integer>  ageColumn = new TableColumn<>("Age");
//        passportNumberColumn.setMinWidth(200);
//        passportNumberColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
//
//        passengerTable.setItems(getPassenger());
//        passengerTable.getColumns().addAll(passportNumberColumn,firstNameColumn,lastNameColumn,ageColumn);

}
