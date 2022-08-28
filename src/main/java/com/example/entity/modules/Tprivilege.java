package com.example.entity.modules;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Tprivilege {

	@Positive
	private Integer id;
	
	@NotBlank
	@Max(30)
	private String namePrivilege;

	
	public Tprivilege() {
		super();
	}


	public Tprivilege(@Positive Integer id) {
		super();
		this.id = id;
	}


	public Tprivilege(@Positive Integer id, @NotBlank @Max(30) String namePrivilege) {
		super();
		this.id = id;
		this.namePrivilege = namePrivilege;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNamePrivilege() {
		return namePrivilege;
	}


	public void setNamePrivilege(String namePrivilege) {
		this.namePrivilege = namePrivilege;
	}


	@Override
	public String toString() {
		return "Tprivileges [id=" + id + ", namePrivilege=" + namePrivilege + "]";
	}
	
	
	
}
