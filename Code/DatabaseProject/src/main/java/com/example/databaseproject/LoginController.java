package com.example.databaseproject;

import Entities.SignUpHandler;
import Entities.UserAuthentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label errorLable;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField usernameTextfield;

    private String errorMessage = "";


    private boolean isCredentialsExists(){
        boolean isExists = true;
        if (usernameTextfield.getText().isBlank()){
            isExists = false;
            errorMessage = errorMessage.isEmpty() ? "Username is Empty." : errorMessage + "\nUsername is Empty.";
        }

        if (passwordTextfield.getText().isBlank()){
            isExists = false;
            errorMessage = errorMessage.isEmpty() ? "Password is Empty." : errorMessage + "\nPassword is Empty.";
        }
        return isExists;
    }

    private boolean isUsernameExistsInDatabase(){
        boolean isExists = true;
        if (UserAuthentication.checkIfUserExist(usernameTextfield.getText()) == -1){
            isExists = false;
            errorMessage = errorMessage.isEmpty() ? "Username Does not exists." : errorMessage + "\nUsername Does not exists.";
        }
        return isExists;
    }

    private boolean isCredentialsCorrect(){
        // add check from database.
        if (UserAuthentication.isCorrectPassword(usernameTextfield.getText(), passwordTextfield.getText()) != -1){
            return true;
        }
        else {
            errorMessage = "Username or Password is Incorrect.";
        }
        return false;
    }

    private String getUserType(){
        // check from the database.
        if (UserAuthentication.isAdmin(usernameTextfield.getText()) == 1){
            return "Admin";
        }
        return "User";
    }

    private void loadSignUpPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadHomePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadAdminHomePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminHomePage.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void setLogedInCustomer() {
        HomeController.setLogedinCustomer(UserAuthentication.SignInRegularUser(usernameTextfield.getText(), passwordTextfield.getText()));
    }

    private void setLogedInAdmin(){
        AdminHomePageController.setLogedinCustomer(UserAuthentication.SignInRegularUser(usernameTextfield.getText(), passwordTextfield.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(event -> {
            if (!isCredentialsExists()){
                errorLable.setText(errorMessage);
                errorMessage = "";
                return;
            }
            if (!isUsernameExistsInDatabase()){
                errorLable.setText(errorMessage);
                errorMessage = "";
                return;
            }
            if (!isCredentialsCorrect()){
                errorLable.setText(errorMessage);
                errorMessage = "";
                return;
            }
            if (getUserType() == "Admin"){
                setLogedInAdmin();
                Stage oldStage = (Stage) loginButton.getScene().getWindow();
                oldStage.close();
                loadAdminHomePage();
            }
            else {
                //go to user Homepage.
                setLogedInCustomer();
                Stage oldStage = (Stage) loginButton.getScene().getWindow();
                oldStage.close();
                loadHomePage();
            }

        });

        signUpButton.setOnAction(event -> {
            Stage oldStage = (Stage) loginButton.getScene().getWindow();
            oldStage.close();
            loadSignUpPage();
        });

    }




}
