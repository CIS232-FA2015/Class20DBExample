package edu.carrollcc.cis232.Class20DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSelectExamples {

	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection(Environment.DB_URL);
		Statement stmt = conn.createStatement();

		System.out.println("Order by points descending");
		String selectOrderByPoints = "select * from Students "
				+ " order by points desc";
		printStudentReults(stmt, selectOrderByPoints);

		
		System.out.println("\nOrder by name case insensitive");
		String selectOrderByName = "select * from Students "
				+ " order by lower(name) asc";
		printStudentReults(stmt, selectOrderByName);
		
		System.out.println("Get highest number of points");
		String selectMaxPoints = "select max(points)from Students ";
		ResultSet results = stmt.executeQuery(selectMaxPoints);
		if(results.next()){
			System.out.println(results.getInt(1));
		}
		
		System.out.println("\nPrint student info for max points");
		String selectStudentsWithMaxPoints = "select * from Students "
				+ " where points = (select max(points) from Students)";
		printStudentReults(stmt, selectStudentsWithMaxPoints);
		
		conn.close();
	}

	private static void printStudentReults(Statement stmt,
			String selectOrderByPoints) throws SQLException {
		ResultSet results = stmt.executeQuery(selectOrderByPoints);

		// Read the results
		while (results.next()) {
			System.out.printf("%d %s %d%n", results.getInt("id"),
					results.getString("name"), results.getInt("points"));
		}
	}

}
