package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Entities.Customer;
import Entities.SearchHotelsByName;
import Models.CitySearchResult;
import Models.HotelCardInHotelPage;
import Models.HotelSearchResult;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label cityDescription;

    @FXML
    private ImageView cityImage;

    @FXML
    private Label cityNameLabel;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private VBox trendingDestinationCard;

    @FXML
    private Label customerNameLabel;

    @FXML
    private Button logoutButton;
    private ObservableList<HotelSearchResult> searchResults = FXCollections.observableArrayList();
    private static Customer logedinCustomer;

    public static Customer getLogedinCustomer() {
        return logedinCustomer;
    }

    public static void setLogedinCustomer(Customer logedinCustomer) {
        HomeController.logedinCustomer = logedinCustomer;
    }

    private ObservableList<HotelSearchResult> getSearchResults(){
        ObservableList<HotelSearchResult> searchResults = FXCollections.observableArrayList();
        searchResults.addAll(SearchHotelsByName.SearchHotelsByName(searchTextField.getText()));
        return searchResults;

    }

    private void loadsHotelPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HotelPage.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerNameLabel.setText(logedinCustomer.getCustomerName() + ",");
        CitySearchResult trending = AdminQueriesHandler.getTrendingDestination();
        cityNameLabel.setText(trending.getCityName());
        cityDescription.setText(trending.getDescription());
        File imageFile = new File("C:\\Users\\moham\\OneDrive\\Desktop\\Computer Engineering\\COMP333 (Data Base Systems)\\Project\\Code\\DatabaseProject\\src\\main\\resources" + trending.getImagePath());
        if (imageFile.exists()) {
            try (InputStream inputStream = new FileInputStream(imageFile)) {
                Image image = new Image(inputStream);
                cityImage.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error creating input stream.");
            }
        } else {
            System.out.println("Image file does not exist.");
        }

        searchButton.setOnAction(event -> {
            searchResults.clear();
            grid.getChildren().clear();
            searchResults.addAll(getSearchResults());
            int row = 1;

            if (searchResults.size() == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Results!");
                alert.setContentText("No results found for your search please try again.");
                alert.showAndWait();
                return;
            }


            try {
                for (int i = 0; i < searchResults.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("HotelSearchResult.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    HotelSearchResultController searchResultController = fxmlLoader.getController();
                    searchResultController.setData(searchResults.get(i));
                    int index = i;
                    searchResultController.getGoToHotelPageButton().setOnAction(e -> {
                        HotelCardInHotelPage chosenHotel = new HotelCardInHotelPage();
                        chosenHotel.setHotelId(searchResults.get(index).getHotelId());
                        chosenHotel.setHotelName(searchResults.get(index).getHotelName());
                        chosenHotel.setHotelDescription(searchResults.get(index).getDescription());
                        chosenHotel.setHotelStarRating(searchResults.get(index).getStarRating());
                        chosenHotel.setImagePath(searchResults.get(index).getImgPath());

                        HotelPageController.setChosenHotel(chosenHotel);

                        loadsHotelPage();
                    });


                    grid.add(anchorPane, 0, row++);
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

        logoutButton.setOnAction(event -> {
            loadLoginPage();
        });



    }
}
