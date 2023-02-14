package com.example.service.user;

import java.util.List;

import com.example.dto.AuthenticationRequest;
import com.example.dto.user.UserRegDto;
import com.example.dto.user.UserUpdatePassDto;
import com.example.entity.user.TuserReg;
import com.example.service.ServiceTemplateCrud;

public interface IServiceUserReg extends ServiceTemplateCrud<UserRegDto, Long>{

	List<UserRegDto> getByName(String name);
	UserRegDto validateAccount(AuthenticationRequest user);
	Integer updatePassword(UserUpdatePassDto user);
	
}
