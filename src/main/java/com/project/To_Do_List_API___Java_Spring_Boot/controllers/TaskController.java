package com.project.To_Do_List_API___Java_Spring_Boot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.To_Do_List_API___Java_Spring_Boot.entities.Task;
import com.project.To_Do_List_API___Java_Spring_Boot.models.ApiResponse;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ReqDeleteTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ReqSaveTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ReqUpdateTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ResDeleteTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ResDetailTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ResListAllTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ResUpdateTask;
import com.project.To_Do_List_API___Java_Spring_Boot.services.TaskService;
import com.project.To_Do_List_API___Java_Spring_Boot.utils.ValidationUtil;

@RestController
@RequestMapping("/api/task")
public class TaskController {

	@Autowired private TaskService service;
	
	
	/// Get All Tasks
	@GetMapping("/list")
	public ResponseEntity<List<ResListAllTask>> list () {
		try {
			List<ResListAllTask> datas = service.findByIsActiveTrue();
			return ResponseEntity.ok().body(datas);
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
		return ResponseEntity.internalServerError().body(null);
	}
	
	
	/**
	 * Save Task
	 * @param reqSaveTask
	 * @return
	 */
	@PostMapping("/save")
	public ResponseEntity<ApiResponse<Task>> save (@RequestBody ReqSaveTask reqSaveTask) {
		try {
			/// Save Task
			ApiResponse<Task> response = service.saveTask(reqSaveTask);
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().body(null);
	}
	
	
	/**
	 * Update Task by Id
	 * @param reqUpdateTask
	 * @return
	 */
	@PostMapping("/update")
	public ResponseEntity<ApiResponse<ResUpdateTask>> update (@RequestBody ReqUpdateTask reqUpdateTask) {
		try {
			/// Update Task
			ApiResponse<ResUpdateTask> response = service.updateTask(reqUpdateTask); 
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().body(null);
	}
	
	/**
	 * Delete Task by Id
	 * @param reqDeleteTask
	 * @return
	 */
	@PostMapping("/delete")
	public ResponseEntity<ApiResponse<ResDeleteTask>> delete (@RequestBody ReqDeleteTask reqDeleteTask) {
		try {
			/// Delete Task
			ApiResponse<ResDeleteTask> response = service.deleteTask(reqDeleteTask);
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().body(new ApiResponse<>(500, "Internal Server Error"));
	}
	
	
	/**
	 * Search by Status
	 * @param paramStatus
	 * @return
	 */
	@GetMapping("/search/{status}")
	public ResponseEntity<ApiResponse<List<ResListAllTask>>> searchByStatus (@PathVariable("status") String paramStatus) {
		try {
			if (!paramStatus.equalsIgnoreCase("pending") && !paramStatus.equalsIgnoreCase("success")) {
				return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Invalid filter value"));
			}
			ApiResponse<List<ResListAllTask>> datas = service.searchByStatus(paramStatus);
			return ResponseEntity.ok().body(datas); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.internalServerError().body(null);
	}
	
	/**
	 * Get by ID
	 * @param paramId
	 * @return
	 */
	@GetMapping("/detail/{id}")
	public ResponseEntity<ApiResponse<ResDetailTask>> detailByTaskId (@PathVariable("id") String paramId) {
		try {
			if (!ValidationUtil.validateInt(paramId)) {
				return ResponseEntity.badRequest().body(new ApiResponse<>(401, "Bad Request", "ID should be a Number"));
			}
			ApiResponse<ResDetailTask> res = service.detailByTaskId(Integer.parseInt(paramId));
			
			return ResponseEntity.ok().body(res);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(new ApiResponse<>(500, "Internal Server Error"));
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
