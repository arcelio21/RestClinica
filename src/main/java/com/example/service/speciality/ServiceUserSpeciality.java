package com.example.service.speciality;

import com.example.dto.speciality.userspeciality.*;
import com.example.dtomapper.speciality.DtoUserSpecialityMapper;
import com.example.exception.NoDataFoundException;
import com.example.exception.speciality.user_speciality.UserSpecialityNotSaveException;
import com.example.exception.speciality.user_speciality.UserSpecialityNotUpdateException;
import com.example.mapper.speciality.MapperUserSpeciality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServiceUserSpeciality implements
		IServiceUserSpeciality<UserSpecialityGetDto, Integer, UserSpecialityUpdateDto, UserSpecialitySaveDto> {

	private final MapperUserSpeciality mapperUserSpeciality;
	private final DtoUserSpecialityMapper dtoUserSpecialityMapper;

	/**
	 * Obtiene una lista de todas las asociaciones entre usuarios y especialidades.
	 *
	 * @return Una lista de objetos UserSpecialityGetDto que representan las
	 *         asociaciones entre usuarios y especialidades.
	 * @throws NoDataFoundException si no se encuentran datos de asociaciones o si
	 *                              la lista resultante está vacía.
	 */
	@Override
	public List<UserSpecialityGetDto> getAll() {

		List<UserSpecialityGetDto> listUserSpeciality = Optional.ofNullable(this.mapperUserSpeciality.getAll())
				.orElseThrow(() -> new NoDataFoundException("Data Not Found"))
				.stream()
				.map(this.dtoUserSpecialityMapper::userSpecialityToUserSpecialityGetDto)
				.toList();

		if (listUserSpeciality.isEmpty()) {
			throw new NoDataFoundException("Data Not exist");
		}

		return listUserSpeciality;
	}

	/**
	 * Obtiene una asociación entre usuario y especialidad por su ID.
	 *
	 * @param idUserSpeciality El ID de la asociación entre usuario y especialidad a
	 *                         recuperar.
	 * @return Un objeto UserSpecialityGetDto que representa la asociación
	 *         correspondiente al ID proporcionado.
	 * @throws NoDataFoundException si no se encuentra la asociación o si el ID no
	 *                              es válido (nulo o menor o igual a cero).
	 */
	@Override
	public UserSpecialityGetDto getById(Integer idUserSpeciality) {

		if (idUserSpeciality == null || idUserSpeciality <= 0) {
			throw new NoDataFoundException("ID NOT VALID");
		}

		return Optional.of(idUserSpeciality)
				.map(this.mapperUserSpeciality::getById)
				.map(this.dtoUserSpecialityMapper::userSpecialityToUserSpecialityGetDto)
				.orElseThrow(() -> new NoDataFoundException("Data Not Found"));
	}

	/**
	 * Actualiza la información de una asociación entre usuario y especialidad en la
	 * base de datos.
	 *
	 * @param userSpecialityUpdate La información actualizada de la asociación entre
	 *                             usuario y especialidad.
	 * @return El número de filas afectadas al actualizar la asociación en la base
	 *         de datos.
	 * @throws UserSpecialityNotUpdateException si no se puede actualizar la
	 *                                          asociación o si los datos de
	 *                                          actualización no son válidos.
	 */
	@Override
	public Integer update(UserSpecialityUpdateDto userSpecialityUpdate) {

		if (userSpecialityUpdate == null
				|| userSpecialityUpdate.id() == null || userSpecialityUpdate.id() <= 0
				|| userSpecialityUpdate.idSpeciality() == null || userSpecialityUpdate.idSpeciality() <= 0
				|| userSpecialityUpdate.idStatus() == null || userSpecialityUpdate.idStatus() <= 0
				|| userSpecialityUpdate.idUserTypeReg() == null || userSpecialityUpdate.idUserTypeReg() <= 0) {
			throw new UserSpecialityNotUpdateException("Data not valid", userSpecialityUpdate);
		}

		Integer rowAffected = Optional.of(userSpecialityUpdate)
				.map(this.dtoUserSpecialityMapper::UserSpecialityUpdateDtoTouserSpeciality)
				.map(this.mapperUserSpeciality::update)
				.orElseThrow(() -> new UserSpecialityNotUpdateException(userSpecialityUpdate));

		if (rowAffected == null || rowAffected == 0) {
			throw new UserSpecialityNotUpdateException(userSpecialityUpdate);
		}

		return rowAffected;
	}

	/**
	 * Guarda una nueva asociación entre usuario y especialidad en la base de datos.
	 *
	 * @param userSpecialitySaveDto La información de la nueva asociación entre usuario y especialidad.
	 * @return El número de filas afectadas al guardar la nueva asociación en la base de datos.
	 * @throws UserSpecialityNotSaveException si no se puede guardar la asociación o si los datos no son válidos.
	 */
	@Override
	public Integer save(UserSpecialitySaveDto userSpecialitySaveDto) {

		if (userSpecialitySaveDto == null
				|| userSpecialitySaveDto.idSpeciality() == null || userSpecialitySaveDto.idSpeciality() <= 0
				|| userSpecialitySaveDto.idStatus() == null || userSpecialitySaveDto.idStatus() <= 0
				|| userSpecialitySaveDto.idUserTypeReg() == null || userSpecialitySaveDto.idUserTypeReg() <= 0) {
			throw new UserSpecialityNotSaveException(userSpecialitySaveDto);
		}

		Integer rowAffected = Optional.of(userSpecialitySaveDto)
				.map(this.dtoUserSpecialityMapper::UserSpecialitySaveDtoToUserSpeciality)
				.map(this.mapperUserSpeciality::save)
				.orElseThrow(() -> new UserSpecialityNotSaveException(userSpecialitySaveDto));

		if (rowAffected == null || rowAffected <= 0) {
			throw new UserSpecialityNotSaveException(userSpecialitySaveDto);
		}

		return rowAffected;
	}

	/**
	 * Obtiene la lista de asociaciones entre usuarios y una especialidad específica según el ID de la especialidad.
	 *
	 * @param idSpeciality El ID de la especialidad para la cual se desea obtener las asociaciones.
	 * @return La lista de asociaciones entre usuarios y la especialidad especificada.
	 * @throws NoDataFoundException si no se encuentran datos válidos para la especialidad dada.
	 */
	@Override
	public List<UserSpecialityBySpecialityGetDto> getByIdSpeciality(Integer idSpeciality) {
		
		if(idSpeciality==null || idSpeciality<=0){
			throw new NoDataFoundException(idSpeciality);
		}

		List<UserSpecialityBySpecialityGetDto> userSpecialityBySpecialityList = Optional.of(idSpeciality)
		.map(this.mapperUserSpeciality::getByIdSpeciality)
		.orElseThrow(()-> new NoDataFoundException(idSpeciality))
		.stream()
		.map(this.dtoUserSpecialityMapper::userSpecialityToUserSpecialityBySpecialityGetDto)
		.toList();

		if(userSpecialityBySpecialityList.isEmpty()){
			throw new NoDataFoundException("DATA FOUND IS NOT VALID");
		}

		return userSpecialityBySpecialityList;
	}

	/**
	 * Obtiene la lista de asociaciones entre usuarios y especialidades basadas en un tipo de usuario específico
	 * según el ID del tipo de usuario.
	 *
	 * @param idUserTypeReg El ID del tipo de usuario para el cual se desea obtener las asociaciones.
	 * @return La lista de asociaciones entre usuarios y especialidades basadas en el tipo de usuario especificado.
	 * @throws NoDataFoundException si no se encuentran datos válidos para el tipo de usuario dado.
	 */
	@Override
	public List<UserSpecialityByUserTypeRegGetDto> getByIdUserTypeReg(Integer idUserTypeReg) {

		if (idUserTypeReg==null || idUserTypeReg<=0){
			throw new NoDataFoundException(idUserTypeReg);
		}

		List<UserSpecialityByUserTypeRegGetDto> userSpecialityByUserTypeRegList = Optional.of(idUserTypeReg)
				.map(this.mapperUserSpeciality::getByIdUserTypeReg)
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.dtoUserSpecialityMapper::userSpecialityToUserSpecialityByUserTypeRegGetDto)
				.toList();

		if(userSpecialityByUserTypeRegList.isEmpty()){
			throw new NoDataFoundException("Data is empty");
		}

		return  userSpecialityByUserTypeRegList;
	}

	/**
	 * Obtiene la lista de asociaciones entre usuarios y especialidades basadas en un estado específico
	 * según el ID del estado.
	 *
	 * @param idStatus El ID del estado para el cual se desea obtener las asociaciones.
	 * @return La lista de asociaciones entre usuarios y especialidades basadas en el estado especificado.
	 * @throws NoDataFoundException si no se encuentran datos válidos para el estado dado.
	 */
	@Override
	public List<UserSpecialityByStatusGetDto> getByIdStatus(Integer idStatus) {

		if (idStatus == null || idStatus<=0){
			throw new NoDataFoundException(idStatus);
		}

		List<UserSpecialityByStatusGetDto> userSpecialityByStatusList = Optional.of(idStatus)
				.map(this.mapperUserSpeciality::getByIdStatus)
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.dtoUserSpecialityMapper::userSpecialityToUserSpecialityByStatusGetDto)
				.toList();

		if(userSpecialityByStatusList.isEmpty()){
			throw new NoDataFoundException("DATA IS EMPTY");
		}

		return userSpecialityByStatusList;
	}

	/**
	 * Obtiene la lista de asociaciones entre usuarios y especialidades activas, basadas en el estado activado.
	 *
	 * @return La lista de asociaciones entre usuarios y especialidades activas.
	 * @throws NoDataFoundException si no se encuentran datos válidos para las asociaciones activadas.
	 */
	@Override
	public List<UserSpecialityByStatusGetDto> getByIdStatusActivated() {

		List<UserSpecialityByStatusGetDto> userSpecialityByStatusList = Optional.ofNullable(this.mapperUserSpeciality.getByIdStatusActivated())
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.dtoUserSpecialityMapper::userSpecialityToUserSpecialityByStatusGetDto)
				.toList();

		if (userSpecialityByStatusList.isEmpty()){
			throw new NoDataFoundException("DATA IS EMPTY");
		}
		return userSpecialityByStatusList;
	}

	/**
	 * Obtiene la lista de asociaciones entre usuarios y especialidades basadas en el identificador de tipo de usuario.
	 *
	 * @param idTypeUser El identificador del tipo de usuario.
	 * @return La lista de asociaciones entre usuarios y especialidades correspondientes al tipo de usuario.
	 * @throws NoDataFoundException si no se encuentran datos válidos para el tipo de usuario proporcionado.
	 */
	@Override
	public List<UserSpecialityByTypeUserGetDto> getByTypeUserId(Integer idTypeUser) {

		if(idTypeUser==null || idTypeUser <= 0){
			throw new NoDataFoundException(idTypeUser);
		}

		List<UserSpecialityByTypeUserGetDto> userSpecialityByTypeUserList = Optional.of(idTypeUser)
				.map(this.mapperUserSpeciality::getByTypeUserId)
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.dtoUserSpecialityMapper::userSpecialityToUserSpecialityByTypeUserGetDto)
				.toList();

		if(userSpecialityByTypeUserList.isEmpty()){
			throw new NoDataFoundException("Data is empty");
		}

		return userSpecialityByTypeUserList;
	}
}
