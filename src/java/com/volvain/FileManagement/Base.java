package com.volvain.FileManagement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Base {
//public static final String BASE="E:/jee-2019-06/eclipse-workspace/FileManagement/src/main/webapp/Home";
	public static final String BASE="C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/FileManagement/Home";
	public Base() {
		// TODO Auto-generated constructor stub
	}
public String  getBase() {	
/*	int i;
	String Base = "";
	try {
		//File f=new File("base.txt");
		//System.out.println("aaa"+f.getAbsolutePath());
		System.out.println(getClass().getResource("base.txt"));
		FileReader r=new FileReader("");
		while(( i=r.read())!=-1) {
			Base+=(char)i;
		}
		System.out.println(Base);
	} catch (FileNotFoundException e) {
	
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}*/
	return BASE;
}

}
