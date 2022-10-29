package com.example.service.user;

import java.util.Collections;
import java.util.List;

import com.example.entity.user.TtypeUserModule;
import com.example.mapper.user.MapperTypeUserModule;

public class ServiceTypeUserModuleImpl implements IServiceTypeUserModule {

	private MapperTypeUserModule mapperTypeUserModule;

	public ServiceTypeUserModuleImpl(MapperTypeUserModule mapperTypeUserModule) {
		this.mapperTypeUserModule = mapperTypeUserModule;
	}

	@Override
	public List<TtypeUserModule> getAll() {
		List<TtypeUserModule> ttypeUserModules = this.mapperTypeUserModule.getAll();
		if (ttypeUserModules == null || ttypeUserModules.isEmpty()) {
			return Collections.emptyList();
		}

		return ttypeUserModules;
	}

	@Override
	@Deprecated
	public TtypeUserModule getById(Integer id) {
		if (id == null || id <= 0) {
		    return null;
		}
		return null;
	}

	@Override
	@Deprecated
	public Integer update(TtypeUserModule ttypeUserModule) {
		// if(ttypeUserModule==null || ttypeUserModule.getModulePrivilegeId()){
		    
		// }
		// return this.mapperTypeUserModule.update(ttypeUserModule, modulPrivBef);
	      return null;
	}

	@Override
	public Integer save(TtypeUserModule ttypeUserModule) {
		if(ttypeUserModule==null){
			return 0;
		}
		return this.mapperTypeUserModule.save(ttypeUserModule);
	}

	

	@Override
	public List<TtypeUserModule> getPrivTypeUser(Integer typeUserId) {
		if(typeUserId==null || typeUserId<=0){
		  return Collections.emptyList();
		}
		return this.mapperTypeUserModule.getPrivTypeUser(typeUserId);
	}

	@Override
	public List<TtypeUserModule> getModulePriv(Integer modulePrivilegeId) {
		if(modulePrivilegeId==null || modulePrivilegeId<=0){
		  return Collections.emptyList();
		}
		return this.mapperTypeUserModule.getModulePriv(modulePrivilegeId);
	}

	@Override
	public Integer update(TtypeUserModule ttypeUserModule, Integer modulPrivBef) {
		if(ttypeUserModule==null || ttypeUserModule.getTypeUser()==null || ttypeUserModule.getTypeUser().getId()==null || ttypeUserModule.getTypeUser().getId()<=0 || modulPrivBef==null || modulPrivBef<=0 ){
		  return 0;
		}

		return this.mapperTypeUserModule.update(ttypeUserModule, modulPrivBef);
	}

}
