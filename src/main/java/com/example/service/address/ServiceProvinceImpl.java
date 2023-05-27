package com.example.service.address;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dto.address.province.ProvinceDto;
import com.example.dtomapper.address.ProvinceMapper;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.province.ProvinceNotSaveException;
import com.example.exception.address.province.ProvinceNotUpdateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.entity.address.Tprovince;
import com.example.mapper.address.MapperProvince;

@RequiredArgsConstructor
@Service
public class ServiceProvinceImpl implements IServiceProvince<ProvinceDto, Integer> {


	private final MapperProvince mapperProvince;
	private final ProvinceMapper provinceMapper;
	

	@Override
	public List<ProvinceDto> getAll() {

		Optional<List<Tprovince>> optionalTprovinces = Optional.ofNullable(this.mapperProvince.getAll());

		if(optionalTprovinces.isPresent() && !optionalTprovinces.get().isEmpty()){
			return optionalTprovinces
					.get()
					.stream()
					.map(this.provinceMapper::tprovinceToProvinceDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException();
	}


	@Override
	public ProvinceDto getById(Integer id) {
		if(id==null || id==0) {
			throw new NoDataFoundException(id);
		}
		return Optional.of(id)
				.map(this.mapperProvince::getById)
				.map(this.provinceMapper::tprovinceToProvinceDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

	@Override
	public Integer update(ProvinceDto provinceDto) {
		
		if(provinceDto==null || provinceDto.getId()==null || provinceDto.getId()==0
			|| provinceDto.getName()==null || provinceDto.getName().trim().isEmpty()
			) {

			throw new ProvinceNotUpdateException("Fallo de actualizacion de provincia",provinceDto);
		}

		return Optional.of(provinceDto)
				.map(this.provinceMapper::provinceDtoToTprovince)
				.map(this.mapperProvince::update)
				.orElseThrow(()-> new ProvinceNotUpdateException("Fallo de actualizacion de provincia",provinceDto));
	}

	@Override
	public Integer save(ProvinceDto provinceDto) {

		if(provinceDto==null || provinceDto.getName()==null || provinceDto.getName().trim().isEmpty()){
			throw new ProvinceNotSaveException("Fallo al guardar province",provinceDto);
		}

		return Optional.of(provinceDto)
				.map(this.provinceMapper::provinceDtoToTprovince)
				.map(this.mapperProvince::save)
				.orElseThrow(()-> new ProvinceNotSaveException("Fallo al guardar province",provinceDto));
	}

}
