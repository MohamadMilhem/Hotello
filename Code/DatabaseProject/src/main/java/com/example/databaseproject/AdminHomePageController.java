package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Entities.Customer;
import Entities.SearchHotelsByName;
import Models.CitySearchResult;
import Models.HotelCardInHotelPage;
import Models.HotelSearchResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminHomePageController implements Initializable {

    @FXML
    private Label cityDescription;

    @FXML
    private Label cityDescription1;

    @FXML
    private Label cityDescription11;

    @FXML
    private Button createCityButton;

    @FXML
    private Button createHotelButton;

    @FXML
    private Button createRoomButton;

    @FXML
    private Label customerNameLabel;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private GridPane grid;

    @FXML
    private Button logoutButton;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button statisticsButton;

    private ObservableList<HotelSearchResult> hotelSearchResults = FXCollections.observableArrayList();
    private ObservableList<CitySearchResult> citySearchResults = FXCollections.observableArrayList();
    private static Customer logedinCustomer;

    public static Customer getLogedinCustomer() {
        return logedinCustomer;
    }

    public static void setLogedinCustomer(Customer logedinCustomer) {
        AdminHomePageController.logedinCustomer = logedinCustomer;
    }

    private ObservableList<HotelSearchResult> getHotelSearchResults(){
        ObservableList<HotelSearchResult> searchResults = FXCollections.observableArrayList();
        searchResults.addAll(SearchHotelsByName.SearchHotelsByName(searchTextField.getText()));
        return searchResults;

    }

    private ObservableList<CitySearchResult> getCitySearchResults(){
        // look at the prev function.
        ObservableList<CitySearchResult> searchResults = FXCollections.observableArrayList();
        searchResults.addAll(AdminQueriesHandler.searchCitiesNames(searchTextField.getText()));
        return searchResults;
    }

    private void loadAdminHotelPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminHotelPage.fxml"));
            Stage stage = (Stage) searchButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadLoginPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Stage stage = (Stage) searchButton.getScene().getWindow();
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
            Stage stage = (Stage) searchButton.getScene().getWindow();
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
            Stage stage = (Stage) searchButton.getScene().getWindow();
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
            Stage stage = (Stage) searchButton.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadUpdateCityPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UpdateCityPage.fxml"));
            Stage stage = (Stage) searchButton.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadUpdateHotelPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UpdateHotelPage.fxml"));
            Stage stage = (Stage) searchButton.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadStatisticsPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StatisticsPage.fxml"));
            Stage stage = (Stage) searchButton.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerNameLabel.setText(logedinCustomer.getCustomerName() + ",");

        filterComboBox.getItems().addAll("City", "Hotel");
        filterComboBox.setValue("Hotel");



        searchButton.setOnAction(event -> {
            if (filterComboBox.getValue().equals("Hotel")) {

                hotelSearchResults.clear();
                grid.getChildren().clear();
                hotelSearchResults.addAll(getHotelSearchResults());
                int row = 1;

                if (hotelSearchResults.size() == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No Results!");
                    alert.setContentText("No results found for your search please try again.");
                    alert.showAndWait();
                    return;
                }

                try {
                    for (int i = 0; i < hotelSearchResults.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("AdminHotelSearchResult.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();

                        AdminHotelSearchResultController adminHotelSearchResultController = fxmlLoader.getController();
                        adminHotelSearchResultController.setData(hotelSearchResults.get(i));
                        int index = i;
                        adminHotelSearchResultController.getGoToHotelPageButton().setOnAction(e -> {
                            HotelCardInHotelPage chosenHotel = new HotelCardInHotelPage();
                            chosenHotel.setHotelId(hotelSearchResults.get(index).getHotelId());
                            chosenHotel.setHotelName(hotelSearchResults.get(index).getHotelName());
                            chosenHotel.setHotelDescription(hotelSearchResults.get(index).getDescription());
                            chosenHotel.setHotelStarRating(hotelSearchResults.get(index).getStarRating());
                            chosenHotel.setImagePath(hotelSearchResults.get(index).getImgPath());

                            AdminHotelPageController.setChosenHotel(chosenHotel);

                            loadAdminHotelPage();
                        });

                        adminHotelSearchResultController.getEditHotelButton().setOnAction(e -> {
                            UpdateHotelPageController.setChosenHotel(hotelSearchResults.get(index));
                            loadUpdateHotelPage();

                        });

                        adminHotelSearchResultController.getDeleteHotelButton().setOnAction(e -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("This action will delete "
                                    + hotelSearchResults.get(index).getHotelName()
                                    + " Hotel and all included rooms.");
                            alert.showAndWait();
                            AdminQueriesHandler.DeleteHotelById(hotelSearchResults.get(index).getHotelId());
                        });

                        grid.add(anchorPane, 0, row++);
                        GridPane.setMargin(anchorPane, new Insets(10));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (filterComboBox.getValue().equals("City")){

                citySearchResults.clear();
                grid.getChildren().clear();
                citySearchResults.addAll(getCitySearchResults());
                int row = 1;

                if (citySearchResults.size() == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No Results!");
                    alert.setContentText("No results found for your search please try again.");
                    alert.showAndWait();
                    return;
                }

                try {
                    for (int i = 0; i < citySearchResults.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("AdminCitySearchResult.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();

                        AdminCitySearchResultController adminCitySearchResultController = fxmlLoader.getController();
                        adminCitySearchResultController.setData(citySearchResults.get(i));
                        int index = i;

                        adminCitySearchResultController.getEditCityButton().setOnAction(e -> {
                            UpdateCityPageController.setChosenCity(citySearchResults.get(index));
                            loadUpdateCityPage();
                        });

                        adminCitySearchResultController.getDeleteCityButton().setOnAction(e -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("This action will delete "
                                    + citySearchResults.get(index).getCityName()
                                    + " City and all included hotels and rooms.");
                            alert.showAndWait();
                            AdminQueriesHandler.DeleteCityById(citySearchResults.get(index).getCityId());
                        });

                        grid.add(anchorPane, 0, row++);
                        GridPane.setMargin(anchorPane, new Insets(10));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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

        statisticsButton.setOnAction(event -> {
            loadStatisticsPage();
        });

        logoutButton.setOnAction(event -> {
            loadLoginPage();
        });




    }
}
