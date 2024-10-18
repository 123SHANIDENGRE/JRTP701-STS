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
import lombok.Data;

@Entity
@Table(name = "JRTP701_Plan_Category")
@Data
public class PlanCategory {

	@Id
	@SequenceGenerator(name = "gen1", sequenceName = "category_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	@Column(name = "Category_Id")
	private Integer categoryId;
	@Column(name = "Category_Name", length = 30)
	private String categoryName;

	@Column(name = "ACTIVE_SW", length = 15)
	private String activeSW;

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
