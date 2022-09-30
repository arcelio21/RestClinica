package com.example.service.address;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tvillage;
import com.example.mapper.address.MapperVillage;

@Service
public class ServiceVillageImpl implements IServiceVillage<Tvillage, Integer>{

	
	private MapperVillage mapperVillage;
	
	public ServiceVillageImpl(MapperVillage mapperVillage) {
		this.mapperVillage=mapperVillage;
	}
	
	@Override
	public List<Tvillage> getAll() {
		
		List<Tvillage> villages=this.mapperVillage.getAll();
		if(villages==null || villages.isEmpty()) {
			return Collections.emptyList();
		}
		return villages;
	}

	@Override
	public Tvillage getById(Integer id) {
		return Optional.ofNullable(id).map(this.mapperVillage::getById).orElse(null);
	}

	@Override
	public Integer update(Tvillage t) {
		return Optional.ofNullable(t).map(this.mapperVillage::update).orElse(0);
	}

	@Override
	public Integer save(Tvillage t) {
		return Optional.ofNullable(t).map(this.mapperVillage::save).orElse(0);
	}

	@Override
	public List<Tvillage> getByDistrictId(Tdistrict tdistrict) {
		
		return Optional.ofNullable(tdistrict).map(this.mapperVillage::getByDistrictId).orElse(Collections.emptyList());
	}

	@Override
	public Tvillage getDistrictAllById(Integer id) {
		return Optional.ofNullable(id).map(this.mapperVillage::getDistrictAllById).orElse(null);
	}

}
