package com.example.entity.speciality;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class Tspeciality {

	@Positive
	private Integer id;
	
	@NotEmpty
	@Max(30)
	private String name;

	
	public Tspeciality() {
		super();
	}


	public Tspeciality(@Positive Integer id) {
		super();
		this.id = id;
	}


	public Tspeciality(@Positive Integer id, @NotEmpty @Max(30) String name) {
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
		return "Tspeciality [id=" + id + ", name=" + name + "]";
	}
	
	
}
