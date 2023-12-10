package com.example;

import java.sql.*;
import java.util.ArrayList;

public class DBOperations {

    // method to connect with the database
    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(Details.URL, Details.USER, Details.PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            connection = null;
        }
        return connection;
    }

    // reading all the data from the database
    public static ArrayList<Employee> readEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        String sqlQuery = "select ep.emp_id, ep.name, ep.salary, ep.start_date, ep.gender, ep.phone, ep.address, ep.deductions, ep.taxable_pay, ep.income_tax, ep.net_pay, dep.department from employee_payroll ep inner join employee_departments ed on ep.emp_id = ed.emp_id inner join departments dep on ed.dep_id = dep.dep_id;";

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("emp_id");
                String name = resultSet.getString("name");
                String start_date = resultSet.getString("start_date");
                String gender = resultSet.getString("gender");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                double salary = resultSet.getInt("salary");
                double deductions = resultSet.getInt("deductions");
                double taxable_pay = resultSet.getInt("taxable_pay");
                double income_tax = resultSet.getInt("income_tax");
                double net_pay = resultSet.getInt("net_pay");
                String department = resultSet.getString("department");

                Employee employee = new Employee(id, name, start_date, gender, phone, address, salary, deductions,
                        taxable_pay, income_tax, net_pay, department);
                employeeList.add(employee);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return employeeList;
    }

    // method to update salary of particular entry
    public static void update(int salary, String name) {
        String sqlQuery = "update employee_payroll set salary = " + salary + " where name = \"" + name + "\";";
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
            System.out.println("Updated the entry successfully!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}