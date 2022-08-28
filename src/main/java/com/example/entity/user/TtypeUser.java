package com.example.entity.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class TtypeUser {
	
	@Positive
	private Integer id;
	
	@NotBlank
	@Max(30)
	private String nameTypeUser;

	
	public TtypeUser() {
		super();
	}


	public TtypeUser(@Positive Integer id) {
		super();
		this.id = id;
	}


	public TtypeUser(@Positive Integer id, @NotBlank @Max(30) String nameTypeUser) {
		super();
		this.id = id;
		this.nameTypeUser = nameTypeUser;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNameTypeUser() {
		return nameTypeUser;
	}


	public void setNameTypeUser(String nameTypeUser) {
		this.nameTypeUser = nameTypeUser;
	}


	@Override
	public String toString() {
		return "TtypeUsers [id=" + id + ", nameTypeUser=" + nameTypeUser + "]";
	}
	
	
	
	
}
