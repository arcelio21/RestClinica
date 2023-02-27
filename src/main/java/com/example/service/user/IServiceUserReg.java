package com.example.service.user;

import java.util.List;

import com.example.dto.AuthenticationRequest;
import com.example.dto.user.user_reg.UserRegDto;
import com.example.dto.user.user_reg.UserUpdatePassDto;
import com.example.service.ServiceTemplateCrud;

public interface IServiceUserReg extends ServiceTemplateCrud<UserRegDto, Long>{

	List<UserRegDto> getByName(String name);
	UserRegDto validateAccount(AuthenticationRequest user);
	Integer updatePassword(UserUpdatePassDto user);
	
}
