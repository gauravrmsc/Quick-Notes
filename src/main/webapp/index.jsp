<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.volvain.FileManagement.Base,com.volvain.FileManagement.loginDetails,java.io.IOException,java.io.File" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quick Notes</title>
<link id="csslink" rel="stylesheet" media="screen" >
<link rel="manifest" href="manifest.json">
 <link rel="icon" href="icons/logo/512_icon.png">
<meta name="description" content="College File Management App">
<!--  <meta name="viewport" content="width=device-width, initial-scale=1.0">-->
<meta name="theme-color" content="rgb(15,157,88)" />
 <style type="text/css">

        </style>
         <!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">-->
         <link rel="stylesheet" href="Styles/bootstrap.min.css">
         
</head>
<body>

	<div id="body">
	<div id="menubar">
	<div class="menuItem" id="logoutButton" onClick="logout()">Logout</div>
	<div class="menuItem" id="loginButton"onClick="openLoginContainer()">Login</div>
	<div class="menuItem" id="loginButton"onClick="openSignupContainer()">Signup</div>
	<div class="menuItem" onclick="openUploadContainer()" >Upload</div>
	<div class="menuItem" onclick="openNewFolderContainer() ">Create Folder</div>
	<div class="menuItem" onClick="openQue()">Previous Year Question Papers</div>
	<div class="menuItem" id="butInstall" hidden>Install App</div>
    </div>
    <br>
	<%!
	public void jspInit(){
		
	}
public String getExtension(String fName) {
	String ext="";
	int index=fName.lastIndexOf(".");
if(index!=-1) {
	try {
	ext=fName.substring(index+1);
	if(ext.equalsIgnoreCase("pdf"));
	else if(ext.equalsIgnoreCase("docx")||ext.equalsIgnoreCase("txt"))ext="doc";
	else if(ext.equalsIgnoreCase("png")||ext.equalsIgnoreCase("jpg")||ext.equalsIgnoreCase("jpeg")||ext.equalsIgnoreCase("gif")||ext.equalsIgnoreCase("bmp")||ext.equalsIgnoreCase("esp")||ext.equalsIgnoreCase("raw")||ext.equalsIgnoreCase("cr2")||ext.equalsIgnoreCase("nef")||ext.equalsIgnoreCase("orf")||ext.equalsIgnoreCase("sr2"))ext="img";
	else if(("mp4, m4a, m4v, f4v, f4a, m4b, m4r, f4b, mov,3gp, 3gp2, 3g2, 3gpp, 3gpp2,ogg, oga, ogv, ogx,wmv, wma, asf*,webm.flv,avi,mp3").contains(ext))ext="media";
	else ext="other";
	}
	catch(StringIndexOutOfBoundsException e) {
		ext="";
	}
}
return ext;
}
 %>

<%
x:{String relative=request.getParameter("relative");
if(relative!=null&&relative.length()>=8&&relative.substring(1,8).equals("Faculty")){
	String userPassword=(String) request.getSession().getAttribute("password");
	String id=(String)request.getSession().getAttribute("id");
	if(loginDetails.authenticate(id,userPassword)!=1) {
		try {
			out.write("<h2 id=relativePath>"+relative+"</h2>");
			
			//out=response.getOutputStream();
			out.print("<br><br>You are Not Allowed To View These Files<br><br> Please Login First To Access This Directory ");
		} catch (IOException e) {
			e.printStackTrace();
		}
		break x;
	}

}
Base base=new Base();
String bp=base.getBase();

if(relative==null)relative="";
out.write("<h2 id=relativePath>"+relative+"</h2>");
out.write("<div id=\"fileContainer\">");
	String path=bp+"/"+relative;
File f=new File(path);
if(f.isDirectory()){
	String name[]=f.list();
	if(name.length==0){out.write("Empty Directory");}
	File f1;
	for(String fName:name){
		if(relative=="") f1=new File(bp+"/"+fName);
		else f1=new File(bp+"/"+relative+"/"+fName);
	if(f1.isDirectory()){
	String s1="<div class=\"icons\" id=\""+relative+"/"+fName+"\"onclick=\"f(this)\"><div class=\"image\"><img src=\".\\icons\\folder.png\"alt=\"icons\"></div><div class=\"fName\">"+fName+"</div></div>";
	//System.out.println(s1);
	out.print(s1);}
	else {
		String s="<a href=\"/Home/"+relative+"/"+fName+"\" ><div class=\"icons\" id=\""+relative+"/"+fName+"\"><div class=\"image\"><img src=\".\\icons\\"+getExtension(fName)+".png\" alt=\"icon\"></div><div class=\"fName\">"+fName+"</div></div></a>";

		out.write(s);}
	}
}
}
%>

</div>
<div id="newFolderContainer" class="bgModel">
		<div class="bgFrame">
				<div class="customBtn" style="position: absolute; right: 5%;margin-top: 2%; " onclick="closeNewFolderContainer()" onmouseover="" >X</div>
			 <br><div style="width:80%;margin:auto">New Folder</div>
				<input type="text" class="form-control"  name="Name" placeholder="Untitled Folder"  style="margin-left: auto;
				margin-right: auto; width: 80%;" id="folderNameField">
				<button  class="form-control" style="width:30%;display:inline-block;left:27%;position:relative;
					color:#000000;background-color:#ffffff " onclick="closeNewFolderContainer()">Cancle</button>
					
					<button type="submit"  class="form-control" style="width:30%;display:inline-block;left:30%;position:relative;
					color:#ffffff;background-color:#337ab7 " onclick="mkdir()">Create</button><br><br>
					
		</div>
		</div>
		<div id="renameFileContainer" class="bgModel">
				<div class="bgFrame">
						<div class="customBtn" style="position: absolute; right: 5%;margin-top: 2%; " onclick="closeRenameFileContainer()" onmouseover="" >X</div>
					 <br><div style="width:80%;margin:auto">File Name</div>
						<input type="text" class="form-control"  name="Name" placeholder="Untitled Folder"  style="margin-left: auto;
						margin-right: auto; width: 80%;" id="fileNameField">
						<button  class="form-control" style="width:30%;display:inline-block;left:28%;position:relative;
							color:#000000;background-color:#ffffff " onclick="closeRenameFileContainer()">Cancle</button>
							<button type="submit"  class="form-control" style="width:30%;display:inline-block;left:30%;position:relative;
							color:#ffffff;background-color:#337ab7 " onclick="renameFile()">Rename</button><br><br>
							
							</div>
				
				</div>
		<div id="uploadContainer" class="bgModel">
				<div class="bgFrame">
						<div class="customBtn" style="position: absolute; right: 5%;margin-top: 2%; " onclick="closeUploadContainer()" onmouseover="" >X</div>
									<br><div style="width:80%;margin:auto">Select Files</div>	
										<input type="hidden" name="relPath" id="relPathForUpload">
								<input type="file"  class="form-control" style="width: 80%;" name="file_chooser"  id="fileChooser" multiple >
								<button  class="form-control" style="width:30%;display:inline-block;left:27%;position: relative;
								color:#000000;background-color:#ffffff ;" onclick="closeUploadContainer()">Cancle</button>
							<button  class="form-control" style="width:30%;display:inline-block;left:30%;position:relative;
							color:#ffffff;background-color:#337ab7 "; name="files" onclick="uploadFile()">Upload</button><br><br>
							
				</div>
						</div>
		<div id="loginContainer" class="bgModel">
				<div class="bgFrame" >
						<div class="customBtn" style="position: absolute; right: 5%;margin-top: 2%; " onclick="closeLoginContainer()" onmouseover="" >X</div>
						<br><div style="width:80%;margin:auto">Enter Username and Password<span style="color:red;"> (Not required for Students) </span></div>	
						<form id="loginForm"><input type="text" class="form-control"  name="id" placeholder="ID"  style="margin-left: auto;
						 margin-right: auto; width: 80%;" id="passwordField">
						 <input type="password" class="form-control"  name="password" placeholder="Password"  style="margin-left: auto;
						 margin-right: auto; width: 80%;" id="passwordField"></form>
						 
						 <button  class="form-control" style="width:30%;display:inline-block;left:27%;position:relative;
							color:#000000;background-color:#ffffff " onclick="closeLoginContainer()">Cancle</button>
							<button  class="form-control" style="width:30%;display:inline-block;left:30%;position:relative;
							color:#ffffff;background-color:#337ab7 "; name="files" onclick="login()">Login</button><br><br>
							
				</div>
		</div>
		<div id="deleteContainer" class="bgModel">
			<div class="bgFrame">
					<div class="customBtn" style="position: absolute; right: 5%;margin-top: 2%; " onclick="closeDeleteContainer()"  >X</div>
								<br><div style="width:80%;margin:auto;">Are You Sure You Want To Delete This File?</div>	
								<div style="width:80%;margin-left: auto;" id="fileToBeDeleted"></div>	
								<button  class="form-control" style="width:30%;display:inline-block;left:27%;position:relative;
						color:#000000;background-color:#ffffff " onclick="closeDeleteContainer()">No</button>
						<button  class="form-control" style="width:30%;display:inline-block;left:30%;position:relative;
						color:#ffffff;background-color:#FF0000 "; name="files" onclick="deleteFile()">Yes</button><br><br>
						
			</div>
	</div>
	<div id="signupContainer" class="bgModel" >
		<div class="bgFrame" style="text-align:center;position:relative;height:auto">
			<div class="customBtn" style="position:absolute;right:5%;margin-top:2%;" onclick="closeSignupContainer()">X</div>
			<br><h4>Create New Account</h4>
			<form id="signup-form">
			<input type="text" name="id" placeholder="ID" class="form-control" style="width:70%;margin:5px;margin-bottom: 0px;display:inline-block" class="form-control">
			<div  style="width:70%;text-align:left;display: inline-block;"><span style="color:#337ab7;cursor: pointer;" id="ChkUsrAval">Check User Name Availability</span>&nbsp;&nbsp;&nbsp;<span style="color: #FF0000;" id="UsrAvalRes"></span></div>
			<input type="text" name="name" placeholder="Name" style="width:70%;margin:5px;display:inline-block"class="form-control">
			<select name="dpt" form="signup-form" style="width:70%;margin:5px;display:inline-block" class="form-control" >
			<option disabled selected hidden> Deptartment</option>
			<option>CSE</option>
			<option>ECE</option>
			<option>EE</option>
			</select>
			<input type="password" name="password" placeholder="Password" style="width:70%;margin:5px;display:inline-block"class="form-control">
			<input type="password" name="confirmPassword" placeholder="Confirm Password" style="width:70%;margin:5px;display:inline-block" class="form-control">
			<input type="submit" name="submit" value="Signup" class="form-control"style="width:70%;margin:5px;color:#ffffff;background-color:#337ab7;display:inline-block">
			<br><input type="button" name="cancle" value="Cancle" class="form-control"style="width:70%;margin:5px;display:inline-block"><br><br>
			</form>
			</div>
		
	</div>
	<div class="bg-modal" id="que" style="display:none">
        <div class="model-content">
            <button id="close" class="btn btn-link" onclick=clse()>X</button><br><br>
                <select id="semester"  class="btn btn-secondary dropdown-toggle">
                        <option value="semester">Semester</option>
                        <option value="I">I</option>
                        <option value="I">II</option>
                        <option value="I">III</option>
                        <option value="I">IV</option>
                        <option value="I">V</option>
                        <option value="I">VI</option>
                        <option value="I">VII</option>
                        </select><br><br>
                        <select id="year" class="btn btn-secondary dropdown-toggle">
                        <option value="YEAR" class="btn btn-secondary dropdown-toggle">Year</option>
                        <option value="I">2018 & Before</option>
                        <option value="I">2019</option>
                        </select><br><br>
                        <select id="branch" class="btn btn-secondary dropdown-toggle">
                            <option value="ece">Branch</option>
                            <option value="cse">CSE</option>
                            <option value="ece">ECE</option>
                        </select><br><br>
                        <select id="sub" onfocus=sel_sub() class="btn btn-secondary dropdown-toggle">
                        <option value="sub">Subject</option>
                        </select><br><br>

                        <select id="mst_end" class="btn btn-secondary dropdown-toggle">
                            <option value="mst1">MST1</option>
                            <option value="mst2">MST2</option>
                            <option value="end_sem">End Sem Exam</option>
                        </select><br><br>
                        <input type="button"  class="btn btn-info" name="search" value="Search" id="search" onclick=srch()>
        </div>
        </div>
	<div id="loadingContainer" class="bgModel" ;">
			<div class="bgFrame" >
			<div id="loadingSign">Processing Request...</div>	
			
			<div id="loadedPercent"> </div>	
				
			</div>
	</div>
		<div id="delBox">
				<div style="display: inline-block;width: 100%;height: 100%;">
			<div class="delBtn" onclick="openDeleteContainer()">Delete</div>
			<div class="delBtn" onclick="openRenameFileContainer()">Rename</div>
			</div>
			</div>
	</div>

</body>
<script src="install.js"></script>
<script src="que.js"></script>
<script type="text/javascript">
var csslink=document.getElementById("csslink");
var deviceWidth;
var iconWidth;
if(screen.width>500)
	{
	csslink.href="Styles/widescreen.css";  
	deviceWidth=screen.width;iconWidth=deviceWidth/4;
	}
else {csslink.href="Styles/smallscreen.css" ;
	deviceWidth=window.innerWidth;iconWidth=deviceWidth;}
	if(document.cookie.search("JSESSIONID")!=-1)document.getElementById("loginButton").style.display="none";
	else document.getElementById("logoutButton").style.display="none";
var screenWidth=deviceWidth+"px";
var screenHeight=screen.height-200+"px";
var relativePath=document.getElementById("relativePath").innerText;
var folderNameField=document.getElementById("folderNameField");
var bdy=document.getElementById("body");
var newFolderContainer=document.getElementById("newFolderContainer");
var uploadContainer=document.getElementById("uploadContainer");
var uploadform=document.getElementById("fileUploadForm");
var delBox= document.getElementById("delBox");
var icons=document.getElementsByClassName("icons");
var loginContainer=document.getElementById("loginContainer");
var loadingContainer=document.getElementById("loadingContainer");
var signupContainer=document.getElementById("signupContainer");
const signupForm=document.getElementById("signup-form");
const ChkUsrAval=document.getElementById("ChkUsrAval");
var selectedFolder;
const UnknownError=4;
ChkUsrAval.onmouseover=function(){
	ChkUsrAval.style.textDecoration="Underline";
}
ChkUsrAval.onmouseout=function(){
	ChkUsrAval.style.textDecoration="";
}
ChkUsrAval.onclick=checkUserAvailability;
signupForm.addEventListener('submit',(event)=>{
	event.preventDefault();
	var data="";
	//var formData=new FormData();
	var dataElements=signupForm.children;
	if(dataElements[4].value!=dataElements[5].value){alert("Passwords in the fields do not match");return}//Match the passwords
	data+=dataElements[0].name+"="+dataElements[0].value+"&"
	for(var i=2;i<5;i++){
		if(dataElements[i].value==""){alert("Fill The Form Correctly");return;}
		//else formData.append(dataElements[i].name,dataElements[i].value);
		else data+=dataElements[i].name+"="+dataElements[i].value+"&";
		}
	
	if(checkUserAvailability()!=1){
		alert("User Id Already Taken\nPlease Choose Some Other Id");
		return;
	}
	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==1)openLoadingContainer();
		else if(this.readyState==4){closeLoadingContainer();closeSignupContainer();}
		if(this.readyState==4&&this.status==200){
			alert("Signup Successful\nNow you can Login into you account");
			openLoginContainer();
		}
		
	};
	
	xhttp.open("POST","signup?"+data,true);
	xhttp.send();	
});

for(var i =0;i<icons.length;i++){
		var icon=icons[i];
		//icon.style.width=(iconWidth)+"px";
		//icon.style.height="80px";
        icon.oncontextmenu=function(event){
        event.preventDefault();
		delBox.style.display="flex";
		if(screen.width>500)delBox.style.left=(event.clientX)+"px";
		else delBox.style.left="100px";
        delBox.style.top=(this.offsetTop+this.getBoundingClientRect().height/2)+"px";
        selectedFolder=this;
		
    }
}   
    document.onclick=function(event){
        if(delBox.style.display!="none")delBox.style.display="none";
    }
function closeNewFolderContainer(){
	newFolderContainer.style.display="none";
}
function closeSignupContainer(){
	signupContainer.style.display="none";
}
function closeRenameFileContainer(){
	document.getElementById("renameFileContainer").style.display="none";
}
function closeUploadContainer(){
	uploadContainer.style.display="none";
}
function closeDeleteContainer(){
	document.getElementById("deleteContainer").style.display="none";
	}
function closeLoginContainer(){
	loginContainer.style.display="none";
}
function closeLoadingContainer(){
	loadingContainer.style.display="none";
}
function openRenameFileContainer(){
	document.getElementById("renameFileContainer").style.display="flex";
}
function openNewFolderContainer(){
	newFolderContainer.style.display="flex";
}
function openUploadContainer(){
	uploadContainer.style.display="flex";
   document.getElementById("relPathForUpload").value=relativePath;
}
function openSignupContainer(){
	signupContainer.style.display="flex";
}
function openDeleteContainer(){
	document.getElementById("deleteContainer").style.display="flex";
	document.getElementById("fileToBeDeleted").innerText=selectedFolder.innerText;
}
function openLoginContainer(){
	loginContainer.style.display="flex";
}
function openLoadingContainer(){
	loadingContainer.style.display="flex";
	var x=document.getElementById("loadingSign");
	//x.style.color="rgba(51,51,51,0)";
	//glow(x);
}
function openQue(){
	document.getElementById("que").style.display="flex";
   document.getElementById("relPathForUpload").value=relativePath;
}
function f(x){
	window.location.assign("./?relative="+x.getAttribute("id"));
}
function getRelativePath(){
	return relativePath;
}
function mkdir(){
	var fName=folderNameField.value;
	if(fName==""){
		alert("Folder Name cannot be Empty");
		return;
		}
	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==1)openLoadingContainer();
		else if(this.readyState==4)closeLoadingContainer();
		if(this.readyState==4 && this.status==200){
			var result=this.responseText;
			if(result=="Login First")openLoginContainer();
			else if(result==1)window.location=window.location;
			else if(result==0)alert("Folder Already Exist");
			else if(result==3)alert("Permission Denied");
			else if(result==4)alert(UnknownError);
			else if(result==2)alert("Folder Name Empty");
		}
	};
	xhttp.open("POST","mkdir?relPath="+relativePath+"&fName="+fName,true);
	xhttp.send();
}
function uploadFile(){
var fileChooser=document.getElementById("fileChooser");
var files=fileChooser.files;
var formData=new FormData();
formData.append("relPath",relativePath);
if(files.length==0){alert("No File is Selected");return;}
for(var i in files){
	if(typeof(files[i])=="object"){
formData.append("myFile",files[i],files[i].name);
	}
}	
	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==1)openLoadingContainer();
		else if(this.readyState==4)closeLoadingContainer();
		if(this.readyState==4&&this.status==200){
			var response=this.responseText;
			
			if(response=="Login First"){
				alert(response);
				openLoginContainer();
			}
			else if(!(response.search("3")==-1))alert("Permission Denied");
			else if(response.search("0")==-1){
				window.location=window.location;
				}
				else if(!(response.search("0")==-1)){
					var result=response.split("");
					for(var i in result){
						if(result[i]=="0")
					alert( files[i].name+" Already Exist");}
					alert(response);
					if(!(response.search("1")==-1))window.location=window.location;
				}
			    else alert("UnKnown Error\nTry Again !");
		}
	};
	xhttp.open("POST","upload",true);
	xhttp.upload.addEventListener('progress',evt =>{
	    const target=document.getElementById("loadedPercent");
		const percent=Math.round(evt.lengthComputable?(evt.loaded/evt.total)*100:0);
		//console.log(percent);
		target.innerText=percent+"%";
		
	});
		xhttp.onerror=function(){
			alert("Please Login First");
			openLoginContainer();
		}
		xhttp.send(formData);
	

}
function up(e){
	
		const percent=e.lengthComputable?(e.loaded/e.total)*100:0;
		console.log(percent);
	
}
function deleteFile(){
	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==1)openLoadingContainer();
		else if(this.readyState==4)closeLoadingContainer();
		if(this.readyState==4&&this.status==200){
			var response=this.responseText;
			if(response=="Login First")openLoginContainer();
			else if(response==1)window.location=window.location;
			else if(response==2) alert("File Does Not Exists");
			else if(response==3)alert("Permission Denied");
			else if(response==0)alert("Error Deleting File\nTry Again");
		}
	}
	xhttp.open("POST","deleteFile?path="+selectedFolder.id,true);
	xhttp.send();
}
function renameFile(){
	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==1)openLoadingContainer();
		else if(this.readyState==4)closeLoadingContainer();
		if(this.readyState==4&&this.status==200){
			var response=this.responseText;
			//alert(response);
			if(response=="Login First")openLoginContainer();
			
			else if(response==1)window.location=window.location;
			else if(response==2) alert("File Does Not Exists");
			else if(response==3) alert("Permission Denied");
			else if(response==5) alert("File Already Exists");
			else alert("Error Renaming File\nTry Again");
		}
	}
	xhttp.open("POST","renameFile?path="+selectedFolder.id+"&newName="+fileNameField.value,true);
	
	
	xhttp.send();
}

function login(){
	var loginForm=document.getElementById("loginForm");
	//var password=document.getElementById("passwordField").value;
	var data="";
	var dataElements=loginForm.children;
	for(var i=0;i<2;i++){
		data+=dataElements[i].name+"="+dataElements[i].value+"&";
		console.log(data);
	}
		var xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange=function(){
		if(this.readyState==1)openLoadingContainer();
		if(this.readyState==4){
			closeLoadingContainer();
			if(this.status==200){
			var response=parseInt(this.responseText);
			switch(response){
				case 0:alert("Fields Are Not Filled Correctly");
				break;
				case 1:loginContainer.style.display="none";
					window.location=window.location;
					alert("Login Successful");
				break;
				case 2:alert("Invalid Username");
				break;
				case 3:alert("Invalid Password");
				break;
				case 4:alert(UnknownError);
			}
			/*if(response==1){
				loginContainer.style.display="none";
				window.location=window.location;
				alert("login Successful");
				}
				else if(response==0)alert("Invalid Password");
				else if(response==2)alert("Error\n Try Again");*/

		}}
	}
	//xhttp.open("POST","login?password="+password,true);
	xhttp.open("POST","login?"+data,true);
	xhttp.send();
}
function logout(){
	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==1)openLoadingContainer();
		if(this.readyState==4){
			closeLoadingContainer();
			if(this.status==200&&this.responseText!=""){
				alert(this.responseText);
				window.location=window.location;
		}
			
			else  {
				alert("Error\n Try Again After Some Time");
			}
		}
		
	}
	xhttp.open("POST","logout",true);
	xhttp.send();
}
function fade(x){
	for(var i=9;i>0;i--){
		if(loadingContainer.style.display=="none")return;
   x.style.color="rgba(51,51,51,."+i+")";
	var now = new Date().getTime();
    while(new Date().getTime() < now + 100){
	}
	}
	glow(x);
}
function glow(x){
	for(var i=0;i<10;i++){
		if(loadingContainer.style.display=="none")return;
   x.style.color="rgba(51,51,51,."+i+")";
	var now = new Date().getTime();
    while(new Date().getTime() < now + 100){}
	}
	fade(x);
	}
function checkUserAvailability(){
	var id=signupForm.children[0].value;
	var response=-1;
	if(id==""){
		alert("Empty Field");
		return UnknownError;
	}
	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==1)openLoadingContainer();
		if(this.readyState==4)closeLoadingContainer();
		if(this.readyState==4&&this.status==200){
			response=parseInt(this.responseText);
			var UsrAvalRes=document.getElementById("UsrAvalRes");
			UsrAvalRes.style.color="#FF0000";
			switch(response){
				case 0:UsrAvalRes.innerText="Not Available";
				
				break;
				case 1:UsrAvalRes.style.color="rgb(15,157,88)";
						UsrAvalRes.innerText="Available";
				break;
				case 2:UsrAvalRes.innerText="Empty User Id Field";
				break;
				case 3:UsrAvalRes.innerText="Empty User Id Field";
				break;
				case 4:UsrAvalRes.innerText=UnknownError;

			}
			
		}
		
	}
	xhttp.open("GET","checkuser?id="+id,false);
	xhttp.send();
	return response;
	
}
bdy.style.minWidth=screenWidth;
bdy.style.minHeight=screenHeight;
var containers=document.getElementsByClassName("bgModel");
for(var i in containers){
var item=containers[i];
if(typeof(item)=="object"){
item.style.minWidth=screenWidth;
item.style.minHeight=screenHeight;	
}
}

if('serviceWorker' in navigator) {
	  navigator.serviceWorker
	           .register('./service-worker.js')
	           .then(function() {console.log("Service Worker Registered"); });
	}
</script>
</html>