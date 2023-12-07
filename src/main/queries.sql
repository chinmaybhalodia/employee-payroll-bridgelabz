-- UC1
-- creating a new database 
create database payroll_service;

-- viewing the database
show databases;

-- using the created database
use payroll_service;

-- UC2
-- creating table for employee_payroll
create table employee_payroll (
	emp_id int auto_increment not null, 
	name varchar(50) not null, 
    salary double, 
    start_date date, 
    primary key (emp_id)
);

-- UC3
-- inseting data into the table
insert into employee_payroll (name, salary, start_date) values
	("Bill", 10000, "2022-10-11"),
    ("Alice", "20000", "2023-5-9"),
    ("Charlie", "35000", "2023-1-1"),
    ("Dave", "25000", "2023-11-1");
    
-- UC4
-- reading all data from the table
select * from employee_payroll;

-- output to above query
-- 1	Bill	10000	2022-10-11
-- 2	Alice	20000	2023-05-09
-- 3	Charlie	35000	2023-01-01
-- 4	Dave	25000	2023-11-01

-- UC5
-- getting salary of employee named 'Bill'
select name, salary from employee_payroll where name = "Bill";

-- output to above query
-- Bill	10000

-- getting employees who joined after 2023
select name, start_date from employee_payroll where start_date between cast("2023-1-1" as date) and date(now());

-- output to above query
-- Alice	2023-05-09
-- Charlie	2023-01-01
-- Dave	2023-11-01

-- UC6
-- adding a column of gender to the table
alter table employee_payroll add column gender varchar(10);
select * from employee_payroll;

-- output to above query
-- 1	Bill	10000	2022-10-11	null
-- 2	Alice	20000	2023-05-09	null
-- 3	Charlie	35000	2023-01-01	null
-- 4	Dave	25000	2023-11-01	null

-- updating values for gender column in table
update employee_payroll set gender = "male" where emp_id in (1, 3, 4);
update employee_payroll set gender = "female" where emp_id = 2;
select * from employee_payroll;

-- output to above queries
-- 1	Bill	10000	2022-10-11	male
-- 2	Alice	20000	2023-05-09	female
-- 3	Charlie	35000	2023-01-01	male
-- 4	Dave	25000	2023-11-01	male

-- UC7
-- getting sum of salaries for male employees
select sum(salary) from employee_payroll where gender = "male" group by gender;

-- output to above query
-- 70000

-- getting average salary for male and female employees
select gender, avg(salary) from employee_payroll group by gender;

-- output to above query
-- male	23333.333333333332
-- female	20000

-- getting number of male and female employees
select gender, count(gender) from employee_payroll group by gender;

-- output to above query
-- male	3
-- female	1

-- getting table sorted by name
select * from employee_payroll order by name asc;

-- output to above query
-- 2	Alice	20000	2023-05-09	female
-- 1	Bill	10000	2022-10-11	male
-- 3	Charlie	35000	2023-01-01	male
-- 4	Dave	25000	2023-11-01	male