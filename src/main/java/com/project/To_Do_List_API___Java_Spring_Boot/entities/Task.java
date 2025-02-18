package com.project.To_Do_List_API___Java_Spring_Boot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table (name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String taskName;
	
	private boolean status;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_by") 
	private String updatedBy;
	
	@Column(name = "deleted_by")
	private String deletedBy;
	
	@Column(name = "created_at")
	private String createdAt;
	
	@Column(name = "updated_at")
	private String updatedAt;
	
	@Column(name = "deleted_at")
	private String deletedAt;
	
	
	
}
