package com.example.csd214lab3bipana;

public class Employee {


    private int iD;
    private String Name;
    private String Email;
    private String Password;
    private String Salary;


    public Employee(int iD, String Name, String Email, String Password, String Salary) {
        this.iD = iD;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.Salary = Salary;

    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD= iD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }
}





