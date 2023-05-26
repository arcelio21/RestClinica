package com.example.service.modules;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.dto.modules.ModulesDto;
import com.example.dtomapper.modules.ModulesMapper;
import com.example.entity.modules.Tmodule;
import com.example.exception.modules.modules.ModulesNoFoundException;
import com.example.exception.modules.modules.ModulesNotSaveException;
import com.example.exception.modules.modules.ModulesNotUpdateException;
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
		
		Optional<List<Tmodule>> tmodules = Optional.ofNullable(this.mapperModules.getAll());
		
		if(tmodules.isPresent() && !tmodules.get().isEmpty()){
			
			return tmodules.get().stream()
					.map(this.modulesMapper::TmoduleToModulesDto)
					.collect(Collectors.toList());
		}
		
		throw new ModulesNoFoundException("Data Not Found");
	}

	@Override
	public ModulesDto getById(Long id) {
		
		return Optional.of(id)
				.map(this.mapperModules::getById)
				.map(this.modulesMapper::TmoduleToModulesDto)
				.orElseThrow(()-> new ModulesNoFoundException("Id Not Valid",id));
	}

	@Override
	public Integer update(ModulesDto modulesDto) {

		
		//TODO RECORDA IMPLEMENTAR VALIDACION POR GRUPOS EN EL CONTROLLER
		return Optional.of(modulesDto)
				.map(this.modulesMapper::modulesDtoToTmodule)
				.map(this.mapperModules::update)
				.orElseThrow(()-> new ModulesNotUpdateException("Data Modules Not Valid", modulesDto));
	}

	@Override
	public Integer save(ModulesDto modulesDto) {

		return Optional.of(modulesDto)
			.map(this.modulesMapper::modulesDtoToTmodule)
			.map(this.mapperModules::insert)
			.orElseThrow(()-> new ModulesNotSaveException("Error, Modules Not Saved", modulesDto))
		;
	}

}
