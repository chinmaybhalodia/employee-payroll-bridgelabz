package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll Service.");

        // employee payroll with file I/O
        EmpPayrollService empPayrollService = new EmpPayrollService("employees.txt");

        // adding new Employee
        empPayrollService.addEmployeeToFile(new Employee(1, "Chinmay", 50000));

        // checking number of entries in file
        System.out.println(empPayrollService.countEmployeesInFile());

        // adding one more employee and then counting entries
        empPayrollService.addEmployeeToFile(new Employee(2, "Person1", 20000));
        System.out.println(empPayrollService.countEmployeesInFile());
    }
}