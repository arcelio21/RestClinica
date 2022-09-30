package com.example.entity.address;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties({"handler"})
@JsonInclude(value = Include.NON_NULL)
public class Tdistrict {

	private Integer id;
	
	@JsonInclude( value = Include.NON_NULL)
	private String name;

	@JsonInclude( value = Include.NON_NULL)
	private Tprovince province;

	@JsonInclude(value = Include.NON_NULL)
	private List<Tvillage> villages;
	
	
	public Tdistrict() {
		super();
	}



	public Tdistrict(Integer id) {
		super();
		this.id = id;
	}



	public Tdistrict(Integer id, String name, Tprovince province) {
		super();
		this.id = id;
		this.name = name;
		this.province = province;
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



	public Tprovince getProvince() {
		return province;
	}



	public void setProvince(Tprovince province) {
		this.province = province;
	}

	

	public List<Tvillage> getVillages() {
		return villages;
	}



	public void setVillages(List<Tvillage> villages) {
		this.villages = villages;
	}



	@Override
	public String toString() {
		return "Tdistricts [id=" + id + ", name=" + name + ", province=" + province.getId() + "]";
	}
	
	
}
