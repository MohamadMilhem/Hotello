package com.example.databaseproject;

import Entities.NavigateHotel;
import Models.HotelCardInHotelPage;
import Models.Room;
import Models.RoomSearchResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HotelPageController implements Initializable {
    private static HotelCardInHotelPage chosenHotel;

    @FXML
    private Button backButton;

    @FXML
    private VBox chosenHotelCard;

    @FXML
    private Label cityNameLabel1;

    @FXML
    private GridPane grid;

    @FXML
    private Label hotelDescription;

    @FXML
    private ImageView hotelMainImage;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label hotelStarRating;

    @FXML
    private VBox mapCard;

    @FXML
    private ScrollPane scroll;

    private List<RoomSearchResult> rooms = new ArrayList<>();

    public static HotelCardInHotelPage getChosenHotel() {
        return chosenHotel;
    }

    public static void setChosenHotel(HotelCardInHotelPage chosenHotel) {
        HotelPageController.chosenHotel = chosenHotel;
    }


    private List<RoomSearchResult> getRoomsSearchResults(){
        List<RoomSearchResult> rooms = new ArrayList<>();
        rooms.addAll(NavigateHotel.getNavigatedHotelRooms(chosenHotel.getHotelId()));
        return rooms;
    }

    private void loadHomePage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Stage stage = (Stage) scroll.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadReservationPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ReservationPage.fxml"));
            Stage stage = (Stage) scroll.getScene().getWindow();
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
        // get hotel data by id (remaning)
        // set the hotel card
        // get rooms and set the rooms.

        hotelNameLabel.setText(chosenHotel.getHotelName());
        hotelDescription.setText(chosenHotel.getHotelDescription());
        Image image = new Image(getClass().getResourceAsStream(chosenHotel.getImagePath()));
        hotelMainImage.setImage(image);
        hotelStarRating.setText(chosenHotel.getHotelStarRating() + "");

        rooms.addAll(getRoomsSearchResults());
        int row = 1;

        try {
            for (int i = 0; i < rooms.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("RoomResult.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                RoomResultController roomResultController = fxmlLoader.getController();
                roomResultController.setData(rooms.get(i));
                int index = i;
                roomResultController.getReserveButton().setOnAction(e -> {
                    RoomSearchResult chosenRoom = new RoomSearchResult();
                    chosenRoom.setRoomId(rooms.get(index).getRoomId());
                    chosenRoom.setRoomNumber(rooms.get(index).getRoomNumber());
                    chosenRoom.setChildrenCount(rooms.get(index).getChildrenCount());
                    chosenRoom.setAdultsCount(rooms.get(index).getAdultsCount());
                    chosenRoom.setDescription(rooms.get(index).getDescription());
                    chosenRoom.setImagePath(rooms.get(index).getImagePath());
                    chosenRoom.setCostPerNight(rooms.get(index).getCostPerNight());
                    chosenRoom.setHotelId(rooms.get(index).getHotelId());

                    ReservationPageController.setRoomCard(chosenRoom);
                    loadReservationPage();
                });


                grid.add(anchorPane, 0, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        backButton.setOnAction(event -> {
            loadHomePage();
        });
    }


}