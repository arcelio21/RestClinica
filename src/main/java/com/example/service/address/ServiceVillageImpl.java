package com.example.service.address;

import com.example.dto.address.village.VillageDistrictDto;
import com.example.dto.address.village.VillageDto;
import com.example.dtomapper.address.VillageMapper;
import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tvillage;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.village.VillageNotSaveException;
import com.example.exception.address.village.VillageNotUpdateException;
import com.example.mapper.address.MapperVillage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz IServiceVillage que proporciona métodos para operaciones relacionadas con Villages.
 */
@RequiredArgsConstructor
@Service
public class ServiceVillageImpl implements IServiceVillage<VillageDto, Integer>{

	
	private final MapperVillage mapperVillage;
	private final VillageMapper villageMapper;

	/**
	 * Obtiene una lista de todos los pueblos disponibles.
	 *
	 * @return Una lista de objetos VillageDto que representan los pueblos disponibles.
	 * @throws NoDataFoundException Si no se encuentran datos de pueblos disponibles.
	 */
	@Override
	public List<VillageDto> getAll() {
		
		Optional<List<Tvillage>> villagesList = Optional.ofNullable(this.mapperVillage.getAll());

		if(villagesList.isPresent() && !villagesList.get().isEmpty()){

		  return villagesList.get()
			.stream()
			.map(this.villageMapper::tvillageToVillageDto)
			.collect(Collectors.toList());
		}

		throw new NoDataFoundException();
	}


	/**
	 * Obtiene un objeto VillageDto por su identificador.
	 *
	 * @param id El identificador del pueblo.
	 * @return Un objeto VillageDto que representa el pueblo encontrado.
	 * @throws NoDataFoundException Si no se encuentra ningún pueblo para el identificador especificado.
	 */
	@Override
	public VillageDto getById(Integer id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}
		return Optional.of(id)
				.map(this.mapperVillage::getById)
				.map(this.villageMapper::tvillageToVillageDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

	/**
	 * Actualiza un pueblo existente con la información proporcionada en el objeto VillageDto.
	 *
	 * @param villageDto El objeto VillageDto con la información del pueblo a actualizar.
	 * @return El identificador del pueblo actualizado.
	 * @throws VillageNotUpdateException Si los datos del pueblo no son válidos para la actualización.
	 */
	@Override
	public Integer update(VillageDto villageDto) {

		if(villageDto==null || villageDto.getId()==null || villageDto.getId()<=0
			|| villageDto.getName()==null || villageDto.getName().trim().isEmpty()
			|| villageDto.getDistrictId()==null || villageDto.getDistrictId()<=0){

			throw new VillageNotUpdateException("Error de actualizacion",villageDto);

		}

		return Optional.of(villageDto)
				.map(this.villageMapper::villageDtoToTvillage)
				.map(this.mapperVillage::update)
				.orElseThrow(()-> new VillageNotUpdateException("Error de actualizacion", villageDto));
	}

	/**
	 * Guarda un objeto VillageDto en el sistema.
	 *
	 * @param villageDto El objeto VillageDto a ser guardado.
	 * @return La cantidad de registros guardados.
	 * @throws VillageNotSaveException Si los datos no son válidos o no se puede guardar el pueblo.
	 */
	@Override
	public Integer save(VillageDto villageDto) {

		if(villageDto==null
				|| villageDto.getName()==null || villageDto.getName().trim().isEmpty()
				|| villageDto.getDistrictId()==null || villageDto.getDistrictId()<=0){

			throw new VillageNotSaveException("Error de guardado",villageDto);

		}

		return Optional.of(villageDto)
				.map(this.villageMapper::villageDtoToTvillage)
				.map(this.mapperVillage::save)
				.orElseThrow(()-> new VillageNotSaveException("Error de guardado",villageDto));
	}

	/**
	 * Obtiene una lista de VillageDto por el identificador del distrito.
	 *
	 * @param id El identificador del distrito.
	 * @return Una lista de VillageDto que pertenecen al distrito especificado.
	 * @throws NoDataFoundException Si no se encuentra ningún pueblo para el identificador del distrito.
	 */
	@Override
	public List<VillageDto> getByDistrictId(Integer id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		Optional<List<Tvillage>> optionalTvillages= Optional.ofNullable(this.mapperVillage.getByDistrictId(new Tdistrict(id)));

		if(optionalTvillages.isPresent() && !optionalTvillages.get().isEmpty()){
			return optionalTvillages.get()
					.stream()
					.map(this.villageMapper::tvillageToVillageDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException(id);
	}

	/**
	 * Obtiene un objeto VillageDistrictDto por su identificador.Este tiene la diferencia que viene los datos de village y district
	 *
	 * @param id El identificador del pueblo.
	 * @return Un objeto VillageDistrictDto que contiene información del pueblo y su distrito.
	 * @throws NoDataFoundException Si no se encuentra ningún pueblo para el identificador especificado.
	 */
	@Override
	public VillageDistrictDto getDistrictAllById(Integer id) {

		if(id==null || id<=0){
			throw  new NoDataFoundException(id);
		}

		return Optional.of(id)
				.map(this.mapperVillage::getDistrictAllById)
				.map(this.villageMapper::tvillageToVillageDistritcDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

	/**
	 * Obtiene una lista de VillageDto que contiene únicamente el id y el nombre de todos los pueblos disponibles.
	 *
	 * @return Una lista de VillageDto con el id y el nombre de cada pueblo.
	 * @throws NoDataFoundException Si no se encuentran datos disponibles.
	 */
	@Override
	public List<VillageDto> getAllIdName() {

		Optional<List<Tvillage>> optionalTvillageList = Optional.ofNullable(this.mapperVillage.getAllIdName());

		if(optionalTvillageList.isPresent() && !optionalTvillageList.get().isEmpty()){
			return  optionalTvillageList
					.get()
					.stream()
					.map(this.villageMapper::tvillageToVillageDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException();
	}

	/**
	 * Obtiene un objeto VillageDto por su identificador, que contiene únicamente el id y el nombre del pueblo.
	 *
	 * @param id El identificador del pueblo.
	 * @return Un objeto VillageDto con el id y el nombre del pueblo encontrado.
	 * @throws NoDataFoundException Si no se encuentra ningún pueblo para el identificador especificado.
	 */
	@Override
	public VillageDto getByIdName(Integer id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		return Optional.of(id).map(this.mapperVillage::getByIdName)
				.map(this.villageMapper::tvillageToVillageDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

}
