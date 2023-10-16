package com.example.service.address;

import com.example.dto.address.district.DistrictAllDto;
import com.example.dto.address.district.DistrictDto;
import com.example.dto.address.district.DistrictSaveDto;
import com.example.dto.address.district.DistrictUpdateDto;
import com.example.dtomapper.address.DtoDistrictMapper;
import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.district.DistrictNotSaveException;
import com.example.exception.address.district.DistrictNotUpdateException;
import com.example.mapper.address.MapperDistrict;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz IServiceDistrict para gestionar la lógica de los distritos.
 */
@RequiredArgsConstructor
@Service
public class ServiceDistrictImpl implements IServiceDistrict<DistrictDto, Integer, DistrictUpdateDto,DistrictSaveDto>{
	
	private final MapperDistrict mapperDistrict;
	private  final DtoDistrictMapper dtoMapper;


	/**
	 * OBTENER LISTA DE DISTRITO DISPONIBLE
	 * @return id, nombre y idProvincia asociado cada distrito
	 * @throws NoDataFoundException si no encuentra datos disponibles
	 */
	@Override
	public List<DistrictDto> getAll() {

		Optional<List<Tdistrict>> tdistricts = Optional.ofNullable(this.mapperDistrict.getAll());
		if(tdistricts.isPresent() && !tdistricts.get().isEmpty()) {
			return tdistricts.get()
					.stream()
					.map(this.dtoMapper::tdistrictToDistrictDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException();
	}

	/**
	 * OBTENER DISTRITO POR ID
	 * @param id
	 * @return devuelve id, nombre y provincia relaciona al distrito encontrado
	 * @throws NoDataFoundException si no encuentra datos disponibles
	 */
	@Override
	public DistrictDto getById(Integer id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		return Optional.of(id).map(mapperDistrict::getById).map(dtoMapper::tdistrictToDistrictDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

	/**
	 * Actualiza un distrito existente con la información proporcionada en el objeto DistrictDto.
	 *
	 * @param districtDto El objeto DistrictDto con la información del distrito a actualizar.
	 * @return El identificador del distrito actualizado.
	 * @throws DistrictNotUpdateException Si los datos del distrito no son válidos para la actualización.
	 */
	@Override
	public Integer update(DistrictUpdateDto districtDto) {

		if (districtDto == null || districtDto.id()==null || districtDto.name() == null
				|| districtDto.id()<=0 || districtDto.name().trim().isEmpty()
				|| districtDto.provinceId()==null || districtDto.provinceId()<=0) {
			throw new DistrictNotUpdateException("Datos de distrito no valido",districtDto);
		}

		return Optional.of(districtDto)
				.map(this.dtoMapper::districtUpdateDtoToTdistrict)
				.map(this.mapperDistrict::update)
				.orElseThrow(()-> new DistrictNotUpdateException("Datos de distrito no valido",districtDto));
	}

	/**
	 * Guarda un objeto DistrictDto en el sistema.
	 *
	 * @param districtDto El objeto DistrictDto a ser guardado.
	 * @return cantidad de registro guardados
	 * @throws DistrictNotSaveException Si los datos no son válidos o no se puede guardar el distrito.
	 */
	@Override
	public Integer save(DistrictSaveDto districtDto) {
		if (districtDto == null || districtDto.name() == null || districtDto.name().trim().isEmpty()
			|| districtDto.provinceId()==null || districtDto.provinceId()<=0) {
			throw new DistrictNotSaveException("Datos no Validos", districtDto);
		}

		return Optional.of(districtDto)
				.map(this.dtoMapper::districtSaveDtoToTdistrict)
				.map(this.mapperDistrict::save)
				.orElseThrow(()-> new DistrictNotSaveException("Datos no validos", districtDto));
	}

	/**
	 * OBTENER PROVINCIA POR ID
	 * @param id - id de distrito
	 * @return devolvera solo el id y nombre de distrito
	 */
	@Override
	public DistrictDto getByIdName(Integer id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		return Optional.of(id)
				.map(mapperDistrict::getByIdName)
				.map(dtoMapper::tdistrictToDistrictDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

	/**
	 * OBTENER LISTA DE DISTRITO DISPONIBLE
	 * @return solo id y nombre de distritos disponibles
	 */
	@Override
	public List<DistrictDto> getAllIdName() {

		Optional<List<Tdistrict>> optionalTdistricts = Optional.ofNullable(this.mapperDistrict.getAllIdName());

		if(optionalTdistricts.isPresent() && !optionalTdistricts.get().isEmpty()){

			return optionalTdistricts.get()
					.stream()
					.map(this.dtoMapper::tdistrictToDistrictDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException();
	}


	/**
	 * Obtiene una lista de DistrictDto por el identificador de la provincia.
	 *
	 * @param id El identificador de la provincia.
	 * @return Una lista de DistrictDto que pertenecen a la provincia especificada.
	 * @throws NoDataFoundException Si no se encuentra ningún distrito para el identificador de la provincia.
	 */
	@Override
	public List<DistrictDto> getByProvinceId(Integer id) {
		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		Optional<List<Tdistrict>> tdistricts = Optional.of(id)
				.map(Tprovince::new)
				.map(this.mapperDistrict::getByProvinceId);

		if(tdistricts.isPresent() && !tdistricts.get().isEmpty()){
			return tdistricts
					.get()
					.stream()
					.map(this.dtoMapper::tdistrictToDistrictDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException(id);
	}


	/**
	 * Obtiene un objeto DistrictAllDto por su identificador.
	 *
	 * @param id El identificador del distrito.
	 * @return Un objeto DistrictAllDto que contiene información del distrito y su provincia.
	 * @throws NoDataFoundException Si no se encuentra ningún distrito para el identificador especificado.
	 */
	@Override
	public DistrictAllDto getDistrictAndProvinceById(Integer id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		return Optional.of(id)
				.map(mapperDistrict::getDistrictAndProvinceById)
				.map(dtoMapper::tdistrictToDistrictAll)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

}
