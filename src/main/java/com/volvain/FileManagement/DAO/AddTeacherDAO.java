package com.volvain.FileManagement.DAO;

import java.sql.SQLException;

public class AddTeacherDAO extends Connect {
public static final int ERROR=0;
public static final int SUCCESS=1;
	public AddTeacherDAO() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
	}

	public int add(String id) throws ClassNotFoundException, SQLException {
	String usrInfo[]=	new RemNewUsrDAO().remove(id);
	if(usrInfo==null)return ERROR;
	String query="insert into teachers values(?,?,?,?)";
	preparedStatement=connection.prepareStatement(query);
	preparedStatement.setString(1, usrInfo[0]);
	preparedStatement.setString(2, usrInfo[1]);
	preparedStatement.setString(3, usrInfo[2]);
	preparedStatement.setString(4, usrInfo[3]);
	int n=preparedStatement.executeUpdate();
	if(n==1)return SUCCESS;
	else return ERROR;
	}
}
