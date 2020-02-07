var c1=["English Communication Skills","Engineering Math I","Engineering Physics","Engineering Mechanics",
     "Computer Fundamentals and Programming in C++","Engineering Drawing & Graphics","Environmental Science"];
 var c2=["Business Communication","Engineering Math II","Engineering Chemistry","Principles of Electrical Engg. ",
    "Fundamentals of Electronics Engg","Workshop Technology","Disaster Mangement"];
 var c3=["Probability and Statistics","Industrial Economics and Management","Data Structures",
       "Object Oriented Programming using C++","Digital Electronics","Computer Architecture & Organization",
       "Sociology And Elements Of Indian History For Engineers"];   
 var c4=["Optimization and Calculus of Variations","Human Values and Professional Ethics","Database Management System",
        "Operating System","Theory of Computation","Microprocessor & Peripherals"]; 
 var c5=["Computer Networks","Core Java","Computer Graphics","Artificial Intelligence & Expert System",
        "Software Engineering","Analysis and Design of Algorithm",""];
 var c6=["Advance Java","Distributed Operating System","Compiler Design","Linux Administration",
    "Data Mining & Data Warehousing","Modeling & Simulation","Multimedia Technology"];
 var e1=["English Communication Skills","Engineering Math I","Engineering Physics","Engineering Mechanics",
     "Computer Fundamentals and Programming in C++","Engineering Drawing & Graphics","Environmental Science"];
 var e2=["Business Communication","Engineering Math II","Engineering Chemistry","Principles of Electrical Engg. ",
    "Fundamentals of Electronics Engg","Workshop Technology","Disaster Mangement"];
 var c7=["Advance Computer Architecture","Wireless & Mobile Computing","Information Security","Cloud Computing",""];
 var e3=["Probability and Statistics","Industrial Economics and Management","Analog Electronics","Digital Electronics","Network Analysis & Synthesis","Signals & Systems",""]
 var e4=["Optimization and Calculus of Variations","Humans Values & Professional ethics","Analog Communication","Microprocessors & Peripherals","Linear Integrated Circuits","Pulse Shaping & Wave Generation",""]
 var e5=["Digital Communication","Electromagnetic Field Theory","Electronic logic circuit design","Electronic Measurements & Measuring Instruments","Power Electronics","Introduction to Microcontrollers for Embedded Systems"]
 var e6=["Advanced Microcontrollers for Embedded systems","Antenna & Wave Propagation","Control Systems","Digital Signal Processing","Microelectronics Technology","Wireless & Mobile Communication",""]
 var e7=["Computer Networks & Data Communication","Microwave & Radar Engineering","Optical Communication","VLSI Design",""]
 
   function sel_sub(){
        var x = document.getElementById("semester").selectedIndex;
        var y = document.getElementById("branch").selectedIndex;
        var arg;
        switch(x){
            case 1:if(y==1)arg=c1;
                   else 
                   if(y==2)arg=e1;
            break;
            case 2:if(y==1)arg=c2;
                   else 
                   if(y==2)arg=e2;
            break;
            case 3:
                   if(y==1)arg=c3;
                   else 
                   if(y==2)arg=e3;
            break;
            case 4:if(y==1)arg=c4;
                   else 
                   if(y==2)arg=e4;
            break;
            case 5:if(y==1)arg=c5;
                   else 
                   if(y==2)arg=e5;
            break;
            case 6:if(y==1)arg=c6;
                   else 
                   if(y==2)arg=e6;
            break;
            case 7:if(y==1)arg=c7;
                   else 
                   if(y==2)arg=e7;
        } 
        var select = document.getElementById("sub");
        select.innerHTML="<option value="+"semester"+">Subject</option>";
        
           for(var agmt in arg){
        var opt=new Option(arg[agmt],arg[agmt]);
     select.options.add(opt);}
       }
function srch(){
var url="./quePapers/";
var urlptrn=["sub","year","mst_end"];

for(var i=0;i<3;i++){
    var x = document.getElementById(""+urlptrn[i]).selectedIndex; 
    var y= document.getElementById(""+urlptrn[i]).options;
url+=" "+y[x].text;

}
url+=".pdf";
var a=document.getElementById("semester").selectedIndex;
var b=document.getElementById("year").selectedIndex;
var c=document.getElementById("branch").selectedIndex;
var d=document.getElementById("sub").selectedIndex;
if(a==0||b==0||c==0||d==0){if(a==0)alert("Pleace Choose Your Semester");
if(b==0)alert("Choose Year");
if(c==0)alert("Choose Branch");
if(d==0)alert("Choose Subject");}
else{
if(fileExists(url)){
window.location.href=url;
}
else {
	
    alert("Question Paper Not Available");
}
}
document.getElementById("sub").selectedIndex=0;
}
var modal = document.getElementById('que');
function display(){
    modal.style.display = "flex";
    
}
function clse(){
    modal.style.display = "none";
}
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
  }
  function fileExists(url) {
    if(url){
        var req = new XMLHttpRequest();
        req.open('GET', url, false);
        req.send();
        return req.status==200;
    } else {
        return false;
    }
}