package com.example.service.user;

import com.example.dto.user.typeuser_module.*;
import com.example.dtomapper.user.DtoTypeUserModuleMapper;
import com.example.exception.NoDataFoundException;
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

	@Override
	public Integer save(TypeUserModuleSaveDto t) {
		return null;
	}

	@Override
	public List<ModuleTypeUserGetDto> getModuleAndTypeUserDistinct() {
		return null;
	}

	@Override
	public List<ModuleOfTypeUserGetDto> getModuleDistinctByIdTypeUserAndStatusActived(Integer idTypeUser) {
		return null;
	}

	@Override
	public List<ModuleOfTypeUserGetDto> getModuleDistinctByIdTypeUserAndIdStatus(Integer idTypeUser, Integer idStatus) {
		return null;
	}

	@Override
	public List<TypeUserOfModuleGetDto> getTypeUserDistinctByIdModuleAndStatusActivated(Long idModule) {
		return null;
	}

	@Override
	public List<TypeUserOfModuleGetDto> getTypeUserDistinctByIdModuleAndIdStatus(Long idModule, Integer idStatus) {
		return null;
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
