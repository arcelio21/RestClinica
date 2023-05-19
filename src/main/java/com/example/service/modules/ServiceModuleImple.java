package com.example.service.modules;

import java.util.List;
import java.util.Optional;

import com.example.dto.modules.ModulesDto;
import com.example.dtomapper.modules.ModulesMapper;
import com.example.entity.modules.Tmodule;
import com.example.exception.modules.modules.ModulesNoFoundException;
import com.example.mapper.modules.MapperModules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ServiceModuleImple implements IServiceModule{

	private MapperModules mapperModules;
	private ModulesMapper modulesMapper;
	
	
	

	@Override
	public List<ModulesDto> getAll() {

		return Optional.of(this.mapperModules.getAll())
				.orElseThrow( ()-> new ModulesNoFoundException("Data Not Found"))
				.stream()
				.map(this.modulesMapper::TmoduleToModulesDto)
				.toList();
	}

	@Override
	public ModulesDto getById(Long id) {
		
		return Optional.of(id)
				.map(this.mapperModules::getById)
				.map(this.modulesMapper::TmoduleToModulesDto)
				.orElseThrow(()-> new ModulesNoFoundException("Id no encontrado",id));
	}

	@Override
	public Integer update(ModulesDto modulesDto) {

		
		//TODO RECORDA IMPLEMENTAR VALIDACION POR GRUPOS EN EL CONTROLLER
		return null;
	}

	@Override
	public Integer save(ModulesDto modulesDto) {

		return null;
	}

}
