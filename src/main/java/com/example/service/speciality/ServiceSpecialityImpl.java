package com.example.service.speciality;

import java.util.Collections;
import java.util.List;

import com.example.entity.speciality.Tspeciality;
import com.example.mapper.speciality.MapperSpeciality;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceSpecialityImpl implements IServiceSpeciality<Tspeciality,Integer>{

	private MapperSpeciality mapperSpeciality;

	public ServiceSpecialityImpl(MapperSpeciality mapperSpeciality) {
		this.mapperSpeciality = mapperSpeciality;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Tspeciality> getAll() {

		List<Tspeciality> sTspecialities = this.mapperSpeciality.getAll();
		if(sTspecialities==null || sTspecialities.size()<=0){
			return Collections.emptyList();
		}
		return sTspecialities;
	}

	@Override
	@Transactional(readOnly = true)
	public Tspeciality getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperSpeciality.getById(id);
	}

	@Override
	public Integer update(Tspeciality tspeciality) {

		if(tspeciality==null || tspeciality.getId()==null || tspeciality.getId()<=0){
			return 0;
		}
		return this.mapperSpeciality.update(tspeciality);
	}

	@Override
	public Integer save(Tspeciality tspeciality) {

		if(tspeciality==null || tspeciality.getId()!=null){
			return 0;
		}
		return this.mapperSpeciality.save(tspeciality);
	} 


}
