//SearchInput.java
package com.nt.model;

import java.time.LocalDateTime;

public class SearchInputs {
	
	private String courseCategory;
	private String  trainingMode;
	private String facultyName;
	private LocalDateTime startsOn;
	
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	public String getTrainingMode() {
		return trainingMode;
	}
	public void setTrainingMode(String trainingMode) {
		this.trainingMode = trainingMode;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public LocalDateTime getStartsOn() {
		return startsOn;
	}
	public void setStartsOn(LocalDateTime startsOn) {
		this.startsOn = startsOn;
	}
	public SearchInputs(String courseCategory, String trainingMode, String facultyName, LocalDateTime startsOn) {
		super();
		this.courseCategory = courseCategory;
		this.trainingMode = trainingMode;
		this.facultyName = facultyName;
		this.startsOn = startsOn;
	}
	public SearchInputs() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
