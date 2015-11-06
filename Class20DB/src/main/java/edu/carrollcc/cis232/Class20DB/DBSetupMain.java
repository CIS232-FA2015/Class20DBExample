package edu.carrollcc.cis232.Class20DB;

import java.sql.*;

public class DBSetupMain {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DriverManager.getConnection(Environment.DB_URL);
		System.out.println("Connected to database!");

		Statement stmt = conn.createStatement();
		
		try {
			String dropTable = "drop table Students";
			stmt.execute(dropTable);
			System.out.println("Students table dropped.");
		} catch (SQLException e) {
			System.out.println("Students table does not exist.");
		}
		
		String createTable = "create table Students("
				+ "id int not null primary key, " 
				+ "name varchar(100), "
				+ "points int" 
				+ ")";
		
		stmt.execute(createTable);
		System.out.println("Students table created.");
		
		addStudent(conn, 1, "Alex", 0);
		addStudent(conn, 2, "Kirby", 0);
		addStudent(conn, 3, "Nick", 0);
		addStudent(conn, 4, "Bill", 0);
		
		conn.close();
	}
	
	public static void addStudent(Connection conn, int id, String name, int points){
		try {
			Statement stmt = conn.createStatement();
			String insertStudent = String.format("insert into Students (id, name, points)"
					+ " values (%d, '%s', %d)", id, name, points);
			stmt.executeUpdate(insertStudent);
			System.out.println("Student added!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
