package com.thehoistory.tasklist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {


	@Id
	@GeneratedValue
	private long id;
	private String description;
	private boolean pending;

	public Task() {
		super();
	}

	public Task(long id, String description, boolean pending) {
		super();
		this.id = id;
		this.description = description;
		this.pending = pending;
	}

	public Task(String description, boolean pending) {
		super();
		this.description = description;
		this.pending = pending;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}


	
	

	
	
	
	

}
