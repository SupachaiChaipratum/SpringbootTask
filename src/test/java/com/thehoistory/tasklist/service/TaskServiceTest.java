package com.thehoistory.tasklist.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.thehoistory.tasklist.model.Task;
import com.thehoistory.tasklist.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTest {
	
	@Mock
	private TaskRepository taskRepository;
	
	@InjectMocks
	private TaskService taskService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllTask(){
		List<Task> taskList = new ArrayList<Task>();
		taskList.add(new Task(1,"Task test 1",true));
		taskList.add(new Task(2,"Task test 2",true));
		taskList.add(new Task(3,"Task test 3",false));
		when(taskRepository.findAll()).thenReturn(taskList);
		
		List<Task> result = taskService.getAllTask();
		assertEquals(3, result.size());
	}
	
	@Test
	public void getTaskById(){
		Task task = new Task(1,"Task test 1",true);
		when(taskRepository.findOne(1L)).thenReturn(task);
		Task result = taskService.getTaskById(1);
		assertEquals(1, result.getId());
		assertEquals("Task test 1", result.getDescription());
		assertEquals(true, result.isPending());
	}
	
	@Test
	public void saveTask(){
		Task task = new Task(8,"Task test 8",true);
		when(taskRepository.save(task)).thenReturn(task);
		Task result = taskService.saveTask(task);
		assertEquals(8, result.getId());
		assertEquals("Task test 8", result.getDescription());
		assertEquals(true, result.isPending());
	}

	@Test
	public void updatePanding(){
		Task task = new Task(8,"Task test 8",true);
		when(taskRepository.save(task)).thenReturn(task);
		Task result = taskService.updatePanding(task);
		assertEquals(8, result.getId());
		assertEquals("Task test 8", result.getDescription());
		assertEquals(false, result.isPending());
	}
	
	@Test
	public void removeTask(){
		Task task = new Task(8,"Task test 8",true);
		taskService.removeTask(task);
        verify(taskRepository, times(1)).delete(task);
	}


	@Test
	public void validateTask() {
		Task task = new Task(1, "Task 1", true);
		assertEquals(false, TaskService.canCreateTask(task));
	}

	@Test
	public void validateInvalidTask() {
		Task task = new Task(0, "Task 1", true);
		assertEquals(true, TaskService.canCreateTask(task));
	}

	

}

