package com.example.service.speciality;

import com.example.service.ServiceTemplateCrud;

import java.util.List;

public interface IServiceUserSpeciality<GET, ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET, ID,UPDATE,SAVE> {

	List<GET> getByIdSpeciality(ID idSpeciality);

	GET getByIdUserTypeReg(ID idUserTypeReg);

	List<GET> getByIdStatus(ID idStatus);

}
