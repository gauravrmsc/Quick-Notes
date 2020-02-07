package com.volvain.FileManagement.DAO;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
public class Connect {
private DriverManager driverManager;
protected Statement statement;
protected PreparedStatement preparedStatement;
public ResultSet resultSet;
public static Connection connection;


	public Connect()throws SQLException,ClassNotFoundException {
		if(connection==null) {
		Class.forName("com.mysql.jdbc.Driver");
		connection=driverManager.getConnection("jdbc:mysql://localhost:3306/QuickNotes","root","3687");
		
		}
	}
	public void close()throws SQLException {
		connection.close();
		connection=null;
		
	}

}
