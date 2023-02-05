package com.example.service.modules;

import java.util.Collections;
import java.util.List;

import com.example.entity.modules.TmodulePrivilege;
import com.example.mapper.modules.MapperModulePrivilege;

import org.springframework.stereotype.Service;

@Service
public class ServiceModulePrivilegeImpl implements IServiceModulePrivilege{
	
	private MapperModulePrivilege mapperModulePrivilege;
	
	
	public ServiceModulePrivilegeImpl(MapperModulePrivilege mapperModulePrivilege) {
		this.mapperModulePrivilege = mapperModulePrivilege;
	}

	@Override
	public List<TmodulePrivilege> getAll() {

		List<TmodulePrivilege> modulesPriv=this.mapperModulePrivilege.getAll();
		if(modulesPriv==null || modulesPriv.isEmpty()) {
			return Collections.emptyList();
		}
		
		return modulesPriv;
	}

	@Override
	public TmodulePrivilege getById(Integer id) {

		if(id==null || id<=0) {
			return null;
		}
		
		return this.mapperModulePrivilege.getById(id);
	}

	@Override
	public Integer update(TmodulePrivilege tmodulePrivilege) {

		if(tmodulePrivilege==null || tmodulePrivilege.getId()==null || tmodulePrivilege.getId()<=0) {
			return 0;
		}
		return this.mapperModulePrivilege.update(tmodulePrivilege);
	}

	@Override
	public Integer save(TmodulePrivilege tmodulePrivilege) {

		if(tmodulePrivilege==null || tmodulePrivilege.getId()<=0) {
			return 0;
		}
		return this.mapperModulePrivilege.save(tmodulePrivilege);
	}

}
