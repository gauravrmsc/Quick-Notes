
package com.volvain.FileManagement;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.volvain.FileManagement.DAO.AddFileDAO;
import com.volvain.FileManagement.DAO.CheckCreaterDAO;
@WebServlet("/upload")

public class fileUpload extends HttpServlet
{

    public fileUpload()
    {
        relPath = "";
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        ServletOutputStream out = response.getOutputStream();
        String userPassword = (String)request.getSession().getAttribute("password");
        String id=(String)request.getSession().getAttribute("id");
        
        try {
			if(loginDetails.authenticate(id,userPassword)!=1)
			{
			    out.print("Login First");
			    return;
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        try
        {
            List files = sf.parseRequest(request);
            for(Iterator iterator = files.iterator(); iterator.hasNext();)
            {
                FileItem item = (FileItem)iterator.next();
                if(item.isFormField() && item.getFieldName().equals("relPath"))
                    relPath = item.getString();
                else
                if(!item.isFormField())
                {
                   // File newFile = new File((new StringBuilder("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/FileManagement/Home")).append(relPath).append("/").append(item.getName()).toString());
                	File newFile = new File(Base.BASE+relPath+"/"+item.getName());
                	if(newFile.exists())
                    {
                        out.print(0);
                    } else
                    {   String parentFileName=newFile.getParentFile().getName();
                        File parent=newFile.getParentFile(); 
                    	String createdBy=new CheckCreaterDAO().find(parent.getParentFile().getName(),parentFileName);
                    	
                    	if(!createdBy.equals(id)&&!createdBy.equals("")&!id.equals("admin")) {
                    		out.print( DeleteFile.NOT_SUFFICIENT_PERMISSION);
                    		return;
                    	}
                        
                        if(new AddFileDAO().add(parentFileName, item.getName(), id)==1) {
                        item.write(newFile);
                        out.print(1);}
                        else out.print(0);
                    }
                }
            }

        }
        catch(FileUploadException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    String relPath;
}
