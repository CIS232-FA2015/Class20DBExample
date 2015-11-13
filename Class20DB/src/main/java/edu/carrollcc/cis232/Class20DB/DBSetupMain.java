package edu.carrollcc.cis232.Class20DB;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DBSetupMain {

	public static void main(String[] args) throws SQLException, FileNotFoundException {
		
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
				+ "id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " 
				+ "name varchar(100), "
				+ "points int, " 
				+ "primary key(id)"
				+ ")";
		
		stmt.execute(createTable);
		System.out.println("Students table created.");
				
		File studentsFile = new File("students.csv");
		Scanner inputStudents = new Scanner(studentsFile);
		while(inputStudents.hasNextLine()){
			String studentLine = inputStudents.nextLine();
			StringTokenizer tokens = new StringTokenizer(studentLine, ",");
			
			addStudent(conn, tokens.nextToken(), Integer.parseInt(tokens.nextToken()));
		}
		inputStudents.close();
		
		conn.close();
	}
	
	public static void addStudent(Connection conn, String name, int points){
		try {
			Statement stmt = conn.createStatement();
			String insertStudent = String.format("insert into Students (name, points)"
					+ " values ( '%s', %d)", name, points);
			stmt.executeUpdate(insertStudent);
			System.out.println("Student added!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
