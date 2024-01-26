package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Models.CitySearchResult;
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

public class CreateHotelPageController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button chooseButton;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private Button createButton;

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

    private static File selectedFile;
    private static Path targetPath = Paths.get("");

    private static String fileName;

    private ArrayList<String> getCities(){
        ArrayList<String> cities = new ArrayList<>();
        cities.addAll(AdminQueriesHandler.getCityOptions());
        return cities;
    }

    private void loadCreateCityPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateCityPage.fxml"));
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadCreateHotelPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateHotelPage.fxml"));
            Stage stage = (Stage) createButton.getScene().getWindow();
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
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadAdminHomePage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
            Stage stage = (Stage) createButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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
        cityComboBox.getItems().addAll(getCities());
        starRatingComboBox.getItems().addAll("1", "2", "3", "4", "5");

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
            } else {
                System.out.println("No image selected.");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No image selected!");
                alert.showAndWait();
            }
        });

        createButton.setOnAction(event -> {

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

            if (selectedFile == null || targetPath.toString().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Photo Chosen!");
                alert.showAndWait();
                return;
            }


            try(InputStream inputStream = new FileInputStream(selectedFile)){
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error storing the image.");
            }

            HotelSearchResult hotelCreated = new HotelSearchResult();
            hotelCreated.setHotelName(hotelNameTextField.getText());
            hotelCreated.setDescription(hotelDescriptionTextArea.getText());
            hotelCreated.setImgPath("/img/" + fileName);
            hotelCreated.setLatitude(Double.parseDouble(hotelLatitudeTextField.getText()));
            hotelCreated.setLongitude(Double.parseDouble(hotelLongitudeTextField.getText()));
            hotelCreated.setCityName(cityComboBox.getValue());
            hotelCreated.setStarRating(Integer.parseInt(starRatingComboBox.getValue()));

            // Call create query.
            int result = AdminQueriesHandler.createHotel(hotelCreated);

            if (result == -1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to create.");
                alert.setContentText("Hotel name should bot be null.");
                alert.showAndWait();
                return;
            }
            else if (result == -2){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to create.");
                alert.setContentText("Hotel name already exists.");
                alert.showAndWait();
                return;
            }
            else if (result == -3){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to create.");
                alert.setContentText("Entered city does not exist.");
                alert.showAndWait();
                return;
            }
            else if (result == -4){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to create.");
                alert.setContentText("Longitude should be between -180 and 180.");
                alert.showAndWait();
                return;
            }
            else if (result == -5){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to create.");
                alert.setContentText("Latitude should be between -180 and 180.");
                alert.showAndWait();
                return;
            }


            System.out.println(hotelCreated.getHotelName());
            System.out.println(hotelCreated.getDescription());
            System.out.println(hotelCreated.getImgPath());
            System.out.println(hotelCreated.getLatitude());
            System.out.println(hotelCreated.getLongitude());
            System.out.println(hotelCreated.getCityName());
            System.out.println(hotelCreated.getStarRating());



            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful Creation.");
            alert.setContentText("New hotel is created successfully.");
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