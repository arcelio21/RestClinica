package com.example.service.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressRequestDto;
import com.example.dtomapper.address.AddressMappper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.entity.address.Taddress;
import com.example.mapper.address.MapperAddress;

@RequiredArgsConstructor
@Service
public class ServiceAddressImpl implements IServiceAddress{

	private final MapperAddress mapper;
	private final AddressMappper addressMappper;
	

	@Override
	public List<AddressGetDto> getAll() {

		List<Taddress> taddresses= this.mapper.getAll();
		List<AddressGetDto> addressGetDtos = new ArrayList<>();

		for(Taddress taddress:taddresses){
			addressGetDtos.add(this.addressMappper.taddressToAddressGetDto(taddress));
		}

		return addressGetDtos;
	}

	@Override
	public AddressGetDto getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}

		return Optional.of(id)
				.map(this.mapper::getById)
				.map(this.addressMappper::taddressToAddressGetDto)
				.orElse(null);
		
	}

	@Override
	public Integer update(AddressRequestDto addressRequestDto) {
		
		if(addressRequestDto ==null || addressRequestDto.getId()==null || addressRequestDto.getId()<=0
				|| addressRequestDto.getVillageId()==null || addressRequestDto.getVillageId()<=0) {
			return 0;
		}
		Taddress taddress = this.addressMappper.AddressRequestDtoToTaddress(addressRequestDto);
		return this.mapper.update(taddress);
	}

	@Override
	public Integer save(AddressRequestDto addressRequestDto) {

		if(addressRequestDto ==null
				|| addressRequestDto.getVillageId()==null || addressRequestDto.getVillageId()<=0) {
			return 0;
		}
		Taddress taddress = this.addressMappper.AddressRequestDtoToTaddress(addressRequestDto);
		return this.mapper.save(taddress);
	}
}
