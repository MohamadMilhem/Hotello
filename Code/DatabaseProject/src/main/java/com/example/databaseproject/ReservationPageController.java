package com.example.databaseproject;

import Entities.ReservationHandler;
import Models.Reservation;
import Models.RoomSearchResult;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ReservationPageController implements Initializable {

    @FXML
    private Label adultsCountLabel;

    @FXML
    private Label amenitiesLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label childrenCountLabel;

    @FXML
    private Button confirmButton;

    @FXML
    private Label costPerNigthLabel;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private ComboBox<String> paymentMethodBox;

    @FXML
    private Label roomDescriptionLabel;

    @FXML
    private ImageView roomImage;

    @FXML
    private Label roomNumberLabel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Label totalAmountLabel;

    private static RoomSearchResult roomCard;
    private static List<String> amenities = new ArrayList<>();
    private static ArrayList<LocalDate> allowedStartDates = new ArrayList<>();
    private static ArrayList<LocalDate> allowedEndDates = new ArrayList<>();

    private static Reservation reservation = new Reservation();

    public static RoomSearchResult getRoomCard() {
        return roomCard;
    }

    public static void setRoomCard(RoomSearchResult roomCard) {
        ReservationPageController.roomCard = roomCard;
    }

    public static List<String> getAmenities() {
        return amenities;
    }

    public static void setAmenities(List<String> amenities) {
        ReservationPageController.amenities = amenities;
    }

    public static List<LocalDate> getAllowedStartDates(){
        List<Date> availableDates = ReservationHandler.getAvailableDates(roomCard.getRoomId());

        List<LocalDate> localDateList = new ArrayList<>();
        for (Date date : availableDates) {
            localDateList.add(date.toLocalDate());
        }
        return localDateList;
    }

    public  List<LocalDate> getAllowedEndDates(){
        List<Date> availableStartDates = ReservationHandler.getAvailableDates(roomCard.getRoomId());
        List<Date> availableDates = ReservationHandler.getAvailableEndDates(Date.valueOf(startDatePicker.getValue()), availableStartDates);

        List<LocalDate> localDateList = new ArrayList<>();
        for (Date date : availableDates){
            localDateList.add(date.toLocalDate());
        }
        return localDateList;
    }
    private void loadsHotelPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HotelPage.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        roomNumberLabel.setText(roomCard.getRoomNumber());
        hotelNameLabel.setText(HotelPageController.getChosenHotel().getHotelName());
        childrenCountLabel.setText(roomCard.getChildrenCount() + "");
        adultsCountLabel.setText(roomCard.getAdultsCount() + "");

        amenities = ReservationHandler.getRoomAmenities(roomCard.getRoomId());
        amenitiesLabel.setText(String.join(", ", amenities));

        roomDescriptionLabel.setText(roomCard.getDescription());
        Image image = new Image(getClass().getResourceAsStream(roomCard.getImagePath()));
        roomImage.setImage(image);
        costPerNigthLabel.setText(roomCard.getCostPerNight() + "");

        endDatePicker.setDisable(true);
        paymentMethodBox.setDisable(true);
        confirmButton.setDisable(true);

        allowedStartDates.clear();
        allowedStartDates.addAll(getAllowedStartDates());

        startDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                if (!empty) {
                    if (!allowedStartDates.contains(date)) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }
            }
        });


        startDatePicker.setOnAction(event -> {
            if (!endDatePicker.isDisabled() || !paymentMethodBox.isDisabled() || (paymentMethodBox.getValue() != null &&  !paymentMethodBox.getValue().isBlank())){
                endDatePicker.setValue(startDatePicker.getValue());
                reservation = ReservationHandler.createReservation(roomCard,
                        HomeController.getLogedinCustomer(),
                        Date.valueOf(startDatePicker.getValue()),
                        Date.valueOf(endDatePicker.getValue()),
                        paymentMethodBox.getValue());
                totalAmountLabel.setText(reservation.getTotalCost() + "$");
            }


            endDatePicker.setDisable(false);
            allowedEndDates.clear();
            allowedEndDates.addAll(getAllowedEndDates());

            endDatePicker.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);

                    if (!empty) {
                        if (!allowedEndDates.contains(date)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                }
            });

        });

        endDatePicker.setOnAction(event -> {
            if (!paymentMethodBox.isDisabled()){
                reservation = ReservationHandler.createReservation(roomCard,
                        HomeController.getLogedinCustomer(),
                        Date.valueOf(startDatePicker.getValue()),
                        Date.valueOf(endDatePicker.getValue()),
                        paymentMethodBox.getValue());
                totalAmountLabel.setText(reservation.getTotalCost() + "$");
            }
            paymentMethodBox.setDisable(false);
        });


        paymentMethodBox.getItems().addAll("VISA", "MASTER CARD", "CASH", "PAYPAL");


        paymentMethodBox.setOnAction(event -> {
            reservation = ReservationHandler.createReservation(roomCard,
                    HomeController.getLogedinCustomer(),
                    Date.valueOf(startDatePicker.getValue()),
                    Date.valueOf(endDatePicker.getValue()),
                    paymentMethodBox.getValue());
            totalAmountLabel.setText(reservation.getTotalCost() + "$");
            confirmButton.setDisable(false);
        });

        confirmButton.setOnAction(event -> {
            ReservationHandler.confirmReservation(reservation);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful Reservation.");
            alert.setContentText("Your Reservation has been completed Successfully.");
            alert.showAndWait();
            loadsHotelPage();
        });

        backButton.setOnAction(event -> {
            loadsHotelPage();
        });

    }
}
