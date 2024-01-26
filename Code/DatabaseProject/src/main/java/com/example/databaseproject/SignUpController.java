package com.example.databaseproject;

import Entities.Customer;
import Entities.SignUpHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private PasswordField confirmPasswordTextfield;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label errorLable;

    @FXML
    private TextField nameTextField;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private Button signupButton;

    @FXML
    private TextField usernameTextfield;

    private String errorMessage = "";

    private boolean checkUserName(){
        //checks if the user is in database.
        boolean valid = true;
        int validateResult = SignUpHandler.isValidUserName(usernameTextfield.getText());
        if (validateResult == -1){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "Username can't be empty." : errorMessage + "\nUsername can't be empty.";
        }
        else if (validateResult == -2){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "Username should start with a character or number." : errorMessage + "\nUsername should start with a character or number.";
        }
        else if (validateResult == -3){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "Username already exists try different username." : errorMessage + "\nUsername already exists try different username.";
        }
        return valid;
    }

    private boolean checkPassword(){
        boolean valid = true;
        int validateResult = SignUpHandler.isValidPassword(passwordTextfield.getText());

        if (validateResult == -1){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "The length of the password should be 8 characters at least." : errorMessage + "\nThe length of the password should be 8 characters at least.";
        }
        else if (validateResult == -2){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "Invalid char in the input password the password should contain only Alphabetic chars and Digits." : errorMessage + "\nInvalid char in the input password the password should contain only Alphabetic chars and Digits";
        }
        else if (!passwordTextfield.getText().equals(confirmPasswordTextfield.getText())){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "Passwords doesn't match." : errorMessage + "\nPasswords doesn't match.";
        }

        return valid;
    }

    private boolean checkEmail(){
        boolean valid = true;
        int validateResult = SignUpHandler.isValidEmail(emailTextField.getText());

        if (validateResult == -1){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "Error: Email cannot be empty." : errorMessage + "\nError: Email cannot be empty.";
        }
        else if (validateResult == -2){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "Error: Invalid email format." : errorMessage + "\nError: Invalid email format.";
        }
        else if (validateResult == -3){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "Email Already Exists" : errorMessage + "\nEmail Already Exists";
        }
        return valid;
    }

    private boolean checkName(){
        boolean valid = true;
        int validateResult = SignUpHandler.isValidCustomerName(nameTextField.getText());

        if (validateResult == -1){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "User should fill the customer name" : errorMessage + "\nUser should fill the customer name";
        }
        else if (validateResult == -2){
            valid = false;
            errorMessage = errorMessage.isEmpty() ? "Customer already exist" : errorMessage + "\nCustomer already exist";
        }
        return valid;
    }

    private void LoadLoginPage(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signupButton.setOnAction(event -> {

            if (!checkUserName()){
                errorLable.setText(errorMessage);
                errorMessage = "";
                return;
            }
            if (!checkPassword()){
                errorLable.setText(errorMessage);
                errorMessage = "";
                return;
            }
            if (!checkEmail()){
                errorLable.setText(errorMessage);
                errorMessage = "";
                return;
            }
            if (!checkName()){
                errorLable.setText(errorMessage);
                errorMessage = "";
                return;
            }

            Customer customer = new Customer(0, nameTextField.getText(), emailTextField.getText());

            if (SignUpHandler.CreateAccount(customer, usernameTextfield.getText(), passwordTextfield.getText()) == -1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Input!");
                alert.setHeaderText(null);
                alert.setContentText("invalid information for account creation check the information!");
                alert.showAndWait();
                return ;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Congratulations You have Created an account successfully!");
            alert.showAndWait();
            Stage oldStage = (Stage) signupButton.getScene().getWindow();
            oldStage.close();
            LoadLoginPage();
        });
    }
}
