package com.example.service.address;

import com.example.dto.address.province.ProvinceDto;
import com.example.dtomapper.address.ProvinceMapper;
import com.example.entity.address.Tprovince;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.province.ProvinceNotSaveException;
import com.example.exception.address.province.ProvinceNotUpdateException;
import com.example.mapper.address.MapperProvince;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación de la interfaz IServiceProvince que proporciona métodos para operaciones relacionadas con provincias.
 */
@RequiredArgsConstructor
@Service
public class ServiceProvinceImpl implements IServiceProvince<ProvinceDto, Integer> {


	private final MapperProvince mapperProvince;
	private final ProvinceMapper provinceMapper;


	/**
	 * Obtiene una lista de todas las provincias disponibles.
	 *
	 * @return Una lista de objetos ProvinceDto que representan las provincias disponibles.
	 * @throws NoDataFoundException Si no se encuentran datos de provincias disponibles.
	 */
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


	/**
	 * Obtiene un objeto ProvinceDto por su identificador.
	 *
	 * @param id El identificador de la provincia.
	 * @return Un objeto ProvinceDto que representa la provincia encontrada.
	 * @throws NoDataFoundException Si no se encuentra ninguna provincia para el identificador especificado.
	 */
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

	/**
	 * Actualiza una provincia existente con la información proporcionada en el objeto ProvinceDto.
	 *
	 * @param provinceDto El objeto ProvinceDto con la información de la provincia a actualizar.
	 * @return El identificador de la provincia actualizada.
	 * @throws ProvinceNotUpdateException Si los datos de la provincia no son válidos para la actualización.
	 */
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

	/**
	 * Guarda un objeto ProvinceDto en el sistema.
	 *
	 * @param provinceDto El objeto ProvinceDto a ser guardado.
	 * @return La cantidad de registros guardados.
	 * @throws ProvinceNotSaveException Si los datos no son válidos o no se puede guardar la provincia.
	 */
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
