package com.FinalProject.connection;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	public class DbCon {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		getConnection();
	}
	    public static Connection getConnection() throws ClassNotFoundException, SQLException{
	        String url = "jdbc:mysql://localhost:3306/e_mall";
	        String user = "root";
	        String password = "root";

	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	//Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection(url, user, password);
	            System.out.println("Connected to the database");
	            return connection;
	        } catch (SQLException e) {
	            System.err.println("Error connecting to the database: " + e.getMessage());
	            return null;
	         }
	    }
	}

