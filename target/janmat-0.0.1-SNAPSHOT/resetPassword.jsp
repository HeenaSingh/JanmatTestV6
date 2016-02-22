<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style>
  
.pagehead{
	float:none;
	color: #3498db;
	margin-left:0;
	margin-right:0;
	margin-top:2px;
	margin-bottom:30px;
	font: normal 14.5px/2 "Verdana",Geneva, sans-serif; 
	text-align:center;
}

.pagehead a{text-decoration: none; color: purple;}

.well{
	float:none;
	background-color: #e6e6e6;
	margin:0% 15%;
	text-align:center;
	
  
}

#logo{
	background-size: contain;
	background-repeat: no-repeat;
	background-position: center;
	background-image: url("http://i67.tinypic.com/py4xl.png") ;
	width:100%;
	height:125px;
	background-color: #e6e6e6; 
	border:#e6e6e6;
    
}

#emptyfirstrow{height:100px;}



#emailinputbox{

	display: inline-block;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	position: relative;
	margin: 5px;
	padding: 5px 5px;
	border: 5px solid rgba(255,255,255,0.54);
	-webkit-border-radius: 7px;
	border-radius: 7px;
	font: normal 14px/normal Verdana, Geneva, sans-serif;
	color: rgba(82,26,104,1);
	-o-text-overflow: clip;
	text-overflow: clip;
	background: rgba(255,255,255,1);
	-webkit-box-shadow: 0 0 9px 3px rgba(198,198,198,0.54) ;
	box-shadow: 0 0 9px 3px rgba(198,198,198,0.54) ;
	text-shadow: 1px 1px 0 rgba(180,180,180,0.54) ;
	-webkit-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
	-moz-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
	-o-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
	transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
}

#confirm {

	display: inline-block;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	position: relative;
	margin:15px;
	cursor: pointer;
	padding: 10px 10px;
	border: 1px solid #018dc4;
	-webkit-border-radius: 3px;
	border-radius: 5px;
	font: normal 14.5px/normal Verdana, Geneva, sans-serif;
	color: rgba(255,255,255,0.9);
	-o-text-overflow: clip;
	text-overflow: clip;
	background: #3498db;
	-webkit-box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) ;
	box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) ;
	text-shadow: -1px -1px 0 rgba(15,73,168,0.66) ;
	-webkit-transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
	-moz-transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
	-o-transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
	transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
}
.footer{
	height: 100px;
	background-color:white;
	text-align: center;
	font: normal 12px/normal Verdana, Geneva, sans-serif;
	padding: 15px;
	margin:15px;
}

.footerlist li{display:inline;  list-style-type:none; padding:10px; text-decoration: none;}

.footerlist a{text-decoration: none; color: rgba(132,131,131,1);}

.footerlist a:hover{text-decoration: none; color: purple;}

/* For mobile phones: */

@media screen and (max-width: 767px) {
      
.row.content {height:auto;} 

.pagehead{
	
	font: normal 14px/2 "Verdana",Geneva, sans-serif; 
	
}

#emptyfirstrow{height:20px;}   

#emailinputbox{width:100%;margin:0;}
   
.well{
	background-color:#e6e6e6;
	margin:0;
}

.footer{
	height: 100px;
	background-color:white;
}

#logo{width:auto;}

}
  
  </style>
</head>
<body>



<div class="container-fluid">  
	<div class="row content">
		<div class="col-sm-12" id="emptyfirstrow">
		</div>
	</div>
	<div class="row content">
		<div class="col-sm-2" >
		</div>
		<div class="col-sm-8 text-center">
			<div class="well">
				
					<div class="panel-heading">
						<div  class="thumbnail text-center" id ="logo" >
						</div>
					</div>
					<div class="panel-body">
						 <form class="forgotform" method="POST" action="SetNewPassword" name="SetNewPassword">
			   <p class="pagehead"> Enter your new password below </p>
			   <p><input type="hidden" name="uKey" value=${param["uniqueKey"]} /></p>
			    <p><input id="emailinputbox" type="password" required name="newPassword" value=""  placeholder="Enter new password" autocorrect="off" autocapitalize="words" /></p>
			    <input type="submit" id="confirm" class="button btn-success" name="submit" value="Confirm" />
			  </form>
					</div>
				
			</div>
		</div>
		<div class="col-sm-2" > 
		</div>
	</div>

</div> <!-- Container ends here -->

<div class= "footer">
	<p><ul class="footerlist">
	<li><a href="#">About us</a></li>
	<li><a href="#">Privacy</a></li> 
	<li><a href="#">Terms and Conditions</a></li>
	<li><a href="#">Sitemap</a></li>
	</ul></p> 
</div>



</body>
</html>
