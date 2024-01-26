package com.example.databaseproject;

import Entities.AdminQueriesHandler;
import Models.CitySearchResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private BarChart<?, ?> barChartAvgByCity;

    @FXML
    private LineChart<?, ?> barChartAvgByHotel;

    @FXML
    private Button createCityButton;

    @FXML
    private Button createHotelButton;

    @FXML
    private Button createRoomButton;

    @FXML
    private PieChart pieChart;
    private void loadCreateCityPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CreateCityPage.fxml"));
            Stage stage = (Stage) pieChart.getScene().getWindow();
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
            Stage stage = (Stage) pieChart.getScene().getWindow();
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
            Stage stage = (Stage) pieChart.getScene().getWindow();
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
            Stage stage = (Stage) pieChart.getScene().getWindow();
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
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Average Reservation Total Cost");

        List<List<String>> data = AdminQueriesHandler.getAverageReservationCostForTrendingHotels();

        for (List<String> row : data){
            series1.getData().add(new XYChart.Data(row.get(0), Double.parseDouble(row.get(1))));
        }

        barChartAvgByHotel.getData().addAll(series1);


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Best Customers (Highest Number Of Reservations)");

        List<List<String>> dataBestCustomers = AdminQueriesHandler.getTopFiveCustomers();

        for (List<String> row : dataBestCustomers){
            series2.getData().add(new XYChart.Data(row.get(0), Double.parseDouble(row.get(1))));
        }

        barChartAvgByCity.getData().addAll(series2);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        CitySearchResult number1TrendingCity = AdminQueriesHandler.getTrendingDestination();



        List<List<String>> hotelsReservationsInTrendingCity = AdminQueriesHandler.getTopFiveTrendingHotelInCity(number1TrendingCity.getCityId());

        pieChart.setTitle("Reservations of top Hotels\n in The most trending city (" + number1TrendingCity.getCityName() + ")");

        for (List<String> row : hotelsReservationsInTrendingCity){
            pieChartData.add(new PieChart.Data(row.get(0), Integer.parseInt(row.get(1))));
        }

        pieChart.setData(pieChartData);


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