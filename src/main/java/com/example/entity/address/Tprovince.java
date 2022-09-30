package com.example.entity.address;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class Tprovince {
	
	@Positive
	private Integer id;
	
	@NotEmpty
	@JsonInclude(value = Include.NON_NULL)
	private String name;
	
	@JsonInclude(value = Include.NON_NULL)
	private List<Tdistrict> districts;
	
	public Tprovince() {
		super();
	}

	public Tprovince(Integer id, String name) {
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
	

	

	public List<Tdistrict> getDistricts() {
		return districts;
	}


	@Override
	public String toString() {
		return "Tprovince [id=" + id + ", name=" + name + "]";
	}
	
	

}
