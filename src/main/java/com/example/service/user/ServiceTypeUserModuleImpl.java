package com.example.service.user;

import com.example.dto.user.typeuser_module.*;
import com.example.dtomapper.user.DtoTypeUserModuleMapper;
import com.example.exception.NoDataFoundException;
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

	@Override
	public Integer update(TypeUserModuleUpdateDto t) {
		return null;
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
