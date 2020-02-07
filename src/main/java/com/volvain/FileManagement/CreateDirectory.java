
package com.volvain.FileManagement;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.volvain.FileManagement.DAO.AddFileDAO;
import com.volvain.FileManagement.DAO.CheckCreaterDAO;

@WebServlet("/mkdir")
public class CreateDirectory extends HttpServlet
{

    public CreateDirectory()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {   
            out = res.getOutputStream();   
        String userPassword = (String)req.getSession().getAttribute("password");
        String id=(String)req.getSession().getAttribute("id");
        try {
			if(loginDetails.authenticate(id,userPassword)!=1)
			{
			        out.print("Login First");   
			    return;
			}
		
        String relPath = req.getParameter("relPath");
        String fName = req.getParameter("fName");
        if(fName == null) {
            
                out.print(NO_NAME);
                return;
            }
            
            
        //File f = new File((new StringBuilder("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/FileManagement/Home")).append(relPath).append("/").append(fName).toString());
        File f=new File(Base.BASE+relPath+"/"+fName);
        if(f.exists())
        {   out.print(FILE_ALREADY_EXISTS);
            
        } else
        {
        	 String parentFileName=f.getParentFile().getName();
         	//String createdBy=new CheckCreaterDAO().find(parentFileName, f.getName());
        	 File parent=f.getParentFile(); 
         	String createdBy=new CheckCreaterDAO().find(parent.getParentFile().getName(),parentFileName);
         	if(!createdBy.equals(id)&&!createdBy.equals("")&&!id.equals("admin")) {
         		out.print( DeleteFile.NOT_SUFFICIENT_PERMISSION);
         		return;
         	}
             
             if(new AddFileDAO().add(parentFileName, f.getName(), id)==1) {
            	 f.mkdir();
             out.print(FILE_CREATED);
             return;
             }
           
             //f.mkdir();
            out.print(4);//UnknownError
            
        }
        } catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    ServletOutputStream out;
    public static final int FILE_ALREADY_EXISTS = 0;
    public static final int FILE_CREATED = 1;
    public static final int NO_NAME = 2;
}
