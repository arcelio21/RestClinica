package com.example.service.visit;

import java.util.Collections;
import java.util.List;

import com.example.entity.visit.Ttest;
import com.example.mapper.visit.MapperTest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceTestImpl implements IServiceTest<Ttest,Integer>{

	private MapperTest mapperTest;

	public ServiceTestImpl(MapperTest mapperTest) {
		this.mapperTest = mapperTest;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ttest> getAll() {

		List<Ttest> ttests = this.mapperTest.getAll();

		if(ttests==null || ttests.size()<=0){
			return Collections.emptyList();
		}
		return ttests;
	}

	@Override
	@Transactional(readOnly = true)
	public Ttest getById(Integer id) {

		if(id==null || id<=0 ){
			return null;
		}
		return this.mapperTest.getById(id);
	}

	@Override
	@Transactional
	public Integer update(Ttest ttest) {

		if( ttest==null || ttest.getId()==null || ttest.getId() <=0  ){
			return 0;
		}
		return this.mapperTest.save(ttest);
	}

	@Override
	@Transactional
	public Integer save(Ttest ttest) {

		if( ttest==null || ttest.getId()!=null){
			return 0;
		}


		return this.mapperTest.save(ttest);
	}

}
