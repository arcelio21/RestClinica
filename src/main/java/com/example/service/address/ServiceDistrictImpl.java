package com.example.service.address;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.mapper.address.MapperDistrict;
import java.util.Collections;

@Service
public class ServiceDistrictImpl implements IServiceDistrict<Tdistrict, Integer>{
	
	private MapperDistrict mapperDistrict;
	
	public ServiceDistrictImpl(MapperDistrict mapperDistrict) {
		
		this.mapperDistrict=mapperDistrict;
	}
	
	@Override
	public List<Tdistrict> getAll() {
		
		List<Tdistrict> districts=this.mapperDistrict.getAll();
		if(districts.isEmpty()) {
			return Collections.emptyList();
		}
		
		return districts;
	}

	@Override
	public Tdistrict getById(Integer id) {
		return Optional.ofNullable(id).map(mapperDistrict::getById).orElse(null);
	}

	@Override
	public Integer update(Tdistrict tdistrict) {
		if(tdistrict==null || tdistrict.getId()==null) {
			return null;
		}
		
		return this.update(tdistrict);
	}

	@Override
	public Integer save(Tdistrict tdistrict) {
		
		if(tdistrict==null) {
			return null;
		}
		return this.save(tdistrict);
	}

	@Override
	public Tdistrict getDistrictAllSimpleById(Integer id) {

		if(id==null) {
			return null;
		}
		return this.mapperDistrict.getDistrictAllSimpleById(id);
	}

	@Override
	public List<Tdistrict> getByProvinceId(Tprovince tprovince) {
		
		if(tprovince==null || tprovince.getId()==null) {
			return Collections.emptyList();
		}
		return this.mapperDistrict.getByProvinceId(tprovince);
	}


	@Override
	public Tdistrict getByIdSimple(Integer id) {
		return Optional.ofNullable(id).map(this.mapperDistrict::getByIdSimple).orElse(null);
	}

}
