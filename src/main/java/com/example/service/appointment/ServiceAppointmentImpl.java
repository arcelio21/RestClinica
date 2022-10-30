package com.example.service.appointment;

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
		return null;
	}

	@Override
	public Tappointment getById(Integer id) {
		return null;
	}

	@Override
	public Integer update(Tappointment t) {
		return null;
	}

	@Override
	public Integer save(Tappointment t) {
		return null;
	}

}
