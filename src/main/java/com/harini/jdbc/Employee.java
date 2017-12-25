package com.harini.jdbc;

public class Employee {
	
	int employeeID;
	String employeeName;
	int employeeSalary;
	
	Employee(int employeeID, String employeeName, int employeeSalary) {
		employeeID = this.employeeID;
		employeeName = this.employeeName;
		employeeSalary = this.employeeSalary;
	}
	
	int getEmployeeID() {
		return employeeID;
	}
	
	String getEmployeeName() {
		return employeeName;
	}
	
	int getEmployeeSalary() {
		return employeeSalary;
	}

}
