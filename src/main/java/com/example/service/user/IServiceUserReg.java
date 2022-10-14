package com.example.service.user;

import java.util.List;

import com.example.entity.user.TuserReg;
import com.example.service.ServiceTemplateCrud;

public interface IServiceUserReg extends ServiceTemplateCrud<TuserReg, Integer>{

	List<TuserReg> getByName(String name);
	TuserReg validateAccount(TuserReg user);
	Integer updatePassword(TuserReg user);
	
}
