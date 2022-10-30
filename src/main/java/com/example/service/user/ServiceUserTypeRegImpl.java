package com.example.service.user;

import java.util.Collections;
import java.util.List;

import com.example.entity.user.TuserTypeReg;
import com.example.mapper.user.MapperUserTypeReg;

public class ServiceUserTypeRegImpl implements IServiceUserTypeReg<TuserTypeReg, Integer> {

	private MapperUserTypeReg mapperUserTypeReg;

	public ServiceUserTypeRegImpl(MapperUserTypeReg mapperUserTypeReg) {
		this.mapperUserTypeReg = mapperUserTypeReg;
	}

	@Override
	public List<TuserTypeReg> getAll() {
		List<TuserTypeReg> tuserTypeRegs= this.mapperUserTypeReg.getAll();
		if(tuserTypeRegs.size()<=0){
		  return Collections.emptyList();
		}
		return tuserTypeRegs;
	}

	@Override
	public TuserTypeReg getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperUserTypeReg.getById(id);
	}

	@Override
	public Integer update(TuserTypeReg tuserTypeReg) {

		if(tuserTypeReg==null || tuserTypeReg.getId()==null || tuserTypeReg.getId()<=0){
			return 0;
		}
		return this.mapperUserTypeReg.update(tuserTypeReg);     
	}

	@Override
	public Integer save(TuserTypeReg tuserTypeReg) {

		if(tuserTypeReg==null){
			return 0;
		}
		return this.mapperUserTypeReg.save(tuserTypeReg);
	}

}
