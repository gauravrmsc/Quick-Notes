package com.volvain.FileManagement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.volvain.FileManagement.DAO.RemNewUsrDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet("/RemNewUsr")
public class RemNewUsr extends HttpServlet {
private static final int EMPTY_FIELD=3;
private static final int SUCCESS=1;
private static final int ERROR=0;

	public RemNewUsr() {
		// TODO Auto-generated constructor stub
	}
public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
	PrintWriter wr=res.getWriter();
	String id=req.getParameter("id");
	if(id==null||id.equals(""))wr.println(EMPTY_FIELD);
	else {
		try {
			String result[]=new RemNewUsrDAO().remove(id);
			if(result==null)wr.print(ERROR);
			else wr.println(SUCCESS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
