package com.example.service.speciality;

import com.example.dto.speciality.speciality.SpecialityGetDto;
import com.example.dto.speciality.speciality.SpecialitySaveDto;
import com.example.dto.speciality.speciality.SpecialityUpdateDto;
import com.example.dtomapper.speciality.DtoSpecialityMapper;
import com.example.exception.NoDataFoundException;
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

	@Override
	public Integer update(SpecialityUpdateDto t) {
		return null;
	}

	@Override
	public Integer save(SpecialitySaveDto t) {
		return null;
	}
}
