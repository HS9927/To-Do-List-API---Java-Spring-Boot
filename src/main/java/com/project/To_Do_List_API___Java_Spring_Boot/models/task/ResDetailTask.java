package com.project.To_Do_List_API___Java_Spring_Boot.models.task;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResDetailTask {
	private String taskName;
	private String status;
	private String isActive;
	private String createdBy;
	private String updatedBy;
	private String deletedBy;
	private String createdAt;
	private String updatedAt;
	private String deletedAt;
}
