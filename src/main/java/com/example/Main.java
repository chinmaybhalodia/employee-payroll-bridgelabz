package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Payroll Service.");

        EmpPayrollService empPayrollService = new EmpPayrollService();
        Scanner inputReader = new Scanner(System.in);

        while (true) {
            System.out.println("What opration do you want to perform?");
            System.out.println("[1] Print all employees details");
            System.out.println("[2] Add new employee");
            System.out.println("[3] Print employees joining in date range");
            System.out.println("[4] Update employee salary");
            System.out.println("[5] View employee details by gender");
            System.out.print("Enter your choice (enter 0 to exit): ");
            int choice = inputReader.nextInt();
            inputReader.nextLine();

            switch (choice) {
                case 0:
                    inputReader.close();
                    return;

                case 1:
                    ArrayList<Employee> employees = empPayrollService.getEmployeesFromDB();
                    int i1 = 1;
                    for (Employee employee : employees) {
                        System.out.println(i1 + ")\n" + employee + "\n");
                        i1++;
                    }
                    break;

                case 2:
                    empPayrollService.addEmployeeConsole(inputReader);
                    break;

                case 3:
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    String start_date = inputReader.nextLine();

                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    String end_date = inputReader.nextLine();

                    System.out.println("\nEmployees joining between" + start_date + " and " + end_date + " are: ");
                    ArrayList<Employee> emp_in_range = empPayrollService.getInDateRange(start_date, end_date);
                    int i2 = 1;
                    for (Employee employee : emp_in_range) {
                        System.out.println(i2 + ")\n" + employee + "\n");
                        i2++;
                    }
                    break;

                case 4:
                    System.out.print("Enter employee name: ");
                    String name = inputReader.nextLine();

                    System.out.print("Enter updated salary: ");
                    double salary = inputReader.nextDouble();
                    inputReader.nextLine();
                    empPayrollService.updateSalaryInDB(salary, name);
                    break;

                case 5:
                    System.out.println("\nSalary statistics by gender are: ");
                    ArrayList<String> salary_stats = empPayrollService.getStatsByGenderFromDB();
                    for (String str : salary_stats) {
                        System.out.println(str);
                    }
                    break;

                default:
                    break;
            }
        }
    }
}