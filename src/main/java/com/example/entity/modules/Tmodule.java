package com.example.entity.modules;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Tmodules {
	
	@Positive
	private Integer id;
	
	@NotBlank
	@Max(30)
	private String nameModule;

	
	public Tmodules() {
		super();
	}


	public Tmodules(@Positive Integer id) {
		super();
		this.id = id;
	}


	public Tmodules(@Positive Integer id, @NotBlank @Max(30) String nameModule) {
		super();
		this.id = id;
		this.nameModule = nameModule;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNameModule() {
		return nameModule;
	}


	public void setNameModule(String nameModule) {
		this.nameModule = nameModule;
	}


	@Override
	public String toString() {
		return "Tmodules [id=" + id + ", nameModule=" + nameModule + "]";
	}
	
	
}
