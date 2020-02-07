package com.volvain.FileManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class login extends HttpServlet
{

    public login()
    {
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        String id=request.getParameter("id");
        String password = request.getParameter("password");
        int result=CheckUser.UNKNOWN_ERROR;
		try {
			result = loginDetails.authenticate(id,password);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        if(result==com.volvain.FileManagement.DAO.LoginDAO.PASSWORD_MATCHED)
        {
            HttpSession session = request.getSession();
            session.setAttribute("password", password);
            session.setAttribute("id", id);
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(365*24*60*60);
            response.addCookie(cookie);
        }
        /*else
        if(!loginDetails.authenticate(password))
            result = 0;*/
       
            response.getWriter().print(result);
       
          
       
    }

    /*private static final int WRONGPASSWORD = 0;
    private static final int SUCCESS = 1;
    private static final int ERROR = 2;*/
}
