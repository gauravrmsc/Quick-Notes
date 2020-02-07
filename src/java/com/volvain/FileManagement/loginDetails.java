package com.volvain.FileManagement;

public class loginDetails {
private static final String password="cse@2017" ;
public static final String LOGIN_FIRST="Login First";
	public loginDetails() {
		// TODO Auto-generated constructor stub
	}

	public static boolean authenticate(String userPassword) {
		if(userPassword==null)return false;
		if(userPassword.equals(password))return true;
		else return false;
	}
}
