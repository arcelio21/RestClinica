package com.example.service.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.dto.user.type_user_reg.UserTypeRegGetDto;
import com.example.dtomapper.user.DtoUserTypeRegMapper;
import com.example.entity.user.TuserTypeReg;
import com.example.exception.NoDataFoundException;
import com.example.mapper.user.MapperUserTypeReg;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceUserTypeRegImpl implements IServiceUserTypeReg<UserTypeRegGetDto, Long,TuserTypeReg,TuserTypeReg> {

	private final MapperUserTypeReg mapperUserTypeReg;
	private final DtoUserTypeRegMapper dtoMapperUserTypeReg;

	@Override
	public List<UserTypeRegGetDto> getAll() {
		
		List<UserTypeRegGetDto> userTypeRegGetDtos = Optional.ofNullable(this.mapperUserTypeReg.getAll())
		.orElseThrow(()-> new NoDataFoundException("Data is Empty"))
		.stream()
		.map(this.dtoMapperUserTypeReg::tuserTypeRegToUserTypeRegDto)
		.toList();

		if(userTypeRegGetDtos.isEmpty()){
			throw new NoDataFoundException("Data is Empty");
		}

		return userTypeRegGetDtos;
	}

	@Override
	public UserTypeRegGetDto getById(Long id) {
		this.isValitedId(id);

		return Optional.of(id).
		map(this.mapperUserTypeReg::getById)
		.map(this.dtoMapperUserTypeReg::tuserTypeRegToUserTypeRegDto)
		.orElseThrow(()-> new NoDataFoundException(id));
	}

	@Override
	public Integer update(TuserTypeReg tuserTypeReg) {

		if(tuserTypeReg==null || tuserTypeReg.getId()==null || tuserTypeReg.getId()<=0){
			return 0;
		}
		return this.mapperUserTypeReg.update(tuserTypeReg);     
	}

	@Override
	public Integer save(TuserTypeReg tuserTypeReg) {

		if(tuserTypeReg==null){
			return 0;
		}
		return this.mapperUserTypeReg.save(tuserTypeReg);
	}

	@Override
	public List<TuserTypeReg> getByIdUserReg(Integer id) {

		if(id==null || id<=0){
			return Collections.emptyList();
		}
		return this.mapperUserTypeReg.getByIdUserReg(id);
	}

	@Override
	public List<TuserTypeReg> getByIdTypeUser(Integer id) {
		
		if(id==null || id<=0){
			return Collections.emptyList();
		}
		return this.mapperUserTypeReg.getByIdTypeUser(id);
	}

	@Override
	public List<TuserTypeReg> getByIdStatus(Integer id) {

		if(id==null || id<=0){
			return Collections.emptyList();
		}
		return this.mapperUserTypeReg.getByIdStatus(id);
	}

	private void isValitedId (Long value){
		if(value==null || value<=0){
			throw new NoDataFoundException(value);
		}
	}

	private void isValitedId (Integer value){
		if(value==null || value<=0){
			throw new NoDataFoundException(value);
		}
	}

}
