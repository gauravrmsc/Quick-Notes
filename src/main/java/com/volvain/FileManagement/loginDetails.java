 

package com.volvain.FileManagement;
import java.sql.SQLException;
import com.volvain.FileManagement.DAO.LoginDAO;
public class loginDetails
{	
private static final int EMPTY_PARAMETERS=0;
private static final String password = "cse@2017";

    public loginDetails()
    {
    }

    public static int authenticate(String id,String userPassword) throws ClassNotFoundException, SQLException
    {
        if(userPassword == null||id==null||id.equals("")||userPassword.equals(""))
            return EMPTY_PARAMETERS;
        else if (id.equals("admin")) {
        	if(userPassword.equals(password))return LoginDAO.PASSWORD_MATCHED;
        	else return LoginDAO.INVALID_PASSWORD;
        }
        else {
        	int result=new LoginDAO().login(id, userPassword);
        	return result;
        }
        
    }

    
	
}
