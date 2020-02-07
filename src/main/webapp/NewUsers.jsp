<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.volvain.FileManagement.DAO.NewUsersDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>Insert title here</title>
 <link rel="stylesheet" href="Styles/bootstrap.min.css">
 <style type="text/css">
 th{
 padding:5px 15px;
 }
 td{
 padding-left:15px;
 }
 </style>
</head>
<body>
<table style="width:100%; margin-top:10px;" class="table-bordered">
<%
HttpSession s=request.getSession();
String id=(String)s.getAttribute("id");
String password=(String)s.getAttribute("password");
if(id==null||!id.equals("admin")||!password.equals("cse@2017")){
	out.print("You Are Not Authorised to Access this Panel");
	return;
}
%>
<tr >
<th >ID</th>
<th >Name</th>
<th >Depratment</th>
<th >Activate/Remove</th>
</tr>
<%

NewUsersDAO obj=new NewUsersDAO();
while(obj.resultSet.next()){
	out.print("<tr>");
	 id=obj.resultSet.getString(1);
	out.print("<td >"+id+"</td>");
	out.print("<td >"+obj.resultSet.getString(2)+"</td>");
	out.print("<td >"+obj.resultSet.getString(3)+"</td>");
	out.print("<td ><button class=\"btn btn-link activate-btn\" name="+id+">Activate</button>&nbsp&nbsp<button class=\"btn btn-link remove-btn\"name="+id+">Remove</button></td>");
	//+obj.resultSet.getString(2)+" "+obj.resultSet.getString(3));
	//style=\"background-color:#337ab7;color:#ffffff\"
	//style=\"color:#ffffff;background-color:#ff0000\"
	out.println("</tr>");
}
%>
</table>
</body>
<script type="text/javascript">
const activateList=document.getElementsByClassName("activate-btn");
const removeList=document.getElementsByClassName("remove-btn");
for(var i in activateList){
	activateList[i].onclick=activate;
}
for(var i in removeList){
	removeList[i].onclick=remove;
}
function activate(){
	const id=this.getAttribute("name");
	xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4&&this.status==200){
			var x=parseInt(this.responseText);
			switch(x){
				case 0:alert("Error");
				break;
				case 1:alert("Account Activated");
						window.location=window.location;
				break;
				case 3:alert("Could not send id to server");
			}

		}
	}
	xhttp.open("GET","ActivateUser?id="+id,true);
	xhttp.send();
}
function remove(){
	const id=this.getAttribute("name");
	xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4&&this.status==200){
			var x=parseInt(this.responseText);
			switch(x){
				case 0:alert("Error");
				break;
				case 1:alert("User Removed");
						window.location=window.location;
				break;
				case 3:alert("Could not send id to server");
			}

		}
	}
	xhttp.open("GET","RemNewUsr?id="+id,true);
	xhttp.send();
	
}
</script>
</html>