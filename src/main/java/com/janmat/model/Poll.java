package com.janmat.model;

import java.util.Date;

public class Poll {
	
	private int createdby;
	private int pollid;
	private String pollname;
	private String createrName;
	private String polldesc;
	private String image;
	private String weblink;
	private Date dateCreated;
	private float avgRating;
    private	int totalRatings;
	
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public int getPollid() {
		return pollid;
	}
	public void setPollid(int pollid) {
		this.pollid = pollid;
	}
	public String getPolldesc() {
		return polldesc;
	}
	public void setPolldesc(String polldesc) {
		this.polldesc = polldesc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getWeblink() {
		return weblink;
	}
	public void setWeblink(String weblink) {
		this.weblink = weblink;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public float getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}
	public int getTotalRatings() {
		return totalRatings;
	}
	public void setTotalRatings(int totalRatings) {
		this.totalRatings = totalRatings;
	}   
	public String getPollname() {
		return pollname;
	}
	public void setPollname(String pollname) {
		this.pollname = pollname;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	
	@Override
	    public String toString() {
	        return "Poll [  pollid=" + pollid + ",   createdby=" + createdby 
	                + ", polldesc=" + polldesc + ", image=" + image + ", dateCreated=" + dateCreated
	                + ", avgRating=" + avgRating + ", totalRatings=" + totalRatings
	                + ", pollname =" + pollname + ", createrName=" + createrName + "]";
	    }
	
	
	
}


