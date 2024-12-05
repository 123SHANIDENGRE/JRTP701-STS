package com.nt.entity;

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
@Table(name="JRTP701_Travel_Plan")
public class TravelPlan {
	
	@Id
	@Column(name="Plan_Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer planId;
	
	@Column(name="Plan_Name",length=30)
	private String planName;
	
	@Column(name="PLAN_MIN_BUDGET")
	private Double planMinBudget;
	
	@Column(name="PLAN_DESCRIPTION",length=50)
	private String planDescription;

	
	@Column(name="PLAN_CATEGORY_ID")
	private Integer planCategoryId;
	
	@Column(name="Active_SW",length=20)
	private String activeSW="active";
	
	
	  public String getActiveSW() {
		return activeSW;
	}

	public void setActiveSW(String activeSW) {
		this.activeSW = activeSW;
	}

	@Column(name = "Created_Date", updatable = false)
	  @CreationTimestamp 
	  private LocalDateTime createdDate;
	 
	@Column(name = "Updated_Date", updatable = true, insertable = false)
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@Column(name = "Created_By", length = 20)
	private String createdBy;

	@Column(name = "Updated_By", length = 20)
	private String updatedBy;

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Double getPlanMinBudget() {
		return planMinBudget;
	}

	public void setPlanMinBudget(Double planMinBudget) {
		this.planMinBudget = planMinBudget;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}

	public Integer getPlanCategoryId() {
		return planCategoryId;
	}

	public void setPlanCategoryId(Integer planCategoryId) {
		this.planCategoryId = planCategoryId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
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
	
	

