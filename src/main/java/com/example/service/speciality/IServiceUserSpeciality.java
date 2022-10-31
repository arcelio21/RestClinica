package com.example.service.speciality;

import java.util.List;

import com.example.entity.speciality.TuserSpeciality;
import com.example.service.ServiceTemplateCrud;

public interface IServiceUserSpeciality<T, I> extends ServiceTemplateCrud<T, I> {

	List<TuserSpeciality> getByIdSpeciality(I idSpeciality);

	TuserSpeciality getByIdUserTypeReg(I idUserTypeReg);

	List<TuserSpeciality> getByIdStatus(I idStatus);

}
