package com.janmat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.janmat.model.Poll;
import com.janmat.model.Ratings;
import com.janmat.util.DbUtil;

public class PollDao {

	private Connection connection;

    public PollDao() {
        connection = DbUtil.getConnection();
    }
    
    public void createPoll(Poll poll) {
		try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("insert into polldata(createdby,pollname, polldesc,image,weblink,doc) values (?, ?, ?, ?, ?, ?)");
		    // Parameters start with 1 
		    preparedStatement.setInt(1, poll.getCreatedby());
		    preparedStatement.setString(2, poll.getPollname());
		    preparedStatement.setString(3, poll.getPolldesc());
		    preparedStatement.setString(4, poll.getImage());
		    preparedStatement.setString(5, poll.getWeblink());   
    		preparedStatement.setDate(6, new java.sql.Date(poll.getDateCreated().getTime()));
    	    preparedStatement.executeUpdate();

		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
    
    public void deletePoll(int pollID) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from polldata where pollid=?");
            // Parameters start with 1
            preparedStatement.setInt(1, pollID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
   /*  getAllPolls gets a list of all polls */
    
    public List<Poll> getAllPolls() {
        List<Poll> polls = new ArrayList<Poll>();
        try {
            Statement statement = connection.createStatement();
            System.out.println("Just before execution");
            ResultSet rs = statement.executeQuery("select pd.pollid, pd.pollname, pd.polldesc, pd.image, pd.weblink, pd.createdby, (select CONCAT(firstname , ' ' , lastname ) from hodusers hd where hd.userid=pd.createdby) createrName , pd.doc, (select AVG(r.rating) from ratings r where r.pollid= pd.pollid) avgRating, (select COUNT(r.rating) from ratings r where r.pollid=pd.pollid) totalRatings from polldata pd ORDER BY pd.doc DESC");
            while (rs.next()) {
            	  System.out.println("got soemthing after all");
                Poll poll = new Poll();
                poll.setPollid(rs.getInt("pd.pollid"));
                poll.setPollname(rs.getString("pd.pollname"));
                poll.setPolldesc(rs.getString("pd.polldesc"));
                poll.setImage(rs.getString("pd.image"));
                poll.setWeblink(rs.getString("pd.weblink"));
                poll.setCreatedby(rs.getInt("pd.createdby"));
                poll.setCreaterName(rs.getString("createrName"));
                poll.setDateCreated(rs.getDate("pd.doc"));
                poll.setAvgRating(rs.getFloat("avgRating"));
                poll.setTotalRatings(rs.getInt("totalRatings"));
                polls.add(poll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return polls;
    }
    
   /*  getPollByUser gets list of all polls created by a user */
    
    public List<Poll> getPollByUser(int userId) {
        List<Poll> mypolls = new ArrayList<Poll>();
        try {
            PreparedStatement preparedStatement = connection.
            prepareStatement("select pd.pollid,pd.pollname,pd.polldesc,pd.image,pd.weblink,pd.createdby,pd.doc, (select CONCAT(firstname , ' ' , lastname ) from hodusers hd where hd.userid=pd.createdby) createrName, (select AVG(r.rating) from ratings r where r.pollid= pd.pollid) avgRating, (select COUNT(r.rating) from ratings r where r.pollid= pd.pollid) totalRatings from polldata pd where pd.createdby = ?");
            preparedStatement.setInt(1, userId);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
          	     System.out.println("got soemthing after all");
            	 Poll poll = new Poll();
                 poll.setPollid(rs.getInt("pd.pollid"));
                 poll.setPollname(rs.getString("pd.pollname"));
                 poll.setPolldesc(rs.getString("pd.polldesc"));
                 poll.setImage(rs.getString("pd.image"));
                 poll.setWeblink(rs.getString("pd.weblink"));
                 poll.setCreatedby(rs.getInt("pd.createdby"));
                 poll.setCreaterName(rs.getString("createrName"));
                 poll.setDateCreated(rs.getDate("pd.doc"));
                 poll.setAvgRating(rs.getFloat("avgRating"));
                 poll.setTotalRatings(rs.getInt("totalRatings"));
                 mypolls.add(poll);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mypolls;
    }
    
    /* getPollsByMaxReviews gets those polls that have got the maximum Reviews   */
    
    public List<Poll> getmostReviewed() {
        List<Poll> mostReviewedPolls = new ArrayList<Poll>();
        try {
            PreparedStatement preparedStatement = connection.
            prepareStatement("select pd.pollid,pd.pollname,pd.polldesc,pd.image,pd.weblink,pd.createdby,pd.doc, (select CONCAT(firstname , ' ' , lastname ) from hodusers hd where hd.userid=pd.createdby) createrName, (select AVG(r.rating) from ratings r where r.pollid= pd.pollid) avgRating, (select COUNT(r.rating) from ratings r where r.pollid= pd.pollid) totalRatings from polldata pd ORDER BY totalRatings DESC");
                       
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
          	   System.out.println("got soemthing after all");
          	   Poll poll = new Poll();
               poll.setPollid(rs.getInt("pd.pollid"));
               poll.setPollname(rs.getString("pd.pollname"));
               poll.setPolldesc(rs.getString("pd.polldesc"));
               poll.setImage(rs.getString("pd.image"));
               poll.setWeblink(rs.getString("pd.weblink"));
               poll.setCreatedby(rs.getInt("pd.createdby"));
               poll.setCreaterName(rs.getString("createrName"));
               poll.setDateCreated(rs.getDate("pd.doc"));
               poll.setAvgRating(rs.getFloat("avgRating"));
               poll.setTotalRatings(rs.getInt("totalRatings"));
               mostReviewedPolls.add(poll);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mostReviewedPolls;
    }
    
    /* getPollsbyMaxRatings gets those polls that have got the highest Ratings  */
    
    public List<Poll> getHighlyRated() {
        List<Poll> highRatedPolls = new ArrayList<Poll>();
        try {
            PreparedStatement preparedStatement = connection.
            prepareStatement("select pd.pollid,pd.pollname,pd.polldesc,pd.image,pd.weblink,pd.createdby,pd.doc, (select CONCAT(firstname , ' ' , lastname ) from hodusers hd where hd.userid=pd.createdby) createrName, (select AVG(r.rating) from ratings r where r.pollid= pd.pollid) avgRating, (select COUNT(r.rating) from ratings r where r.pollid= pd.pollid) totalRatings from polldata pd ORDER BY avgRating DESC");
                       
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
          	     System.out.println("got soemthing after all");
            	 Poll poll = new Poll();
                 poll.setPollid(rs.getInt("pd.pollid"));
                 poll.setPollname(rs.getString("pd.pollname"));
                 poll.setPolldesc(rs.getString("pd.polldesc"));
                 poll.setImage(rs.getString("pd.image"));
                 poll.setWeblink(rs.getString("pd.weblink"));
                 poll.setCreatedby(rs.getInt("pd.createdby"));
                 poll.setCreaterName(rs.getString("createrName"));
                 poll.setDateCreated(rs.getDate("pd.doc"));
                 poll.setAvgRating(rs.getFloat("avgRating"));
                 poll.setTotalRatings(rs.getInt("totalRatings"));
                 highRatedPolls.add(poll);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return highRatedPolls;
    }
    
/* pollsCreatedByUser gets those polls that have got the highest Ratings  */
    
    public List<Poll> pollsCreatedByUser(int userId) {
    	    List<Poll> createdByMe = new ArrayList<Poll>();
            try {
                PreparedStatement preparedStatement = connection.
                prepareStatement("select pd.pollid,pd.pollname,pd.polldesc,pd.image,pd.weblink,pd.createdby,pd.doc, (select CONCAT(firstname , ' ' , lastname ) from hodusers hd where hd.userid=pd.createdby) createrName, (select AVG(r.rating) from ratings r where r.pollid= pd.pollid) avgRating, (select COUNT(r.rating) from ratings r where r.pollid= pd.pollid) totalRatings from polldata pd where pd.createdby = ?");
                preparedStatement.setInt(1, userId);
                
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
              	     System.out.println("Got result for polls created by user");
                	 Poll poll = new Poll();
                	 poll.setPollid(rs.getInt("pd.pollid"));
                     poll.setPollname(rs.getString("pd.pollname"));
                     poll.setPolldesc(rs.getString("pd.polldesc"));
                     poll.setImage(rs.getString("pd.image"));
                     poll.setWeblink(rs.getString("pd.weblink"));
                     poll.setCreatedby(rs.getInt("pd.createdby"));
                     poll.setCreaterName(rs.getString("createrName"));
                     poll.setDateCreated(rs.getDate("pd.doc"));
                     poll.setAvgRating(rs.getFloat("avgRating"));
                     poll.setTotalRatings(rs.getInt("totalRatings"));
                     createdByMe.add(poll);
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return createdByMe;
        }
        
/* pollsRatedByUser gets those polls that have got the highest Ratings  */
    
    public List<Poll> pollsRatedByUser(int userId) {
    	    List<Poll> ratedByMe = new ArrayList<Poll>();
            try {
                PreparedStatement preparedStatement = connection.
                prepareStatement("select r.pollid,pd.pollname,pd.polldesc,pd.image,pd.weblink,pd.doc, pd.createdby,(select CONCAT(firstname , ' ' , lastname ) from hodusers hd where hd.userid=pd.createdby) createrName, (select AVG(r.rating) from ratings r where r.pollid= pd.pollid) avgRating, (select COUNT(r.rating) from ratings r where r.pollid= pd.pollid) totalRatings from polldata pd inner  JOIN ratings r on  r.pollid=pd.pollid and r.ratedby=? ");
                preparedStatement.setInt(1, userId);
                
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
              	     System.out.println("Got result for polls rated by user");
                	 Poll poll = new Poll();
                	 poll.setPollid(rs.getInt("r.pollid"));
                     poll.setPollname(rs.getString("pd.pollname"));
                     poll.setPolldesc(rs.getString("pd.polldesc"));
                     poll.setImage(rs.getString("pd.image"));
                     poll.setWeblink(rs.getString("pd.weblink"));
                     poll.setCreatedby(rs.getInt("pd.createdby"));
                     poll.setCreaterName(rs.getString("createrName"));
                     poll.setDateCreated(rs.getDate("pd.doc"));
                     poll.setAvgRating(rs.getFloat("avgRating"));
                     poll.setTotalRatings(rs.getInt("totalRatings"));
                     ratedByMe.add(poll);
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return ratedByMe;
        }
    
    
/* allReviewsByPoll gets reviews for a particular poll by pollId  */
    
    public List<Ratings> allReviewsByPoll(int pollId) {
    	    List<Ratings> totalReviews = new ArrayList<Ratings>();
            try {
                PreparedStatement preparedStatement = connection.
                prepareStatement("select r.rateid, r.pollid, r.dor,r.ratedby,r.rating,r.review,(select CONCAT(hd.firstname ,' ' , hd.lastname) from hodusers hd where r.ratedby=hd.userid) reviewerName,(select (hd.profileImage) from hodusers hd where r.ratedby=hd.userid) reviewerImg from ratings r where r.pollid = ?" );
                preparedStatement.setInt(1, pollId);
                
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
              	     System.out.println(" Got reviews for poll");
                	 Ratings rating = new Ratings();
                	 rating.setPollId(rs.getInt("r.rateId"));
                	 rating.setPollId(rs.getInt("r.pollid"));
                	 rating.setDateRated(rs.getDate("r.dor"));
                	 rating.setRatedBy(rs.getInt("r.ratedby"));
                	 rating.setRating(rs.getInt("r.rating"));
                	 rating.setReview(rs.getString("r.review"));
                	 rating.setReviewerName(rs.getString("reviewerName"));
                	 rating.setReviewerImg(rs.getString("reviewerImg"));
                     totalReviews.add(rating);
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return totalReviews;
        }
 
    
/* getPollById gets a poll by its Id */
    
    public Poll getPollById(int pollId) {
    	Poll poll = new Poll();
            try {
                PreparedStatement preparedStatement = connection.
                prepareStatement("select pd.pollid,pd.pollname,pd.polldesc,pd.image,pd.weblink,pd.createdby,pd.doc, (select CONCAT(firstname , ' ' , lastname ) from hodusers hd where hd.userid=pd.createdby) createrName, (select AVG(r.rating) from ratings r where r.pollid= pd.pollid) avgRating, (select COUNT(r.rating) from ratings r where r.pollid= pd.pollid) totalRatings from polldata pd where pd.pollid = ?");
                   preparedStatement.setInt(1, pollId);
                
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                	 System.out.println("Got a Poll info by pollid"); 
                	 poll.setPollid(rs.getInt("pd.pollid"));
                     poll.setPollname(rs.getString("pd.pollname"));
                     poll.setPolldesc(rs.getString("pd.polldesc"));
                     poll.setImage(rs.getString("pd.image"));
                     poll.setWeblink(rs.getString("pd.weblink"));
                     poll.setCreatedby(rs.getInt("pd.createdby"));
                     poll.setCreaterName(rs.getString("createrName"));
                     poll.setDateCreated(rs.getDate("pd.doc"));
                     poll.setAvgRating(rs.getFloat("avgRating"));
                     poll.setTotalRatings(rs.getInt("totalRatings"));
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return poll;
        }
    
    /* Add a review and rating  */
    public void addRating(Ratings rating) {
        try {
		    PreparedStatement preparedStatement = connection
		            .prepareStatement("insert into ratings(pollid,ratedby,rating,review,dor) values (?, ?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setInt(1, rating.getPollId());
		    preparedStatement.setInt(2, rating.getRatedBy());
		    preparedStatement.setFloat(3, rating.getRating());
		    preparedStatement.setString(4, rating.getReview());
		    preparedStatement.setDate(5, new java.sql.Date(rating.getDateRated().getTime()));
    		preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
