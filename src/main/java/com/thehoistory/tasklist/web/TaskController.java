package com.thehoistory.tasklist.web;

import java.util.List;

import com.thehoistory.tasklist.model.Task;
import com.thehoistory.tasklist.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thehoistory.tasklist.exception.TaskException;
import com.thehoistory.tasklist.model.Response;

@RestController
public class TaskController {
	

	@Autowired
	private TaskService taskService;


	@GetMapping({"/tasks","/"})
	public ResponseEntity<List<Task>> getAllTask(){
		return new ResponseEntity<List<Task>>(taskService.getAllTask(), HttpStatus.OK);
	}
	
    @GetMapping(value = "/task/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") long id) throws TaskException {
    	Task task = taskService.getTaskById(id);
    	if (task == null || task.getId() <= 0){
            throw new TaskException("Task doesn´t exist");
    	}
		return new ResponseEntity<Task>(taskService.getTaskById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/task")
	public ResponseEntity<Task> saveTask(@RequestBody Task payload) throws TaskException {
		if (!TaskService.canCreateTask(payload)){
			throw new TaskException("can not create new task");
		}
		return new ResponseEntity<Task>(taskService.saveTask(payload), HttpStatus.OK);
	}

    @DeleteMapping(value = "/task/{id}")
	public ResponseEntity<Response> removeTaskById(@PathVariable("id") long id) throws TaskException {
    	Task task = taskService.getTaskById(id);
    	if (task == null || task.getId() <= 0){
            throw new TaskException("Task to delete doesn´t exist");
    	}
		taskService.removeTask(task);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Task has been deleted"), HttpStatus.OK);
	}
    

    
    @PatchMapping(value = "/task")
   	public ResponseEntity<Task>  updateTask(@RequestBody Task payload) throws TaskException {
    	Task task = taskService.getTaskById(payload.getId());
    	if (task == null || task.getId() <= 0){
            throw new TaskException("Task update doesn´t exist");
    	}
		return new ResponseEntity<Task>(taskService.saveTask(payload), HttpStatus.OK);
   	}


	
}
