 package com.example.service.user;

import java.util.List;

import com.example.entity.user.TuserTypeReg;
import com.example.service.ServiceTemplateCrud;

public interface IServiceUserTypeReg<GET,ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET,ID,UPDATE,SAVE>{

 

	 List<GET> getByIdUserReg(ID id);

	List<GET> getByIdTypeUser( ID id);

	 List<GET> getByIdStatus(ID id);

}


 
