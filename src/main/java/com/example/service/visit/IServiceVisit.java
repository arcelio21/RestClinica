package com.example.service.visit;

import java.time.LocalDateTime;
import java.util.List;

import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;
import com.example.service.ServiceTemplateCrud;

public interface IServiceVisit<GET,ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET,ID,UPDATE,SAVE> {

	List<GET> getByPatientId(TuserTypeReg reg, Tstatus tstatus);

	List<GET> getByNurseId(TuserSpeciality tuserSpeciality, Tstatus tstatus);

	List<GET> getByPhysicianId(TuserSpeciality speciality, Tstatus tstatus);

	List<GET> getBySpecialityId(Tspeciality tspeciality, Tstatus tstatus);

	List<GET> getByCreationDate(Tstatus tstatus, LocalDateTime date);

	List<GET> getByStatusId(Tstatus tstatus);

}
