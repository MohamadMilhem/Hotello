package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Models.RoomSearchResult;
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
import java.util.ResourceBundle;
import java.util.UUID;

public class UpdateRoomPageController implements Initializable {

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
    private Button createCityButton;

    @FXML
    private Button createHotelButton;

    @FXML
    private Button createRoomButton;

    @FXML
    private ComboBox<String> hotelComboBox;

    @FXML
    private TextArea roomDescriptionTextArea;

    @FXML
    private TextField roomNumberTextField;

    @FXML
    private Button updateButton;

    private static RoomSearchResult chosenRoom;

    public static RoomSearchResult getChosenRoom() {
        return chosenRoom;
    }

    public static void setChosenRoom(RoomSearchResult chosenRoom) {
        UpdateRoomPageController.chosenRoom = chosenRoom;
    }

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
        hotelComboBox.getItems().addAll(getHotels());
        childrenCountComboBox.getItems().addAll("0", "1", "2", "3", "4", "5");
        AdultsCountComboBox.getItems().addAll("0", "1", "2", "3", "4", "5");
        amenitiesMenu.getItems().addAll(getAmenities());

        roomNumberTextField.setText(chosenRoom.getRoomNumber());
        roomDescriptionTextArea.setText(chosenRoom.getDescription());
        AdultsCountComboBox.setValue(chosenRoom.getAdultsCount() + "");
        childrenCountComboBox.setValue(chosenRoom.getChildrenCount() + "");
        costPerNightTextField.setText(chosenRoom.getCostPerNight() + "");
        hotelComboBox.setValue(AdminQueriesHandler.getHotelName(chosenRoom.getHotelId()));
        ArrayList<String> roomAmenities = new ArrayList<>();
        roomAmenities.addAll(AdminQueriesHandler.getRoomAmenties(chosenRoom.getRoomId()));

        for (MenuItem menuItem : amenitiesMenu.getItems()) {
            if (menuItem instanceof CheckMenuItem) {
                CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
                if (roomAmenities.contains(checkMenuItem.getText())) {
                    checkMenuItem.setSelected(true);
                }
            }
        }

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


            if (fileName != null && !fileName.isBlank()) {
                try {
                    Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Error storing the image.");
                }
            }

            RoomSearchResult updatedroom = new RoomSearchResult();
            updatedroom.setRoomId(chosenRoom.getRoomId());
            updatedroom.setRoomNumber(roomNumberTextField.getText());
            updatedroom.setDescription(roomDescriptionTextArea.getText());
            updatedroom.setHotelId(AdminQueriesHandler.getHotelId_byName(hotelComboBox.getValue()));
            updatedroom.setAdultsCount(Integer.parseInt(AdultsCountComboBox.getValue()));
            updatedroom.setChildrenCount(Integer.parseInt(childrenCountComboBox.getValue()));
            updatedroom.setCostPerNight(Double.parseDouble(costPerNightTextField.getText()));
            if (fileName != null && !fileName.isBlank()) {
                updatedroom.setImagePath("/img/" + fileName);
            }
            else {
                updatedroom.setImagePath(chosenRoom.getImagePath());
            }

            ArrayList<String> updatedAmenities = new ArrayList<>();

            for (MenuItem menuItem : amenitiesMenu.getItems()) {
                if (menuItem instanceof CheckMenuItem) {
                    CheckMenuItem checkMenuItem = (CheckMenuItem) menuItem;
                    if (checkMenuItem.isSelected()) {
                        updatedAmenities.add(checkMenuItem.getText());
                    }
                }
            }

            // Call create query.
            int result = AdminQueriesHandler.updateRoom(updatedroom, updatedAmenities);

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


            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful update.");
            alert.setContentText("the chosen room is updated successfully.");
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
