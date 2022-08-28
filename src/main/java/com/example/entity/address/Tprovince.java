package com.example.entity.address;

import java.util.List;

public class Tprovince {
	
	private Integer id;
	private String name;
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

	public void setDistricts(List<Tdistrict> districts) {
		this.districts = districts;
	}

	@Override
	public String toString() {
		return "Tprovince [id=" + id + ", name=" + name + "]";
	}
	
	

}
