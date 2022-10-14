package com.example.service.user;

import java.util.Map;

import com.example.entity.user.TtypeUser;
import com.example.service.ServiceTemplateCrud;

public interface IServiceTypeUser extends ServiceTemplateCrud<TtypeUser, Integer>{

	
	Map<Integer,TtypeUser> getAllMap();
}
