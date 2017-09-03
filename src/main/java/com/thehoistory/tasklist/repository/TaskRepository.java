package com.thehoistory.tasklist.repository;

import com.thehoistory.tasklist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
