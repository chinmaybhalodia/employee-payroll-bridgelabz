package com.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll Service.");

        // employee payroll with file I/O
        EmpPayrollService empPayrollService = new EmpPayrollService("employees.txt");

        // adding new Employee
        empPayrollService.addEmployeeToFile(new Employee(1, "Chinmay", 50000));

        // checking number of entries in file
        System.out.println(empPayrollService.countEmployeesInFile());

        // adding more employees and then counting entries
        empPayrollService.addEmployeeToFile(new Employee(2, "Rahul", 20000));
        empPayrollService.addEmployeeToFile(new Employee(4, "Shyam", 40000));
        empPayrollService.addEmployeeToFile(new Employee(3, "Kush", 60000));
        System.out.println(empPayrollService.countEmployeesInFile());

        // printing all the employee data from the file
        System.out.println("\nEmployee Payroll Data: ");
        empPayrollService.printEmployeesFromFile();

        // sorting employees by salary
        System.out.println("\nEmployees sorted by salary: ");
        ArrayList<Employee> sortedBySalary = empPayrollService.sortBySalary();
        for (Employee employee : sortedBySalary) {
            System.out.println(employee);
        }

        // sorting employees by ID
        System.out.println("\nEmployees sorted by Id: ");
        ArrayList<Employee> sortedByID = empPayrollService.sortByID();
        for (Employee employee : sortedByID) {
            System.out.println(employee);
        }

        // sorting employees by Name
        System.out.println("\nEmployees sorted by Name: ");
        ArrayList<Employee> sortedByName = empPayrollService.sortByName();
        for (Employee employee : sortedByName) {
            System.out.println(employee);
        }

        // filter employees based on salary
        System.out.println("\nEmployees filtered by minimum salary: ");
        ArrayList<Employee> filteredbySalary = empPayrollService.getSalaryGreaterThan(30000);
        for (Employee employee : filteredbySalary) {
            System.out.println(employee);
        }
    }
}