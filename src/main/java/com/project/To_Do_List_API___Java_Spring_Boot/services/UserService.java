package com.project.To_Do_List_API___Java_Spring_Boot.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.To_Do_List_API___Java_Spring_Boot.entities.User;
import com.project.To_Do_List_API___Java_Spring_Boot.models.ApiResponse;
import com.project.To_Do_List_API___Java_Spring_Boot.models.user.ResListAllUser;
import com.project.To_Do_List_API___Java_Spring_Boot.repositories.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository repository;
	
	public List<User> findAll () {
		return repository.findAll();
	}

	
	public ApiResponse<List<ResListAllUser>> listAllUser () {
		try {
			int ind = 1;
			List<ResListAllUser> res = new ArrayList<>();
			ResListAllUser user;
			List<User> users = repository.findAll();
			if (users.isEmpty()) {
				return new ApiResponse<List<ResListAllUser>>(500, "No user available");
			}
			for (User tUser : users) {
				user = new ResListAllUser();
				user.setNo(ind++);
				user.setUsername(tUser.getUsername());
				user.setRoleName(tUser.getRole());
				res.add(user);
			}
			return new ApiResponse<List<ResListAllUser>>(200, "User retrieved successfully", res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ApiResponse<List<ResListAllUser>>(500, "No user available");
	}
}
