package com.example.entity.address;

public class Tvillage {

	private Integer id;
	private String name;
	private Tdistricts district;
	
	
	
	public Tvillage() {
		super();
	}
	public Tvillage(Integer id) {
		super();
		this.id = id;
	}
	public Tvillage(Integer id, String name, Tdistricts district) {
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
	public Tdistricts getDistrict() {
		return district;
	}
	public void setDistrict(Tdistricts district) {
		this.district = district;
	}
	@Override
	public String toString() {
		return "Tvillage [id=" + id + ", name=" + name + ", district=" + district.getId() + "]";
	}
	
	
}
