package com.example.service.user;

import com.example.dto.user.type_user.TypeUserDto;
import com.example.dtomapper.user.TypeUserMapper;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.type_user.TypeUserNotSaveException;
import com.example.exception.user.type_user.TypeUserNotUpdateException;
import com.example.mapper.user.MapperTypeUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceTypeUserImpl implements IServiceTypeUser{

	
	private final MapperTypeUser mapperTypeUser;
	private final TypeUserMapper typeUserMapper;

	@Override
	public List<TypeUserDto> getAll() {

		return Optional.ofNullable(this.mapperTypeUser.getAll())
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.typeUserMapper::ttypeUserToTypeUserDto)
				.toList();
	}

	@Override
	public TypeUserDto getById(Integer id) {

		if(id==null || id<=0) {
			throw new NoDataFoundException("Valor de ID no valido");
		}
		
		return Optional.ofNullable(this.mapperTypeUser.getById(id))
				.map(this.typeUserMapper::ttypeUserToTypeUserDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

	@Override
	public Integer update(TypeUserDto t) {

		if(t==null || t.getId()==null || t.getId()<=0) {
			throw new TypeUserNotUpdateException("Datos no validos", t);
		}
		
		return Optional.of(t)
				.map(this.typeUserMapper::typeUserDtoToTtypeUser)
				.map(this.mapperTypeUser::update)
				.orElseThrow(() -> new TypeUserNotUpdateException("Error al intentar actualizar, Datos no validos",t));
	}

	@Override
	public Integer save(TypeUserDto t) {

		if(t==null || t.getName().trim().equals("") ) {
			throw new TypeUserNotSaveException("Datos no validos", t);
		}

		return Optional.of(t)
				.map(this.typeUserMapper::typeUserDtoToTtypeUser)
				.map(this.mapperTypeUser::save)
				.orElseThrow(()-> new TypeUserNotSaveException("Error al intentar guardar", t));
	}





}
