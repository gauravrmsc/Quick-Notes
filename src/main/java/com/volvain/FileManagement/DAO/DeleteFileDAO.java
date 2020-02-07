package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

public class DeleteFileDAO extends Connect{
	public static final int ERROR_DELETING_FILE=0;
    public static final int FILE_DELETED=1;
	public DeleteFileDAO() throws SQLException,ClassNotFoundException{
	}
    public int delete(String parent,String fName)throws SQLException {
    	String query="delete from fileDesc where parent=? and name=?";
    	preparedStatement=connection.prepareStatement(query);
    	preparedStatement.setString(1, parent);
    	preparedStatement.setString(2, fName);
    	int n=preparedStatement.executeUpdate();
    	if(n==1)return FILE_DELETED;
    	return ERROR_DELETING_FILE;
    }
}
