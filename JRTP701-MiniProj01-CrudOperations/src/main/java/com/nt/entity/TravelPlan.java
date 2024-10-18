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
import lombok.Data;

@Entity
@Table(name="JRTP701_Travel_Plan")
@Data
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

}
	
	

