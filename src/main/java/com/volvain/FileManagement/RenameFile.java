
package com.volvain.FileManagement;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.volvain.FileManagement.DAO.CheckCreaterDAO;
import com.volvain.FileManagement.DAO.RenameFileDAO;

@WebServlet("/renameFile")

public class RenameFile extends HttpServlet
{

    public RenameFile()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException
    {
        String userPassword = (String)req.getSession().getAttribute("password");
        String id=(String)req.getSession().getAttribute("id");
        try {
			if((loginDetails.authenticate(id,userPassword))!=1)
			{
			    
			        out = res.getOutputStream();
			        out.print("Login First");
			    
			    return;
			}
		
        String relPath = req.getParameter("path");
        int index = relPath.lastIndexOf('/');
        out = res.getOutputStream();
       // File f = new File((new StringBuilder("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/FileManagement/Home")).append(relPath).toString());
       File f=new File(Base.BASE+relPath);
        String newName = req.getParameter("newName");
        String fileName=f.getName();
    	String parent=f.getParentFile().getName();
    	String createdBy=new CheckCreaterDAO().find(parent, fileName);
    	if(!createdBy.equals(id)) {
    		out.print( DeleteFile.NOT_SUFFICIENT_PERMISSION);
    		return;
    	}
    	if(!(new RenameFileDAO().rename(parent, fileName,newName+getExtension(relPath))==1)) {
    		out.print( ERROR);
    		return;
    	}
         
            out.print(rename(f, index, newName, relPath));
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println(FILE_Already_EXISTS);
		}
    }

    private int rename(File f, int index, String newName, String relPath)
    {
        if(f.exists())
        {
           /* int extIndex = relPath.lastIndexOf('.');
            String extension = "";
            if(extIndex != -1)
                extension = relPath.substring(extIndex);*/
           // File f1 = new File((new StringBuilder("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/FileManagement/Home")).append(relPath.substring(0, index + 1)).append(newName).append(extension).toString());
            File f1 = new File((new StringBuilder(Base.BASE)).append(relPath.substring(0, index + 1)).append(newName).append(getExtension(relPath)).toString());
            if(f1.exists())
                return FILE_Already_EXISTS ;
            boolean b = f.renameTo(f1);
            return !b ? ERROR :SUCCESS;
        }
        return f.exists() ?ERROR : FILE_DOES_NOT_EXISTS;
    }
String getExtension(String path) {
	 int extIndex = path.lastIndexOf('.');
     String extension = "";
     if(extIndex != -1)
         extension = path.substring(extIndex);
     return extension;
}
    ServletOutputStream out;
    public static final int ERROR = 0;
    public static final int SUCCESS = 1;
    public static final int FILE_DOES_NOT_EXISTS = 2;
    public static final int FILE_Already_EXISTS = 5;
}
