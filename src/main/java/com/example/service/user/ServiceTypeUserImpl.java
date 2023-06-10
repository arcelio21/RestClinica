package com.example.service.user;

import com.example.dto.user.type_user.TypeUserDto;
import com.example.dtomapper.user.DtoTypeUserMapper;
import com.example.entity.user.TtypeUser;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.type_user.TypeUserNotSaveException;
import com.example.exception.user.type_user.TypeUserNotUpdateException;
import com.example.mapper.user.MapperTypeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceTypeUserImpl implements IServiceTypeUser <TypeUserDto, Integer,TypeUserDto,TypeUserDto>{

	
	private final MapperTypeUser mapperTypeUser;
	private final DtoTypeUserMapper dtoTypeUserMapper;

	@Override
	public List<TypeUserDto> getAll() {

		Optional<List<TtypeUser>> optionalTtypeUsers = Optional.ofNullable(this.mapperTypeUser.getAll());

		if(optionalTtypeUsers.isPresent() && !optionalTtypeUsers.get().isEmpty()){
			return optionalTtypeUsers
						.get()
						.stream()
						.map(this.dtoTypeUserMapper::ttypeUserToTypeUserDto)
						.collect(Collectors.toList());
		}

		throw new NoDataFoundException();
	}

	@Override
	public TypeUserDto getById(Integer id) {

		if(id==null || id<=0) {
			throw new NoDataFoundException("Valor de ID no valido");
		}
		
		return Optional.of(id)
				.map(this.mapperTypeUser::getById)
				.map(this.dtoTypeUserMapper::ttypeUserToTypeUserDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

	@Override
	public Integer update(TypeUserDto t) {

		if(t==null || t.getId()==null || t.getId()<=0) {
			throw new TypeUserNotUpdateException("Datos no validos", t);
		}
		
		return Optional.of(t)
				.map(this.dtoTypeUserMapper::typeUserDtoToTtypeUser)
				.map(this.mapperTypeUser::update)
				.orElseThrow(() -> new TypeUserNotUpdateException("Error al intentar actualizar, Datos no validos",t));
	}

	@Override
	public Integer save(TypeUserDto t) {

		if(t==null || t.getName().trim().equals("") ) {
			throw new TypeUserNotSaveException("Datos no validos", t);
		}

		return Optional.of(t)
				.map(this.dtoTypeUserMapper::typeUserDtoToTtypeUser)
				.map(this.mapperTypeUser::save)
				.orElseThrow(()-> new TypeUserNotSaveException("Error al intentar guardar", t));
	}





}
