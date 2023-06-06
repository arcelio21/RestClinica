package com.example.service.visit;

import java.util.Collections;
import java.util.List;

import com.example.entity.visit.Tdiagnose;
import com.example.mapper.visit.MapperDiagnoses;

import org.springframework.stereotype.Service;

@Service
public class ServiceDiagnosesImpl implements IServiceDiagnoses<Tdiagnose,Integer,Tdiagnose,Tdiagnose>{

	private MapperDiagnoses mapperDiagnoses;

	public ServiceDiagnosesImpl(MapperDiagnoses mapperDiagnoses) {
		this.mapperDiagnoses = mapperDiagnoses;
	}

	@Override
	public List<Tdiagnose> getAll() {

		List<Tdiagnose> diagnoses= this.mapperDiagnoses.getAll();

		if(diagnoses==null || diagnoses.size()<=0){
			return Collections.emptyList();
		}
		return diagnoses;
	}

	@Override
	public Tdiagnose getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperDiagnoses.getById(id);
	}

	@Override
	public Integer update(Tdiagnose tdiagnose) {

		if(tdiagnose==null || tdiagnose.getId()==null || tdiagnose.getId()<=0){
			return 0;
		}
		return this.mapperDiagnoses.update(tdiagnose);
	}

	@Override
	public Integer save(Tdiagnose tdiagnose) {

		if(tdiagnose==null || tdiagnose.getId()!=null){
			return 0;
		}
		return this.mapperDiagnoses.save(tdiagnose);
	}


}
