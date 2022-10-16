package com.example.service.user;

import java.util.Collections;
import java.util.List;

import com.example.entity.user.TtypeUserModule;
import com.example.mapper.user.MapperTypeUserModule;

public class ServiceTypeUserModuleImpl implements IServiceTypeUserModule {

	private MapperTypeUserModule mapperTypeUserModule;

	@Override
	public List<TtypeUserModule> getAll() {
		// TODO Auto-generated method stubi
		List<TtypeUserModule> ttypeUserModules = this.mapperTypeUserModule.getAll();
		if (ttypeUserModules == null || ttypeUserModules.isEmpty()) {
			return Collections.emptyList();
		}

		return ttypeUserModules;
	}

	@Override
	@Deprecated
	public TtypeUserModule getById(Integer id) {
		// TODO Auto-generated method stub
		if (id == null || id <= 0) {
		    return null;
		}
		return null;
	}

	@Override
	@Deprecated
	public Integer update(TtypeUserModule ttypeUserModule) {
		// TODO Auto-generated method stub
		// if(ttypeUserModule==null || ttypeUserModule.getModulePrivilegeId()){
		    
		// }
		// return this.mapperTypeUserModule.update(ttypeUserModule, modulPrivBef);
	      return null;
	}

	@Override
	public Integer save(TtypeUserModule t) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServiceTtyoeUserModleImpl(MapperTypeUserModule mapperTypeUserModule) {
		this.mapperTypeUserModule = mapperTypeUserModule;
	}

	@Override
	public List<TtypeUserModule> getPrivTypeUser(Integer typeUserId) {
		// TODO Auto-generated method stub
		if(typeUserId==null || typeUserId<=0){
		  return Collections.emptyList();
		}
		return this.mapperTypeUserModule.getPrivTypeUser(typeUserId);
	}

	@Override
	public List<TtypeUserModule> getModulePriv(Integer modulePrivilegeId) {
		// TODO Auto-generated method stub
		if(modulePrivilegeId==null || modulePrivilegeId<=0){
		  return Collections.emptyList();
		}
		return this.mapperTypeUserModule.getModulePriv(modulePrivilegeId);
	}

	@Override
	public Integer update(TtypeUserModule ttypeUserModule, Integer modulPrivBef) {
		// TODO Auto-generated method stub
		if(ttypeUserModule==null || ttypeUserModule.getTypeUser()==null || ttypeUserModule.getTypeUser().getId()==null || ttypeUserModule.getTypeUser().getId()<=0 || modulPrivBef==null || modulPrivBef<=0 ){
		  return 0;
		}

		return this.mapperTypeUserModule.update(ttypeUserModule, modulPrivBef);
	}

}
