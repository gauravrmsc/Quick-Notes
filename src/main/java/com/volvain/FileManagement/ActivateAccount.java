package com.volvain.FileManagement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.volvain.FileManagement.DAO.AddTeacherDAO;
import com.volvain.FileManagement.DAO.RemNewUsrDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ActivateUser")
public class ActivateAccount extends HttpServlet {
private static final int EMPTY_FIELD=3; 
	public ActivateAccount() {
	}
	@Override
public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		PrintWriter wr=res.getWriter();
		String id=req.getParameter("id");
		if(id==null||id.equals("")) {
			wr.print(EMPTY_FIELD);
			return;
		}
		try {
			int n=new AddTeacherDAO().add(id);
			wr.println(n);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
