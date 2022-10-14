package com.example.service.status;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.entity.status.Tstatus;
import com.example.mapper.status.MapperStatus;

@Service
public class ServiceStatusImpl implements IServiceStatus{

	private MapperStatus mapperStatus;
	
	
	
	public ServiceStatusImpl(MapperStatus mapperStatus) {
		this.mapperStatus = mapperStatus;
	}

	@Override
	public List<Tstatus> getAll() {
		
		List<Tstatus> status=this.mapperStatus.getAll();
		if(status==null || status.isEmpty()){
			return Collections.emptyList();
		}
		
		return status;
	}

	@Override
	public Tstatus getById(Integer id) {
		
		if(id==null || id<=0){
			return null;
		}
		
		return this.mapperStatus.getById(id);
	}

	@Override
	public Integer update(Tstatus t) {

		if(t==null || t.getId()==null || t.getId()>=0){
			return 0;
		}
		
		return this.mapperStatus.update(t);
	}

	@Override
	public Integer save(@Valid Tstatus t) {

		if(t==null || t.getName()==null){
			return 0;
		}
		
		return this.mapperStatus.save(t);
	}

	@Override
	public List<Tstatus> getByName(String name) {
		
		if(name==null || name.trim().equals("")) {
			return Collections.emptyList();
		}
		
		return this.mapperStatus.getByName(name);
	}

}
