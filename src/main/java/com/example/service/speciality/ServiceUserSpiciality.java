package com.example.service.speciality;

import com.example.dto.speciality.userspeciality.*;
import com.example.dtomapper.speciality.DtoUserSpecialityMapper;
import com.example.mapper.speciality.MapperUserSpeciality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceUserSpiciality implements IServiceUserSpeciality<UserSpecialityGetDto,Integer, UserSpecialityUpdateDto, UserSpecialitySaveDto>{

	private final MapperUserSpeciality mapperUserSpeciality;
	private final DtoUserSpecialityMapper dtoUserSpecialityMapper;


	@Override
	public List<UserSpecialityGetDto> getAll() {
		return null;
	}

	@Override
	public UserSpecialityGetDto getById(Integer integer) {
		return null;
	}

	@Override
	public Integer update(UserSpecialityUpdateDto t) {
		return null;
	}

	@Override
	public Integer save(UserSpecialitySaveDto t) {
		return null;
	}

	@Override
	public List<UserSpecialityBySpecialityGetDto> getByIdSpeciality(Integer idSpeciality) {
		return null;
	}

	@Override
	public List<UserSpecialityByUserTypeRegGetDto> getByIdUserTypeReg(Integer idUserTypeReg) {
		return null;
	}

	@Override
	public List<UserSpecialityByStatusGetDto> getByIdStatus(Integer idStatus) {
		return null;
	}

	@Override
	public List<UserSpecialityByStatusGetDto> getByIdStatusActivated() {
		return null;
	}

	@Override
	public List<UserSpecialityByTypeUserGetDto> getByTypeUserId(Integer idTypeUser) {
		return null;
	}
}
