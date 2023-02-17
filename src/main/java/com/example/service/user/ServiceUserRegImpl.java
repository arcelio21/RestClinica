package com.example.service.user;

import com.example.dto.AuthenticationRequest;
import com.example.dto.user.UserRegDto;
import com.example.dto.user.UserRegSaveDto;
import com.example.dto.user.UserUpdatePassDto;
import com.example.dtomapper.address.AddressMappper;
import com.example.dtomapper.user.UserRegMapper;
import com.example.entity.address.Taddress;
import com.example.entity.user.TuserReg;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.user.UserNotSaveException;
import com.example.exception.user.UsernameInvalid;
import com.example.mapper.address.MapperAddress;
import com.example.mapper.user.MapperUserReg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceUserRegImpl implements IServiceUserReg{

	private final MapperUserReg mapperUserReg;
	private final UserRegMapper userRegMapper;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final AddressMappper addressMappper;
	private final MapperAddress mapperAddress;


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

	@Transactional
	@Override
	public Integer save(UserRegDto user)  {

		if( user == null || !user.getClass().equals(UserRegSaveDto.class)) throw  new UserNotSaveException("Datos de usuario no guardado", user);


		Taddress taddress = this.addressMappper.userRegSaveDtoToTaddres((UserRegSaveDto) user);

		try {
			Optional.ofNullable(taddress)
					.map(this.mapperAddress::save)
					.orElseThrow(()-> new AddressNotSaveException("Datos de direccion no son validos", user));
		}catch (Exception e){
			throw new AddressNotSaveException("Datos de direccion no validos", user);
		}

		try {
			return Optional.of(user)
					.map(this.userRegMapper::userRegDtoToTuserReg)
					.map((tuserReg -> {tuserReg.setAddressId(new Taddress(taddress.getId()));
						return tuserReg;
					}))
					.map(this.mapperUserReg::save)
					.orElseThrow(()-> new UserNotSaveException("Datos no son validos para ser guardados", user));

		}catch ( Exception e){
			// TODO: 02/16/23 Crear map para enviar argumentos y asi no se vea datos innecesarios 
			throw new UserNotSaveException("Datos no son validos, verifiquelos", user);
		}


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
