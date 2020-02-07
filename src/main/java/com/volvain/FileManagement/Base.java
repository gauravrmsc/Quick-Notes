package com.volvain.FileManagement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Base {
	public static final String BASE;
//public static final String BASE="E:/jee-2019-06/eclipse-workspace/FileManagement/src/main/webapp/Home";
	//public static final String BASE="C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/FileManagement/Home";
	static {
		Base obj=new Base();
		BASE=obj.getBase();
	}
	public Base() {
		// TODO Auto-generated constructor stub
	}
public String  getBase() {	
	
	String url=getClass().getResource("base.txt").toString();
	String path=url.substring(6);
	File f=new File(path);
	int i;
	String Base = "";
	try {
		FileReader r=new FileReader(f);
		while(( i=r.read())!=-1) {
			Base+=(char)i;
		}
	} catch (FileNotFoundException e) {
	
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return Base;
}

}
