package com.example;

import java.sql.*;
import java.util.ArrayList;

public class DBOperations {

    // method to connect with the database
    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(Details.URL, Details.USER, Details.PASSWORD);
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
                String name = resultSet.getString("name");
                String start_date = resultSet.getString("start_date");
                String gender = resultSet.getString("gender");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                double salary = resultSet.getInt("salary");
                String department = resultSet.getString("department");

                Employee employee = new Employee(name, start_date, gender, phone, address, salary, department);
                employeeList.add(employee);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return employeeList;
    }

    // method to add new employee to the database
    public static void addEmployee(Employee employee) {
        String sqlQueryEmployeePayroll = "insert into employee_payroll (name, salary, start_date, gender, phone, address) values (?, ?, cast(? as date), ?, ?, ?);";
        String sqlQueryPayrollDetails = "insert into payroll_details (emp_id, deduction, taxable_pay, income_tax, net_pay) values (?, ?, ?, ?, ?);";
        String sqlQueryDepartmentCheck = "select dep_id from departments where department = ?;";
        String sqlQueryDepartmentInsert = "insert into departments (department) values (?);";
        String sqlQueryEmployeeDepartments = "insert into employee_departments (emp_id, dep_id) values (?, ?);";
        Connection connection = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            // Insert into employee_payroll
            PreparedStatement addEmployeePayroll = connection.prepareStatement(sqlQueryEmployeePayroll,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            addEmployeePayroll.setString(1, employee.getName());
            addEmployeePayroll.setDouble(2, employee.getSalary());
            addEmployeePayroll.setString(3, employee.getStartDate());
            addEmployeePayroll.setString(4, employee.getGender());
            addEmployeePayroll.setString(5, employee.getPhone());
            addEmployeePayroll.setString(6, employee.getAddress());
            addEmployeePayroll.executeUpdate();

            // Retrieve the auto-generated emp_id
            int emp_id;
            try (ResultSet generatedKeys = addEmployeePayroll.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    emp_id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating record failed, no ID obtained.");
                }
            }

            // insert into payroll_details
            PreparedStatement addPayrollDetails = connection.prepareStatement(sqlQueryPayrollDetails,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            addPayrollDetails.setInt(1, emp_id);
            addPayrollDetails.setDouble(2, employee.getDeductions());
            addPayrollDetails.setDouble(3, employee.getTaxablePay());
            addPayrollDetails.setDouble(4, employee.getIncomeTax());
            addPayrollDetails.setDouble(5, employee.getNetPay());
            addPayrollDetails.executeUpdate();

            // Check if the department already exists
            PreparedStatement checkDepartment = connection.prepareStatement(sqlQueryDepartmentCheck);
            checkDepartment.setString(1, employee.getDepartment());
            ResultSet resultSet = checkDepartment.executeQuery();

            int dep_id;
            if (resultSet.next()) {
                // Department already exists, return dep_id
                dep_id = resultSet.getInt("dep_id");
            } else {
                // If department does not exist, insert it
                PreparedStatement insertDepartment = connection.prepareStatement(sqlQueryDepartmentInsert,
                        PreparedStatement.RETURN_GENERATED_KEYS);
                insertDepartment.setString(1, employee.getDepartment());
                insertDepartment.executeUpdate();

                // Retrieve the auto-generated dep_id
                try (ResultSet generatedKeys = insertDepartment.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        dep_id = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating record failed, no ID obtained.");
                    }
                }
            }

            // Insert into employee_departments
            PreparedStatement addEmployeeDepartments = connection.prepareStatement(sqlQueryEmployeeDepartments);
            addEmployeeDepartments.setInt(1, emp_id);
            addEmployeeDepartments.setInt(2, dep_id);
            addEmployeeDepartments.executeUpdate();

            connection.commit();
            System.out.println("Employee has been added successfully.\n");
        } catch (SQLException exception) {
            exception.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // method to update salary of particular entry
    public static void updateSalary(double salary, String name) {
        String sqlQuery = "update employee_payroll set salary = ? where name = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setDouble(1, salary);
            statement.setString(2, name);
            statement.executeUpdate();
            System.out.println("Updated the entry successfully!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    // method to get employee data in date range
    public static ArrayList<Employee> getInDataRange(String range_start_date, String range_end_date) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        String sqlQuery = "select ep.emp_id, ep.name, ep.salary, ep.start_date, ep.gender, ep.phone, ep.address, ep.deductions, ep.taxable_pay, ep.income_tax, ep.net_pay, dep.department from employee_payroll ep inner join employee_departments ed on ep.emp_id = ed.emp_id inner join departments dep on ed.dep_id = dep.dep_id where start_date between cast(? as date) and cast(? as date);";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sqlQuery);) {
            statement.setString(1, range_start_date);
            statement.setString(2, range_end_date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String start_date = resultSet.getString("start_date");
                String gender = resultSet.getString("gender");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                double salary = resultSet.getInt("salary");
                String department = resultSet.getString("department");

                Employee employee = new Employee(name, start_date, gender, phone, address, salary, department);
                employeeList.add(employee);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return employeeList;
    }

    // method to execute custom query to fetch data
    public static ArrayList<String> getData(String sqlQuery) {
        ArrayList<String> data = new ArrayList<>();
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery)) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String start_date = resultSet.getString("start_date");
                data.add(name + ", " + start_date);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return data;
    }

    // method to get salary stats by gender
    public static ArrayList<String> getStatsByGender() {
        ArrayList<String> data = new ArrayList<>();
        String sqlQuery = "select gender, sum(salary), min(salary), max(salary), avg(salary) from employee_payroll group by gender;";
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);) {
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                double sum = resultSet.getDouble("sum(salary)");
                double min = resultSet.getDouble("min(salary)");
                double max = resultSet.getDouble("max(salary)");
                double avg = resultSet.getDouble("avg(salary)");
                data.add(gender + ", " + sum + ", " + min + ", " + max + ", " + avg);
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
        return data;
    }
}