package edu.carrollcc.cis232.Class20DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBResultsMetaData {

	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection(Environment.DB_URL);
		Statement stmt = conn.createStatement();
		
		String selectStarStudents = "select * from Students";
		ResultSet results = stmt.executeQuery(selectStarStudents);
		
		ResultSetMetaData meta = results.getMetaData();
		System.out.println("Number of columns: " + meta.getColumnCount());
		System.out.println("Columns:");
		for(int col = 1; col <= meta.getColumnCount(); col++){
			System.out.printf("%s %s %d from table %s%n",meta.getColumnLabel(col),
					meta.getColumnTypeName(col), meta.getColumnDisplaySize(col),
					meta.getTableName(col));
		}
		conn.close();
	}

}
