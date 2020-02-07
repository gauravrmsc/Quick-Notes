package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

public class RenameFileDAO extends Connect {

	public static final int FILE_RENAIMED = 1;
	public static final int ERROR_RENAIMING_FILE =0;
	public RenameFileDAO() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
	}
public int rename(String parent,String fName,String newName)throws SQLException {
	String query="update  fileDesc set name=? where parent=? and name=?";
	preparedStatement=connection.prepareStatement(query);
	preparedStatement.setString(1, newName);
	preparedStatement.setString(2, parent);
	preparedStatement.setString(3, fName);
	int n=preparedStatement.executeUpdate();
	if(n==1)return FILE_RENAIMED;
	return ERROR_RENAIMING_FILE;
}
}
