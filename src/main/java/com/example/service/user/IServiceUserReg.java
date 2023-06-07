package com.example.service.user;

import com.example.dto.user.user_reg.UserUpdatePassDto;
import com.example.service.ServiceTemplateCrud;

import java.util.List;

public interface IServiceUserReg<GET,ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET,ID,UPDATE,SAVE>{

	List<GET> getByName(String name);
	Integer updatePassword(UserUpdatePassDto user);
	
}
