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
		// TODO Auto-generated method stub
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
	public Integer update(TuserTypeReg t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(TuserTypeReg t) {
		// TODO Auto-generated method stub
		return null;
	}

}
