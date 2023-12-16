package com.example.service.speciality;

import com.example.dto.speciality.speciality.SpecialityGetDto;
import com.example.dto.speciality.speciality.SpecialitySaveDto;
import com.example.dto.speciality.speciality.SpecialityUpdateDto;
import com.example.dtomapper.speciality.DtoSpecialityMapper;
import com.example.exception.NoDataFoundException;
import com.example.exception.speciality.speciality.SpecialityNotSaveException;
import com.example.exception.speciality.speciality.SpecialityNotUpdateException;
import com.example.mapper.speciality.MapperSpeciality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServiceSpecialityImpl implements IServiceSpeciality<SpecialityGetDto,Integer, SpecialityUpdateDto, SpecialitySaveDto>{


	private final MapperSpeciality mapperSpeciality;
	private final DtoSpecialityMapper dtoSpecialityMapper;

	/**
	 * Obtiene una lista de todas los tipos de especialidades disponibles.
	 *
	 * @return Una lista de objetos SpecialityGetDto que representan las especialidades.
	 * @throws NoDataFoundException si no se encuentran datos de especialidades o si la lista resultante está vacía.
	 */
	@Transactional(readOnly = true)
	@Override
	public List<SpecialityGetDto> getAll() {

		List<SpecialityGetDto> specialityList = Optional.ofNullable(this.mapperSpeciality.getAll())
				.orElseThrow(()-> new NoDataFoundException("DATA NOT FOUND"))
				.stream()
				.map(this.dtoSpecialityMapper::TspecialityToSpecialityGet)
				.toList();

		if(specialityList.isEmpty()){
			throw new NoDataFoundException("DATA IS EMPTY");
		}

		return specialityList;
	}

	/**
	 * Obtiene una especialidad por su ID.
	 *
	 * @param id El ID de la especialidad a recuperar.
	 * @return Un objeto SpecialityGetDto que representa la especialidad correspondiente al ID proporcionado.
	 * @throws NoDataFoundException si no se encuentra la especialidad o si el ID no es válido (nulo o menor o igual a cero).
	 */
	@Transactional(readOnly = true)
	@Override
	public SpecialityGetDto getById(Integer id) {

		if(id==null || id<=0){
			throw new NoDataFoundException("ID NO VALID");
		}

        return Optional.of(id)
				.map(this.mapperSpeciality::getById)
				.map(this.dtoSpecialityMapper::TspecialityToSpecialityGet)
				.orElseThrow(()-> new NoDataFoundException("DATA NOT FOUND"));
	}

	/**
	 * Actualiza una especialidad existente en la base de datos con la información proporcionada.
	 *
	 * @param updateDto La información actualizada de la especialidad.
	 * @return El número de filas afectadas al actualizar la especialidad en la base de datos.
	 * @throws SpecialityNotUpdateException si no se puede actualizar la especialidad o si los datos de actualización no son válidos.
	 */
	@Override
	public Integer update(SpecialityUpdateDto updateDto) {

		if(updateDto==null || updateDto.id()==null || updateDto.id()<=0 || updateDto.name() == null || updateDto.name().trim().isEmpty()){
			throw new SpecialityNotUpdateException("DATA NOT VALID",updateDto);
		}

		Integer rowAffected = Optional.of(updateDto)
				.map(this.dtoSpecialityMapper::SpecialityUpdateDtoToTspeciality)
				.map(this.mapperSpeciality::update)
				.orElseThrow(()-> new SpecialityNotUpdateException(updateDto));

		if(rowAffected==null || rowAffected<=0){
			throw  new SpecialityNotUpdateException(updateDto);
		}

		return rowAffected;
	}

	/**
	 * Guarda una nueva especialidad en la base de datos con la información proporcionada.
	 *
	 * @param specialitySave La información de la especialidad a guardar.
	 * @return El número de filas afectadas al guardar la especialidad en la base de datos.
	 * @throws SpecialityNotSaveException si no se puede guardar la especialidad o si los datos de la especialidad no son válidos.
	 */
	@Override
	public Integer save(SpecialitySaveDto specialitySave) {
		if(specialitySave == null || specialitySave.name()==null || specialitySave.name().trim().isEmpty()){
			throw new SpecialityNotSaveException(specialitySave,"DATA NOT VALID");
		}

		Integer rowSave = Optional.of(specialitySave)
				.map(this.dtoSpecialityMapper::SpecialitySaveDtoToTspeciality)
				.map(this.mapperSpeciality::save)
				.orElseThrow(()-> new SpecialityNotSaveException(specialitySave));

		if(rowSave==null || rowSave <= 0){
			throw new SpecialityNotSaveException(specialitySave,"DATA NOT SAVED");
		}

		return rowSave;
	}
}
