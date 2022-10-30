package com.example.service.appointment;

import java.util.Collections;
import java.util.List;

import com.example.entity.appointment.Tappointment;
import com.example.mapper.appointment.MapperAppointment;

public class ServiceAppointmentImpl implements IServiceAppointment<Tappointment, Integer> {

	private MapperAppointment mapperAppointment;

	public ServiceAppointmentImpl(MapperAppointment mapperAppointment) {
		this.mapperAppointment = mapperAppointment;
	}

	@Override
	public List<Tappointment> getAll() {
	
		List<Tappointment> aTappointments = this.mapperAppointment.getAll();

		if(aTappointments==null || aTappointments.size()<=0){
			return Collections.emptyList();
		}
		return aTappointments;
	}

	@Override
	public Tappointment getById(Integer id) {

		if(id==null || id<=0){
			return null;
		}
		return this.mapperAppointment.getById(id);
	}

	@Override
	public Integer update(Tappointment tappointment) {

		if(tappointment==null || tappointment.getId()==null || tappointment.getId()<=0){
			return 0;
		}
		return this.mapperAppointment.update(tappointment);
	}

	@Override
	public Integer save(Tappointment tappointment) {

		if(tappointment==null || tappointment.getId()!=null){
			return 0;
		}
		return this.mapperAppointment.save(tappointment);
	}

}
