package com.project.To_Do_List_API___Java_Spring_Boot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.To_Do_List_API___Java_Spring_Boot.models.ApiResponse;
import com.project.To_Do_List_API___Java_Spring_Boot.models.user.ResListAllUser;
import com.project.To_Do_List_API___Java_Spring_Boot.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired private UserService service;

	/// List All Users
	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<ResListAllUser>>> listAllUser(){
		
		try {
			
			ApiResponse<List<ResListAllUser>> res = service.listAllUser();
			
			return ResponseEntity.ok().body(res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return ResponseEntity.internalServerError().body(null);
	}
	
}	
