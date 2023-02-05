package com.example.service.address;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.village.VillageDistrictDto;
import com.example.dto.address.village.VillageDto;
import com.example.dtomapper.address.VillageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tvillage;
import com.example.mapper.address.MapperVillage;

@RequiredArgsConstructor
@Service
public class ServiceVillageImpl implements IServiceVillage<VillageDto, Integer>{

	
	private final MapperVillage mapperVillage;
	private final VillageMapper villageMapper;

	
	@Override
	public List<AddressGetDto> getAll() {
		
		List<Tvillage> villages=this.mapperVillage.getAll();
		if(villages==null || villages.isEmpty()) {
			return Collections.emptyList();
		}

		List<VillageDto> villageDtos = new ArrayList<>();
		for (Tvillage village :
			 villages) {
			villageDtos.add(this.villageMapper.tvillageToVillageDto(village));
		}
		return villageDtos;
	}

	@Override
	public VillageDto getById(Integer id) {
		return Optional
				.ofNullable(id)
				.map(this.mapperVillage::getById)
				.map(this.villageMapper::tvillageToVillageDto)
				.orElse(null);
	}

	@Override
	public Integer update(VillageDto t) {
		return Optional.ofNullable(t)
				.map(this.villageMapper::villageDtoToTvillage)
				.map(this.mapperVillage::update).orElse(0);
	}

	@Override
	public Integer save(VillageDto t) {
		return Optional.ofNullable(t)
				.map(this.villageMapper::villageDtoToTvillage)
				.map(this.mapperVillage::save).orElse(0);
	}

	@Override
	public List<VillageDto> getByDistrictId(Integer id) {

		if(id==null || id<=0){
			return Collections.emptyList();
		}

		List<Tvillage> tvillages = this.mapperVillage.getByDistrictId(new Tdistrict(id));

		if( tvillages==null ||tvillages.isEmpty()){
			return Collections.emptyList();
		}

		List<VillageDto> villageDistrictDtos = new ArrayList<>();

		for(Tvillage tvillage: tvillages){
			villageDistrictDtos.add(this.villageMapper.tvillageToVillageDto(tvillage));
		}
		return villageDistrictDtos;
	}

	@Override
	public VillageDistrictDto getDistrictAllById(Integer id) {
		return Optional.ofNullable(id).map(this.mapperVillage::getDistrictAllById)
				.map(this.villageMapper::tvillageToVillageDistritcDto)
				.orElse(null);
	}

	@Override
	public List<VillageDto> getAllIdName() {
		List<Tvillage> tvillages = this.mapperVillage.getAllIdName();

		if(tvillages.isEmpty()){
			return  Collections.emptyList();
		}

		List<VillageDto> villageDtos = new ArrayList<>();
		for(Tvillage tvillage:tvillages){
			villageDtos.add(this.villageMapper.tvillageToVillageDto(tvillage));
		}
		return villageDtos;
	}

	@Override
	public VillageDto getByIdName(Integer id) {
		return Optional.ofNullable(id).map(this.mapperVillage::getByIdName)
				.map(this.villageMapper::tvillageToVillageDto)
				.orElse(null);
	}

}
