package com.example.service.modules;

import java.util.Collections;
import java.util.List;

import com.example.entity.modules.Tmodule;
import com.example.mapper.modules.MapperModules;

public class ServiceModuleImple implements IServiceModule{

	private MapperModules mapperModules;
	
	
	public ServiceModuleImple(MapperModules mapperModules) {
		this.mapperModules = mapperModules;
	}

	@Override
	public List<Tmodule> getAll() {

		List<Tmodule> modules=this.mapperModules.getAll();
		
		if(modules==null || modules.isEmpty()) {
			return Collections.emptyList();
		}
		return modules;
	}

	@Override
	public Tmodule getById(Integer id) {

		if(id==null || id<=0) {
			return null;
		}
		
		return this.mapperModules.getById(id);
	}

	@Override
	public Integer update(Tmodule tmodule) {

		if(tmodule==null || tmodule.getId()==null || tmodule.getId()<=0) {
			return 0;
		}
		return this.mapperModules.update(tmodule);
	}

	@Override
	public Integer save(Tmodule tmodule) {

		if(tmodule==null) {
			return 0;
		}
		
		if(tmodule.getId()!=null && tmodule.getId()<=0) {
			return 0;
		}
		return this.mapperModules.insert(tmodule);
	}

}
