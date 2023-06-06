package com.example.service.appointment;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.example.entity.appointment.Tappointment;
import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;
import com.example.mapper.appointment.MapperAppointment;

import org.springframework.stereotype.Service;

@Service
public class ServiceAppointmentImpl implements IServiceAppointment<Tappointment, Integer,Tappointment,Tappointment> {

	private MapperAppointment mapperAppointment;

	public ServiceAppointmentImpl(MapperAppointment mapperAppointment) {
		this.mapperAppointment = mapperAppointment;
	}

	@Override
	public List<Tappointment> getAll() {

		List<Tappointment> aTappointments = this.mapperAppointment.getAll();

		if (aTappointments == null || aTappointments.size() <= 0) {
			return Collections.emptyList();
		}
		return aTappointments;
	}

	@Override
	public Tappointment getById(Integer id) {

		if (id == null || id <= 0) {
			return null;
		}
		return this.mapperAppointment.getById(id);
	}

	@Override
	public Integer update(Tappointment tappointment) {

		if (tappointment == null || tappointment.getId() == null || tappointment.getId() <= 0) {
			return 0;
		}
		return this.mapperAppointment.update(tappointment);
	}

	@Override
	public Integer save(Tappointment tappointment) {

		if (tappointment == null || tappointment.getId() != null) {
			return 0;
		}
		return this.mapperAppointment.save(tappointment);
	}

	@Override
	public List<Tappointment> getByPatientId(TuserTypeReg tuserTypeReg, Tstatus tstatus) {

		if (tuserTypeReg == null || tuserTypeReg.getId() == null || tuserTypeReg.getId() <= 0 || tstatus == null
				|| tstatus.getId() == null) {
			return Collections.emptyList();
		}

		return this.mapperAppointment.getByPatientId(tuserTypeReg, tstatus);
	}

	@Override
	public List<Tappointment> getBydSpecialityId(Tspeciality tspeciality, Tstatus tstatus) {

		if (tspeciality == null || tspeciality.getId() == null || tspeciality.getId() <= 0 || tstatus == null
				|| tstatus.getId() == null) {
			return Collections.emptyList();
		}
		return this.mapperAppointment.getBydSpecialityId(tspeciality, tstatus);
	}

	@Override
	public List<Tappointment> getBySecretaryId(TuserTypeReg tuserTypeReg, Tstatus tstatus) {

		if (tuserTypeReg == null || tuserTypeReg.getId() == null || tuserTypeReg.getId() <= 0 || tstatus == null
				|| tstatus.getId() == null) {
			return Collections.emptyList();
		}
		return this.mapperAppointment.getBySecretaryId(tuserTypeReg, tstatus);
	}

	@Override
	public List<Tappointment> getBySpecialityPhysicianId(TuserSpeciality tuserSpeciality, Tstatus tstatus) {

		if (tuserSpeciality == null || tuserSpeciality.getId() == null || tuserSpeciality.getId() <= 0
				|| tstatus == null || tstatus.getId() == null) {
			return Collections.emptyList();
		}
		return this.mapperAppointment.getBySpecialityPhysicianId(tuserSpeciality, tstatus);
	}

	@Override
	public List<Tappointment> getByCreationDate(LocalDateTime dateTime, Tstatus tstatus) {

		if (dateTime == null || tstatus == null || tstatus.getId() == null) {
			return Collections.emptyList();
		}
		return this.mapperAppointment.getByCreationDate(dateTime, tstatus);
	}

	@Override
	public List<Tappointment> getByVisitDate(LocalDateTime dateTime, Tstatus tstatus) {

		if (dateTime == null || tstatus == null || tstatus.getId() == null) {
			return Collections.emptyList();
		}

		return this.mapperAppointment.getByVisitDate(dateTime, tstatus);
	}

	@Override
	public List<Tappointment> getByStatusId(Tstatus tstatus) {

		if (tstatus == null || tstatus.getId() == null) {
			return Collections.emptyList();
		}
		return this.mapperAppointment.getByStatusId(tstatus);
	}

	@Override
	public Integer changeStatus(Tappointment tappointment) {

		if (tappointment == null || tappointment.getId() == null || tappointment.getId() <= 0
				|| tappointment.getStatusId() == null || tappointment.getStatusId().getId() == null) {
			return 0;
		}
		return this.mapperAppointment.changeStatus(tappointment);
	}

}
