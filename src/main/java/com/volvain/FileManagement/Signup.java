package com.volvain.FileManagement;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.RequestContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.volvain.FileManagement.DAO.CheckUserDAO;
import com.volvain.FileManagement.DAO.SignupDAO;

@WebServlet("/signup")
public class Signup extends HttpServlet{
public static final int SIGNUP_SUCCESSFUL=1;
public static final int SIGNUP_FAILURE=0;
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException {
		PrintWriter wr=res.getWriter();
		Map hm=req.getParameterMap();
		Collection lst=hm.values();
		for(Object s:lst) {
			String m[]=(String[]) s;
			System.out.println(m[0]);
			if(m[0].equals("")) {
				wr.println(SIGNUP_FAILURE);
				return;
			}
		}
	
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String department=req.getParameter("dpt");
		String password=req.getParameter("password");
		if(id==null||name==null||department==null||password==null) {
			wr.println(SIGNUP_FAILURE);
			return;
		}
		try {
			CheckUserDAO obj=new CheckUserDAO();
			if(obj.check(id)!=1) {
				wr.print(SIGNUP_FAILURE);
			}
			SignupDAO obj1 =new SignupDAO(id,name,department,password);
			wr.println(SIGNUP_SUCCESSFUL);
		} catch (ClassNotFoundException e) {
			wr.println("Could Not Load Driver");
			e.printStackTrace();
		} catch (SQLException e) {
			wr.println("Error Accessiong Database");
			wr.println(e);		}
	}

}
