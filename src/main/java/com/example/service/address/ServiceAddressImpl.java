package com.example.service.address;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressRequestDto;
import com.example.dtomapper.address.AddressMappper;
import com.example.entity.address.Taddress;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.address.AddressNotUpdateException;
import com.example.exception.address.province.ProvinceNotSaveException;
import com.example.exception.address.province.ProvinceNotUpdateException;
import com.example.mapper.address.MapperAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz IServiceAddress que proporciona métodos para operaciones relacionadas con direcciones.
 */
@RequiredArgsConstructor
@Service
public class ServiceAddressImpl implements IServiceAddress{

	private final MapperAddress mapper;
	private final AddressMappper addressMappper;


	/**
	 * Obtiene una lista de todas las direcciones disponibles.
	 *
	 * @return Una lista de objetos AddressGetDto que representan las direcciones disponibles.
	 * @throws NoDataFoundException Si no se encuentran datos de direcciones disponibles.
	 */
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

	/**
	 * Obtiene un objeto AddressGetDto por su identificador.
	 *
	 * @param id El identificador de la dirección.
	 * @return Un objeto AddressGetDto que representa la dirección encontrada.
	 * @throws NoDataFoundException Si no se encuentra ninguna dirección para el identificador especificado.
	 */
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

	/**
	 * Actualiza una dirección existente con la información proporcionada en el objeto AddressRequestDto.
	 *
	 * @param addressRequestDto El objeto AddressRequestDto con la información de la dirección a actualizar.
	 * @return El identificador de la dirección actualizada.
	 * @throws AddressNotUpdateException Si los datos de la dirección no son válidos para la actualización.
	 */
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

	/**
	 * Guarda un objeto AddressRequestDto en el sistema.
	 *
	 * @param addressRequestDto El objeto AddressRequestDto a ser guardado.
	 * @return La cantidad de registros guardados.
	 * @throws AddressNotSaveException Si los datos no son válidos o no se puede guardar la dirección.
	 */
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
