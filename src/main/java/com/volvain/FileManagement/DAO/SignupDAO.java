package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

public class SignupDAO extends Connect {

	public SignupDAO(String id,String Name,String department,String password) throws SQLException, ClassNotFoundException {
		String query="insert into validate values(?,?,?,?)";
		preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, id);
		preparedStatement.setString(2, Name);
		preparedStatement.setString(3, department);
		preparedStatement.setString(4, password);
		preparedStatement.executeUpdate();
	}

}
