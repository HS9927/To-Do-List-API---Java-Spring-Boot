package com.project.To_Do_List_API___Java_Spring_Boot.models.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResDeleteTask {
	private String taskName;
	private String status;
}
