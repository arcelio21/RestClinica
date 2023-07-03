package com.example.service.modules;

import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeUpdateDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegesDto;
import com.example.dtomapper.modules.DtoModulesPrivilegesMapper;
import com.example.entity.modules.TmodulePrivilege;
import com.example.exception.NoDataFoundException;
import com.example.mapper.modules.MapperModulePrivilege;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceModulePrivilegeImpl implements IServiceModulePrivilege{
	
	private final MapperModulePrivilege mapperModulePrivilege;
	private final DtoModulesPrivilegesMapper dtoModulesPrivilegesMapper;


	/**
	 * Obtiene todos los objetos ModulePrivilegesDto.
	 *
	 * @return Lista de objetos ModulePrivilegesDto
	 * @throws NoDataFoundException si no se encuentran datos disponibles o si no se encuentran datos encontrados
	 */
	@Override
	public List<ModulePrivilegesDto> getAll() {

		List<TmodulePrivilege> tmodulePrivilegeList = Optional.ofNullable(this.mapperModulePrivilege.getAll())
				.orElseThrow(() -> new NoDataFoundException("Datos no disponibles"));

		if(tmodulePrivilegeList.isEmpty()){
			throw  new NoDataFoundException("Datos no encontrados");
		}

		return tmodulePrivilegeList
				.stream()
				.map(this.dtoModulesPrivilegesMapper::TmodulePrivilegeToModulePrivilegeDto)
				.collect(Collectors.toList());

	}

	@Override
	public ModulePrivilegesDto getById(Long id) {
		return null;
	}

	/**
	 * @param t 
	 * @return
	 */
	@Override
	public Integer update(ModulePrivilegeUpdateDto modulePrivilegeUpdateDto) {
		return null;
	}

	/**
	 * @param t 
	 * @return
	 */
	@Override
	public Integer save(ModulePrivilegeSaveDto modulePrivilegeSaveDto) {
		return null;
	}
}
