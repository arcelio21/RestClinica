 package com.example.service.user;

import java.util.List;

import com.example.entity.user.TuserTypeReg;
import com.example.service.ServiceTemplateCrud;

public interface IServiceUserTypeReg<T,I> extends ServiceTemplateCrud<T,I>{

 

	 List<TuserTypeReg> getByIdUserReg(Integer id);

	List<TuserTypeReg> getByIdTypeUser( Integer id);

	 List<TuserTypeReg> getByIdStatus(Integer id);

}


 
