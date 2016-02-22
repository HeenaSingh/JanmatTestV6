<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
	    color:white;
	    margin: 0px;
	    border-radius: 0;
	    border:0px solid ;
	    background-color:white;
	}
    
    /*  navbar hambbuger menu  */
    .navbar-toggle{border:1px solid purple;}
    
    /*  navbar hambbuger menu horizontal bars */
    .icon-bar{background-color:purple;}
    
    /*  navbar links color */
    .navbar a{color:#808080;}

    /*  navbar's menu items color on selection and background  */ 
    #myNavbar a:hover, a:active {background-color:#cccccc; color:purple;}
     
    /* Logo image in the navigation bar  */
	#logo{
		background-size: contain;
		background-repeat: no-repeat;
		background-position: center;
		background-image: url("http://i67.tinypic.com/py4xl.png") ;
		width:80px;
	}

    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 540px}
    
   /* Set  background color and 100% height */
   .sidenav {
	   padding-top: 20px;
	   background-color: #ffffff;
	   height: 100%;
	   margin-bottom:20px;
   }
   
   /* Set footer styling */
   .footer {
		font: normal 12px/normal Verdana, Geneva, sans-serif;
		background-color: white;
		color: #ffffff;
		text-align: center;
		padding: 15px;
		margin:35px;
		vertical-align:base;
	}
	
	.footerlist li{display:inline;  list-style-type:none; padding:10px; text-decoration: none;}

	.footerlist a{text-decoration: none; color: rgba(132,131,131,1);}

	.footerlist a:hover{text-decoration: none; color: purple;}

    /*  badge color where avg rating is */
    .badge{background-color:purple; margin-left:5px; }
    
    /* sidenav badge */
    .sidenavbadge {;margin-left:5px; }
     
    /* date and time of each review */
    .datecreated {color:grey; }

    /* Rating buttons  */
    #ratingbutton{     
	    background-color: white ;
	    border: 2px solid purple; 
	    color:purple; 
	    margin:5px; 
	    padding:10px 15px;
    }
  
    /* Rating buttons  */
    #ratingbutton:hover{ background-color:purple;color:white;}    

    #ratingbutton:active{ background-color:purple;color:white;}  
    
    
    .profileform input[type=email], input[type=text]{

	display: inline-block;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	position: relative;
	margin: 10px;
	padding: 10px 5px;
	border: 5px solid rgba(255,255,255,0.54);
	-webkit-border-radius: 7px;
	border-radius: 7px;
	font: normal 14.5px/normal Verdana, Geneva, sans-serif;
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
    
    
    .saveinfo{
	  display: inline-block;
	  -webkit-box-sizing: content-box;
	  -moz-box-sizing: content-box;
	  box-sizing: content-box;
	  position: relative;
	  margin: 10px;
	  cursor: pointer;
	  padding: 10px 40px;
	  border: 1px solid rgba(46,204,113,1);
	  -webkit-border-radius: 5px;
	  border-radius: 5px;
	  font: normal 14.5px/normal Verdana, Geneva, sans-serif;
	  color: rgba(255,255,255,0.9);
	  -o-text-overflow: clip;
	  text-overflow: clip;
	  background: rgba(46,204,113,1);
	  -webkit-box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) ;
	  box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) ;
	  text-shadow: -1px -1px 0 rgba(165,165,165,0.6) , -1px -1px 0 rgba(165,165,165,0.6) ;
	  -webkit-transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
	  -moz-transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
	  -o-transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
	  transition: all 300ms cubic-bezier(0.42, 0, 0.58, 1);
}  
    
    /* The comment box */
    
    .enjoy-css {
	      word-wrap: break-word;
		  -webkit-box-sizing: content-box;
		  -moz-box-sizing: content-box;
		  box-sizing: content-box;
		  z-index: 2;
		  width: 80%;
		  
		  position: relative;
		  padding: 20px;
		  border: none;
		  -webkit-border-radius: 11px;
		  border-radius: 11px;
		  font: normal 14px/1"Abel", Helvetica, sans-serif;
		  color: rgba(255,255,255,1);
		  text-align: center;
		  -o-text-overflow: ellipsis;
		  text-overflow: ellipsis;
		  background: #0199d9;
		  text-shadow: 1px 1px 1px rgba(0,0,0,0.2) ;
	}

		.enjoy-css::before {
		  -webkit-box-sizing: content-box;
		  -moz-box-sizing: content-box;
		  box-sizing: content-box;
		  z-index: 1;
		  width: 28px;
		  height: 16px;
		  position: absolute;
		  content: "";
		  bottom: 0;
		  left: 0;
		  border: none;
		  font: normal 14px/normal "Times New Roman", Times, serif;
		  color: rgba(0,0,0,0.9);
		  -o-text-overflow: clip;
		  text-overflow: clip;
		  background: #0199d9;
		  text-shadow: none;
		  -webkit-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1) 10ms;
		  -moz-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1) 10ms;
		  -o-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1) 10ms;
		  transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1) 10ms;
		  -webkit-transform: rotateX(-7.448451336700702deg) rotateY(-40.680003454288446deg) rotateZ(-45.26366581533504deg)  translateX(-5px) skewX(-36deg);
		  transform: rotateX(-7.448451336700702deg) rotateY(-40.680003454288446deg) rotateZ(-45.26366581533504deg)  translateX(-5px) skewX(-36deg);
	}
    

    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
    	.sidenav {
        	height: auto;
        	padding: 15px;
      	}
      
        .row.content {height:auto;} 
    }

  </style>
</head>
<body>

<!-- Top purple line starts -->   

	 <div class="container-fluid">
	 <div class = "row">
	 	<div class = "col-sm-12" style="background-color:purple; height:2px;">
	 	</div>
	 </div>
	 </div>
 
<!-- Top purple line ends -->  

<!--Navigation  Bar  Menu starts -->

	<nav class="navbar">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"s></span>                        
				</button>
				<a class="navbar-brand" href="loginHome.jsp" id="logo"></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar" >
				<ul class="nav navbar-nav">
					<li class="active"><a href="loginHome.jsp">Home</a></li>
					<li><a href="createReview.jsp">Create One</a></li>
					<li><a href="myReviews?submit=myreviews">My Reviews</a></li>
					<li><a href="myProfile.jsp">My Profile</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="LogoutUser?action=logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

<!-- Navigation  Bar  Menu ends */ --> 

<!-- Thin line below the nav bar starts here  --> 

	<div class="container-fluid">
		<div class = "row">
		 	<div class = "col-sm-12" style="background-color:#cccccc; height:2px;">
		 	</div>
	 	 </div>
	</div>
	
<!-- Thin line below the nav bar ends here  --> 

<div class="container-fluid">  
 <div class="row">
	 <div class="col-sm-2" >
         </div>
	
 	 <div class="col-sm-2" style="padding-top:20px;" >
	 	<div class= "well">
	        </br>
	        <c:set var="imagePath" value="${loginuser.profileImage}"/>
			<div  class="thumbnail text-center" >
			    <img id="profileImage" style="width:100% ;height:auto;"  src="<c:url value="${loginuser.profileImage}"/>"/> 
			</div>
	    </div><!-- end div well ONE-->
	    <div style=" text-align:center">
			   
			 <form method="POST" action="UploadImage" enctype="multipart/form-data">

             <input type="file" name="profileimage" value="" >
             <input type="submit" value="Upload">
			</form>
		</div>
     </div>

	 <div class="col-sm-5 text-center" style="padding-top:20px;">
	   <div class= "well">
	   	
    <form class="profileform" action="ChangeUserDetails">
        First Name : <input
            type="text" name="firstName"
            value="<c:out value="${loginuser.firstName}" />" /> <br /> 
        Last Name : <input
            type="text" name="lastName"
            value="<c:out value="${loginuser.lastName}" />" /> <br /> 
        Your Email : <input type="email" name="email"
            value="<c:out value="${loginuser.email}" />" /> <br /> 
        
	<input type="submit" class="saveinfo" value="Save" />
    </form>

			
	   </div><!-- end div well TWO-->
         </div>
	   
         <div class="col-sm-3" > 
         </div>

 </div> <!-- first row ends here -->

  
 <div class="row content" ><!-- second row starts here -->
	 <div class="col-sm-1">
	 </div>
	
	 <div class="col-sm-10" style="background-color:white;text-align:center"> 
	 <!-- The repetition will start here  -->
	 	
		<!-- The repetition will end here -->
	</div> 
	
	<div class="col-sm-1" >
	</div>

 </div><!-- second row ends here -->
</div>

	<div class="footer">
	  <p><ul class="footerlist">
	   <li><a href="#">About us</a></li>
	   <li><a href="#">Privacy</a></li>
	   <li><a href="#">Terms and Conditions</a></li>
	   <li><a href="#">Sitemap</a></li>
	  </ul></p>
	</div>


</body>
</html>


</body>
</html>