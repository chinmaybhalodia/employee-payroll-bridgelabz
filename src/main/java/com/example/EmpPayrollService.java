package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class EmpPayrollService {
    private ArrayList<Employee> employees;
    private String filePath;

    public EmpPayrollService() {
        this.employees = new ArrayList<>();
    }

    // employee payroll constructor with file I/O
    public EmpPayrollService(String filePath) {
        this.filePath = filePath;
        FileOperations.createFile(filePath);
    }

    public String getFilePath() {
        return this.filePath;
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    // method to add new employee to the file
    public void addEmployeeToFile(Employee employee) {
        FileOperations.writeToFile(filePath, employee.toString());
    }

    // method to count number of empl oyees in file
    public int countEmployeesInFile() {
        return FileOperations.countLines(this.filePath);
    }

    // method to print employees from file
    public void printEmployeesFromFile() {
        ArrayList<Employee> empList = FileOperations.readFromFile(this.filePath);
        for (Employee employee : empList) {
            System.out.println(employee);
        }
    }

    // method to print employees after sorting them by salary
    public ArrayList<Employee> sortBySalary() {
        ArrayList<Employee> empList = FileOperations.readFromFile(this.filePath);
        empList.sort(Comparator.comparing(Employee::getSalary));
        return empList;
    }

    // method to print employees after sorting them by Id
    public ArrayList<Employee> sortByID() {
        ArrayList<Employee> empList = FileOperations.readFromFile(this.filePath);
        empList.sort(Comparator.comparing(Employee::getId));
        return empList;
    }

    // method to print employees after sorting them by Name
    public ArrayList<Employee> sortByName() {
        ArrayList<Employee> empList = FileOperations.readFromFile(this.filePath);
        empList.sort(Comparator.comparing(Employee::getName));
        return empList;
    }

    // method to check employees with salaries greater than given input
    public ArrayList<Employee> getSalaryGreaterThan(double salary) {
        ArrayList<Employee> empList = FileOperations.readFromFile(this.filePath);
        empList.removeIf(employee -> employee.getSalary() < salary);
        return empList;
    }

    // method to add employee from console to employees list
    public void addEmployeeConsole(Scanner inputReader) {
        System.out.print("\nEnter employee Id: ");
        int id = inputReader.nextInt();
        inputReader.nextLine();

        System.out.print("Enter employee Name: ");
        String name = inputReader.nextLine();

        System.out.print("Enter employee Salary: ");
        double salary = inputReader.nextDouble();
        inputReader.nextLine();

        this.employees.add(new Employee(id, name, salary));
        System.out.println("Employee has been added successfully.\n");
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
