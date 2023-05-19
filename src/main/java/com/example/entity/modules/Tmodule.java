package com.example.entity.modules;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Tmodule {
	
	@Positive
	private Long id;
	
	@NotBlank
	@Max(30)
	private String nameModule;

	
	public Tmodule() {
		super();
	}


	public Tmodule(@Positive Long id) {
		super();
		this.id = id;
	}


	public Tmodule(@Positive Long id, @NotBlank @Max(30) String nameModule) {
		super();
		this.id = id;
		this.nameModule = nameModule;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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
