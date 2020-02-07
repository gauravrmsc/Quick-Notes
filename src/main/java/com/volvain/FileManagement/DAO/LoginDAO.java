package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

public class LoginDAO extends Connect {
	public static final int INVALID_USER_NAME=2;
	public static final int INVALID_PASSWORD=3;
	public static final int PASSWORD_MATCHED=1;
	public LoginDAO() throws SQLException, ClassNotFoundException {
	}
	public int login(String id,String password) throws SQLException {
		String query="select password from teachers where id='"+id+"'";
		statement=connection.createStatement();
		resultSet=statement.executeQuery(query);
		if(resultSet.next()) {
			String pass=resultSet.getString(1);
			
			if(password.equals(pass)) 
			//	System.out.println(pass.equals(password));
				return PASSWORD_MATCHED;
			
			else return INVALID_PASSWORD;
			
		}
		
		return INVALID_USER_NAME;
	}
}
