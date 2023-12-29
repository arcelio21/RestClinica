package com.example.service.speciality;

import com.example.dto.speciality.userspeciality.UserSpecialityBySpecialityGetDto;
import com.example.dto.speciality.userspeciality.UserSpecialityByStatusGetDto;
import com.example.dto.speciality.userspeciality.UserSpecialityByTypeUserGetDto;
import com.example.dto.speciality.userspeciality.UserSpecialityByUserTypeRegGetDto;
import com.example.service.ServiceTemplateCrud;

import java.util.List;

public interface IServiceUserSpeciality<GET, ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET, ID,UPDATE,SAVE> {

	List<UserSpecialityBySpecialityGetDto> getByIdSpeciality(Integer idSpeciality);
	List<UserSpecialityByUserTypeRegGetDto> getByIdUserTypeReg(Integer idUserTypeReg);
	List<UserSpecialityByStatusGetDto> getByIdStatus(Integer idStatus);
	List<UserSpecialityByStatusGetDto> getByIdStatusActivated();
	List<UserSpecialityByTypeUserGetDto> getByTypeUserId(Integer idTypeUser);


}
