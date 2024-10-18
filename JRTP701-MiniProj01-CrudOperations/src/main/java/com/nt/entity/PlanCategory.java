package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "JRTP701_Plan_Category")

public class PlanCategory {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "category_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@Column(name = "Category_Id")
	private Integer categoryId;
	
	@Column(name = "Category_Name", length = 30)
	private String categoryName;

	@Column(name = "ACTIVE_SW", length = 15)
	private String activeSW="active";

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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getActiveSW() {
		return activeSW;
	}

	public void setActiveSW(String activeSW) {
		this.activeSW = activeSW;
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
