package com.janmat.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.janmat.dao.PollDao;
import com.janmat.dao.UserDao;
import com.janmat.model.Poll;
import com.janmat.model.Ratings;
import com.janmat.model.User;


public class PollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String HOME = "/loginHome.jsp";
	private static String MY_REVIEWS = "/myReviews.jsp";
	private static String TOP_RATED = "/topRatedPolls.jsp";
	private static String MOST_REVIEWED = "/mostReviewedPolls.jsp";
	private static String I_CREATED = "/createdByMe.jsp";
	private static String I_RATED = "/ratedByMe.jsp";
	private static String TOTAL_REVIEWS = "/totalReviews.jsp";
	private static String REFRESH = "/refresh.jsp" ;
	
	
    private PollDao pollDao;
	
    public PollController() {
        super();
        pollDao = new PollDao();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User polluser = (User) request.getSession().getAttribute("loginuser");
		System.out.println("PollController  User " +polluser);
		int useridno = polluser.getUserid();
		
		String forward="";  
	    String action = request.getParameter("submit");
	    System.out.println(action);
	    if (action.equalsIgnoreCase("Create")){
	    	
	    	Date dateCreated = new Date();
	    	
	    	System.out.println(dateCreated.toString());
	    	String pollname = request.getParameter("pollname");
	    	String polldesc = request.getParameter("polldesc");
	    	String weblink = request.getParameter("weblink");
	    	
	    	
	    	Poll newPoll = new Poll();
	    	newPoll.setCreatedby(useridno);
	    	newPoll.setPollname(pollname);
	    	newPoll.setPolldesc(polldesc);
	    	newPoll.setImage("n");
	    	newPoll.setWeblink(weblink);
	    	newPoll.setDateCreated(dateCreated);
	    	
	    	pollDao.createPoll(newPoll);
	    	System.out.println("NEW =  POll   SUBMITTED");
	    	forward = REFRESH ;
	    } 
	    
	    if (action.equalsIgnoreCase("myreviews")){
	    	System.out.println("POLL CONTROLLER : ACTION IS MY REVIEWS");
	    	List<Poll> mypolls = new ArrayList<Poll>();
            mypolls = pollDao.getPollByUser(useridno);
            System.out.println("MY POLLS ARE : " +mypolls);
            forward = MY_REVIEWS;
            request.getSession().setAttribute("mypolls", mypolls);
        }
	    if (action.equalsIgnoreCase("highlyRated")){
	    	System.out.println("POLL CONTROLLER : ACTION IS ACTION IS HIGHLY RATED");
	    	List<Poll> highRatedPolls = new ArrayList<Poll>();
	    	highRatedPolls = pollDao.getHighlyRated();
            System.out.println("HIGHLY RATED POLLS ARE  : " +highRatedPolls);
            forward = TOP_RATED ;
            request.getSession().setAttribute("highRatedPolls", highRatedPolls);
        }
	    if (action.equalsIgnoreCase("mostReviewed")){
	    	System.out.println("POLL CONTROLLER : ACTION IS MOST REVIEWED");
	    	List<Poll> mostReviewedPolls = new ArrayList<Poll>();
	    	mostReviewedPolls = pollDao.getmostReviewed();
            System.out.println("MOST REVIEWED POLLS ARE : " +mostReviewedPolls);
            forward = MOST_REVIEWED ;
            request.getSession().setAttribute("mostReviewedPolls", mostReviewedPolls);
        }
	    
	    if (action.equalsIgnoreCase("createdByMe")){
	    	System.out.println("POLL CONTROLLER : ACTION IS CREATED BY ME");
	    	List<Poll> createdByMe = new ArrayList<Poll>();
	    	createdByMe = pollDao.pollsCreatedByUser(useridno);
            System.out.println("POLLS CREATED BY ME : " +createdByMe);
            forward = I_CREATED ;
            request.getSession().setAttribute("createdByMe", createdByMe);
        }
	    
	    if (action.equalsIgnoreCase("ratedByMe")){
	    	System.out.println("POLL CONTROLLER : ACTION RATED BY ME");
	    	List<Poll> ratedByMe = new ArrayList<Poll>();
	    	ratedByMe = pollDao.pollsRatedByUser(useridno);
            System.out.println("POLLS RATED BY ME : " +ratedByMe);
            forward = I_RATED ;
            request.getSession().setAttribute("ratedByMe", ratedByMe);
        }
	    
	    if (action.equalsIgnoreCase("totalReviews")){
	    	int pollId = Integer.parseInt(request.getParameter("pollid"));
	    	System.out.println("POLL CONTROLLER : TOTAL REVIEWS");
	    	List<Ratings> totalReviews = new ArrayList<Ratings>();
	    	Poll thispoll = new Poll();
	    	thispoll = pollDao.getPollById(pollId);
	    	totalReviews = pollDao.allReviewsByPoll(pollId);
	    	System.out.println("THIS POLL : " +thispoll);
            System.out.println("TOTAL REVIEWS : " +totalReviews);
            forward = TOTAL_REVIEWS ;
            request.getSession().setAttribute("thispoll", thispoll);
            request.getSession().setAttribute("totalReviews", totalReviews);
        }
	        
	    if (action.equalsIgnoreCase("Submit")){
	    	Date dateRated = new Date();
	    	int pollId = Integer.parseInt(request.getParameter("pollid"));
	    	float ratingNo = Float.parseFloat((request.getParameter("ratingno")).trim());
	    	String review = request.getParameter("review") ;
	    	System.out.println("POLL CONTROLLER : WRITE REVIEW FOR " + pollId);
	    	System.out.println("POLL CONTROLLER : REVIEW IS THIS " + review);
	    	System.out.println("POLL CONTROLLER :RATING IS " + ratingNo);
	    	
	    	Ratings newRating = new Ratings();
	    	newRating.setDateRated(dateRated);
	    	newRating.setPollId(pollId);
	    	newRating.setRatedBy(useridno);
	    	newRating.setRating(ratingNo);
	    	newRating.setReview(review);
	    	pollDao.addRating(newRating);
	    	
	      	forward = REFRESH ;    	
        }
	    
	    if (action.equalsIgnoreCase("refreshAll")){
	    	System.out.println("REFRESH ALL");
	    		    	
	    	List<Poll> polls = new ArrayList<Poll>();
            polls = pollDao.getAllPolls();
            request.getSession().setAttribute("polls", polls);
	      	forward = HOME ;    	
        }
	        
        response.sendRedirect(request.getContextPath() + forward);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException {
	    doPost(req, res);
	}
}
