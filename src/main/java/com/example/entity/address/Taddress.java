package com.example.entity.address;

public class Taddress {
	
	private Integer id;
	private Tvillage villageId;
	private String specificAddress;
	
	
	
	public Taddress() {
		super();
	}



	public Taddress(Integer id) {
		super();
		this.id = id;
	}



	public Taddress(Integer id, Tvillage villageId, String specificAddress) {
		super();
		this.id = id;
		this.villageId = villageId;
		this.specificAddress = specificAddress;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Tvillage getVillageId() {
		return villageId;
	}



	public void setVillageId(Tvillage villageId) {
		this.villageId = villageId;
	}



	public String getSpecificAddress() {
		return specificAddress;
	}



	public void setSpecificAddress(String specificAddress) {
		this.specificAddress = specificAddress;
	}



	@Override
	public String toString() {
		return "Taddress [id=" + id + ", villageId=" + villageId.getId() + ", specificAddress=" + specificAddress + "]";
	}
	
	
	
}
