package com.nt.model;

import java.time.LocalDateTime;

public class SearchResults {
	
	private Integer courseId;
	private String courseName;
	private String  location;
	private String courseCategory;
	private String facultyName;
	private Double fee;
	private Long 	adminContact;
	private String 	trainingMode;
	private LocalDateTime startDate;
	private String courseStatus;
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Long getAdminContact() {
		return adminContact;
	}
	public void setAdminContact(Long adminContact) {
		this.adminContact = adminContact;
	}
	public String getTrainingMode() {
		return trainingMode;
	}
	public void setTrainingMode(String trainingMode) {
		this.trainingMode = trainingMode;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public String getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	public SearchResults(Integer courseId, String courseName, String location, String courseCategory,
			String facultyName, Double fee, Long adminContact, String trainingMode, LocalDateTime startDate,
			String courseStatus) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.location = location;
		this.courseCategory = courseCategory;
		this.facultyName = facultyName;
		this.fee = fee;
		this.adminContact = adminContact;
		this.trainingMode = trainingMode;
		this.startDate = startDate;
		this.courseStatus = courseStatus;
	}
	public SearchResults() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
