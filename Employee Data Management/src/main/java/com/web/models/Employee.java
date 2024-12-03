package com.web.models;

import java.sql.Date;
import java.util.Arrays;

public class Employee {
    private String name;
    private String email;
    private String mobile;
    private String designation;
    private String gender;
    private String courses;
    private byte[] imageData;
    
    private String id;
    private Date createdDate;
    private String imageUrl;

    public Employee(String name, String email, String mobile, String designation, String gender, String courses, byte[] imageData) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.designation = designation;
        this.gender = gender;
        this.courses = courses;
        this.imageData = imageData;
    }

    public Employee() {}

    public Employee(int int1, String string, String string2, String string3, String string4, String string5,
			String string6, Date date) {
		// TODO Auto-generated constructor stub
	}
    
    // Constructor with parameters
    public Employee(String id, String name, String email, String mobile, String designation, String gender, String courses, byte[] imageData, Date createdDate, String imageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.designation = designation;
        this.gender = gender;
        this.courses = courses;
        this.imageData = imageData;
        this.createdDate = createdDate;
        this.imageUrl = imageUrl;
    }

	public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
    public String getDesignation() { return designation; }
    public String getGender() { return gender; }
    public String getCourses() { return courses; }
    public byte[] getImageData() { return imageData; }
    public String getId() { return id; }
    public Date getCreatedDate() { return createdDate; }
    public String getImageUrl() { return imageUrl; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public void setDesignation(String designation) { this.designation = designation; }
    public void setGender(String gender) { this.gender = gender; }
    public void setCourses(String courses) { this.courses = courses; }
    public void setImageData(byte[] imageData) { this.imageData = imageData; }
    public void setId(String id) { this.id = id; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }    
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

	@Override
	public String toString() {
		return "name=" + name + ", email=" + email + ", mobile=" + mobile + ", designation=" + designation
				+ ", gender=" + gender + ", courses=" + courses + ", imageData=" + ", id="
				+ id + ", createdDate=" + createdDate;
	}
    
    
    
    
}
