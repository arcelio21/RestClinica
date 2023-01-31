package com.example.entity.address;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;


@JsonIgnoreProperties({"handler"})
@JsonInclude(content = Include.NON_NULL)
@Builder
public class Taddress implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	@JsonInclude(content = Include.NON_NULL)
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



	@JsonGetter
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
