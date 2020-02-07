package com.volvain.FileManagement;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;;
@WebServlet("/mkdir")
public class CreateDirectory extends HttpServlet {
ServletOutputStream out;
public static final int FILE_ALREADY_EXISTS=0;
public static final int FILE_CREATED=1;
public static final int NO_NAME=2;
@Override
public void service(HttpServletRequest req,HttpServletResponse res) {
	
	try {
		out = res.getOutputStream();
	} catch (IOException e) {
		e.printStackTrace();
	}
	String userPassword=(String) req.getSession().getAttribute("password");
	if(!loginDetails.authenticate(userPassword)) {
		try {
			out.print(loginDetails.LOGIN_FIRST);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	String relPath=req.getParameter("relPath");
	String fName=req.getParameter("fName");
	if(fName==null) {
		try {
			out.print(NO_NAME);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    File f=new File(Base.BASE+relPath+"/"+fName);
    if(f.exists()) {
    	try {
			out.print(FILE_ALREADY_EXISTS);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    else {
    	f.mkdir();
    	try {
			out.print(FILE_CREATED);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
}
