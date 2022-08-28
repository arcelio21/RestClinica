package com.example.entity.visit;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class TvitalSign {

	@Positive
	private Integer id;
	
	@NotEmpty
	@Max(50)
	private String name;

	
	public TvitalSign() {
		super();
	}


	public TvitalSign(@Positive Integer id) {
		super();
		this.id = id;
	}


	public TvitalSign(@Positive Integer id, @NotEmpty @Max(50) String name) {
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
		return "TvitalSign [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
