package com.example.service.modules;

import java.util.Collections;
import java.util.List;
import com.example.entity.modules.Tprivilege;
import com.example.mapper.modules.MapperPrivilege;

public class ServicePrivilegeImpl implements IServicePrivilege{

	private MapperPrivilege mapperPrivilege;	
	
	public ServicePrivilegeImpl(MapperPrivilege mapperPrivilege) {
		this.mapperPrivilege = mapperPrivilege;
	}

	@Override
	public List<Tprivilege> getAll() {

		List<Tprivilege> privileges=this.mapperPrivilege.getAll();
		if(privileges==null || privileges.isEmpty()) {
			return Collections.emptyList();
		}
		return privileges;
	}

	@Override
	public Tprivilege getById(Integer id) {

		if(id==null || id<=0) {
			return null;
		}
		
		return this.mapperPrivilege.getByid(id);
	}

	@Override
	public Integer update(Tprivilege tprivilege) {

		if(tprivilege==null || tprivilege.getId()==null || tprivilege.getId()<=0) {
			return 0; 
		}
		
		return this.mapperPrivilege.update(tprivilege);
	}

	@Override
	public Integer save(Tprivilege tprivilege){
	    if(tprivilege==null){
	    	return 0;
	    }
	    
	    if(tprivilege.getId()!=null || tprivilege.getId()>=0) {
	    	return 0;
	    }

	    return this.mapperPrivilege.save(tprivilege);
	}
}
