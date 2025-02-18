package com.project.To_Do_List_API___Java_Spring_Boot.models.task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqUpdateTask {
	private int id;
	private String taskName;
	private boolean status;
}
