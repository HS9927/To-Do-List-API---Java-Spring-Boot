package com.project.To_Do_List_API___Java_Spring_Boot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.To_Do_List_API___Java_Spring_Boot.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	Optional<Task> findByTaskName(String taskName);
	List<Task> findByStatus(boolean status);
}
