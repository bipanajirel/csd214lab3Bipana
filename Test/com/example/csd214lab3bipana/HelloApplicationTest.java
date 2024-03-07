package com.example.csd214lab3bipana;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloApplicationTest {

    @Test
    void annualSalary() {
        HelloApplication x= new HelloApplication();
        assertEquals(x.annualSalary(3000),36000);


    }




}