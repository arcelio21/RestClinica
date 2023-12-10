package com.example.service.speciality;

import com.example.dto.speciality.speciality.SpecialityGetDto;
import com.example.dto.speciality.speciality.SpecialitySaveDto;
import com.example.dto.speciality.speciality.SpecialityUpdateDto;
import com.example.dtomapper.speciality.DtoSpecialityMapper;
import com.example.mapper.speciality.MapperSpeciality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ServiceSpecialityImpl implements IServiceSpeciality<SpecialityGetDto,Integer, SpecialityUpdateDto, SpecialitySaveDto>{


	private final MapperSpeciality mapperSpeciality;
	private final DtoSpecialityMapper dtoSpecialityMapper;
	@Override
	public List<SpecialityGetDto> getAll() {
		return null;
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
