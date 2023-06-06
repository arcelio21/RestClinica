package com.example.service.appointment;

import java.time.LocalDateTime;
import java.util.List;

import com.example.entity.appointment.Tappointment;
import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;
import com.example.service.ServiceTemplateCrud;

public interface IServiceAppointment<GET,ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET,ID,UPDATE,SAVE> {

	List<Tappointment> getByPatientId(TuserTypeReg tuserTypeReg, Tstatus tstatus);

	List<Tappointment> getBydSpecialityId(Tspeciality tspeciality, Tstatus tstatus);

	List<Tappointment> getBySecretaryId(TuserTypeReg tuserTypeReg, Tstatus tstatus);

	List<Tappointment> getBySpecialityPhysicianId(TuserSpeciality tuserSpeciality, Tstatus tstatus);

	List<Tappointment> getByCreationDate(LocalDateTime dateTime, Tstatus tstatus);

	List<Tappointment> getByVisitDate(LocalDateTime dateTime, Tstatus tstatus);

	List<Tappointment> getByStatusId(Tstatus tstatus);

	Integer changeStatus(Tappointment tappointment);

}
