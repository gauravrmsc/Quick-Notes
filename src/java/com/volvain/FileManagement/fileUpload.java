package com.volvain.FileManagement;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



/**
 * Servlet implementation class fileUpload
 */
@WebServlet("/upload")
public class fileUpload extends HttpServlet {
	String relPath="";
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream out=response.getOutputStream();
		String userPassword=(String) request.getSession().getAttribute("password");
		if(!loginDetails.authenticate(userPassword)) {
			out.print(loginDetails.LOGIN_FIRST);
			return;
		}
		ServletFileUpload sf=new ServletFileUpload(new DiskFileItemFactory());
		try {
			List<FileItem> files=sf.parseRequest(request);
			for (FileItem item:files) {
				if(item.isFormField()&&item.getFieldName().equals("relPath")) {
					relPath=item.getString();
				}
				else if(!item.isFormField()) {File newFile=new File(Base.BASE+relPath+"/"+item.getName());
				if(newFile.exists()) {
					out.print(CreateDirectory.FILE_ALREADY_EXISTS);
				}
				else {
				item.write(newFile);
				out.print(CreateDirectory.FILE_CREATED);
			}
				}}
		} catch (FileUploadException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}