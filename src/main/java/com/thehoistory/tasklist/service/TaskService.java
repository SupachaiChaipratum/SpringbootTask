package com.thehoistory.tasklist.service;

import java.util.List;

import com.thehoistory.tasklist.model.Task;
import com.thehoistory.tasklist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

	public Task getTaskById(long id) {
		return taskRepository.findOne(id);
	}

	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}

	public Task updatePanding(Task task) {
		task.setPending(!task.isPending());

		return taskRepository.save(task);
	}

	public void removeTask(Task task) {
		taskRepository.delete(task);
	}

	public static boolean canCreateTask(Task task){
		if (task.getId() > 0){
			return false;
		}
		return true;
	}
	

}
