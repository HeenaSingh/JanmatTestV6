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

    /* Rating option selection  */
    .ratingoption{     
	   
	    border: 2px solid purple; 
	    color:purple; 
	    margin:5px; 
	    padding:5px 5px;
    }
  
   
    /* The comment box */
    
    .reviewbox {
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

		.reviewbox::before {
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
				<span class="icon-bar"></span>                        
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
  <div class="row ">
	    <div class="col-sm-2" >
         </div>
	
	    <div class="col-sm-8 text-center" style="padding-top:20px;">
			<div class= "well">
		        </br>
			  	<p class= "datecreated">Created on : <c:out value="${thispoll.dateCreated}" /> , Created by : <a href="#"><c:out value="${thispoll.createrName}" /> </a></p>
				<h4><c:out value="${thispoll.pollname}" /> <span class="badge" style="background-color:purple"><c:out value="${thispoll.avgRating}" /> </span></h4>
		     	<p><c:out value="${thispoll.polldesc}" /> </p>
		    	<p><a href="#"><c:out value="${thispoll.weblink}" /> </a></p>
			</div>	<!-- end div well-->
			
	
			  <form class = "formsubmit" id="ratingform" action="WriteReview">
				 <p></p><p></p>
				 <h4 style="color:purple">Rate and submit a review </h4>
				 <input id="pollid" type="hidden" name="pollid" value="<c:out value="${thispoll.pollid}"/>"/>
				  <p>
				  <select id="ratingform" name="ratingno" class="ratingoption">
				  <option value="1">1</option>
				  <option value="2">2</option>
				  <option value="3">3</option>
				  <option value="4">4</option>
				  <option value="5">5</option>
				  <option value="6">6</option>
				  <option value="7">7</option>
				  <option value="8">8</option>
				  <option value="9">9</option>
				  <option value="10">10</option>
				  </select> 
				  </p>
				 
				 <input type="text" name="review" placeholder="Add Review" style="width:80% ; height:80px; word-wrap: break-word;"/><p></p><p></p>
				 <input type="submit" name="submit" class="btn btn-success" value="Submit"/>
			     </p><p></p>
		   	  </form>
        </div>
	   
	   <div class="col-sm-2" > 
       </div>

  </div> <!-- first row ends here -->

  
  <div class="row content" ><!-- second row starts here -->

	    <div class="col-sm-1">
	    </div>
	
	    <div class="col-sm-10" style="background-color:white;text-align:center"> 
		<!-- The repetition will start here  -->
			<c:forEach items="${totalReviews}" var="totalrev">
			    <label class="panel text-center" style="width:330px; background-color:white;margin:auto; border:1px solid white; " >
					<div class="panel-body" style="background-color:white; text-align:center; margin:auto; background-size:contain;" > 
						<label class = "reviewbox"><c:out value="${totalrev.review}" />
						</label>
						<p></p>  
						<div class="panel-footer" style="background-color:white; border:white; height:60px">
							<div style=" width:40px; height:40px; background-color:#bfbfbf; background-repeat: no-repeat; background-position: center; background-size:contain;">
								<img id="profileImage" style="width:100% ;height:auto;" src="<%=request.getContextPath()%><c:out value="${totalrev.reviewerImg} " />"/> 
						  	</div>
						</div>
					</div>
				</label>
		    </c:forEach>
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