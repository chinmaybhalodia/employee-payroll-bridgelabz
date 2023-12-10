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

-- UC8
-- adding employee information like phone, address and department
alter table employee_payroll add column phone varchar(20), add column address varchar(50), add column department varchar(20);
update employee_payroll set phone = "91 9456713547", address = "block-201", department = "Development" where emp_id = 1;
update employee_payroll set phone = "91 9456712345", address = "block-304", department = "Sales" where emp_id = 2;
update employee_payroll set phone = "91 9456123475", address = "block-503", department = "Marketing" where emp_id = 3;
update employee_payroll set phone = "91 9456785612", address = "block-204", department = "Development" where emp_id = 4;
select * from employee_payroll;

-- output to above query is
-- 1	Bill	10000	2022-10-11	male	91 9456713547	block-201	Development
-- 2	Alice	20000	2023-05-09	female	91 9456712345	block-304	Sales
-- 3	Charlie	35000	2023-01-01	male	91 9456123475	block-503	Marketing
-- 4	Dave	25000	2023-11-01	male	91 9456785612	block-204	Development

-- UC9
-- adding deductions, taxable pay, income tax, net pay to the table
alter table employee_payroll add column deductions int, add column taxable_pay int, add column income_tax int, add column net_pay int;
update employee_payroll set deductions = 2000, taxable_pay = 1000, income_tax = 200, net_pay = 8000 where emp_id = 1;
update employee_payroll set deductions = 3000, taxable_pay = 1500, income_tax = 500, net_pay = 17000 where emp_id = 2;
update employee_payroll set deductions = 5000, taxable_pay = 3000, income_tax = 1000, net_pay = 30000 where emp_id = 3;
update employee_payroll set deductions = 3000, taxable_pay = 1500, income_tax = 500, net_pay = 22000 where emp_id = 4;
select * from employee_payroll;

-- output to above query
-- 1	Bill	10000	2022-10-11	male	91 9456713547	block-201	Development		2000	1000	200		8000
-- 2	Alice	20000	2023-05-09	female	91 9456712345	block-304	Sales			3000	1500	500		17000
-- 3	Charlie	35000	2023-01-01	male	91 9456123475	block-503	Marketing		5000	3000	1000	30000
-- 4	Dave	25000	2023-11-01	male	91 9456785612	block-204	Development		3000	1500	500		22000

-- UC10
-- adding Terissa to Sales and Marketing department
insert into employee_payroll(name, salary, start_date, gender, phone, address, department, deductions, taxable_pay, income_tax, net_pay) values
 ("Terissa", 40000, "2023-12-08", "female", "91 9456314785", "block-405", "Sales", 5000, 3000, 1000, 35000),
 ("Terissa", 40000, "2023-12-08", "female", "91 9456314785", "block-405", "Marketing", 5000, 3000, 1000, 35000);
select * from employee_payroll;

-- output to the above query
-- 1	Bill	10000	2022-10-11	male	91 9456713547	block-201	Development	2000	1000	200		8000
-- 2	Alice	20000	2023-05-09	female	91 9456712345	block-304	Sales		3000	1500	500		17000
-- 3	Charlie	35000	2023-01-01	male	91 9456123475	block-503	Marketing	5000	3000	1000	30000
-- 4	Dave	25000	2023-11-01	male	91 9456785612	block-204	Development	3000	1500	500		22000
-- 5	Terissa	40000	2023-12-08	female	91 9456314785	block-405	Sales		5000	3000	1000	35000
-- 6	Terissa	40000	2023-12-08	female	91 9456314785	block-405	Marketing	5000	3000	1000	35000

-- UC11
-- normalizing the database and creating tables as per entities and their relations
alter table employee_payroll drop column department; 
delete from employee_payroll where name = "Terissa";
insert into employee_payroll(name, salary, start_date, gender, phone, address, deductions, taxable_pay, income_tax, net_pay) values 
	("Terissa", 40000, "2023-12-08", "female", "91 9456314785", "block-405", 5000, 3000, 1000, 35000);
select * from employee_payroll;
desc employee_payroll;

-- output to the above query
-- 1	Bill	10000	2022-10-11	male	91 9456713547	block-201	2000	1000	200		8000
-- 2	Alice	20000	2023-05-09	female	91 9456712345	block-304	3000	1500	500		17000
-- 3	Charlie	35000	2023-01-01	male	91 9456123475	block-503	5000	3000	1000	30000
-- 4	Dave	25000	2023-11-01	male	91 9456785612	block-204	3000	1500	500		22000
-- 7	Terissa	40000	2023-12-08	female	91 9456314785	block-405	5000	3000	1000	35000

-- creating table for all the departments
create table departments(
	dep_id int not null auto_increment,
    department varchar(20),
    primary key(dep_id)
);
insert into departments(department) values ("Development"), ("Sales"), ("Marketing");
select * from departments;

-- output to above query
-- 1	Development
-- 2	Sales
-- 3	Marketing

-- creating table to map emp_id to dep_id
create table employee_departments(
	emp_id int not null,
    dep_id int not null,
    foreign key(emp_id) references employee_payroll(emp_id),
    foreign key(dep_id) references departments(dep_id)
);
insert into employee_departments(emp_id, dep_id) values
	(1,1), (2,2), (3,3), (4,1), (7,2), (7,3);
select * from employee_departments;

-- output to the above query
-- 1	1
-- 2	2
-- 3	3
-- 4	1
-- 7	2
-- 7	3

-- UC12
-- ability to retrieve data as per UC4,5 and 7

-- retrieving all the data
select 
	ep.emp_id,
    ep.name,
    ep.salary,
    ep.start_date,
    ep.gender,
    ep.phone,
    ep.address,
    ep.deductions,
    ep.taxable_pay,
    ep.income_tax,
    ep.net_pay,
    dep.department
from employee_payroll ep inner join employee_departments ed on ep.emp_id = ed.emp_id 
	inner join departments dep on ed.dep_id = dep.dep_id;
    
-- output to the above query
-- 1	Bill	10000	2022-10-11	male	91 9456713547	block-201	2000	1000	200		8000	Development
-- 2	Alice	20000	2023-05-09	female	91 9456712345	block-304	3000	1500	500		17000	Sales
-- 3	Charlie	35000	2023-01-01	male	91 9456123475	block-503	5000	3000	1000	30000	Marketing
-- 4	Dave	25000	2023-11-01	male	91 9456785612	block-204	3000	1500	500		22000	Development
-- 7	Terissa	40000	2023-12-08	female	91 9456314785	block-405	5000	3000	1000	35000	Sales
-- 7	Terissa	40000	2023-12-08	female	91 9456314785	block-405	5000	3000	1000	35000	Marketing

-- retrieving employees who have joined between particular start date
select name, start_date from employee_payroll where start_date between cast("2023-1-1" as date) and date(now());

-- output to the above query
-- Alice	2023-05-09
-- Charlie	2023-01-01
-- Dave		2023-11-01
-- Terissa	2023-12-08

-- getting sum, min and max of salaries for male employees
select sum(salary), min(salary), max(salary) from employee_payroll where gender = "male" group by gender;

-- output to above query
-- 70000	10000	35000

-- getting average salary for male and female employees
select gender, avg(salary) from employee_payroll group by gender;

-- output to above query
-- male	23333.333333333332
-- female	30000

-- getting number of male and female employees
select gender, count(gender) from employee_payroll group by gender;

-- output to above query
-- male	3
-- female	2
 