package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Models.CitySearchResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;

public class UpdateCityPageController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button chooseButton;

    @FXML
    private TextArea cityDescriptionTextArea;

    @FXML
    private TextField cityNameTextField;

    @FXML
    private Button createCityButton;

    @FXML
    private Button createHotelButton;

    @FXML
    private Button createRoomButton;

    @FXML
    private Button updateButton;

    private static CitySearchResult chosenCity;

    private static File selectedFile;
    private static Path targetPath = Paths.get("");
    private static String fileName;

    public static CitySearchResult getChosenCity() {
        return chosenCity;
    }

    public static void setChosenCity(CitySearchResult chosenCity) {
        UpdateCityPageController.chosenCity = chosenCity;
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

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cityNameTextField.setText(chosenCity.getCityName());
        cityDescriptionTextArea.setText(chosenCity.getDescription());

        CitySearchResult existingCity = new CitySearchResult();
        existingCity.setCityId(chosenCity.getCityId());
        existingCity.setCityName(chosenCity.getCityName());
        existingCity.setDescription(chosenCity.getDescription());
        existingCity.setImagePath(chosenCity.getImagePath());

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

            if (cityNameTextField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Name Chosen!");
                alert.showAndWait();
                return;
            }

            if (fileName != null && !fileName.isBlank()) {
                try {
                    Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Error storing the image.");
                }
            }


            CitySearchResult updatedCity = new CitySearchResult();
            updatedCity.setCityId(existingCity.getCityId());
            updatedCity.setCityName(cityNameTextField.getText());
            updatedCity.setDescription(cityDescriptionTextArea.getText());
            if (fileName != null && !fileName.isBlank()) {
                updatedCity.setImagePath("/img/" + fileName);
            }
            else {
                updatedCity.setImagePath(existingCity.getImagePath());
            }

            // Call create query.
            int result = AdminQueriesHandler.updateCity(updatedCity);

            if (result == -1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to create.");
                alert.setContentText("City Name Already exists.");
                alert.showAndWait();
                return;
            }

            //System.out.println(cityCreated.getCityName());
            //System.out.println(cityCreated.getDescription());
            //System.out.println(cityCreated.getImagePath());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful Update.");
            alert.setContentText("The chosen city is updated successfully.");
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