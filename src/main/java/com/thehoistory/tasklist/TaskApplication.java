package com.thehoistory.tasklist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}
	

}
