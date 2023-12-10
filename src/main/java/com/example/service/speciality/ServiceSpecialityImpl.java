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

	@Override
	public SpecialityGetDto getById(Integer integer) {
		return null;
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
