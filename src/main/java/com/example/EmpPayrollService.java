package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class EmpPayrollService {
    private ArrayList<Employee> employees;
    private String filePath;

    public EmpPayrollService() {
        this.employees = new ArrayList<>();
        // connecting to the database
        this.connectDatabase();
    }

    // employee payroll constructor with file I/O
    public EmpPayrollService(String filePath) {
        this.filePath = filePath;
        FileOperations.createFile(filePath);
    }

    // DATABASE FUNCTIONS

    // connecting to the database
    public void connectDatabase() {
        DBOperations.getConnection();
    }

    // method to get all employee data from database
    public ArrayList<Employee> getEmployeesFromDB() {
        return DBOperations.readEmployees();
    }

    // method to update salary in database
    public void updateSalaryInDB(double salary, String name) {
        DBOperations.updateSalary(salary, name);
    }

    // method to get employees between date range
    public ArrayList<Employee> getInDateRange(String start_date, String end_date) {
        return DBOperations.getInDataRange(start_date, end_date);
    }

    // method to allow custom query execution
    public ArrayList<String> getQueryDataFromDB(String query) {
        return DBOperations.getData(query);
    }

    // method to get salary stats by gender
    public ArrayList<String> getStatsByGenderFromDB() {
        return DBOperations.getStatsByGender();
    }

    // FILE IO FUNCTIONS

    public String getFilePath() {
        return this.filePath;
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    // method to add new employee to the file
    public void addEmployeeToFile(Employee employee) {
        FileOperations.writeToFile(filePath, employee.toCSVString());
    }

    // method to count number of employees in file
    public int countEmployeesInFile() {
        return FileOperations.countLines(this.filePath);
    }

    // method to add employee from console to employees list
    public void addEmployeeConsole(Scanner inputReader) {
        System.out.print("Enter employee Name: ");
        String name = inputReader.nextLine();

        System.out.print("Enter start date (YYYY-MM-DD): ");
        String start_date = inputReader.nextLine();

        System.out.print("Enter employee gender: ");
        String gender = inputReader.nextLine();

        System.out.print("Enter employee phone number: ");
        String phone = inputReader.nextLine();

        System.out.print("Enter employee address: ");
        String address = inputReader.nextLine();

        System.out.print("Enter employee Salary: ");
        double salary = inputReader.nextDouble();
        inputReader.nextLine();

        System.out.print("Enter employee department: ");
        String department = inputReader.nextLine();

        Employee employee = new Employee(name, start_date, gender, phone, address, salary, department);

        DBOperations.addEmployee(employee);
    }

    // method to print all employees in employees list
    @Override
    public String toString() {
        String data = "";
        for (Employee employee : this.employees) {
            data += employee.toString() + "\n";
        }
        return data;
    }
}
