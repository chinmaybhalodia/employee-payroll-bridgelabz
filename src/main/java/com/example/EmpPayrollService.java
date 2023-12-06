package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class EmpPayrollService {
    private ArrayList<Employee> employees;

    public EmpPayrollService() {
        this.employees = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    public boolean addEmployee(Employee employee) {
        return this.employees.add(employee);
    }

    public void readEmpData(Scanner inputReader) {
        System.out.print("\nEnter employee Id: ");
        int id = inputReader.nextInt();
        inputReader.nextLine();

        System.out.print("Enter employee Name: ");
        String name = inputReader.nextLine();

        System.out.print("Enter employee Salary: ");
        double salary = inputReader.nextDouble();
        inputReader.nextLine();

        this.addEmployee(new Employee(id, name, salary));
        System.out.println("Employee has been added successfully.\n");
    }

    @Override
    public String toString() {
        String data = "";
        for (Employee employee : this.employees) {
            data += employee.toString() + "\n";
        }
        return data;
    }
}
