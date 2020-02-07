package com.volvain.FileManagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {
	private static final int WRONGPASSWORD=0;
	private static final int SUCCESS=1;
	private static final int ERROR=2;
    public login() {
        super();
        
    }	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result=ERROR;
		String password=request.getParameter("password");
	    if(loginDetails.authenticate(password)) {
	    	result=SUCCESS;
	    	HttpSession session=request.getSession();
	    	session.setAttribute("password", password);
	    	Cookie cookie=new Cookie("JSESSIONID",session.getId());
	    	cookie.setMaxAge(365*24*60*60);
	    	response.addCookie(cookie);
	    }
	    else if(!loginDetails.authenticate(password))result=WRONGPASSWORD;
	  try {
		  response.getWriter().print(result);
	  }catch(IOException e) {
		  e.printStackTrace();
	  }
	}

}
