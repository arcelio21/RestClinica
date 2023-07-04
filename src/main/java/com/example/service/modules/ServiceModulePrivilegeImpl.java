package com.example.service.modules;

import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeUpdateDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegesDto;
import com.example.dtomapper.modules.DtoModulesPrivilegesMapper;
import com.example.entity.modules.TmodulePrivilege;
import com.example.exception.NoDataFoundException;
import com.example.exception.modules.modulesprivilege.ModulePrivilegesNotSaveException;
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

	/**
	 * Obtiene un objeto ModulePrivilegesDto por su ID.
	 *
	 * @param id ID del objeto a obtener
	 * @return Objeto ModulePrivilegesDto correspondiente al ID proporcionado
	 * @throws NoDataFoundException si el ID es nulo, menor o igual a cero o si no se encuentra ningún objeto con el ID proporcionado
	 */
	@Override
	public ModulePrivilegesDto getById(Long id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);
		}

		return Optional.of(id)
				.map(this.mapperModulePrivilege::getById)
				.map(this.dtoModulesPrivilegesMapper::TmodulePrivilegeToModulePrivilegeDto)
				.orElseThrow(()-> new NoDataFoundException(id));
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
	 * Guarda un nuevo objeto ModulePrivilege a partir de los datos proporcionados en ModulePrivilegeSaveDto.
	 *
	 * @param modulePrivilegeSaveDto Objeto ModulePrivilegeSaveDto con los datos a guardar
	 * @return Número de filas afectadas por el guardado
	 * @throws ModulePrivilegesNotSaveException si los datos proporcionados no son válidos para el guardado
	 *         o si se produce un error durante el guardado
	 */
	@Override
	public Integer save(ModulePrivilegeSaveDto modulePrivilegeSaveDto) {

		if (modulePrivilegeSaveDto == null || modulePrivilegeSaveDto.getPrivilegeId()==null || modulePrivilegeSaveDto.getPrivilegeId()<=0
			|| modulePrivilegeSaveDto.getModuleId()==null || modulePrivilegeSaveDto.getModuleId()<=0
			|| modulePrivilegeSaveDto.getStatusId()==null || modulePrivilegeSaveDto.getStatusId()<=0
		) {
			throw new ModulePrivilegesNotSaveException("Data Not Valid", modulePrivilegeSaveDto);
		}

		Integer rowAffected = Optional.of(modulePrivilegeSaveDto)
				.map(this.dtoModulesPrivilegesMapper::ModulePrivilegeSaveDtoToTmodulePrivilege)
				.map(this.mapperModulePrivilege::save)
				.orElseThrow(()-> new ModulePrivilegesNotSaveException("Data not valid", modulePrivilegeSaveDto));

		if (rowAffected==null || rowAffected<=0){
			throw new ModulePrivilegesNotSaveException("Error to update", modulePrivilegeSaveDto);
		}

		return rowAffected;
	}
}
