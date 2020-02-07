package com.volvain.FileManagement;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
@WebServlet("/logout")
public class Logout extends HttpServlet {

	public Logout () {
		// TODO Auto-generated constructor stub
	}
	@Override
public void service(HttpServletRequest req,HttpServletResponse res) {
		HttpSession s=req.getSession();
		s.removeAttribute("password");
		Cookie cookie=new Cookie("JSESSIONID",s.getId());
		cookie.setMaxAge(0);
		res.addCookie(cookie);
		try {
			res.getOutputStream().print("Logout Successful");
		}catch(IOException e) {
			
		}
}
}
