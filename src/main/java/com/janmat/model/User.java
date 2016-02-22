package com.janmat.model;

public class User {

    private int userid;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private int pollsCreated;
    private int pollsRated;
    private String profileImage;

    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getFirstName() {
        return firstName;

    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPollsCreated() {
		return pollsCreated;
	}
	public void setPollsCreated(int pollsCreated) {
		this.pollsCreated = pollsCreated;
	}
	public int getPollsRated() {
		return pollsRated;
	}
	public void setPollsRated(int pollsRated) {
		this.pollsRated = pollsRated;
	}    

	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

    @Override
    public String toString() {
        return "User [userid=" + userid + ", firstName=" + firstName
                + ", lastName=" + lastName + ", phone=" + phone + ", email="
                + email +", password=" + password +", pollsCreated=" + pollsCreated
                +", pollsRated=" + pollsRated + ", profileImage=" + profileImage + "]";
    }
	
	
}