package com.harini.jdbc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TableData {

	public static void main(String args[]) {
		
		Employee employee1 = new Employee(501, "Prabhas", 200000);
		Employee employee2 = new Employee(502, "Rana", 300000);
		Employee employee3 = new Employee(503, "Anushka", 400000);

		Map<Integer, Employee> employeeTable = new HashMap<Integer, Employee>();

		employeeTable.put(employee1.getEmployeeID(),employee1);
		employeeTable.put(employee2.getEmployeeID(),employee2);
		employeeTable.put(employee3.getEmployeeID(),employee3);
		
		
		Set<Integer> mapKeys = employeeTable.keySet();

		Iterator<Integer> itr = mapKeys.iterator();
		
	//	System.out.println(itr.next());

		while (itr.hasNext()) {
			Integer employeeID = itr.next();
			Employee employee = employeeTable.get(employeeID);
			
			System.out.println(employee.getEmployeeID() + employee.getEmployeeName() + employee.getEmployeeSalary());
		}
		
	}
}


