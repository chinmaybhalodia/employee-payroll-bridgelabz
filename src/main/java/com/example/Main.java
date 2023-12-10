package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll Service.");

        EmpPayrollService empPayrollService = new EmpPayrollService();

        // connecting to the database
        empPayrollService.connectDatabase();
    }
}