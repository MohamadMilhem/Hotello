package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Models.CitySearchResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class CreateCityPageController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button chooseButton;

    @FXML
    private Label cityDescription;

    @FXML
    private Label cityDescription1;

    @FXML
    private Label cityDescription11;

    @FXML
    private TextArea cityDescriptionTextArea;

    @FXML
    private TextField cityNameTextField;

    @FXML
    private Button createButton;

    @FXML
    private Button createCityButton;

    @FXML
    private Button createHotelButton;

    @FXML
    private Button createRoomButton;

    @FXML
    private Label customerNameLabel;

    private static File selectedFile;
    private static Path targetPath = Paths.get("");

    private static String fileName;
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No image selected!");
                alert.showAndWait();
            }
        });

        createButton.setOnAction(event -> {

            if (cityNameTextField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Name Chosen!");
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


            try {
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
            catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error storing the image.");
            }



            CitySearchResult cityCreated = new CitySearchResult();
            cityCreated.setCityName(cityNameTextField.getText());
            cityCreated.setDescription(cityDescriptionTextArea.getText());
            cityCreated.setImagePath("/img/" + fileName);

            // Call create query.
            int result = AdminQueriesHandler.createCity(cityCreated);

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
            alert.setTitle("Successful Creation.");
            alert.setContentText("New city is created successfully.");
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