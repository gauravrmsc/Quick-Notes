

package com.volvain.FileManagement;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.volvain.FileManagement.DAO.CheckCreaterDAO;
import com.volvain.FileManagement.DAO.DeleteFileDAO;

@WebServlet("/deleteFile")
public class DeleteFile extends HttpServlet
{

    public DeleteFile()
    {
    }

    public void service(HttpServletRequest req, HttpServletResponse res)
    {
        String relPath = req.getParameter("path");
        //File f = new File((new StringBuilder("C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/FileManagement/Home")).append(relPath).toString());
        File f=new File(Base.BASE+relPath);
        String userPassword = (String)req.getSession().getAttribute("password");
        String id=(String)req.getSession().getAttribute("id");
        try {
			if(loginDetails.authenticate(id,userPassword)!=1)
			{
			    try
			    {
			        out = res.getOutputStream();
			        out.print("Login First");
			    }
			    catch(IOException e)
			    {
			        e.printStackTrace();
			    }
			    return;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        try
        {
            out = res.getOutputStream();
            out.print(delete(f,id));
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private int delete(File f,String id) throws ClassNotFoundException, SQLException
    {
    	String fileName=f.getName();
    	String parent=f.getParentFile().getName();
    	String createdBy=new CheckCreaterDAO().find(parent, fileName);
    	if(!createdBy.equals(id))return NOT_SUFFICIENT_PERMISSION;
    	if(!(new DeleteFileDAO().delete(parent, fileName)==1))return ERROR;
        if(f.exists())
        {
            if(f.isDirectory())
            {
                String fList[] = f.list();
                String as[];
                int j = (as = fList).length;
                for(int i = 0; i < j; i++)
                {
                    String fName = as[i];
                    File childFile = new File((new StringBuilder(String.valueOf(f.getAbsolutePath()))).append("/").append(fName).toString());
                    delete(childFile,id);
                }

            }
            boolean b = f.delete();
            return !b ? 0 : 1;
        }
        return f.exists() ? 0 : 2;
    }

    ServletOutputStream out;
    public static final int NOT_SUFFICIENT_PERMISSION=3;
    public static final int ERROR = 0;
    public static final int SUCCESS = 1;
    public static final int FILE_DOES_NOT_EXISTS = 2;
}
