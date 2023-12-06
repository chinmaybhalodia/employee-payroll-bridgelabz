package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll Service.");
        Scanner sc = new Scanner(System.in);

        // UC1: creating a payroll system from console
        EmpPayrollService empPayrollService = new EmpPayrollService();

        // reading inputs from console
        while (true) {
            System.out.println("Which operation do you want to perform?");
            System.out.println("[1] Add Employee");
            System.out.println("[2] View Employees");
            System.out.print("Enter your choice (enter 0 to exit): ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 0:
                    return;

                case 1:
                    empPayrollService.readEmpData(sc);
                    break;

                case 2:
                    System.out.println("\nList of employees: \n" + empPayrollService);
                    break;

                default:
                    break;
            }
        }
    }
}