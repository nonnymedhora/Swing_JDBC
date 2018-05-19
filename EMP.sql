
DROP DATABASE IF EXISTS EMP;

CREATE DATABASE EMP;

USE EMP;

CREATE TABLE department (
	DeptNo VARCHAR(5) NOT NULL,
	DeptName VARCHAR(20),
	PRIMARY KEY (DeptNo)
);

CREATE TABLE salary (
	id VARCHAR(3) NOT NULL,
	salary VARCHAR(10) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE employee (
	LastName VARCHAR(20) NOT NULL,
	FirstName VARCHAR(20),
	EmpID VARCHAR(3) NOT NULL REFERENCES salary(id),
	DeptNo VARCHAR(5) NOT NULL REFERENCES department(DeptNo),
	PRIMARY KEY (EmpID)
);

INSERT INTO department VALUES("10001","ECONOMICS");
INSERT INTO department VALUES("10002","FINANCE");
INSERT INTO department VALUES("10003","ACCOUNTING");
INSERT INTO department VALUES("10004","RESEARCH");
INSERT INTO department VALUES("10005","MARKETING");


INSERT INTO employee VALUES("Stone", "Sharon", "101", "10004");
INSERT INTO employee VALUES("Saint", "Sylvia", "102", "10004");
INSERT INTO employee VALUES("Michaels", "Trina", "103", "10002");
INSERT INTO employee VALUES("Raveena", "Tandon", "104", "10001");
INSERT INTO employee VALUES("Skye", "Brittney", "105", "10003");
INSERT INTO employee VALUES("Applegate", "Christina", "106", "10003");
INSERT INTO employee VALUES("Faltoyano", "Rita", "107", "10002");
INSERT INTO employee VALUES("Silver", "Stacy", "108", "10005");
INSERT INTO employee VALUES("Deboor", "Krystal", "109", "10002");
INSERT INTO employee VALUES("Kean", "Katja", "110", "10003");

INSERT INTO salary VALUES("101","25000");
INSERT INTO salary VALUES("102","100000");
INSERT INTO salary VALUES("103","25000");
INSERT INTO salary VALUES("104","45000");
INSERT INTO salary VALUES("105","100000");
INSERT INTO salary VALUES("106","100000");
INSERT INTO salary VALUES("107","100000");
INSERT INTO salary VALUES("108","45000");
INSERT INTO salary VALUES("109","25000");
INSERT INTO salary VALUES("110","100000");