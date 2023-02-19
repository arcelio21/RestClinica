package com.example.service.user;

import com.example.dto.AuthenticationRequest;
import com.example.dto.user.UserRegDto;
import com.example.dto.user.UserRegSaveDto;
import com.example.dto.user.UserRegUpdateDto;
import com.example.dto.user.UserUpdatePassDto;
import com.example.dtomapper.address.AddressMappper;
import com.example.dtomapper.user.UserRegMapper;
import com.example.entity.address.Taddress;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.address.AddressNotUpdateException;
import com.example.exception.user.UserNotSaveException;
import com.example.exception.user.UserNotUpdateException;
import com.example.exception.user.UsernameInvalid;
import com.example.mapper.address.MapperAddress;
import com.example.mapper.user.MapperUserReg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceUserRegImpl implements IServiceUserReg{

	private final MapperUserReg mapperUserReg;
	private final UserRegMapper userRegMapper;
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

	@Transactional
	@Override
	public Integer update(UserRegDto t) {

		if(t==null || t.getId()==null) {
			throw  new UserNotUpdateException("Datos no validos ");
		}


		if(!t.getClass().equals(UserRegUpdateDto.class)) throw new UserNotUpdateException("Datos no validos");

		Taddress address = this.addressMappper.userRegUpdateDtoToTaddres((UserRegUpdateDto) t);

		try {
			Optional.ofNullable(address)
					.map(this.mapperAddress::update)
					.orElseThrow(()-> new AddressNotUpdateException("Datos de direccion no son validos"));
		}catch (Exception e){
			log.info(e.getMessage());
			throw new AddressNotUpdateException("Datos de direccion no validos", t);
		}

		try {

			return Optional.of(t)
					.map(this.userRegMapper::userRegDtoToTuserReg)
					.map(tuserReg -> {
						tuserReg.setAddressId(new Taddress(address.getId()));
						return tuserReg;
					})
					.map(this.mapperUserReg::update)
					.orElseThrow(()-> new UserNotUpdateException("Datos no validos de usuario"));

		}catch (Exception e){
			throw new UserNotUpdateException("Datos no validos de usuario",(UserRegUpdateDto) t);
		}
		
	}

	@Transactional
	@Override
	public Integer save(UserRegDto user)  {

		if( user == null || !user.getClass().equals(UserRegSaveDto.class)) throw  new UserNotSaveException("Datos de usuario no guardado", user);


		Taddress taddress = this.addressMappper.userRegSaveDtoToTaddres((UserRegSaveDto) user);


		try {
			Optional.ofNullable(taddress)
					.map(this.mapperAddress::save)
					.orElseThrow(()-> {
						throw  new AddressNotSaveException("Datos de direccion no son validos");
					});
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
					.orElseThrow(()-> new UserNotSaveException("Datos no son validos para ser guardados"));

		}catch ( Exception e){
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
