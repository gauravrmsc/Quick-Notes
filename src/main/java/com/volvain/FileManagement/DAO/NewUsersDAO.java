package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

public class NewUsersDAO extends Connect {

	public NewUsersDAO() throws SQLException, ClassNotFoundException {
		String query="select id,name,department from validate";
		statement=connection.createStatement();
		resultSet=statement.executeQuery(query);
		}

}
