package com.example.service.user;

import java.util.List;
import java.util.Optional;

import com.example.dto.user.type_user_reg.TypeUserOfUserRegGetDto;
import com.example.dto.user.type_user_reg.UserRegOfTypeUserGetDto;
import com.example.dto.user.type_user_reg.UserTypeRegGetDto;
import com.example.dto.user.type_user_reg.UserTypeRegUpdateDto;
import com.example.dtomapper.user.DtoUserTypeRegMapper;
import com.example.entity.user.TuserTypeReg;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.type_user_reg.UserTypeRegNotUpdateException;
import com.example.mapper.user.MapperUserTypeReg;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceUserTypeRegImpl
		implements IServiceUserTypeReg<UserTypeRegGetDto, Long, UserTypeRegUpdateDto, TuserTypeReg> {

	private final MapperUserTypeReg mapperUserTypeReg;
	private final DtoUserTypeRegMapper dtoMapperUserTypeReg;

	@Override
	public List<UserTypeRegGetDto> getAll() {

		List<UserTypeRegGetDto> userTypeRegGetDtos = Optional.ofNullable(this.mapperUserTypeReg.getAll())
				.orElseThrow(() -> new NoDataFoundException("Data is Empty"))
				.stream()
				.map(this.dtoMapperUserTypeReg::tuserTypeRegToUserTypeRegDto)
				.toList();

		if (userTypeRegGetDtos.isEmpty()) {
			throw new NoDataFoundException("Data is Empty");
		}

		return userTypeRegGetDtos;
	}

	@Override
	public UserTypeRegGetDto getById(Long id) {
		this.isValitedId(id);

		return Optional.of(id).map(this.mapperUserTypeReg::getById)
				.map(this.dtoMapperUserTypeReg::tuserTypeRegToUserTypeRegDto)
				.orElseThrow(() -> new NoDataFoundException(id));
	}

	@Override
	public Integer update(UserTypeRegUpdateDto userTypeRegUpdate) {

		if(userTypeRegUpdate==null){
			throw new UserTypeRegNotUpdateException(userTypeRegUpdate);
		}

		Integer rowAffected = Optional.of(userTypeRegUpdate)
			.map(this.dtoMapperUserTypeReg::userTypeRegUpdateToTuserTypeReg)
			.map(this.mapperUserTypeReg::update)
			.orElseThrow(() -> new UserTypeRegNotUpdateException(userTypeRegUpdate));

		if(rowAffected ==null ||rowAffected==0){
			throw new UserTypeRegNotUpdateException("Datos no encontrados para actualizar", userTypeRegUpdate);
		}

		return rowAffected;
	}

	@Override
	public Integer save(TuserTypeReg tuserTypeReg) {

		if (tuserTypeReg == null) {
			return 0;
		}
		return this.mapperUserTypeReg.save(tuserTypeReg);
	}

	@Override
	public List<TypeUserOfUserRegGetDto> getByIdUserReg(Long id) {

		this.isValitedId(id);

		List<TypeUserOfUserRegGetDto> typeUserOfUserRegGetDtos = Optional.of(id)
				.map(this.mapperUserTypeReg::getByIdUserReg)
				.orElseThrow(() -> new NoDataFoundException("Data Not Found"))
				.stream()
				.map(this.dtoMapperUserTypeReg::tuserTypeRegToTypeUserOfUserRegGet)
				.toList();

		if (typeUserOfUserRegGetDtos.isEmpty()) {
			throw new NoDataFoundException("Data is Empty");
		}

		return typeUserOfUserRegGetDtos;
	}

	@Override
	public List<TypeUserOfUserRegGetDto> getByIdUserRegActivated(Long id) {

		this.isValitedId(id);

		List<TypeUserOfUserRegGetDto> typeUserOfUserRegGetDtos = Optional.of(id)
				.map(this.mapperUserTypeReg::getByIdUserRegActivated)
				.orElseThrow(() -> new NoDataFoundException("Data Not Found"))
				.stream()
				.map(this.dtoMapperUserTypeReg::tuserTypeRegToTypeUserOfUserRegGet)
				.toList();

		if (typeUserOfUserRegGetDtos.isEmpty()) {
			throw new NoDataFoundException("Data is Empty");
		}

		return typeUserOfUserRegGetDtos;
	}

	@Override
	public List<UserRegOfTypeUserGetDto> getByIdTypeUser(Integer id) {

		this.isValitedId(id);

		List<UserRegOfTypeUserGetDto> userRegOfTypeUserGetDtos = Optional.of(id)
				.map(this.mapperUserTypeReg::getByIdTypeUser)
				.orElseThrow(() -> new NoDataFoundException("Data Not Found"))
				.stream()
				.map(this.dtoMapperUserTypeReg::tuserTypeRegToUserRegOfTypeUserGet)
				.toList();

		if (userRegOfTypeUserGetDtos.isEmpty()) {
			throw new NoDataFoundException("Data is Empty");
		}

		return userRegOfTypeUserGetDtos;
	}

	@Override
	public List<UserTypeRegGetDto> getByIdStatus(Integer id) {
		this.isValitedId(id);

		List<UserTypeRegGetDto> userTypeRegGetDtos = Optional.of(id)
				.map(this.mapperUserTypeReg::getByIdStatus)
				.orElseThrow(() -> new NoDataFoundException("Data Not Valid"))
				.stream()
				.map(this.dtoMapperUserTypeReg::tuserTypeRegToUserTypeRegDto)
				.toList();

		if (userTypeRegGetDtos.isEmpty()) {
			throw new NoDataFoundException("Data is Empty");
		}

		return userTypeRegGetDtos;
	}

	private void isValitedId(Long value) {
		if (value == null || value <= 0) {
			throw new NoDataFoundException(value);
		}
	}

	private void isValitedId(Integer value) {
		if (value == null || value <= 0) {
			throw new NoDataFoundException(value);
		}
	}

}
