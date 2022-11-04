package com.example.service.speciality;

import java.util.Collections;
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
		
		List<TuserSpeciality> userSpecialities = this.mapperUserSpeciality.getAll();
		if(userSpecialities==null || userSpecialities.size()<=0){
			return Collections.emptyList();
		}
		return userSpecialities;
	}

	@Transactional(readOnly = true)
	@Override
	public TuserSpeciality getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperUserSpeciality.getById(id);
	}

	@Override
	@Transactional
	public Integer update(TuserSpeciality tuserSpeciality) {

		if(tuserSpeciality==null || tuserSpeciality.getId()==null || tuserSpeciality.getId()<=0){
			return 0;
		}
		return this.mapperUserSpeciality.update(tuserSpeciality);
	}

	@Transactional
	@Override
	public Integer save(TuserSpeciality tuserSpeciality) {

		if(tuserSpeciality==null || tuserSpeciality.getId()!=null){
			return 0;
		}
		return this.mapperUserSpeciality.save(tuserSpeciality);
	}

	@Transactional(readOnly = true)
	@Override
	public List<TuserSpeciality> getByIdSpeciality(Integer idSpeciality) {

		if(idSpeciality==null || idSpeciality<=0){
			return Collections.emptyList();
		}

		List<TuserSpeciality> userSpecialities = this.mapperUserSpeciality.getByIdSpeciality(idSpeciality);

		if(userSpecialities==null || userSpecialities.size()<=0){
			return Collections.emptyList();
		}
		return userSpecialities;
	}

	@Transactional(readOnly = true)
	@Override
	public TuserSpeciality getByIdUserTypeReg(Integer idUserTypeReg) {

		if(idUserTypeReg==null || idUserTypeReg<=0){
			return null;
		}
	
		TuserSpeciality userSpecialities= this.mapperUserSpeciality.getByIdUserTypeReg(idUserTypeReg);
		
		return userSpecialities;
	}

	@Transactional(readOnly = true)
	@Override
	public List<TuserSpeciality> getByIdStatus(Integer idStatus) {

		if(idStatus==null || idStatus<=0){
			return Collections.emptyList();
		}
		
		List<TuserSpeciality> userSpecialities = this.mapperUserSpeciality.getByIdStatus(idStatus);
		if(userSpecialities==null || userSpecialities.size()<=0){
			return Collections.emptyList();
		}
		return userSpecialities;
	}

	
}
