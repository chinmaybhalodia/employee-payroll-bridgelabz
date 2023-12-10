package com.example;

public class Employee {
    private int id;
    private String name;
    private String start_date;
    private String gender;
    private String phone;
    private String address;
    private double salary;
    private double deductions;
    private double taxable_pay;
    private double income_tax;
    private double net_pay;
    private String department;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee(int id, String name, String start_date, String gender, String phone, String address, double salary,
            double deductions, double taxable_pay, double income_tax, double net_pay, String department) {
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.deductions = deductions;
        this.taxable_pay = taxable_pay;
        this.income_tax = income_tax;
        this.net_pay = net_pay;
        this.department = department;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getStartDate() {
        return this.start_date;
    }

    public String getGender() {
        return this.gender;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAddress() {
        return this.address;
    }

    public double getSalary() {
        return this.salary;
    }

    public double getDeductions() {
        return this.deductions;
    }

    public double getTaxablePay() {
        return this.taxable_pay;
    }

    public double getIncomeTax() {
        return this.income_tax;
    }

    public double getNetPay() {
        return this.net_pay;
    }

    public String department() {
        return this.department;
    }

    @Override
    public String toString() {
        return this.id + "," + this.name + "," + this.salary;
    }
}