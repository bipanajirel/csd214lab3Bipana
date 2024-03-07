package com.example.csd214lab3bipana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-employee.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("HR Management Bipana!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();

    }
    public double annualSalary(double Salary) {
        double annual = 12 * Salary;
        return annual;
    }
}