package com.example.service.user;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.example.entity.user.TtypeUser;
import com.example.mapper.user.MapperTypeUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceTypeUserImpl implements IServiceTypeUser{

	
	private final MapperTypeUser mapperTypeUser;

	@Override
	public List<TtypeUser> getAll() {
		return Collections.emptyList();
	}

	@Override
	public TtypeUser getById(Integer id) {

		if(id==null || id<=0) {
			return null;
		}
		
		return this.mapperTypeUser.getById(id);
	}

	@Override
	public Integer update(TtypeUser t) {

		if(t==null || t.getId()==null || t.getId()<=0) {
			return 0;
		}
		
		if(this.mapperTypeUser.getById(t.getId())==null ) {
			return 0;
		}
		
		return this.mapperTypeUser.update(t);
	}

	@Override
	public Integer save(TtypeUser t) {

		if(t==null ) {
			return 0;
		}
		return this.mapperTypeUser.save(t);
	}





}
