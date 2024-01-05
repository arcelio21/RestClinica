package com.example.service.speciality;

import com.example.dto.speciality.userspeciality.*;
import com.example.dtomapper.speciality.DtoUserSpecialityMapper;
import com.example.exception.NoDataFoundException;
import com.example.mapper.speciality.MapperUserSpeciality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServiceUserSpeciality implements IServiceUserSpeciality<UserSpecialityGetDto,Integer, UserSpecialityUpdateDto, UserSpecialitySaveDto>{

	private final MapperUserSpeciality mapperUserSpeciality;
	private final DtoUserSpecialityMapper dtoUserSpecialityMapper;


	/**
	 * Obtiene una lista de todas las asociaciones entre usuarios y especialidades.
	 *
	 * @return Una lista de objetos UserSpecialityGetDto que representan las asociaciones entre usuarios y especialidades.
	 * @throws NoDataFoundException si no se encuentran datos de asociaciones o si la lista resultante está vacía.
	 */
	@Override
	public List<UserSpecialityGetDto> getAll() {

		List<UserSpecialityGetDto> listUserSpeciality = Optional.ofNullable(this.mapperUserSpeciality.getAll())
				.orElseThrow(()-> new NoDataFoundException("Data Not Found"))
				.stream()
				.map(this.dtoUserSpecialityMapper::userSpecialityToUserSpecialityGetDto)
				.toList();

		if(listUserSpeciality.isEmpty()){
			throw  new NoDataFoundException("Data Not exist");
		}

		return listUserSpeciality;
	}

	/**
	 * Obtiene una asociación entre usuario y especialidad por su ID.
	 *
	 * @param idUserSpeciality El ID de la asociación entre usuario y especialidad a recuperar.
	 * @return Un objeto UserSpecialityGetDto que representa la asociación correspondiente al ID proporcionado.
	 * @throws NoDataFoundException si no se encuentra la asociación o si el ID no es válido (nulo o menor o igual a cero).
	 */
	@Override
	public UserSpecialityGetDto getById(Integer idUserSpeciality) {

		if(idUserSpeciality==null || idUserSpeciality<=0){
			throw new NoDataFoundException("ID NOT VALID");
		}

		return Optional.of(idUserSpeciality)
		.map(this.mapperUserSpeciality::getById)
		.map(this.dtoUserSpecialityMapper::userSpecialityToUserSpecialityGetDto)
		.orElseThrow(()-> new NoDataFoundException("Data Not Found"));
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
