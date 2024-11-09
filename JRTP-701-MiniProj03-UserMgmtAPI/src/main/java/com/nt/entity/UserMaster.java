package com.nt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity	
@Table(name="JRTP_USER_MASTER")
public class UserMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	@Column(length=20)
  private String name;
	@Column(length=20)
	private String password;
	@Column(length=30,unique = true,nullable = false)
	private String email;
	private Long MobileNo;
	private Long aadharNo;
	@Column(length=10)
	private String gender;
	private LocalDate dob;
	@Column(length=10)
	private String active_sw;
	
	//MetaData
	@CreationTimestamp
	@Column(insertable = true,updatable = false)
	 private LocalDateTime createdOn;	
	
	@UpdateTimestamp
	@Column(insertable = false,updatable = true)
	 private LocalDateTime updatedOn;	
	
	
	@Column(length=20	)
	private String createBy;
	
	@Column(length=20)
	private String updatedBy;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		MobileNo = mobileNo;
	}

	public Long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getActive_sw() {
		return active_sw;
	}

	public void setActive_sw(String active_sw) {
		this.active_sw = active_sw;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public UserMaster(Integer userId, String name, String password, String email, Long mobileNo, Long aadharNo,
			String gender, LocalDate dob, String active_sw, LocalDateTime createdOn, LocalDateTime updatedOn,
			String createBy, String updatedBy) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		MobileNo = mobileNo;
		this.aadharNo = aadharNo;
		this.gender = gender;
		this.dob = dob;
		this.active_sw = active_sw;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.createBy = createBy;
		this.updatedBy = updatedBy;
	}

	public UserMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
