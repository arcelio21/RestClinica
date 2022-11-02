package com.example.service.visit;

import java.time.LocalDateTime;
import java.util.List;

import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;
import com.example.service.ServiceTemplateCrud;

public interface IServiceVisit<T, I> extends ServiceTemplateCrud<T, I> {

	List<T> getByPatientId(TuserTypeReg reg, Tstatus tstatus);

	List<T> getByNurseId(TuserSpeciality tuserSpeciality, Tstatus tstatus);

	List<T> getByPhysicianId(TuserSpeciality speciality, Tstatus tstatus);

	List<T> getBySpecialityId(Tspeciality tspeciality, Tstatus tstatus);

	List<T> getByCreationDate(Tstatus tstatus, LocalDateTime date);

	List<T> getByStatusId(Tstatus tstatus);

}
