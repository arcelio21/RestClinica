package com.example.entity.address;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties({"handler"})
@JsonInclude(content = Include.NON_NULL)
public class Tvillage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String name;

	@JsonInclude(content = Include.NON_NULL)
	private Tdistrict district;
	
	
	
	public Tvillage() {
		super();
	}
	public Tvillage(Integer id) {
		super();
		this.id = id;
	}
	public Tvillage(Integer id, String name, Tdistrict district) {
		super();
		this.id = id;
		this.name = name;
		this.district = district;
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
	public Tdistrict getDistrict() {
		return district;
	}
	public void setDistrict(Tdistrict district) {
		this.district = district;
	}
	@Override
	public String toString() {
		return "Tvillage [id=" + id + ", name=" + name + ", district=" + district.getId() + "]";
	}
	
	
}
