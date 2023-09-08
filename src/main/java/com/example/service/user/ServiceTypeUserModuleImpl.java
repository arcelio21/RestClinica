package com.example.service.user;

import com.example.dto.user.typeuser_module.*;
import com.example.dtomapper.user.DtoTypeUserModuleMapper;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.typeuser_module.TypeUserModuleNotSaveException;
import com.example.exception.user.typeuser_module.TypeUserModuleNotUpdateException;
import com.example.mapper.user.MapperTypeUserModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServiceTypeUserModuleImpl implements IServiceTypeUserModule<TypeUserModuleGetDto, Long, TypeUserModuleUpdateDto, TypeUserModuleSaveDto> {

	private final MapperTypeUserModule mapperTypeUserModule;
	private final DtoTypeUserModuleMapper dtoTypeUserModuleMapper;


	/**
	 * Obtiene todos los objetos TypeUserModuleGetDto.
	 *
	 * @return Lista de objetos TypeUserModuleGetDto
	 * @throws NoDataFoundException si no se encuentran datos o la lista está vacía
	 */
	@Override
	public List<TypeUserModuleGetDto> getAll() {

		List<TypeUserModuleGetDto> typeusermodules = Optional.of(this.mapperTypeUserModule.getAll())
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.dtoTypeUserModuleMapper::tTypeUserModuleToTypeUserModuleGetDto)
				.toList();

		if(typeusermodules.isEmpty()){
			throw new NoDataFoundException();
		}

		return typeusermodules;
	}

	/**
	 * Obtiene un objeto TypeUserModuleGetDto por su ID.
	 *
	 * @param id ID del objeto a obtener
	 * @return Objeto TypeUserModuleGetDto correspondiente al ID proporcionado
	 * @throws NoDataFoundException si el ID es nulo, menor o igual a cero o si no se encuentra ningún objeto con el ID proporcionado
	 */
	@Override
	public TypeUserModuleGetDto getById(Long id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		return Optional.of(id)
				.map(this.mapperTypeUserModule::getById)
				.map(this.dtoTypeUserModuleMapper::tTypeUserModuleToTypeUserModuleGetDto)
				.orElseThrow(NoDataFoundException::new);
	}

	/**
	 * Actualiza un objeto TypeUserModule a partir de los datos proporcionados en TypeUserModuleUpdateDto.
	 *
	 * @param typeUserModuleUpdateDto Objeto TypeUserModuleUpdateDto con los datos a actualizar
	 * @return Número de filas afectadas por la actualización
	 * @throws TypeUserModuleNotUpdateException si los datos proporcionados no son válidos para la actualización
	 *         o si se produce un error durante la actualización
	 */
	@Override
	public Integer update(TypeUserModuleUpdateDto typeUserModuleUpdateDto) {

		if(typeUserModuleUpdateDto==null || typeUserModuleUpdateDto.getIdTypeUser()==null || typeUserModuleUpdateDto.getIdTypeUser()<=0
			|| typeUserModuleUpdateDto.getIdModulePrivilege()==null || typeUserModuleUpdateDto.getIdModulePrivilege()<=0
			|| typeUserModuleUpdateDto.getId()==null || typeUserModuleUpdateDto.getId()<=0
		){
			throw new TypeUserModuleNotUpdateException("Data not valid", typeUserModuleUpdateDto);
		}

		Integer rowAffected = Optional.of(typeUserModuleUpdateDto)
				.map(this.dtoTypeUserModuleMapper::TypeUserModuleUpdateDtoToTtypeUserModule)
				.map(this.mapperTypeUserModule::update)
				.orElseThrow(()-> new TypeUserModuleNotUpdateException("Failed to update", typeUserModuleUpdateDto));

		if(rowAffected==null || rowAffected<=0){
			throw new TypeUserModuleNotUpdateException("Failed to update", typeUserModuleUpdateDto);
		}

		return rowAffected;
	}


	/**
	 * Guarda un nuevo objeto TypeUserModule a partir de los datos proporcionados en TypeUserModuleSaveDto.
	 *
	 * @param typeUserModuleSaveDto Objeto TypeUserModuleSaveDto con los datos a guardar
	 * @return Número de filas afectadas por el guardado
	 * @throws TypeUserModuleNotSaveException si los datos proporcionados no son válidos para el guardado
	 *         o si se produce un error durante el guardado
	 */
	@Override
	public Integer save(TypeUserModuleSaveDto typeUserModuleSaveDto) {

		if(typeUserModuleSaveDto==null || typeUserModuleSaveDto.getTypeUser()==null || typeUserModuleSaveDto.getTypeUser()<=0
			|| typeUserModuleSaveDto.getIdModulePrivilege()==null || typeUserModuleSaveDto.getIdModulePrivilege()<=0
		){
			throw new TypeUserModuleNotSaveException(typeUserModuleSaveDto,"Data no valid");
		}

		Integer rowAffected = Optional.of(typeUserModuleSaveDto)
				.map(this.dtoTypeUserModuleMapper::TypeUserModuleSaveDtoToTtypeUserModule)
				.map(this.mapperTypeUserModule::save)
				.orElseThrow(()-> new TypeUserModuleNotSaveException(typeUserModuleSaveDto,"Data no valid"));

		if(rowAffected==null || rowAffected<=0){
			throw new TypeUserModuleNotSaveException(typeUserModuleSaveDto,"Record creation failed");
		}

		return rowAffected;
	}

	/**
	 * Obtiene una lista de objetos ModuleTypeUserGetDto con combinaciones únicas de módulos y tipos de usuario.
	 *
	 * @return Lista de objetos ModuleTypeUserGetDto con combinaciones únicas de módulos y tipos de usuario
	 * @throws NoDataFoundException si no se encuentran datos o la lista está vacía
	 */
	@Override
	public List<ModuleTypeUserGetDto> getModuleAndTypeUserDistinct() {

		List<ModuleTypeUserGetDto> moduleTypeUserGetDtoList = Optional.of(this.mapperTypeUserModule.getModuleAndTypeUserDistinct())
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.dtoTypeUserModuleMapper::tTypeUserModuleToModuleTypeUserGetDto)
				.toList();

		if(moduleTypeUserGetDtoList.isEmpty()){
			throw new NoDataFoundException();
		}

		return moduleTypeUserGetDtoList;
	}

	/**
	 * Obtiene una lista de objetos ModuleOfTypeUserGetDto con módulos distintos activos para un tipo de usuario dado.
	 *
	 * @param idTypeUser ID del tipo de usuario para el que se obtienen los módulos
	 * @return Lista de objetos ModuleOfTypeUserGetDto con módulos distintos activos para el tipo de usuario dado
	 * @throws NoDataFoundException si el ID del tipo de usuario es nulo o menor o igual a cero,
	 *         si no se encuentran datos o la lista está vacía
	 */
	@Override
	public List<ModuleOfTypeUserGetDto> getModuleDistinctByIdTypeUserAndStatusActived(Integer idTypeUser) {

		if(idTypeUser==null || idTypeUser<=0){
			throw new NoDataFoundException("Parameter Not Valid");
		}

		List<ModuleOfTypeUserGetDto> moduleOfTypeUserGetDtoList = Optional.of(idTypeUser)
				.map(this.mapperTypeUserModule::getModuleDistinctByIdTypeUserAndStatusActived)
				.orElseThrow(()-> new NoDataFoundException("Data Not found"))
				.stream()
				.map(this.dtoTypeUserModuleMapper::tTypeUserModuleToModuleOfTypeUserGetDto)
				.toList();

		if(moduleOfTypeUserGetDtoList.isEmpty()){
			throw new NoDataFoundException("Data not exits");
		}

		return moduleOfTypeUserGetDtoList;

	}

	/**
	 * Obtiene una lista de objetos ModuleOfTypeUserGetDto con módulos distintos para un tipo de usuario y un estado de módulo dados.
	 *
	 * @param idTypeUser ID del tipo de usuario para el que se obtienen los módulos
	 * @param idStatus ID del estado de módulo para el que se obtienen los módulos
	 * @return Lista de objetos ModuleOfTypeUserGetDto con módulos distintos para el tipo de usuario y estado dados
	 * @throws NoDataFoundException si los ID de tipo de usuario o estado son nulos o menores o iguales a cero,
	 *         si no se encuentran datos o la lista está vacía
	 */
	@Override
	public List<ModuleOfTypeUserGetDto> getModuleDistinctByIdTypeUserAndIdStatus(Integer idTypeUser, Integer idStatus) {

		if(idTypeUser == null || idTypeUser<=0
		   || idStatus == null || idStatus<=0 ){

			throw new NoDataFoundException("Parameter Not Valid");
		}

		List<ModuleOfTypeUserGetDto> moduleOfTypeUserGetDtoList = Optional.ofNullable(this.mapperTypeUserModule.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser,idStatus))
									.orElseThrow(()-> new NoDataFoundException("DATA NO VALID"))
									.stream()
									.map(this.dtoTypeUserModuleMapper::tTypeUserModuleToModuleOfTypeUserGetDto)
									.toList();

		if (moduleOfTypeUserGetDtoList.isEmpty()){
			throw new NoDataFoundException("Data is empty");
		}

		return moduleOfTypeUserGetDtoList;
	}

	/**
	 * Obtiene una lista de objetos TypeUserOfModuleGetDto con tipos de usuario distintos activos para un módulo dado.
	 *
	 * @param idModule ID del módulo para el que se obtienen los tipos de usuario
	 * @return Lista de objetos TypeUserOfModuleGetDto con tipos de usuario distintos activos para el módulo dado
	 * @throws NoDataFoundException si el ID del módulo es nulo o menor o igual a cero,
	 *         si no se encuentran datos o la lista está vacía
	 */
	@Override
	public List<TypeUserOfModuleGetDto> getTypeUserDistinctByIdModuleAndStatusActivated(Long idModule) {

		if(idModule==null || idModule<=0){
			throw new NoDataFoundException("Parameter No valid");
		}

		List<TypeUserOfModuleGetDto> typeUserOfModuleGetDtoList = Optional.of(idModule)
				.map(this.mapperTypeUserModule::getTypeUserDistinctByIdModuleAndStatusActivated)
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.dtoTypeUserModuleMapper::tTypeUserModuleToTypeUserOfModuleGetDto)
				.toList();

		if(typeUserOfModuleGetDtoList.isEmpty()){
			throw new NoDataFoundException("Data empty");
		}
		return typeUserOfModuleGetDtoList;
	}

	/**
	 * Obtiene una lista de objetos TypeUserOfModuleGetDto con tipos de usuario distintos para un módulo y un estado dados.
	 *
	 * @param idModule ID del módulo para el que se obtienen los tipos de usuario
	 * @param idStatus ID del estado de módulo para el que se obtienen los tipos de usuario
	 * @return Lista de objetos TypeUserOfModuleGetDto con tipos de usuario distintos para el módulo y estado dados
	 * @throws NoDataFoundException si el ID del módulo o el ID del estado son nulos o menores o iguales a cero,
	 *         si no se encuentran datos o la lista está vacía
	 */
	@Override
	public List<TypeUserOfModuleGetDto> getTypeUserDistinctByIdModuleAndIdStatus(Long idModule, Integer idStatus) {

		if(idModule == null || idModule <= 0
			|| idStatus == null || idStatus <= 0){
			throw new NoDataFoundException("Parameter Not Valid");
		}

		List<TypeUserOfModuleGetDto> typeUserOfModuleGetDtoList = Optional.ofNullable(this.mapperTypeUserModule.getTypeUserDistinctByIdModuleAndIdStatus(idModule,idStatus))
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.dtoTypeUserModuleMapper::tTypeUserModuleToTypeUserOfModuleGetDto)
				.toList();

		if(typeUserOfModuleGetDtoList.isEmpty()){
			throw new NoDataFoundException("Data is empty");
		}

		return typeUserOfModuleGetDtoList;
	}

	@Override
	public List<PrivilegeOfModuleGetDto> getPrivelegeOfModuleByIdTypeUserAndIdModuleAndStatusActived(Integer idTypeUser, Long idModule) {
		return null;
	}

	@Override
	public List<ModuleTypeUserGetDto> getTypeModulePrivilegeByidTypeUserAndStatusActived(Integer idTypeUser) {
		return null;
	}
}
