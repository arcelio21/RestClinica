package com.example.service.user;

import java.util.List;
import java.util.Optional;

import com.example.dto.user.type_user_reg.TypeUserOfUserRegGetDto;
import com.example.dto.user.type_user_reg.UserRegOfTypeUserGetDto;
import com.example.dto.user.type_user_reg.UserTypeRegGetDto;
import com.example.dto.user.type_user_reg.UserTypeRegSaveDto;
import com.example.dto.user.type_user_reg.UserTypeRegUpdateDto;
import com.example.dtomapper.user.DtoUserTypeRegMapper;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.type_user_reg.UserTypeRegNotSaveException;
import com.example.exception.user.type_user_reg.UserTypeRegNotUpdateException;
import com.example.mapper.user.MapperUserTypeReg;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ServiceUserTypeRegImpl implements IServiceUserTypeReg<UserTypeRegGetDto, Long, UserTypeRegUpdateDto, UserTypeRegSaveDto> {

	private final MapperUserTypeReg mapperUserTypeReg;
	private final DtoUserTypeRegMapper dtoMapperUserTypeReg;

	/**
	 * Recupera una lista de objetos UserTypeRegGetDto que representan todos los registros de tipos de usuario.
	 *
	 * @return Una lista de objetos UserTypeRegGetDto que representan registros de usuario asociados a un tipo de usuario.
	 * @throws NoDataFoundException si no se encuentra ningún dato o si la lista resultante está vacía.
	 */
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

	/**
	 * Recupera un objeto UserTypeRegGetDto que representa un registro de usuario asociado a un tipo de usuario por su ID.
	 *
	 * @param id El ID del registro de usuario a recuperar.
	 * @return Un objeto UserTypeRegGetDto que representa el registro de usuario asociado a un tipo de usuario.
	 * @throws NoDataFoundException si no se encuentra ningún dato con el ID proporcionado.
	 */
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

	/**
	 * Actualiza un registro de usuario asociado a un tipo de usuario utilizando los datos proporcionados en el objeto UserTypeRegUpdateDto.
	 *
	 * @param userTypeRegUpdate El objeto UserTypeRegUpdateDto que contiene los datos para la actualización.
	 * @return El número de filas afectadas por la actualización.
	 * @throws UserTypeRegNotUpdateException si no se pueden actualizar los datos debido a problemas de validez o si no se encuentran datos para actualizar.
	 */
	@Override
	public Integer save(UserTypeRegSaveDto userTypeRegSave) {

		if (userTypeRegSave == null) {
			throw new UserTypeRegNotSaveException(userTypeRegSave);
		}
		
		Integer rowAffected = Optional.of(userTypeRegSave)
		.map(this.dtoMapperUserTypeReg::userTypeRegSaveToTuserTypeReg)
		.map(this.mapperUserTypeReg::save)
		.orElseThrow(()-> new UserTypeRegNotSaveException(userTypeRegSave));

		if(rowAffected==null || rowAffected <= 0){
			throw new UserTypeRegNotSaveException(userTypeRegSave);
		}

		return rowAffected;
	}

	/**
	 * Recupera una lista de objetos TypeUserOfUserRegGetDto que representan los tipos de usuario asociados a un registro de usuario por su ID.
	 *
	 * @param id El ID del registro de usuario.
	 * @return Una lista de objetos TypeUserOfUserRegGetDto que representan los tipos de usuario asociados al registro de usuario.
	 * @throws NoDataFoundException si no se encuentra ningún dato o si la lista resultante está vacía.
	 */
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

	/**
	 * Recupera una lista de objetos TypeUserOfUserRegGetDto que representan los tipos de usuario asociados a un registro activado de usuario por su ID.
	 *
	 * @param id El ID del registro de usuario.
	 * @return Una lista de objetos TypeUserOfUserRegGetDto que representan los tipos de usuario activados asociados al registro de usuario.
	 * @throws NoDataFoundException si no se encuentra ningún dato o si la lista resultante está vacía.
	 */
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

	/**
	 * Recupera una lista de objetos UserRegOfTypeUserGetDto que representan los registros de usuario asociados a un tipo de usuario por su ID.
	 *
	 * @param id El ID del tipo de usuario.
	 * @return Una lista de objetos UserRegOfTypeUserGetDto que representan los registros de usuario asociados al tipo de usuario.
	 * @throws NoDataFoundException si no se encuentra ningún dato o si la lista resultante está vacía.
	 */
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

	/**
	 * Recupera una lista de objetos UserTypeRegGetDto que representan los registros de usuario asociados a un estado por su ID.
	 *
	 * @param id El ID del estado.
	 * @return Una lista de objetos UserTypeRegGetDto que representan los registros de usuario asociados al estado.
	 * @throws NoDataFoundException si no se encuentra ningún dato o si la lista resultante está vacía.
	 * @throws IllegalArgumentException si el ID proporcionado no es válido (es decir, nulo o menor o igual a cero).
	 */
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

	/**
	 * Valida si un valor de tipo Long es válido como identificador (ID).
	 *
	 * @param value El valor de tipo Long a validar como identificador.
	 * @throws NoDataFoundException si el valor no es válido (es decir, nulo o menor o igual a cero).
	 */
	private void isValitedId(Long value) {
		if (value == null || value <= 0) {
			throw new NoDataFoundException(value);
		}
	}

	/**
	 * Valida si un valor de tipo Integer es válido como identificador (ID).
	 *
	 * @param value El valor de tipo Integer a validar como identificador.
	 * @throws NoDataFoundException si el valor no es válido (es decir, nulo o menor o igual a cero).
	 */
	private void isValitedId(Integer value) {
		if (value == null || value <= 0) {
			throw new NoDataFoundException(value);
		}
	}

}
