package com.example.databaseproject;

import Models.HotelSearchResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HotelSearchResultController {
    @FXML
    private Label cityNameOnHotelCardLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button goToHotelPageButton;

    @FXML
    private ImageView hotelMainImage;

    @FXML
    private Label hotelNameLabel;

    @FXML
    private Label starRatingLabel;

    private HotelSearchResult hotelSearchResult;

    public Button getGoToHotelPageButton() {
        return goToHotelPageButton;
    }
    public void setData(HotelSearchResult hotelSearchResult) {
        this.hotelSearchResult = hotelSearchResult;
        hotelNameLabel.setText(hotelSearchResult.getHotelName());
        cityNameOnHotelCardLabel.setText(hotelSearchResult.getCityName());
        descriptionLabel.setText(hotelSearchResult.getDescription());
        Image image = new Image(getClass().getResourceAsStream(hotelSearchResult.getImgPath()));
        hotelMainImage.setImage(image);
        starRatingLabel.setText(hotelSearchResult.getStarRating() + "");

    }
}
