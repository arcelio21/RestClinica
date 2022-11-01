package com.example.service.visit;

import java.util.Collections;
import java.util.List;

import com.example.entity.visit.Tsymptom;
import com.example.entity.visit.TsymptomVisit;
import com.example.entity.visit.Tvisit;
import com.example.mapper.visit.MapperSymptomVisit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceSymptomVisitImpl implements IServiceSymptomVisit<TsymptomVisit,Integer> {

	private MapperSymptomVisit mapperSymptomVisit;


	public ServiceSymptomVisitImpl(MapperSymptomVisit mapperSymptomVisit) {
		this.mapperSymptomVisit = mapperSymptomVisit;
	}


	@Override
	@Transactional(readOnly = true)
	public List<TsymptomVisit> getAll() {

		List<TsymptomVisit> tsymptomVisits = this.mapperSymptomVisit.getAll();

		if(tsymptomVisits==null || tsymptomVisits.size()<=0){
			return Collections.emptyList();
		}
		return tsymptomVisits;
	}


	@Override
	@Transactional(readOnly = true)
	public TsymptomVisit getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperSymptomVisit.getById(id);
	}


	@Override
	@Transactional
	public Integer update(TsymptomVisit tsymptomVisit) {

		if(tsymptomVisit==null || tsymptomVisit.getId()==null || tsymptomVisit.getId()<=0){
			return 0;
		}
		return this.mapperSymptomVisit.update(tsymptomVisit);
	}


	@Override
	@Transactional
	public Integer save(TsymptomVisit tsymptomVisit) {

		if(tsymptomVisit==null || tsymptomVisit.getId()!=null){
			return 0;
		}
		return this.mapperSymptomVisit.save(tsymptomVisit);
	}


	@Override
	@Transactional(readOnly = true)
	public List<TsymptomVisit> getByVisitId(Tvisit tvisit) {

		if(tvisit==null || tvisit.getId()==null || tvisit.getId()<=0){
			return Collections.emptyList();
		}
		return this.mapperSymptomVisit.getByVisitId(tvisit);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<TsymptomVisit> getBySymptomId(Tsymptom tsymptom) {
	
		if(tsymptom==null || tsymptom.getId()==null || tsymptom.getId()<=0){
			return Collections.emptyList();
		}
		return this.mapperSymptomVisit.getBySymptomId(tsymptom);
	}


}
