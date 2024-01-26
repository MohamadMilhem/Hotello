package com.example.databaseproject;

import Models.RoomSearchResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AdminRoomResultController {

    @FXML
    private Label adultsCountLabel;

    @FXML
    private Label childrenCountLabel;

    @FXML
    private Label costLabel;

    @FXML
    private Button deleteRoomButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button editRoomButton;

    @FXML
    private Button reserveButton;

    @FXML
    private ImageView roomImage;

    @FXML
    private Label roomNumberLabel;
    private RoomSearchResult room;
    public Button getDeleteRoomButton() {
        return deleteRoomButton;
    }

    public Button getEditRoomButton() {
        return editRoomButton;
    }

    public Button getReserveButton() {
        return reserveButton;
    }

    public void setData(RoomSearchResult room) {
        this.room = room;
        roomNumberLabel.setText(room.getRoomNumber());
        adultsCountLabel.setText(room.getAdultsCount() + "");
        childrenCountLabel.setText(room.getChildrenCount() + "");
        descriptionLabel.setText(room.getDescription());
        costLabel.setText(room.getCostPerNight() + "$");
        File imageFile = new File("C:\\Users\\moham\\OneDrive\\Desktop\\Computer Engineering\\COMP333 (Data Base Systems)\\Project\\Code\\DatabaseProject\\src\\main\\resources" + room.getImagePath());

        if (imageFile.exists()) {
            try (InputStream inputStream = new FileInputStream(imageFile)) {
                Image image = new Image(inputStream);
                roomImage.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error creating input stream.");
            }
        } else {
            System.out.println("Image file does not exist.");
        }

    }

}