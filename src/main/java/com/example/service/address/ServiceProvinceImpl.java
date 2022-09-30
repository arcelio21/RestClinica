package com.example.service.address;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.address.Tprovince;
import com.example.mapper.address.MapperProvince;

@Service
public class ServiceProvinceImpl implements IServiceProvince<Tprovince, Integer> {

	private MapperProvince mapperProvince;
	
	public ServiceProvinceImpl(MapperProvince mapperProvince) {
		this.mapperProvince=mapperProvince;
	}
	
	/**
	 * SE EJECUTARA UN QUERY QUE TRAERA LAS PROVINCIAS, CON
	 * LOS DISTRITOS ASOCIADO A CADA PROVINCIA Y LOS CORREGIMIENTOS ASOCIADO A CADA DISTRITOS
	 */
	@Override
	public List<Tprovince> getAll() {
		return this.mapperProvince.getAll();
	}

	/**
	 * SE OBTENDRA UN PROVINCIA POR ID 
	 */
	@Override
	public Tprovince getById(Integer id) {
		if(id==null || id==0) {
			return null;
		}
		return this.mapperProvince.getById(id);
	}

	@Override
	public Integer update(Tprovince tprovince) {
		
		if(tprovince==null || tprovince.getId()==null || tprovince.getId()==0) {
			return 0;
		}
		
		return this.mapperProvince.update(tprovince);
	}

	@Override
	public Integer save(Tprovince tprovince) {

		return this.mapperProvince.save(tprovince);
	}

	/**
	 * TRAERA LA INFORMACION MAS SIMPLE DE UNA PROVINCIA, SU NOMBRE E ID
	 */
	@Override
	public List<Tprovince> getAllSimple() {
		return this.mapperProvince.getAllSimple();
	}

	@Override
	public Tprovince getByIdSimple(Integer id) {
		
		return Optional.ofNullable(id).map(this.mapperProvince::getByIdSimple).orElse(null);
	}

}
