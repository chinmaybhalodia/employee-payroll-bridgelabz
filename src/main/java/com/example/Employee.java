package com.example;

public class Employee {
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

    public Employee(String name, String start_date, String gender, String phone, String address, double salary,
            String department) {
        this.name = name;
        this.start_date = start_date;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
        this.deductions = this.salary * 0.2;
        this.taxable_pay = this.salary - this.deductions;
        this.income_tax = this.taxable_pay * 0.1;
        this.net_pay = this.salary - this.income_tax;
        this.department = department;
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

    public String getDepartment() {
        return this.department;
    }

    // method to convert to CSV String
    public String toCSVString() {
        return this.name + ","
                + this.start_date + ","
                + this.gender + ","
                + this.phone + ","
                + this.address + ","
                + this.salary + ","
                + this.deductions + ","
                + this.taxable_pay + ","
                + this.income_tax + ","
                + this.net_pay + ","
                + this.department;
    }

    @Override
    public String toString() {
        return "Name: " + this.name
                + "\nStart Date: " + this.start_date
                + "\nGender: " + gender
                + "\nPhone: " + this.phone
                + "\nAddress: " + this.address
                + "\nSalary: " + this.salary
                + "\nDeductions: " + this.deductions
                + "\nTaxable Pay: " + this.taxable_pay
                + "\nIncome Tax: " + this.income_tax
                + "\nNet Pay: " + this.net_pay
                + "\nDepartment: " + this.department;
    }
}