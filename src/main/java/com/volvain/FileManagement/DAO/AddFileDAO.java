package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

import com.volvain.FileManagement.CheckUser;

public class AddFileDAO extends Connect{

	public AddFileDAO() throws SQLException, ClassNotFoundException {
	}
	public int add(String parent,String name,String createdBy) throws SQLException {
		String query="insert into fileDesc values(?,?,?)";
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, parent);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3,createdBy);
		int n=preparedStatement.executeUpdate();
		
		return n;
	}

}
