package com.volvain.FileManagement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;

@WebServlet("/deleteFile")
public class DeleteFile extends HttpServlet {
ServletOutputStream out;
public static final int ERROR=0;
public static final int SUCCESS=1;
public static final int FILE_DOES_NOT_EXISTS=2;

	@Override
	public void service(HttpServletRequest req,HttpServletResponse res) {
		String relPath=req.getParameter("path");
		File f=new File(Base.BASE+relPath);	
		
		try {
			out=res.getOutputStream();
			out.print(delete(f));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	private int delete(File f) {
		if(f.exists()) {
			if(f.isDirectory()) {
				String[] fList=f.list();
				for(String fName:fList) {
					File childFile=new File(f.getAbsolutePath()+"/"+fName);
					delete(childFile);
				}
			}
				
			boolean b=f.delete();
			System.out.println(b);
			if(b)return SUCCESS;
			else return ERROR;
		}
		else if(!f.exists()) return FILE_DOES_NOT_EXISTS;
		else return ERROR;
	}
	
}
