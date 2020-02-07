package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

public class CheckUserDAO extends Connect {
	public static final int ID_NOT_AVAILABLE=0;
	public static final int ID_AVAILABLE=1;
	public CheckUserDAO() throws SQLException, ClassNotFoundException {
		
	}

	public int check(String id) throws SQLException {
		System.out.println(id);
		String query="select * from teachers where id='"+id+"'";
		String query1="select * from validate where id='"+id+"'";
		statement=connection.createStatement();
		resultSet=statement.executeQuery(query);
		if(resultSet.next())return ID_NOT_AVAILABLE;
		resultSet=statement.executeQuery(query1);
		if(resultSet.next())return ID_NOT_AVAILABLE;
		return ID_AVAILABLE;
	}
}
