package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Entities.NavigateHotel;
import Models.HotelCardInHotelPage;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminHotelPageController implements Initializable {

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
        AdminHotelPageController.chosenHotel = chosenHotel;
    }


    private List<RoomSearchResult> getRoomsSearchResults(){
        List<RoomSearchResult> rooms = new ArrayList<>();
        rooms.addAll(NavigateHotel.getNavigatedHotelRooms(chosenHotel.getHotelId()));
        return rooms;
    }

    private void loadAdminHomePage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
            Stage stage = (Stage) scroll.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadAdminReservationPage(){
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

    private void loadUpdateRoomPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UpdateRoomPage.fxml"));
            Stage stage = (Stage) scroll.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
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
        hotelStarRating.setText(chosenHotel.getHotelStarRating() + "");
        rooms.addAll(getRoomsSearchResults());
        File imageFile = new File("C:\\Users\\moham\\OneDrive\\Desktop\\Computer Engineering\\COMP333 (Data Base Systems)\\Project\\Code\\DatabaseProject\\src\\main\\resources" + chosenHotel.getImagePath());

        if (imageFile.exists()) {
            try (InputStream inputStream = new FileInputStream(imageFile)) {
                Image image = new Image(inputStream);
                hotelMainImage.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error creating input stream.");
            }
        } else {
            System.out.println("Image file does not exist.");
        }


        int row = 1;

        try {
            for (int i = 0; i < rooms.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("AdminRoomResult.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                AdminRoomResultController adminRoomResultController = fxmlLoader.getController();
                adminRoomResultController.setData(rooms.get(i));
                int index = i;

                adminRoomResultController.getDeleteRoomButton().setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("This action will delete "
                            + rooms.get(index).getRoomNumber()
                            + " Room and all associated reservations.");
                    alert.showAndWait();
                    AdminQueriesHandler.DeleteRoomById(rooms.get(index).getHotelId());
                });

                adminRoomResultController.getEditRoomButton().setOnAction(event -> {
                    UpdateRoomPageController.setChosenRoom(rooms.get(index));
                    loadUpdateRoomPage();
                });


                grid.add(anchorPane, 0, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        backButton.setOnAction(event -> {
            loadAdminHomePage();
        });


    }
}
