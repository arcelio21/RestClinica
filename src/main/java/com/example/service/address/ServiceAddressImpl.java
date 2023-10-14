package com.example.service.address;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressSaveDto;
import com.example.dto.address.AddressUpdatetDto;
import com.example.dtomapper.address.DtoAddressMappper;
import com.example.entity.address.Taddress;
import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.entity.address.Tvillage;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.address.AddressNotUpdateException;
import com.example.exception.address.province.ProvinceNotSaveException;
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
	private final DtoAddressMappper dtoAddressMappper;


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
					.map(this.dtoAddressMappper::taddressToAddressGetDto)
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

		this.validId(id);

		return Optional.of(id)
				.map(this.mapper::getById)
				.map(this.dtoAddressMappper::taddressToAddressGetDto)
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
	public Integer update(AddressUpdatetDto addressUpdate) {
		
		if(addressUpdate ==null || addressUpdate.getId()==null || addressUpdate.getId()<=0
				|| addressUpdate.getVillageId()==null || addressUpdate.getVillageId()<=0) {

			throw new AddressNotUpdateException("Fallo en actualizacion",addressUpdate);
		}

		return Optional.of(addressUpdate)
				.map(this.dtoAddressMappper::AddressUpdateDtoToTaddress)
				.map(this.mapper::update)
				.orElseThrow(()-> new AddressNotUpdateException("Fallo en actualizacion", addressUpdate));

	}


	/**
	 * Guarda un objeto AddressRequestDto en el sistema.
	 * @param addressRequestDto El objeto AddressRequestDto a ser guardado.
	 * @return La cantidad de registros guardados.
	 * @throws AddressNotSaveException Si los datos no son válidos o no se puede guardar la dirección.
	 */
	@Override
	public Integer save(AddressSaveDto addressSave) {

		if(addressSave ==null
				|| addressSave.villageId()==null || addressSave.villageId()<=0
				|| addressSave.specificAddress()==null || addressSave.specificAddress().trim().isEmpty()) {

			throw new AddressNotSaveException("Fallo al guardar", addressSave);
		}

		return Optional.of(addressSave)
				.map(this.dtoAddressMappper::AddressSaveDtoToTaddress)
				.map(this.mapper::save)
				.map(addres -> (addres>0)?1:0) //PORQUE EL MAPPER DEVUELVE LONG Y HAY QUE DEVOLVER INTERGER
				.orElseThrow(()-> new ProvinceNotSaveException("Fallo al guardar", addressSave));
	}

	/**
	 * Obtiene todas las direcciones asociadas a un pueblo (Village) dado su ID.
	 *
	 * @param idVillage ID del pueblo para el cual se obtendrán las direcciones
	 * @return Lista de objetos AddressGetDto que representan las direcciones asociadas al pueblo
	 * @throws NoDataFoundException si no se encuentran direcciones para el pueblo especificado
	 */
	@Override
	public List<AddressGetDto> getAddressByVillage(Integer idVillage) {

		this.validId(idVillage);

		Optional<List<Taddress>> optionalTaddresses = Optional.of(new Tvillage(idVillage))
														.map(this.mapper::getAddressByVillage);

		if(optionalTaddresses.isPresent() && !optionalTaddresses.get().isEmpty()){
			return optionalTaddresses
					.get()
					.stream()
					.map(this.dtoAddressMappper::taddressToAddressGetDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException(idVillage);
	}


	/**
	 * Obtiene todas las direcciones asociadas a un distrito (District) dado su ID.
	 *
	 * @param idDistrict ID del distrito para el cual se obtendrán las direcciones
	 * @return Lista de objetos AddressGetDto que representan las direcciones asociadas al distrito
	 * @throws NoDataFoundException si no se encuentran direcciones para el distrito especificado
	 */
	@Override
	public List<AddressGetDto> getAddressByDistrict(Integer idDistrict) {

		this.validId(idDistrict);

		Optional<List<Taddress>> optionalTaddresses = Optional.of(new Tdistrict(idDistrict))
				.map(this.mapper::getAddressByDistrict);

		if(optionalTaddresses.isPresent() && !optionalTaddresses.get().isEmpty()){
			return optionalTaddresses
					.get()
					.stream()
					.map(this.dtoAddressMappper::taddressToAddressGetDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException(idDistrict);
	}

	/**
	 * Obtiene todas las direcciones asociadas a una provincia (Province) dada su ID.
	 *
	 * @param idProvince ID de la provincia para la cual se obtendrán las direcciones
	 * @return Lista de objetos AddressGetDto que representan las direcciones asociadas a la provincia
	 * @throws NoDataFoundException si no se encuentran direcciones para la provincia especificada
	 */
	@Override
	public List<AddressGetDto> getAddressByProvince(Integer idProvince) {

		this.validId(idProvince);

		Optional<List<Taddress>> optionalTaddresses = Optional.of(new Tprovince(idProvince))
				.map(this.mapper::getAddressByProvince);

		if(optionalTaddresses.isPresent() && !optionalTaddresses.get().isEmpty()){
			return optionalTaddresses
					.get()
					.stream()
					.map(this.dtoAddressMappper::taddressToAddressGetDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException(idProvince);
	}

	/**
	 * Valida si el ID proporcionado es válido.
	 *
	 * @param id ID a validar
	 * @throws NoDataFoundException si el ID es nulo o no
	 */
	private void validId(Integer id){
		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}
	}
}
