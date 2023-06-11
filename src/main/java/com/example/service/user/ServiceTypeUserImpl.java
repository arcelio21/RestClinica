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

	/**
	 * Obtiene todos los objetos TypeUserDto.
	 *
	 * @return Lista de objetos TypeUserDto
	 * @throws NoDataFoundException si no se encuentran datos
	 */
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


	/**
	 * Obtiene un objeto TypeUserDto por su ID.
	 *
	 * @param id ID del objeto TypeUserDto a buscar
	 * @return Objeto TypeUserDto correspondiente al ID proporcionado
	 * @throws NoDataFoundException si no se encuentra ningún objeto con el ID proporcionado
	 *         o si se proporciona un valor de ID no válido
	 */
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

	/**
	 * Actualiza un objeto TypeUserDto.
	 *
	 * @param typeUserDto Objeto TypeUserDto a actualizar
	 * @return ID del objeto TypeUserDto actualizado
	 * @throws TypeUserNotUpdateException si se produjo un error al intentar actualizar los datos o si los datos proporcionados no son válidos
	 */
	@Override
	public Integer update(TypeUserDto typeUserDto) {

		this.validDataTypeUserUpdate(typeUserDto);
		
		return Optional.of(typeUserDto)
				.map(this.dtoTypeUserMapper::typeUserDtoToTtypeUser)
				.map(this.mapperTypeUser::update)
				.orElseThrow(() -> new TypeUserNotUpdateException("Error al intentar actualizar, Datos no validos",typeUserDto));
	}

	/**
	 * Guarda un objeto TypeUserDto.
	 *
	 * @param typeUserDto Objeto TypeUserDto a guardar
	 * @return ID del objeto TypeUserDto guardado
	 * @throws TypeUserNotSaveException si se produjo un error al intentar guardar el objeto
	 *         o si los datos proporcionados no son válidos
	 */
	@Override
	public Integer save(TypeUserDto typeUserDto) {

		this.validDataTypeUserSave(typeUserDto);

		return Optional.of(typeUserDto)
				.map(this.dtoTypeUserMapper::typeUserDtoToTtypeUser)
				.map(this.mapperTypeUser::save)
				.orElseThrow(()-> new TypeUserNotSaveException("Error al intentar guardar", typeUserDto));
	}


	/**
	 * Valida los datos de un objeto TypeUserDto antes de realizar una actualización.
	 *
	 * @param typeUserDto Objeto TypeUserDto a validar
	 * @throws TypeUserNotUpdateException si los datos proporcionados no son válidos para una actualización
	 */
	private void validDataTypeUserUpdate(TypeUserDto typeUserDto) throws TypeUserNotUpdateException{
		if(typeUserDto==null || typeUserDto.getId()==null || typeUserDto.getId()<=0
				|| typeUserDto.getName()==null || typeUserDto.getName().trim().isEmpty()
		) {
			throw new TypeUserNotUpdateException("Datos no validos", typeUserDto);
		}
	}

	/**
	 * Valida los datos de un objeto TypeUserDto antes de realizar un guardado.
	 *
	 * @param typeUserDto Objeto TypeUserDto a validar
	 * @throws TypeUserNotSaveException si los datos proporcionados no son válidos para un guardado
	 */
	private void validDataTypeUserSave(TypeUserDto typeUserDto){
		if(typeUserDto==null || typeUserDto.getName()==null ||typeUserDto.getName().trim().equals("") ) {
			throw new TypeUserNotSaveException("Datos no validos", typeUserDto);
		}
	}

}
