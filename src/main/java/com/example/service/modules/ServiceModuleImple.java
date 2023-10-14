package com.example.service.modules;

import com.example.dto.modules.ModuleSaveDto;
import com.example.dto.modules.ModulesDto;
import com.example.dto.modules.ModulesUpdateDto;
import com.example.dtomapper.modules.DtoModulesMapper;
import com.example.entity.modules.Tmodule;
import com.example.exception.NoDataFoundException;
import com.example.exception.modules.modules.ModulesNotSaveException;
import com.example.exception.modules.modules.ModulesNotUpdateException;
import com.example.mapper.modules.MapperModules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase de implementación para el módulo de servicio.
 */
@AllArgsConstructor
@Service
public class ServiceModuleImple implements IServiceModule<ModulesDto, Long,ModulesUpdateDto, ModuleSaveDto>{

	private MapperModules mapperModules;
	private DtoModulesMapper dtoModulesMapper;

	/**
	 * Obtiene todos los módulos.
	 *
	 * @return Lista de objetos ModulesDto.
	 * @throws NoDataFoundException si no se encuentra ningún dato.
	 */
	@Override
	public List<ModulesDto> getAll() {
		
		Optional<List<Tmodule>> tmodules = Optional.ofNullable(this.mapperModules.getAll());
		
		if(tmodules.isPresent() && !tmodules.get().isEmpty()){
			
			return tmodules.get().stream()
					.map(this.dtoModulesMapper::TmoduleToModulesDto)
					.collect(Collectors.toList());
		}
		throw new NoDataFoundException("Data Not Found");
	}

	/**
	 * Obtiene un módulo por su ID.
	 *
	 * @param id ID del módulo.
	 * @return Objeto ModulesDto correspondiente al ID.
	 * @throws NoDataFoundException si no se encuentra el módulo.
	 */
	@Override
	public ModulesDto getById(Long id) {

		if(id==null ||id<=0){
			throw new NoDataFoundException(id);
		}

		return Optional.of(id)
				.map(this.mapperModules::getById)
				.map(this.dtoModulesMapper::TmoduleToModulesDto)
				.orElseThrow(()-> new NoDataFoundException(id));
	}

	/**
	 * Actualiza un módulo.
	 *
	 * @param modulesDto Objeto ModulesDto con los datos a actualizar.
	 * @return Número de registros actualizados.
	 * @throws ModulesNotUpdateException si los datos del módulo no son válidos.
	 */
	@Override
	public Integer update(ModulesUpdateDto modulesDto) {

		if(modulesDto==null || modulesDto.getId()==null || modulesDto.getId()<=0
			|| modulesDto.getName()==null || modulesDto.getName().trim().isEmpty()){

			throw new ModulesNotUpdateException("Data Modules Not Valid", modulesDto);
		}

		return Optional.of(modulesDto)
				.map(this.dtoModulesMapper::modulesUpdateToTmodule)
				.map(this.mapperModules::update)
				.orElseThrow(()-> new ModulesNotUpdateException("Data Modules Not Valid", modulesDto));
	}


	/**
	 * Guarda un nuevo módulo.
	 *
	 * @param modulesDto Objeto ModulesDto con los datos del módulo a guardar.
	 * @return Número de registros guardados.
	 * @throws ModulesNotSaveException si los datos del módulo no son válidos.
	 */
	@Override
	public Integer save(ModuleSaveDto modulesDto) {

		if(modulesDto==null || modulesDto.name()==null || modulesDto.name().trim().isEmpty()){

			throw new ModulesNotSaveException("Error, Modules Not Saved", modulesDto);
		}

		return Optional.of(modulesDto)
			.map(this.dtoModulesMapper::modulesSaveToTmodule)
			.map(this.mapperModules::insert)
			.orElseThrow(()-> new ModulesNotSaveException("Error, Modules Not Saved", modulesDto))
		;
	}

}
