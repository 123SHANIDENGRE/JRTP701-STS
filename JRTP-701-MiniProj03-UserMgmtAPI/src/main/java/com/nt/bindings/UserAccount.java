package com.nt.bindings;

import java.time.LocalDate;

public class UserAccount {
	
	private Integer  userId;    //will not be filledup  during user registration
	private String name;
	private String email;
	private Long mobileNo;
	private String gender="Male";
	private LocalDate dob	= LocalDate.now();
	private Long aadharNo;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
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
	public Long getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}
	
	public UserAccount(Integer userId, String name, String email, Long mobileNo, String gender, LocalDate dob,
			Long aadharNo) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.dob = dob;
		this.aadharNo = aadharNo;
	}
	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
