package com.example.service.visit;

import java.util.Collections;
import java.util.List;

import com.example.entity.visit.TvitalSign;
import com.example.mapper.visit.MapperVitalSign;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceVitalSignImpl implements IServiceVitalSign<TvitalSign,Integer,TvitalSign,TvitalSign>{

	private MapperVitalSign mapperVitalSign;

	public ServiceVitalSignImpl(MapperVitalSign mapperVitalSign) {
		this.mapperVitalSign = mapperVitalSign;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TvitalSign> getAll() {

		List<TvitalSign> tvitalSigns = this.mapperVitalSign.getAll();

		if(tvitalSigns==null || tvitalSigns.size()<=0){
			return Collections.emptyList();
		}
		return tvitalSigns;
	}

	@Transactional(readOnly = true)
	@Override
	public TvitalSign getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}

		return this.mapperVitalSign.getById(id);
	}

	@Override
	@Transactional
	public Integer update(TvitalSign tvitalSign) {

		if(tvitalSign==null || tvitalSign.getId()==null || tvitalSign.getId()<=0){
			return 0;
		}
		return this.mapperVitalSign.update(tvitalSign);
	}

	@Override
	@Transactional
	public Integer save(TvitalSign tvitalSign) {

		if(tvitalSign==null || tvitalSign.getId()!=null){
			return 0;
		}
		return this.mapperVitalSign.save(tvitalSign);
	}

}
