package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Models.HotelCardInHotelPage;
import Models.HotelSearchResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class UpdateHotelPageController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button chooseButton;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private Button createCityButton;

    @FXML
    private Button createHotelButton;

    @FXML
    private Button createRoomButton;

    @FXML
    private TextArea hotelDescriptionTextArea;

    @FXML
    private TextField hotelLatitudeTextField;

    @FXML
    private TextField hotelLongitudeTextField;

    @FXML
    private TextField hotelNameTextField;

    @FXML
    private ComboBox<String> starRatingComboBox;

    @FXML
    private Button updateButton;

    private static HotelSearchResult chosenHotel;

    private static File selectedFile;
    private static Path targetPath = Paths.get("");

    private static String fileName;

    private ArrayList<String> getCities(){
        ArrayList<String> cities = new ArrayList<>();
        cities.addAll(AdminQueriesHandler.getCityOptions());
        return cities;
    }
    private void loadAdminHomePage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadCreateCityPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateCityPage.fxml"));
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadCreateHotelPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateHotelPage.fxml"));
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadCreateRoomPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateRoomPage.fxml"));
            Stage stage = (Stage) updateButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static HotelSearchResult getChosenHotel() {
        return chosenHotel;
    }

    public static void setChosenHotel(HotelSearchResult chosenHotel) {
        UpdateHotelPageController.chosenHotel = chosenHotel;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hotelNameTextField.setText(chosenHotel.getHotelName());
        hotelDescriptionTextArea.setText(chosenHotel.getDescription());
        cityComboBox.getItems().addAll(getCities());
        cityComboBox.setValue(chosenHotel.getCityName());
        starRatingComboBox.getItems().addAll("1", "2", "3", "4", "5");
        starRatingComboBox.setValue(chosenHotel.getStarRating() + "");
        hotelLongitudeTextField.setText(AdminQueriesHandler.getHotelLongitude(chosenHotel.getHotelId()) + "");
        hotelLatitudeTextField.setText(AdminQueriesHandler.getHotelLatitude(chosenHotel.getHotelId()) + "");

        HotelSearchResult existingHotel = new HotelSearchResult();
        existingHotel.setHotelId(chosenHotel.getHotelId());
        existingHotel.setHotelName(chosenHotel.getHotelName());
        existingHotel.setLongitude(chosenHotel.getLongitude());
        existingHotel.setLatitude(chosenHotel.getLatitude());
        existingHotel.setDescription(chosenHotel.getDescription());
        existingHotel.setCityName(chosenHotel.getCityName());
        existingHotel.setStarRating(chosenHotel.getStarRating());
        existingHotel.setImgPath(chosenHotel.getImgPath());



        chooseButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
            selectedFile = fileChooser.showOpenDialog(new Stage());
            String targetDirectoryPath = "C:\\Users\\moham\\OneDrive\\Desktop\\Computer Engineering\\COMP333 (Data Base Systems)\\Project\\Code\\DatabaseProject\\src\\main\\resources\\img";
            fileName = "";
            if (selectedFile != null) {
                try {
                    Path targetDirectory = Path.of(targetDirectoryPath);

                    if (!Files.exists(targetDirectory)) {
                        Files.createDirectories(targetDirectory);
                    }

                    fileName = UUID.randomUUID().toString() + getFileExtension(selectedFile.getName());

                    targetPath = targetDirectory.resolve(fileName);


                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Error storing the image.");
                }
            }
        });

        updateButton.setOnAction(event -> {

            if (hotelNameTextField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Name Chosen!");
                alert.showAndWait();
                return;
            }

            if (hotelLatitudeTextField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Latitude Entered!");
                alert.showAndWait();
                return;
            }

            if (hotelLongitudeTextField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Longitude Entered!");
                alert.showAndWait();
                return;
            }

            if (cityComboBox.getValue() == null || cityComboBox.getValue().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No City Entered!");
                alert.showAndWait();
                return;
            }

            if (starRatingComboBox.getValue() == null || starRatingComboBox.getValue().isBlank()){

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Star Rating Entered!");
                alert.showAndWait();
                return;
            }

            if (fileName != null && !fileName.isBlank()) {
                try (InputStream inputStream = new FileInputStream(selectedFile)) {
                    Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Error storing the image.");
                }
            }

            HotelSearchResult updatedHotel = new HotelSearchResult();
            updatedHotel.setHotelId(chosenHotel.getHotelId());
            updatedHotel.setHotelName(hotelNameTextField.getText());
            updatedHotel.setDescription(hotelDescriptionTextArea.getText());
            updatedHotel.setLatitude(Double.parseDouble(hotelLatitudeTextField.getText()));
            updatedHotel.setLongitude(Double.parseDouble(hotelLongitudeTextField.getText()));
            updatedHotel.setCityName(cityComboBox.getValue());
            updatedHotel.setStarRating(Integer.parseInt(starRatingComboBox.getValue()));

            if (fileName != null && !fileName.isBlank()){
                updatedHotel.setImgPath("/img/" + fileName);
            }
            else {
                updatedHotel.setImgPath(existingHotel.getImgPath());
            }


            // Call create query.
            int result = AdminQueriesHandler.updateHotel(updatedHotel);

            if (result == -1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to update.");
                alert.setContentText("Hotel name already exists.");
                alert.showAndWait();
                return;
            }
            else if (result == -2){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to update.");
                alert.setContentText("Invalid star rating.");
                alert.showAndWait();
                return;
            }
            else if (result == -3){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to update.");
                alert.setContentText("Invalid latitude , latitude should be between -180 and 180 ");
                alert.showAndWait();
                return;
            }
            else if (result == -4){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to update.");
                alert.setContentText("Longitude should be between -180 and 180.");
                alert.showAndWait();
                return;
            }


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful Update.");
            alert.setContentText("The chosen hotel is updated successfully.");
            alert.showAndWait();
            loadAdminHomePage();
        });

        createCityButton.setOnAction(event -> {
            loadCreateCityPage();
        });

        createHotelButton.setOnAction(event -> {
            loadCreateHotelPage();
        });

        createRoomButton.setOnAction(event -> {
            loadCreateRoomPage();
        });

        backButton.setOnAction(event -> {
            loadAdminHomePage();
        });
    }
}
