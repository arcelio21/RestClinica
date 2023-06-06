package com.example.service.visit;

import java.util.Collections;
import java.util.List;

import com.example.entity.visit.Tprescription;
import com.example.mapper.visit.MapperPrescriptions;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicePrescriptionsImpl implements IServicePrescriptions<Tprescription,Integer,Tprescription,Tprescription> {

	private MapperPrescriptions mapperPrescriptions;

	public ServicePrescriptionsImpl(MapperPrescriptions mapperPrescriptions) {
		this.mapperPrescriptions = mapperPrescriptions;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Tprescription> getAll() {
	
		List<Tprescription> tprescriptions = this.mapperPrescriptions.getAll();

		if(tprescriptions==null || tprescriptions.size()<=0){
			return Collections.emptyList();
		}
		return tprescriptions;
	}

	@Override
	@Transactional(readOnly = true)
	public Tprescription getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperPrescriptions.getById(id);
	}

	@Override
	@Transactional
	public Integer update(Tprescription tprescription) {

		if(tprescription==null || tprescription.getId()==null || tprescription.getId()<=0){
			return 0;
		}	
		return this.mapperPrescriptions.update(tprescription);
	}

	@Override
	@Transactional
	public Integer save(Tprescription tprescription) {

		if(tprescription==null || tprescription.getId()!=null){
			return 0;
		}
		return this.mapperPrescriptions.save(tprescription);
	}


}
