package com.example.service.speciality;

import java.util.List;

import com.example.entity.speciality.TuserSpeciality;
import com.example.mapper.speciality.MapperUserSpeciality;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceUserSpiciality implements IServiceUserSpeciality<TuserSpeciality,Integer>{

	private MapperUserSpeciality mapperUserSpeciality;

	public ServiceUserSpiciality(MapperUserSpeciality mapperUserSpeciality) {
		this.mapperUserSpeciality = mapperUserSpeciality;
	}

	@Transactional(readOnly = true)
	@Override
	public List<TuserSpeciality> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public TuserSpeciality getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Integer update(TuserSpeciality t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Integer save(TuserSpeciality t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public List<TuserSpeciality> getByIdSpeciality(Integer idSpeciality) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public TuserSpeciality getByIdUserTypeReg(Integer idUserTypeReg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public List<TuserSpeciality> getByIdStatus(Integer idStatus) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
