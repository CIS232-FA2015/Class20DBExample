package edu.carrollcc.cis232.Class20DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBDelete {

	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection(Environment.DB_URL);
		Statement stmt = conn.createStatement();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What is the id of the student you\'d like to delete?");
		
		int id = keyboard.nextInt();
		
		String deleteStudentById = "delete from Students"
				+ String.format(" where id = %d", id);
		
		int numDeleted = stmt.executeUpdate(deleteStudentById);
		
		System.out.printf("%d Students deleted!%n", numDeleted);
		
		conn.close();
				
	}

}
