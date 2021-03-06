package edu.carrollcc.cis232.Class20DB;

import java.sql.*;

public class DBReader {

	public static void main(String[] args) throws SQLException{
		Connection conn = DriverManager.getConnection(Environment.DB_URL);
		
		Statement stmt = conn.createStatement();
		
		//Read all rows from the Students table
		
		//1. Create  Select statement SQL
		String select = "select name, points from Students";
		
		//Execute query
		ResultSet results = stmt.executeQuery(select);
		
		//Read the results
		while(results.next()){
			System.out.printf("%s %d%n", results.getString("name"), results.getInt("points"));
		}
		
		conn.close();
	}

}
