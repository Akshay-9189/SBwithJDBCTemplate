# SB with JDBC Template

## SQL QUERIES :-

1. ### Create Database :- 

CREATE DATABASE sb_jdbc

2. ### Create Table

CREATE TABLE employee(empid INT, empname VARCHAR(255), empaddress VARCHAR(255), empcontactnumber BIGINT, empdob DATE, 
empsalary DOUBLE, empemailid VARCHAR(255), emppassword VARCHAR(255), PRIMARY KEY(empid), UNIQUE KEY(empcontactnumber), 
UNIQUE KEY(empemailid))

3. ### Get All Data

SELECT * FROM employee
