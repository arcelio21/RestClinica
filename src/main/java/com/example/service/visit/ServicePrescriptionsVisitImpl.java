package com.example.service.visit;

import java.util.List;

import com.example.entity.visit.Tprescription;
import com.example.entity.visit.TprescriptionVisit;
import com.example.entity.visit.Tvisit;
import com.example.mapper.visit.MapperPrescriptionVisit;

public class ServicePrescriptionsVisitImpl implements IServicePrescriptionsVisit<TprescriptionVisit, Integer,TprescriptionVisit,TprescriptionVisit> {

	private MapperPrescriptionVisit mapperPrescriptionVisit;

	public ServicePrescriptionsVisitImpl(MapperPrescriptionVisit mapperPrescriptionVisit) {
		this.mapperPrescriptionVisit = mapperPrescriptionVisit;
	}

	@Override
	public List<TprescriptionVisit> getAll() {
		return this.mapperPrescriptionVisit.getAll();
	}

	@Override
	public TprescriptionVisit getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(TprescriptionVisit t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(TprescriptionVisit t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TprescriptionVisit> getByVisitId(Tvisit tvisit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TprescriptionVisit> getByPrescriptionId(Tprescription tprescription) {
		// TODO Auto-generated method stub
		return null;
	}

}
