package com.example.service.address;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.dto.address.province.ProvinceDto;
import com.example.dtomapper.address.ProvinceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.entity.address.Tprovince;
import com.example.mapper.address.MapperProvince;

@RequiredArgsConstructor
@Service
public class ServiceProvinceImpl implements IServiceProvince<ProvinceDto, Integer> {


	private final MapperProvince mapperProvince;
	private final ProvinceMapper provinceMapper;
	

	/**
     * SE EJECUTARA UN QUERY QUE TRAERA LAS PROVINCIAS, CON
     * LOS DISTRITOS ASOCIADO A CADA PROVINCIA Y LOS CORREGIMIENTOS ASOCIADO A CADA DISTRITOS
     */
	@Override
	public List<ProvinceDto> getAll() {
		List<Tprovince> tprovinces = this.mapperProvince.getAll();
		if(tprovinces==null || tprovinces.isEmpty()){
			return Collections.emptyList();
		}
		List<ProvinceDto> provinceDtos = new ArrayList<>();
		for (Tprovince province:
			 tprovinces) {
			provinceDtos.add(this.provinceMapper.tprovinceToProvinceDto(province));
		}
		return provinceDtos;
	}

	/**
	 * SE OBTENDRA UN PROVINCIA POR ID 
	 */
	@Override
	public ProvinceDto getById(Integer id) {
		if(id==null || id==0) {
			return null;
		}
		return this.provinceMapper.tprovinceToProvinceDto(this.mapperProvince.getById(id));
	}

	@Override
	public Integer update(ProvinceDto provinceDto) {
		
		if(provinceDto==null || provinceDto.getId()==null || provinceDto.getId()==0) {
			return 0;
		}

		Tprovince tprovince = this.provinceMapper.provinceDtoToTprovince(provinceDto);
		
		return this.mapperProvince.update(tprovince);
	}

	@Override
	public Integer save(ProvinceDto provinceDto) {

		if(provinceDto==null || provinceDto.getNombre()==null || provinceDto.getNombre().isEmpty()){
			return 0;
		}

		Tprovince tprovince =this.provinceMapper.provinceDtoToTprovince(provinceDto);
		return this.mapperProvince.save(tprovince);
	}

}
