package com.harini.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyFirstJdbc {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		String sql = "SELECT EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_SALARY FROM EMPLOYEE";

		MyFirstJdbc myFirstJdbc = new MyFirstJdbc(); // Class object is created to call non static methods of this call

		Connection connection = myFirstJdbc.getConnection();
		
		boolean success =  myFirstJdbc.addEmployee(connection, 106, "Lakshmi", 2000000);
		System.out.println("Row Inserted : " + success);
		

		List<Map<String, String>> rowData = myFirstJdbc.executeQuery(connection, sql);
		myFirstJdbc.closeConnection(connection);

		printTable(rowData);

	}

	private static void printTable(List<Map<String, String>> rowData) {
		Set<String> columnSet = rowData.get(0).keySet();

		for (Map<String, String> row : rowData) {
			for (String column : columnSet) {

				System.out.print(row.get(column) + "\t");
			}
			System.out.println();
		}
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connection = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/harini", "harini", "harini");
		return connection;

	}

	public boolean addEmployee(Connection connection, int employeeId, String employeeName, int salary) throws SQLException {
		
		String sql = "INSERT INTO EMPLOYEE VALUES (" + employeeId +",'" + employeeName +"'," + salary + " )";
		System.out.println("Insert Query" + sql);
		Statement statement = connection.prepareStatement(sql);
		int rowsInserted = statement.executeUpdate(sql);
		return rowsInserted > 0;

	}
	
	public boolean addEmployee1(Connection connection, int employeeId, String employeeName, int salary) throws SQLException {
		
		String sql = "INSERT INTO EMPLOYEE (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_SALARY) VALUES (?,?,?)";
		System.out.println("Insert Query : " + sql);
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, employeeId);
		preparedStatement.setString(2, employeeName);
		preparedStatement.setInt(3, salary);
		int rowsInserted = preparedStatement.executeUpdate(sql);
		return rowsInserted > 0;

	}
	
	
	

	public List<Map<String, String>> executeQuery(Connection connection, String sql)
			throws SQLException, ClassNotFoundException {

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		ResultSetMetaData metaData = resultSet.getMetaData();

		List<String> columns = new ArrayList<String>();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			columns.add(metaData.getColumnName(i));
		}

		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		while (resultSet.next()) { // Iterates if there are rows of data
			Map<String, String> row = new HashMap<String, String>();
			for (String col : columns) {
				row.put(col, resultSet.getString(col));
			}
			data.add(row);
		}

		resultSet.close();
		statement.close();

		return data;

	}

	public void closeConnection(Connection connection) throws SQLException, ClassNotFoundException {

		connection.close();

	}

}