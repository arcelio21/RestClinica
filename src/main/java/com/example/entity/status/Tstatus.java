package com.example.entity.status;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Tstatus {

	@Positive
	private Integer id;
	
	@NotBlank(message = "ESTE ATRIBUTO DEBE TENER UN VALOR")
	@Max(30)
	private String name;

	
	public Tstatus() {
		super();
	}


	public Tstatus(@Positive Integer id) {
		super();
		this.id = id;
	}


	public Tstatus(@Positive Integer id,
			@NotBlank(message = "ESTE ATRIBUTO DEBE TENER UN VALOR") @Max(30) String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Tstatus [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
