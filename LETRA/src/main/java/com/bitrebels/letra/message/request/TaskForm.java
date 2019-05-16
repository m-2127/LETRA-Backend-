package com.bitrebels.letra.message.request;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bitrebels.letra.model.Task;
import com.bitrebels.letra.services.DateHandler;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class TaskForm {
	
	@NotBlank
	private String name;
	
	@NotNull
	@JsonDeserialize(using = DateHandler.class)
	private LocalDate startDate;
	
	@NotNull
	@JsonDeserialize(using = DateHandler.class)
	private LocalDate endDate;
	
	@NotBlank
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
