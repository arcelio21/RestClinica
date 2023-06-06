package com.example.service.visit;

import java.util.Collections;
import java.util.List;

import com.example.entity.visit.Tdiagnose;
import com.example.entity.visit.TdiagnoseVisit;
import com.example.entity.visit.Tvisit;
import com.example.mapper.visit.MapperDiagnosesVisit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceDiagnosesVisit implements IServiceDiagnosesVisit<TdiagnoseVisit,Integer,TdiagnoseVisit,TdiagnoseVisit>{

	private MapperDiagnosesVisit mapperDiagnosesVisit;

	public ServiceDiagnosesVisit(MapperDiagnosesVisit mapperDiagnosesVisit) {
		this.mapperDiagnosesVisit = mapperDiagnosesVisit;
	}

	@Transactional(readOnly = true)
	@Override
	public List<TdiagnoseVisit> getAll() {

		List<TdiagnoseVisit> diagnosesVisits = this.mapperDiagnosesVisit.getAll();

		if(diagnosesVisits==null || diagnosesVisits.size()<=0){
			return Collections.emptyList();	
		}
		return diagnosesVisits;
	}

	@Transactional(readOnly = true)
	@Override
	public TdiagnoseVisit getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperDiagnosesVisit.getById(id);
	}

	@Transactional
	@Override
	public Integer update(TdiagnoseVisit tdiagnoseVisit) {

		if(tdiagnoseVisit==null || tdiagnoseVisit.getId()==null || tdiagnoseVisit.getId()<=0){
			return 0;
		}	
		return this.mapperDiagnosesVisit.update(tdiagnoseVisit);
	}

	@Transactional
	@Override
	public Integer save(TdiagnoseVisit tdiagnoseVisit) {

		if(tdiagnoseVisit==null || tdiagnoseVisit.getId()!=null){
			return 0;
		}
		return this.mapperDiagnosesVisit.save(tdiagnoseVisit);
	}

	@Transactional(readOnly = true)
	@Override
	public List<TdiagnoseVisit> getByVisitId(Tvisit tvisit) {

		if(tvisit==null || tvisit.getId()==null || tvisit.getId()<=0){
			return Collections.emptyList();
		}
		return this.mapperDiagnosesVisit.getByVisitId(tvisit);
	}

	@Transactional(readOnly =  true)
	@Override
	public List<TdiagnoseVisit> getByDiagnosesId(Tdiagnose tdiagnose) {

		if(tdiagnose==null || tdiagnose.getId()==null || tdiagnose.getId()<=0){
			return Collections.emptyList();
		}
		return this.mapperDiagnosesVisit.getByDiagnosesId(tdiagnose);
	}


}
