package com.example.service.user;

import com.example.dto.AuthenticationRequest;
import com.example.dto.user.UserRegDto;
import com.example.dto.user.UserUpdatePassDto;
import com.example.dtomapper.user.UserRegMapper;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.UsernameInvalid;
import com.example.mapper.user.MapperUserReg;
import com.example.service.jwt.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceUserRegImpl implements IServiceUserReg{

	private final MapperUserReg mapperUserReg;
	private final UserRegMapper userRegMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;


	@Override
	public List<UserRegDto> getAll() {

		return Optional.ofNullable(this.mapperUserReg.getAll())
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.userRegMapper::TuserRegToUserRegDto)
				.toList();
	}

	@Override
	public UserRegDto getById(Long id) {
		return Optional.ofNullable(id)
				.map(this.mapperUserReg::getById)
				.map(this.userRegMapper::TuserRegToUserRegDto)
				.orElseThrow(() -> new NoDataFoundException(id));
	}

	@Override
	public Integer update(UserRegDto t) {

		if(t==null || t.getId()==null) {
			return 0;
		}

		return Optional.of(t)
				.map(this.userRegMapper::userRegDtoToTuserReg)
				.map(this.mapperUserReg::update)
				.orElse(0);
		
	}
	
	@Override
	public Integer save(UserRegDto t) {
		
		return Optional.ofNullable(t)
				.map(this.userRegMapper::userRegDtoToTuserReg)
				.map(this.mapperUserReg::save)
				.orElse(0);
	}

	@Override
	public List<UserRegDto> getByName(String name) {
		
	  if(name==null || name.trim().equals("")) {
		  throw new NoDataFoundException();
	  }

	  return Optional.of(name)
			  .map(this.mapperUserReg::getByName)
			  .orElseThrow(NoDataFoundException::new)
			  .stream()
			  .map(this.userRegMapper::TuserRegToUserRegDto)
			  .toList();
	}

	@Override
	public UserRegDto validateAccount(AuthenticationRequest user) {
		
		if(user==null || user.getIdenCard()==null || user.getPassword()==null) {
			return null;
		}
		
		if(user.getPassword().trim().equals("")) {
			user.setPassword("PASS VACIO");
			return null;
		}



		this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getIdenCard(),
						user.getPassword()
				)
		);

		
		return this.mapperUserReg.getByIdenCard(user.getIdenCard())
				.map(this.userRegMapper::TuserRegToUserRegDto)
				.orElseThrow(() -> new UsernameInvalid("Datos no valido"));
	}

	@Override
	public Integer updatePassword(UserUpdatePassDto user) {
		
		if(user==null ){
			return 0;
		}
		List<String> properties = Arrays.asList(user.getOldPassword(), user.getIndeCard().toString(), user.getNewPassword());
		long count = properties.stream()
				.filter(p -> p != null && !p.trim().equals(""))
				.count();



		return (count== properties.size())?
					Optional.of(user)
					.map(this.userRegMapper::userUpdatePassToTuserReg)
					.map(tuserReg -> this.mapperUserReg.updatePassword(tuserReg, user.getNewPassword()))
					.orElseThrow()
					: 0;

	}

}
