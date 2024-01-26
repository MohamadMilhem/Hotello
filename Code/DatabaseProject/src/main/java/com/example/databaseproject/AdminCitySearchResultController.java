package com.example.databaseproject;

import Models.CitySearchResult;
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
import java.net.URL;

public class AdminCitySearchResultController {

    @FXML
    private ImageView cityMainPhoto;

    @FXML
    private Label cityNameLabel;

    @FXML
    private Button deleteCityButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button editCityButton;

    private CitySearchResult citySearchResult;
    public Button getDeleteCityButton() {
        return deleteCityButton;
    }

    public Button getEditCityButton() {
        return editCityButton;
    }

    public void setData(CitySearchResult citySearchResult) throws IOException {
        this.citySearchResult = citySearchResult;
        cityNameLabel.setText(citySearchResult.getCityName());
        descriptionLabel.setText(citySearchResult.getDescription());
        File imageFile = new File("C:\\Users\\moham\\OneDrive\\Desktop\\Computer Engineering\\COMP333 (Data Base Systems)\\Project\\Code\\DatabaseProject\\src\\main\\resources" + citySearchResult.getImagePath());

        if (imageFile.exists()) {
            try (InputStream inputStream = new FileInputStream(imageFile)) {
                Image image = new Image(inputStream);
                cityMainPhoto.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error creating input stream.");
            }
        } else {
            System.out.println("Image file does not exist.");
        }

    }
}