package com.example.service.visit;

import java.util.Collections;
import java.util.List;

import com.example.entity.visit.Tsymptom;
import com.example.mapper.visit.MapperSymptoms;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceSymptomsImpl implements IServiceSymptoms<Tsymptom,Integer> {

	private MapperSymptoms mapperSymptoms;

	public ServiceSymptomsImpl(MapperSymptoms mapperSymptoms) {
		this.mapperSymptoms = mapperSymptoms;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tsymptom> getAll() {

		List<Tsymptom> tsymptoms= this.mapperSymptoms.getAll();
		if(tsymptoms==null || tsymptoms.size()<=0){
			return Collections.emptyList();
		}
		return tsymptoms;
	}

	@Override
	@Transactional(readOnly = true)
	public Tsymptom getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperSymptoms.getById(id);
	}

	@Override
	@Transactional
	public Integer update(Tsymptom tsymptom) {

		if(tsymptom==null || tsymptom.getId()==null || tsymptom.getId()<=0){
			return 0;
		}
		return this.mapperSymptoms.update(tsymptom);
	}

	@Override
	@Transactional
	public Integer save(Tsymptom tsymptom) {

		if(tsymptom==null || tsymptom.getId()!=null){
			return 0;
		}
		return this.mapperSymptoms.save(tsymptom);
	}


}
