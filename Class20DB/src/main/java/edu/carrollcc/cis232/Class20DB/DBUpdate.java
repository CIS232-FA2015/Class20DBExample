package edu.carrollcc.cis232.Class20DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBUpdate {

	public static void main(String[] args) throws SQLException{
		Connection conn = DriverManager.getConnection(Environment.DB_URL);
		Statement stmt = conn.createStatement();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What is the id of the student you\'d like to look up?");
		
		int id = keyboard.nextInt();
		
		printStudentById(stmt, id);
		
		System.out.println("How many points would you like to add to the student?");
		int points = keyboard.nextInt();
		String updatePointsById = "update Students"
				+String.format(" set points = points + %d", points)
				+String.format(" where id = %d", id);
		
		stmt.executeUpdate(updatePointsById);
		
		printStudentById(stmt, id);
		
		conn.close();
	}

	private static void printStudentById(Statement stmt, int id)
			throws SQLException {
		//Create select statement with "where" filter
		String getStudentById = "select name, points from Students "
				+ String.format("where id = %d", id);
		
		//Execute select statement
		ResultSet results = stmt.executeQuery(getStudentById);
		
		//Iterate over results
		while(results.next()){
			String name = results.getString("name");
			int points = results.getInt("points");
			
			System.out.printf("Student with id %d has name '%s' and points '%d'%n", id,
					name, points);
		}
	}
}
