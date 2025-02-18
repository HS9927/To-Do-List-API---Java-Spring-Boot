package com.project.To_Do_List_API___Java_Spring_Boot.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.To_Do_List_API___Java_Spring_Boot.entities.Task;
import com.project.To_Do_List_API___Java_Spring_Boot.models.ApiResponse;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ReqDeleteTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ReqSaveTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ReqUpdateTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ResDeleteTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ResDetailTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ResListAllTask;
import com.project.To_Do_List_API___Java_Spring_Boot.models.task.ResUpdateTask;
import com.project.To_Do_List_API___Java_Spring_Boot.repositories.TaskRepository;
import com.project.To_Do_List_API___Java_Spring_Boot.utils.DateTimeUtil;

@Service
public class TaskService {

	private TaskRepository repository;
	
	private Authentication authentication = null;
	
	public TaskService (TaskRepository repository) {
		this.repository = repository;
		this.authentication = SecurityContextHolder.getContext().getAuthentication();
	}
	
	/**
	 * Find All Task - Hibernate
	 * @return
	 */
	public List<Task> findAll () {
		return repository.findAll();
	}
	
	/**
	 * Find by Task Name - Hibernate
	 * @param taskName
	 * @return
	 */
	public Optional<Task> findByTaskName (String taskName) {
		return repository.findByTaskName(taskName);
	}

	/**
	 * Find by Is Active True
	 * @return
	 */
	public List<ResListAllTask> findByIsActiveTrue () {
		int ind = 1;
		List<ResListAllTask> datas = new ArrayList<>();
		ResListAllTask res;
		List<Task> tasks = repository.findAll();
		for (Task task : tasks) {
			res = new ResListAllTask();
			res.setNo(ind);
			res.setTaskName(task.getTaskName());
			res.setStatus((task.isStatus()) ? "Pending" : "Completed");
			res.setIsActive(task.isActive() ? "Active" : "Inactive");
			res.setCreatedBy(task.getCreatedBy());
			res.setUpdatedBy(task.getUpdatedBy());
			res.setDeletedBy(task.getDeletedBy());
			res.setCreatedAt(task.getCreatedAt());
			res.setUpdatedAt(task.getUpdatedAt());
			res.setDeletedAt(task.getDeletedAt());
			datas.add(res);
			ind++;
		}
		return datas;
	}
	
	
	/**
	 * Save Task
	 * @param reqSaveTask
	 * @return
	 */
	public ApiResponse<Task> saveTask (ReqSaveTask reqSaveTask) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			/// Save Task
			Task task = new Task();
			task.setTaskName(reqSaveTask.getTaskName());
			task.setStatus(true);
			task.setActive(true);
			task.setCreatedAt(DateTimeUtil.TimestampNow());
			task.setCreatedBy(auth.getName());
			Task savedTask = repository.save(task);
			
			/// Prepare ApiResponse
			if (savedTask.isActive() == true) {
				return new ApiResponse<>(200, "Task saved successfully.");
			} else {
				return new ApiResponse<>(500, "Task failed to save.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse<Task>(500, "Task failed to save.", e.getMessage());
		}
	}
	
	
	/**
	 * Update Task by Id
	 * @param reqUpdateTask
	 * @return
	 */
	public ApiResponse<ResUpdateTask> updateTask (ReqUpdateTask reqUpdateTask) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			Optional<Task> existingTask = repository.findById(reqUpdateTask.getId());
			if (existingTask.isPresent()) {
				Task task = existingTask.get();
				task.setTaskName(reqUpdateTask.getTaskName());
				task.setStatus(reqUpdateTask.isStatus());
				task.setUpdatedBy(auth.getName());
				task.setUpdatedAt(DateTimeUtil.TimestampNow());
				Task updatedTask = repository.save(task);
				if (updatedTask.getTaskName().equalsIgnoreCase(reqUpdateTask.getTaskName())) {
					return new ApiResponse<ResUpdateTask>(200, "Task update successfully.", new ResUpdateTask(task.getTaskName(), task.isStatus()));					
				} else {
					return new ApiResponse<ResUpdateTask>(500, "Task field to update.");
				}
			} else {
				return new ApiResponse<ResUpdateTask>(404, "Task field to update.", "Task ID not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse<ResUpdateTask>(500, "Task failed to update.", e.getMessage());
		}
	}
	
	
	/**
	 * Delete Task by Id
	 * @param reqDeleteTask
	 * @return
	 */
	public ApiResponse<ResDeleteTask> deleteTask (ReqDeleteTask reqDeleteTask) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			Optional<Task> existingTask = repository.findById(reqDeleteTask.getId());
			if (existingTask.isPresent()) {
				Task task = existingTask.get();
				if (!task.isActive()) {
					return new ApiResponse<ResDeleteTask>(201, "Task field to delete.", "Task ID already deleted.");
				}
				task.setActive(false);
				task.setDeletedBy(auth.getName());
				task.setDeletedAt(DateTimeUtil.TimestampNow());
				Task deletedTask = repository.save(task);
				if (deletedTask.isActive() == false) {
					return new ApiResponse<ResDeleteTask>(200, "Task delete successfully.", new ResDeleteTask(task.getTaskName(), (task.isStatus()) ? "Active" : "Inactive"));
				} else {
					return new ApiResponse<ResDeleteTask>(500, "Task field to delete.");
				}
			} else {
				return new ApiResponse<ResDeleteTask>(404, "Task faield to delete.", "Task Id not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse<ResDeleteTask>(500, "Task failed to delete.", e.getMessage());
		}
	}
	
	
	public ApiResponse<List<ResListAllTask>> searchByStatus (String paramStatus) {
		try {
			int ind = 1;
			List<ResListAllTask> datas = new ArrayList<>();
			List<Task> tasks = repository.findByStatus((paramStatus.equalsIgnoreCase("pending") ? true : false));
			ResListAllTask res;
			for (Task tTask : tasks) {
				res = new ResListAllTask();
				res.setNo(ind++);
				res.setTaskName(tTask.getTaskName());
				res.setStatus((tTask.isStatus()) ? "Pending" : "Success");
				res.setIsActive((tTask.isActive()) ? "Active" : "Inactive");
				res.setCreatedBy(tTask.getCreatedBy());
				res.setUpdatedBy(tTask.getUpdatedBy());
				res.setDeletedBy(tTask.getDeletedBy());
				res.setCreatedAt(tTask.getCreatedAt());
				res.setUpdatedAt(tTask.getUpdatedAt());
				res.setDeletedAt(tTask.getDeletedAt());
				datas.add(res);
			}
			return new ApiResponse<List<ResListAllTask>>(200, "Task retrieved successfully", datas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResponse<List<ResListAllTask>>(500, "No task available");
	}
	
	
	public ApiResponse<ResDetailTask> detailByTaskId (int paramId) {
		try {
			ResDetailTask data = new ResDetailTask();
			Optional<Task> existingTask = repository.findById(paramId);
			if (existingTask.isPresent()) {
				Task task = existingTask.get();
				data.setTaskName(task.getTaskName());
				data.setStatus((task.isStatus()) ? "Pending" : "Success");
				data.setIsActive((task.isActive()) ? "Active" : "Inactive");
				data.setCreatedBy(task.getCreatedBy());
				data.setUpdatedBy(task.getUpdatedBy());
				data.setDeletedBy(task.getDeletedBy());
				data.setCreatedAt(task.getCreatedAt());
				data.setUpdatedAt(task.getUpdatedAt());
				data.setDeletedAt(task.getDeletedAt());
				return new ApiResponse<ResDetailTask>(200, "Task retrieved successfully", data);
			} else {
				return new ApiResponse<ResDetailTask>(500, "No task available");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResponse<ResDetailTask>(500, "No task available");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
