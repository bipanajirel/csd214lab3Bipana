package com.example.csd214lab3bipana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    public Button Get;
    public TextField mail;
    public PasswordField Secret;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        Get.setText("");

        String Email = mail.getText();
        String Pro_pass = Secret.getText();

        if (Email.equals("") || Pro_pass.equals("")) {

            Get.setText("Please Enter Email or Password");

        } else {
            // Establish a database connection
            String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab3bipana";
            String dbUser = "root";
            String dbPassword = "";
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                    dbPassword)) {
// Execute a SQL query to retrieve data from the database
                String query = "SELECT * FROM `employee` WHERE Email='" + Email + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database

                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String Mail = resultSet.getString("Email");
                    String Password = resultSet.getString("Password");

                    if (Password.equals(Pro_pass)) {

                        Get.setText("Login Success!!");
                        try {
                            // Load the FXML file for the second scene
                            Parent secondScene = FXMLLoader.load(getClass().getResource("hello-dashboard.fxml"));

                            // Create a new stage for the second scene
                            Stage secondStage = new Stage();
                            secondStage.setTitle("Dashboard");
                            secondStage.setScene(new Scene(secondScene));

                            Stage firstSceneStage = (Stage) Get.getScene().getWindow();
                            firstSceneStage.close();
                            // Show the second stage
                            secondStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();

                        }
                    } else {
                        Get.setText("Invalid Email or Password.");
                    }

                } else {
                    Get.setText("Invalid Email or Password.");
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
    }
}