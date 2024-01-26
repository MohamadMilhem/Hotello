package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Models.HotelSearchResult;
import Models.RoomSearchResult;
import javafx.beans.binding.DoubleExpression;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.UUID;

public class CreateRoomPageController implements Initializable {

    @FXML
    private ComboBox<String> AdultsCountComboBox;

    @FXML
    private MenuButton amenitiesMenu;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> childrenCountComboBox;

    @FXML
    private Button chooseButton;

    @FXML
    private TextField costPerNightTextField;

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

    @FXML
    private ComboBox<String> hotelComboBox;

    @FXML
    private HBox hotelLatitudeTextField;

    @FXML
    private HBox hotelLatitudeTextField1;

    @FXML
    private TextArea roomDescriptionTextArea;

    @FXML
    private TextField roomNumberTextField;
    private static File selectedFile;
    private static Path targetPath = Paths.get("");

    private static String fileName;

    private ArrayList<String> getHotels(){
        ArrayList<String> hotelsNames = new ArrayList<>();
        hotelsNames.addAll(AdminQueriesHandler.getHotelOptions());
        return hotelsNames;
    }

    private ArrayList<CheckMenuItem> getAmenities(){
        ArrayList<CheckMenuItem> amenitiesCheckitems = new ArrayList<>();
        ArrayList<String> amenities = new ArrayList<>();
        amenities.addAll(AdminQueriesHandler.getAmenitiesOptions());

        for (String amenity : amenities){
            amenitiesCheckitems.add(new CheckMenuItem(amenity));
        }

        return amenitiesCheckitems;
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
        hotelComboBox.getItems().addAll(getHotels());
        childrenCountComboBox.getItems().addAll("0", "1", "2", "3", "4", "5");
        childrenCountComboBox.setValue("0");
        AdultsCountComboBox.getItems().addAll("0", "1", "2", "3", "4", "5");
        AdultsCountComboBox.setValue("0");
        amenitiesMenu.getItems().addAll(getAmenities());

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

            if (roomNumberTextField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Room Number Chosen!");
                alert.showAndWait();
                return;
            }

            if (costPerNightTextField.getText().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Cost Pre Night Entered!");
                alert.showAndWait();
                return;
            }

            if (childrenCountComboBox.getValue() == null || childrenCountComboBox.getValue().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Childrent Count Entered!");
                alert.showAndWait();
                return;
            }

            if (AdultsCountComboBox.getValue() == null || AdultsCountComboBox.getValue().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Adults Count Entered!");
                alert.showAndWait();
                return;
            }

            if (hotelComboBox.getValue() == null || hotelComboBox.getValue().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No hotel Entered!");
                alert.showAndWait();
                return;
            }

            if (selectedFile == null || targetPath.toString().isBlank()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Field!!");
                alert.setContentText("No Image Chosen!");
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


            RoomSearchResult roomCreated = new RoomSearchResult();
            roomCreated.setRoomNumber(roomNumberTextField.getText());
            roomCreated.setDescription(roomDescriptionTextArea.getText());
            roomCreated.setImagePath("/img/" + fileName);
            roomCreated.setHotelId(AdminQueriesHandler.getHotelId_byName(hotelComboBox.getValue()));
            roomCreated.setAdultsCount(Integer.parseInt(AdultsCountComboBox.getValue()));
            roomCreated.setChildrenCount(Integer.parseInt(childrenCountComboBox.getValue()));
            roomCreated.setCostPerNight(Double.parseDouble(costPerNightTextField.getText()));


            ArrayList<String> amenities = new ArrayList<>();

            for (MenuItem menuItem : amenitiesMenu.getItems()) {
                if (menuItem instanceof CheckMenuItem) {
                    CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
                    if (checkMenuItem.isSelected()) {
                        amenities.add(checkMenuItem.getText());
                    }
                }
            }

            // Call create query.
            int result = AdminQueriesHandler.createRoom(amenities, roomCreated);

            if (result == -1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to create.");
                alert.setContentText("Room number exists within the chosen hotel.");
                alert.showAndWait();
                return;
            }
            else if (result == -2){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed to create.");
                alert.setContentText("Cost per Night should be greater than 0.");
                alert.showAndWait();
                return;
            }


            System.out.println(roomCreated.getRoomNumber());
            System.out.println(roomCreated.getDescription());
            System.out.println(roomCreated.getImagePath());
            System.out.println(roomCreated.getAdultsCount());
            System.out.println(roomCreated.getChildrenCount());
            System.out.println(roomCreated.getHotelId());
            System.out.println(roomCreated.getCostPerNight());


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful Creation.");
            alert.setContentText("New room is created successfully.");
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