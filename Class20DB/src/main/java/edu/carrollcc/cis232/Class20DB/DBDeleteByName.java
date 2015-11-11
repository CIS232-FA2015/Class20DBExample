package edu.carrollcc.cis232.Class20DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBDeleteByName {

	public static void main(String[] args) throws SQLException{
		Connection conn = DriverManager.getConnection(Environment.DB_URL);
		Statement stmt = conn.createStatement();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What is the beginning of the name of the student you\'d like to delete?");
		
		String name = keyboard.nextLine().trim();
		
		String deleteStudentById = "delete from Students"
				+ String.format(" where name like '%s%%'", name);
		
		int numDeleted = stmt.executeUpdate(deleteStudentById);
		
		System.out.printf("%d Students deleted!%n", numDeleted);
		
		conn.close();
				
	}

}
