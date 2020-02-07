package com.volvain.FileManagement;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import com.volvain.FileManagement.DAO.CheckUserDAO;
@WebServlet("/checkuser")
public class CheckUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int NULL_ID=2;//for null values
    private static final int EMPTY_ID=3;//for empty string("")
    public static final int UNKNOWN_ERROR=4;
    public CheckUser() {
        super();
        
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		PrintWriter wr=response.getWriter();
		if(id==null) {
			wr.println(NULL_ID);
			return;
		}
		else if(id.equals("")) {
			wr.println(EMPTY_ID);
			return;
		}
		else {
			try {
				CheckUserDAO obj=new CheckUserDAO();
				int n=obj.check(id);
				if(n==obj.ID_AVAILABLE)wr.println(obj.ID_AVAILABLE);
				else if(n==obj.ID_NOT_AVAILABLE)wr.println(obj.ID_NOT_AVAILABLE);
				else wr.println(UNKNOWN_ERROR);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
