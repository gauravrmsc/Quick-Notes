package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

import com.volvain.FileManagement.DAO.Connect;;

public class RemNewUsrDAO extends Connect {

	public RemNewUsrDAO() throws SQLException, ClassNotFoundException {
		super();
		
	}
public String[] remove(String id) throws SQLException {
	String query="select * from validate where id='"+id+"'";
	statement=connection.createStatement();
	resultSet=statement.executeQuery(query);
	if(resultSet.next()) {
		String result[]= {resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)};
	query="delete from validate where id='"+id+"'";
	statement=connection.createStatement();
	int n=statement.executeUpdate(query);
	if(n==1) 
		return result;
	}

return null;
}
}
