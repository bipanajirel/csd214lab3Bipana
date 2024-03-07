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
    private TableColumn<Employee, Integer> Id;
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
        Id.setCellValueFactory(new
                PropertyValueFactory<Employee, Integer>("Id"));
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
    protected void OnClick() {populateTable();
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
                int Id = resultSet.getInt("eid");
                String Name = resultSet.getString("Name");
                String Email = resultSet.getString("Email");
                String Password = resultSet.getString("Password");
                String Salary = resultSet.getString("Salary");
                employee.getItems().add(new Employee(Id, Name, Email, Password, Salary));
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
            String query = "INSERT INTO `employee`(`Name`, `Email`, `Password`,`Salary`) VALUES ('" + name + "','" + email + "','" + password + "','" + salary + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    public void DeleteData(ActionEvent actionEvent) {
        Integer Id = Integer.valueOf(iD.getText());
        DeleteTable(Id);
    }

    private void DeleteTable(Integer Id) {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab3bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `employee` WHERE `eid`='" + Id + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    public void UpdateData(ActionEvent actionEvent) {
        Integer Id = Integer.valueOf(iD.getText());
        String name = Name.getText();
        String email = Email.getText();
        String password = Password.getText();
        String salary = Salary.getText();

        UpdateTable(Id,name, email,password,salary);

    }

    private void UpdateTable(Integer Id, String name, String email, String password, String salary) {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab3bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `employee` SET `Name`='" + name + "',`Email`='" + email + "',`Password`='" + password+ "',`Salary`='" +salary + "' WHERE `eid`='" + Id + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }

    public void LoadData(ActionEvent actionEvent) {
        Integer Id = Integer.valueOf(iD.getText());
        LoadTable(Id);
    }

    private void LoadTable(Integer Id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214lab3bipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM employee WHERE `eid`='" + Id + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {

                String name = resultSet.getString("Name");
                String email = resultSet.getString("Email");
                String password = resultSet.getString("Password");
                String salary = resultSet.getString("Salary");

                Name.setText(name);
                Email.setText(email);
                Password.setText(password);
                Salary.setText(salary);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }


}



