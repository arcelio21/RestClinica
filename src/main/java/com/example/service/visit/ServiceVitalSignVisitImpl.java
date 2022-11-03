package com.example.service.visit;

import java.util.Collections;
import java.util.List;

import com.example.entity.visit.Tvisit;
import com.example.entity.visit.TvitalSign;
import com.example.entity.visit.TvitalSignVisit;
import com.example.mapper.visit.MapperVitalSignVisit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceVitalSignVisitImpl implements IServiceVitalSignVisit<TvitalSignVisit,Integer> {

	private MapperVitalSignVisit mapperVitalSignVisit;

	public ServiceVitalSignVisitImpl(MapperVitalSignVisit mapperVitalSignVisit) {
		this.mapperVitalSignVisit = mapperVitalSignVisit;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TvitalSignVisit> getAll() {

		List<TvitalSignVisit> tvitalSignVisits = this.mapperVitalSignVisit.getAll();

		if(tvitalSignVisits==null || tvitalSignVisits.size()<=0){
			return Collections.emptyList();
		}
		return tvitalSignVisits;
	}

	@Override
	@Transactional(readOnly = true)
	public TvitalSignVisit getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperVitalSignVisit.getById(id);
	}

	@Override
	@Transactional
	public Integer update(TvitalSignVisit tvitalSignVisit) {

		if(tvitalSignVisit==null || tvitalSignVisit.getId()==null || tvitalSignVisit.getId()<=0){
			return 0;
		}
		return this.mapperVitalSignVisit.update(tvitalSignVisit);
	}

	@Override
	@Transactional
	public Integer save(TvitalSignVisit tvitalSignVisit) {

		if(tvitalSignVisit==null || tvitalSignVisit.getId()!=null){
			return 0;
		}
		return this.mapperVitalSignVisit.save(tvitalSignVisit);
	}

	
	@Override
	@Transactional
	public List<TvitalSignVisit> getByVisitId(Tvisit tvisit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TvitalSignVisit> getByVitalSignId(TvitalSign sign) {
		// TODO Auto-generated method stub
		return null;
	}


}
