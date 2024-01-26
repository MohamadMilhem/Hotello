package com.example.databaseproject;

import Models.RoomSearchResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RoomResultController {
    @FXML
    private Label adultsCountLabel;

    @FXML
    private Label childrenCountLabel;

    @FXML
    private Label costLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button reserveButton;

    @FXML
    private ImageView roomImage;

    @FXML
    private Label roomNumberLabel;
    private RoomSearchResult room;
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
        Image image = new Image(getClass().getResourceAsStream(room.getImagePath()));
        roomImage.setImage(image);

    }
}
