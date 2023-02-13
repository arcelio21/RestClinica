package com.example.service.user;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.entity.user.TuserReg;
import com.example.mapper.user.MapperUserReg;

@Service
@RequiredArgsConstructor
public class ServiceUserRegImpl implements IServiceUserReg{

	private final MapperUserReg mapperUserReg;

	
	@Override
	public List<TuserReg> getAll() {

		//TODO Hacer DTO para mapear estos datos
		return Optional.ofNullable(this.mapperUserReg.getAll()).
				orElseThrow(NoDataFoundException::new);
	}

	@Override
	public TuserReg getById(Integer id) {
		return Optional.ofNullable(id).map(this.mapperUserReg::getById).orElse(null);
	}

	@Override
	public Integer update(TuserReg t) {
		
		if(t==null || t.getId()==null) {
			return 0;
		}
		
		Optional<TuserReg> user=Optional.ofNullable(t.getId()).map(this.mapperUserReg::getById);
		if(user.isEmpty()) {
			//System.out.println(user);
			return 0;
		}
		
		try {
			return Optional.ofNullable(t).map(this.mapperUserReg::update).orElse(0);
		}catch (Exception e) {
			return 0;
		}
		
		
	}
	
	@Override
	public Integer save(TuserReg t) {
		
		if(t==null ) {
			return 0;
		}
		
		
		return Optional.ofNullable(t).map(this.mapperUserReg::save).orElse(0);
	}

	@Override
	public List<TuserReg> getByName(String name) {
		
	  if(name==null || name.trim().equals("")) {
		  return Collections.emptyList();
	  }
	  List<TuserReg> users=this.getByName(name);
	  if(users==null || users.isEmpty()) {
		  return Collections.emptyList();
	  }
		  
		
	  return users;
	}

	@Override
	public TuserReg validateAccount(TuserReg user) {
		
		if(user==null || user.getIdenCard()==null || user.getPassword()==null) {
			return null;
		}
		
		if(user.getPassword().trim().equals("")) {
			user.setPassword("PASS VACIO");
			return user;
		}
		
		return this.mapperUserReg.validateAccount(user);
	}

	@Override
	public Integer updatePassword(TuserReg user) {
		
		if(user==null || user.getPassword()==null ||user.getPassword().trim().equals("") || user.getIdenCard()==null){
			return 0;
		}
		
		return this.mapperUserReg.updatePassword(user);
	}

}
