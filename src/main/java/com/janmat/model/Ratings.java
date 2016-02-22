package com.janmat.model;

import java.util.Date;

public class Ratings {
	
		private String rateid;
		private int ratedBy;
		private int pollid;
		private Date dateRated;
		private float rating;
		private String review;
		private String reviewerName;
		private String reviewerImg;		
		
		
		public String getRateId() {
			return rateid;
		}
		public void setRateId(String rateId) {
			this.rateid = rateId;
		}
		public int getRatedBy() {
			return ratedBy;
		}
		public void setRatedBy(int ratedBy) {
			this.ratedBy = ratedBy;
		}
		public int getPollId() {
			return pollid;
		}
		public void setPollId(int pollId) {
			this.pollid = pollId;
		}
		public Date getDateRated() {
			return dateRated;
		}
		public void setDateRated(Date dateRated) {
			this.dateRated = dateRated;
		}
		public float getRating() {
			return rating;
		}
		public void setRating(float rating) {
			this.rating = rating;
		}
		public String getReview() {
			return review;
		}
		public void setReview(String review) {
			this.review = review;
		}	
		
		public String getReviewerImg() {
			return reviewerImg;
		}
		public void setReviewerImg(String reviewerImg) {
			this.reviewerImg = reviewerImg;
		}
		public String getReviewerName() {
			return reviewerName;
		}
		public void setReviewerName(String reviewerName) {
			this.reviewerName = reviewerName;
		}
		
		@Override
	    public String toString() {
	    return " Rating [rateid=" + rateid + ", ratedBy=" + ratedBy
        + ", pollid=" + pollid + ", dateRated=" + dateRated + ", rating="
        + rating + ", review=" + review  + ", reviewerImg=" + reviewerImg 
        + ", reviewerName=" + reviewerName + "]";
		}
		
			
}