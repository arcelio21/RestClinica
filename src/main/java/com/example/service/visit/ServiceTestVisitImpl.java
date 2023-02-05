package com.example.service.visit;

import java.util.Collections;
import java.util.List;

import com.example.entity.visit.Ttest;
import com.example.entity.visit.TtestVisit;
import com.example.entity.visit.Tvisit;
import com.example.mapper.visit.MapperTestVisit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceTestVisitImpl implements IServiceTestVisit<TtestVisit,Integer> {

	private MapperTestVisit mapperTestVisit;

	public ServiceTestVisitImpl(MapperTestVisit mapperTestVisit) {
		this.mapperTestVisit = mapperTestVisit;
	}

	@Transactional(readOnly = true)
	@Override
	public List<TtestVisit> getAll() {

		List<TtestVisit> ttestVisits = this.mapperTestVisit.getAll();

		if(ttestVisits==null || ttestVisits.size()<=0){
			return Collections.emptyList();
		}
		return ttestVisits;
	}

	@Transactional(readOnly = true)
	@Override
	public TtestVisit getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperTestVisit.getById(id);
	}

	@Transactional
	@Override
	public Integer update(TtestVisit ttestVisit) {

		if(ttestVisit==null || ttestVisit.getId()==null || ttestVisit.getId()<=0){
			return 0;
		}

		return this.mapperTestVisit.update(ttestVisit);
	}

	@Override
	@Transactional
	public Integer save(TtestVisit ttestVisit) {

		if(ttestVisit == null || ttestVisit.getId()!=null){
			return 0;
		}
		return this.mapperTestVisit.save(ttestVisit);
	}

	@Transactional(readOnly = true)
	@Override
	public List<TtestVisit> getByVisitId(Tvisit tvisit) {

		if(tvisit==null || tvisit.getId()==null || tvisit.getId()<=0){
			return Collections.emptyList();
		}

		List<TtestVisit> ttestVisits = this.mapperTestVisit.getByVisitId(tvisit);

		if(ttestVisits==null || ttestVisits.size()<=0){
			return Collections.emptyList();
		}
		return ttestVisits;
	}

	@Transactional(readOnly = true)
	@Override
	public List<TtestVisit> getByTestId(Ttest ttest) {

		if(ttest==null || ttest.getId()==null || ttest.getId()<=0){
			return Collections.emptyList();
		}

		List<TtestVisit> ttestVisits = this.mapperTestVisit.getByTestId(ttest);

		if(ttestVisits==null || ttestVisits.size()<=0){
			return Collections.emptyList();
		}
		return ttestVisits;
	}

}
