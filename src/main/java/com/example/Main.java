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

        // getting all employees joining between the given dates
        System.out.println("\nEmployees joining after 2023-1-1 are: ");
        ArrayList<String> emp_by_start_date = empPayrollService.getQueryDataFromDB(
                "select name, start_date from employee_payroll where start_date between cast(\"2023-1-1\" as date) and date(now());");
        for (String str : emp_by_start_date) {
            System.out.println(str);
        }

        // getting salary statistics by gender
        System.out.println("\nSalary statistics by gender are: ");
        ArrayList<String> salary_stats = empPayrollService.getStatsByGenderFromDB(
                "select gender, sum(salary), min(salary), max(salary), avg(salary) from employee_payroll group by gender; ");
        for (String str : salary_stats) {
            System.out.println(str);
        }
    }
}