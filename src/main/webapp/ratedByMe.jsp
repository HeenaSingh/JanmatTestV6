<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>Rated By Me</title>
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
    
  /* Set gray background color and 100% height */
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

  /*  totalreviews and writeareview buttons*/
  .btn btn-success{border:8px;}
  .btn btn-primary{border:8px;}

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

	<div class="container-fluid text-center">    
		<div class="row content">
	 	
	    	<div class="col-sm-1 margin">
	     	</div>
	
		    <div class="col-sm-2 sidenav">
				<div  class="thumbnail text-center" >
			  		<img id="profileImage" style="width:100% ;height:auto;" src="<%=request.getContextPath()%><c:out value="${loginuser.profileImage} " />"/> 
		        </div>
				<p><a href="#"> <c:out value="${loginuser.firstName} " /><c:out value=" ${loginuser.lastName}" /></a></p>
				<p><a href="RatedByMe?submit=ratedByMe">Polls  Rated :<span class="sidenavbadge"><c:out value="${loginuser.pollsRated} " /></span></a></p>
				<p><a href="CreatedByMe?submit=createdByMe">Polls Created :<span class="sidenavbadge"><c:out value="${loginuser.pollsCreated} " /></span></a></p>
			    <p><a href="MostReviewed?submit=mostReviewed">Most Reviewed</a></p>
			    <p><a href="HighlyRated?submit=highlyRated">Highly Rated</a></p>
		    </div>
	
		    <div class="col-sm-6 text-left" style="padding-top:20px;"> <!--* Main Content Here -->
		    <!--repeat for each row fetched by LoginController -->
		      <c:forEach items="${ratedByMe}" var="ratedByMe">
			      <p class = "datecreated">Created on : <c:out value="${ratedByMe.dateCreated}" /> , Created by : <a href="#"><c:out value="${ratedByMe.createrName}" /></a></p>
			      <p class = "pollname"><strong><c:out value ="${ratedByMe.pollname}" /></strong></strong> <span class="badge"><c:out value="${ratedByMe.avgRating}" /></span></p>
			      <p><c:out value="${ratedByMe.polldesc}" /></p>
			      <p><a href ="#"><c:out value="${ratedByMe.weblink}" /></a></p>
			      <a href="TotalReviews?submit=totalReviews&pollid=<c:out value="${ratedByMe.pollid}"/>"><button id ="totalreviews" class="btn btn-success" type="button">Total Reviews <span class="badge"><c:out value="${ratedByMe.totalRatings}" /></span></button></a>
			      <a href="TotalReviews?submit=totalReviews&pollid=<c:out value="${ratedByMe.pollid}"/>"><button id = "writereview" class="btn btn-primary" type="button">Write a Review</button></a>
			      <hr>
		      </c:forEach>
		    </div>
	
		    <div class="col-sm-2 sidenav">
		      <div class="rightsidenav">
		      </div>
		    </div>
	
		    <div class="col-sm-1 margin">
		    </div>
		
		</div>
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
