package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="JRTP701_COURSE_DETAILS")
public class CourseDetails {
  
	@Id
	@GeneratedValue
	private Integer courseId;
	@Column(length=50)
	private String courseName;
	@Column(length=30)
	private String location;
	@Column(length=30)
	private String courseCategory;
	public String getCourseCategory() {
		return courseCategory;
	}
	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}
	@Column(length=30)
	private String facultyName;
	private Double fee;
	@Column(length=30)
	private String adminName;
	
	private Long adminContact;
	@Column(length=30)
	private String trainingMode;
	private  LocalDateTime startDate;
	@Column(length=30)
	private String courseStatus;
	@CreationTimestamp
	@Column(insertable=true,updatable = false)
	private LocalDateTime creationDate;
	@UpdateTimestamp
	@Column(insertable = false,updatable = true)
	private LocalDateTime   updationDate;
	@Column(length=30)
	private String createdBy;
	@Column(length=30)
	private String updatedBy;

	public CourseDetails(Integer courseId, String courseName, String location, String courseCategory,
			String facultyName, Double fee, String adminName,Long  adminContact, String trainingMode,
			LocalDateTime startDate, String courseStatus, LocalDateTime creationDate, LocalDateTime updationDate,
			String createdBy, String updatedBy) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.location = location;
		this.courseCategory = courseCategory;
		this.facultyName = facultyName;
		this.fee = fee;
		this.adminName = adminName;
		this.adminContact = adminContact;
		this.trainingMode = trainingMode;
		this.startDate = startDate;
		this.courseStatus = courseStatus;
		this.creationDate = creationDate;
		this.updationDate = updationDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}
	public CourseDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
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
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public LocalDateTime getUpdationDate() {
		return updationDate;
	}
	public void setUpdationDate(LocalDateTime updationDate) {
		this.updationDate = updationDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
}
