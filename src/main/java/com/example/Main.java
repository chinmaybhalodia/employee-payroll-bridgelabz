package com.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll Service.");

        EmpPayrollService empPayrollService = new EmpPayrollService();

        // connecting to the database
        empPayrollService.connectDatabase();

        // reading data of all the employees in the database
        ArrayList<Employee> employees = empPayrollService.getEmployeesFromDB();
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // updating salary of Terissa to 3000000
        empPayrollService.updateSalaryInDB(3000000, "Terissa");
    }
}