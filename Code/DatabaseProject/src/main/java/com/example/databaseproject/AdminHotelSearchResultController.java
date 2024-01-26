package com.example.databaseproject;

import Models.HotelSearchResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class AdminHotelSearchResultController {

    @FXML
    private Label cityNameOnHotelCardLabel;

    @FXML
    private Button deleteHotelButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button editHotelButton;

    @FXML
    private Button goToHotelPageButton;

    @FXML
    private ImageView hotelMainImage;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label starRatingLabel;

    private HotelSearchResult hotelSearchResult;

    public Button getDeleteHotelButton() {
        return deleteHotelButton;
    }

    public Button getEditHotelButton() {
        return editHotelButton;
    }

    public Button getGoToHotelPageButton() {
        return goToHotelPageButton;
    }

    public void setData(HotelSearchResult hotelSearchResult) {
        this.hotelSearchResult = hotelSearchResult;
        hotelNameLabel.setText(hotelSearchResult.getHotelName());
        cityNameOnHotelCardLabel.setText(hotelSearchResult.getCityName());
        descriptionLabel.setText(hotelSearchResult.getDescription());
        starRatingLabel.setText(hotelSearchResult.getStarRating() + "");
        File imageFile = new File("C:\\Users\\moham\\OneDrive\\Desktop\\Computer Engineering\\COMP333 (Data Base Systems)\\Project\\Code\\DatabaseProject\\src\\main\\resources" + hotelSearchResult.getImgPath());

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

    }
}
