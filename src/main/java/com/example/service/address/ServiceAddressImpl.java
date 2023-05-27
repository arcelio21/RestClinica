package com.example.service.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressRequestDto;
import com.example.dtomapper.address.AddressMappper;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.address.AddressNotUpdateException;
import com.example.exception.address.province.ProvinceNotSaveException;
import com.example.exception.address.province.ProvinceNotUpdateException;
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

		Optional<List<Taddress>> optionalTaddresses = Optional.ofNullable(this.mapper.getAll());

		if(optionalTaddresses.isPresent() && !optionalTaddresses.get().isEmpty()){
			return optionalTaddresses
					.get()
					.stream()
					.map(this.addressMappper::taddressToAddressGetDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException();
	}

	@Override
	public AddressGetDto getById(Integer id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		return Optional.of(id)
				.map(this.mapper::getById)
				.map(this.addressMappper::taddressToAddressGetDto)
				.orElseThrow(()-> new NoDataFoundException(id));
		
	}

	@Override
	public Integer update(AddressRequestDto addressRequestDto) {
		
		if(addressRequestDto ==null || addressRequestDto.getId()==null || addressRequestDto.getId()<=0
				|| addressRequestDto.getVillageId()==null || addressRequestDto.getVillageId()<=0) {

			throw new AddressNotUpdateException("Fallo en actualizacion",addressRequestDto);
		}

		return Optional.of(addressRequestDto)
				.map(this.addressMappper::AddressRequestDtoToTaddress)
				.map(this.mapper::update)
				.orElseThrow(()-> new ProvinceNotUpdateException("Fallo en actualizacion", addressRequestDto));

	}

	@Override
	public Integer save(AddressRequestDto addressRequestDto) {

		if(addressRequestDto ==null
				|| addressRequestDto.getId()==null || addressRequestDto.getId()<=0
				|| addressRequestDto.getVillageId()==null || addressRequestDto.getVillageId()<=0
				|| addressRequestDto.getSpecificAddress()==null) {

			throw new AddressNotSaveException("Fallo al guardar", addressRequestDto);
		}

		return Optional.of(addressRequestDto)
				.map(this.addressMappper::AddressRequestDtoToTaddress)
				.map(this.mapper::save)
				.orElseThrow(()-> new ProvinceNotSaveException("Fallo al guardar", addressRequestDto));
	}
}
