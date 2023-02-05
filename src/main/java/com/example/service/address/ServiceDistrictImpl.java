package com.example.service.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.dto.address.district.DistrictAllDto;
import com.example.dto.address.district.DistrictDto;
import com.example.dtomapper.address.DistrictMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.mapper.address.MapperDistrict;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class ServiceDistrictImpl implements IServiceDistrict<DistrictDto, Integer>{
	
	private final MapperDistrict mapperDistrict;
	private  final DistrictMapper districtMapper;

	
	@Override
	public List<DistrictDto> getAll() {
		
		List<Tdistrict> districts=this.mapperDistrict.getAll();
		if(districts.isEmpty()) {
			return Collections.emptyList();
		}

		List<DistrictDto> districtDtos = new ArrayList<>();

		for (Tdistrict tdistrict:
			 districts) {
			districtDtos.add(this.districtMapper.tdistrictToDistrictDto(tdistrict));
		}

		return districtDtos;
	}

	@Override
	public DistrictDto getById(Integer id) {
		return Optional.ofNullable(id).map(mapperDistrict::getById).map(districtMapper::tdistrictToDistrictDto).orElse(null);
	}

	@Override
	public Integer update(DistrictDto districtDto) {

		if (districtDto == null || districtDto.getId()==null || districtDto.getName() == null
				|| districtDto.getId()<=0 || districtDto.getName().isEmpty()) {
			return 0;
		}

		Tdistrict tdistrict= this.districtMapper.districtDtoToTdistrict(districtDto);


		return this.mapperDistrict.update(tdistrict);
	}

	@Override
	public Integer save(DistrictDto districtDto) {
		if (districtDto == null || districtDto.getName() == null || districtDto.getName().isEmpty()) {
			return 0;
		}

		Tdistrict tdistrict = this.districtMapper.districtDtoToTdistrict(districtDto);
		return this.mapperDistrict.save(tdistrict);
	}


	@Override
	public DistrictDto getByIdName(Integer id) {
		return Optional.ofNullable(id)
				.map(mapperDistrict::getByIdName)
				.map(districtMapper::tdistrictToDistrictDto)
				.orElse(null);
	}

	@Override
	public List<DistrictDto> getAllIdName() {
		List<Tdistrict> districts=this.mapperDistrict.getAllIdName();
		if(districts.isEmpty()) {
			return Collections.emptyList();
		}

		List<DistrictDto> districtAllDtos= new ArrayList<>();

		for (Tdistrict tdistrict:
				districts) {
			districtAllDtos.add(this.districtMapper.tdistrictToDistrictDto(tdistrict));
		}

		return districtAllDtos;
	}


	@Override
	public List<DistrictDto> getByProvinceId(Integer id) {
		if(id==null || id<=0){
			return  Collections.emptyList();
		}

		List<Tdistrict> tdistricts = this.mapperDistrict.getByProvinceId(new Tprovince(id));

		if(tdistricts.isEmpty()){
			return  Collections.emptyList();
		}
		List<DistrictDto> districtDtos = new ArrayList<>();
		for(Tdistrict tdistrict:tdistricts ){
			districtDtos.add(this.districtMapper.tdistrictToDistrictDto(tdistrict));
		}
		return districtDtos;
	}

	@Override
	public DistrictAllDto getDistrictAndProvinceById(Integer id) {

		return Optional.ofNullable(id)
				.map(mapperDistrict::getDistrictAndProvinceById)
				.map(districtMapper::tdistrictToDistrictAll)
				.orElse(null);
	}

}
