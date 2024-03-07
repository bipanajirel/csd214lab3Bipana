package com.example.csd214lab3bipana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable{


    public TextField iD;
    public TextField Name;
    public TextField Email;
    public TextField Password;
    public TextField Salary;



    @FXML
    private Label welcomeText;
    @FXML
    private TableView<Employee> employee;
    @FXML
    private TableColumn<Employee, Integer> id;
    @FXML
    private TableColumn<Employee, String> name;
    @FXML
    private TableColumn<Employee, String> email;
    @FXML
    private TableColumn<Employee, String> password;
    @FXML
    private TableColumn<Employee, String> salary;
    ObservableList<Employee> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new
                PropertyValueFactory<Employee, Integer>("id"));
        name.setCellValueFactory(new
                PropertyValueFactory<Employee, String>("Name"));
        email.setCellValueFactory(new
                PropertyValueFactory<Employee, String>("Email"));
        password.setCellValueFactory(new
                PropertyValueFactory<Employee, String>("Password"));
        salary.setCellValueFactory(new
                PropertyValueFactory<Employee, String>("Salary"));
        employee.setItems(list);
    }

    @FXML
    protected void OnClick() {
        populateTable();
    }

    public void populateTable() {
        // Establish a database connection

        list.clear();
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab3bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM employee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int iD = resultSet.getInt("id");
                String Name = resultSet.getString("Name");
                String Email = resultSet.getString("Email");
                String Password = resultSet.getString("Password");
                String Salary = resultSet.getString("Salary");
                employee.getItems().add(new Employee(iD, Name, Email, Password, Salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void CreateData(ActionEvent actionEvent) {
        String name = Name.getText();
        String email = Email.getText();
        String password = Password.getText();
        String salary = Salary.getText();

        InserTable(name, email,password,salary);

    }

    private void InserTable(String name, String email, String password,String salary) {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab3bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "INSERT INTO `admin`(`Name`, `Email`, `Password`,`Salary`) VALUES ('" + name + "','" + email + "','" + password + "','" + salary + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    public void DeleteData(ActionEvent actionEvent) {
        Integer id = Integer.valueOf(iD.getText());
        DeleteTable(id);
    }

    private void DeleteTable(Integer id) {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab3bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `employee` WHERE iD='" + id + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    public void UpdateData(ActionEvent actionEvent) {
        Integer id = Integer.valueOf(iD.getText());
        String name = Name.getText();
        String email = Email.getText();
        String password = Password.getText();
        String salary = Salary.getText();

        UpdateTable(id,name, email,password,salary);

    }

    private void UpdateTable(Integer id, String name, String email, String password, String salary) {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab3bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `employee` SET `Name`='" + name + "',`Email`='" + email + "',`Password`='" + password+ "',`Salary`='" +salary + "' WHERE iD='" + id + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    public void LoadData(ActionEvent actionEvent) {
        Integer id = Integer.valueOf(iD.getText());
        LoadTable(id);
    }

    private void LoadTable(Integer id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab3bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM employee WHERE iD='" + id + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {

                String Name = resultSet.getString("Name");
                String Email = resultSet.getString("Email");
                String Password = resultSet.getString("Password");
                String Salary = resultSet.getString("Salary");

                name.setText(Name);
                email.setText(Email);
                password.setText(Password);
                salary.setText(Salary);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }


}



