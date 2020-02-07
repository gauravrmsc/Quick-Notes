package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

public class CheckCreaterDAO extends Connect {

	public CheckCreaterDAO() throws SQLException,ClassNotFoundException{
		// TODO Auto-generated constructor stub
	}
    public String find(String parent,String fName)throws SQLException {
    	String query="select createdBy from fileDesc where parent=? and name=?";
    	preparedStatement=connection.prepareStatement(query);
    	preparedStatement.setString(1, parent);
    	preparedStatement.setString(2, fName);
    	resultSet=preparedStatement.executeQuery();
    	if(resultSet.next())return resultSet.getString(1);
    	return "";
    }
}
