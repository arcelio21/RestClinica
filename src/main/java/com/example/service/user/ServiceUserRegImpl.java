package com.example.service.user;

import com.example.dto.AuthenticationRequest;
import com.example.dto.user.user_reg.UserRegDto;
import com.example.dto.user.user_reg.UserRegSaveDto;
import com.example.dto.user.user_reg.UserRegUpdateDto;
import com.example.dto.user.user_reg.UserUpdatePassDto;
import com.example.dtomapper.address.DtoAddressMappper;
import com.example.dtomapper.user.DtoUserRegMapper;
import com.example.entity.address.Taddress;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.address.AddressNotUpdateException;
import com.example.exception.user.user_reg.PasswordNotUpdateException;
import com.example.exception.user.user_reg.UserNotSaveException;
import com.example.exception.user.user_reg.UserNotUpdateException;
import com.example.exception.user.user_reg.UsernameInvalid;
import com.example.mapper.address.MapperAddress;
import com.example.mapper.user.MapperUserReg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final DtoUserRegMapper dtoUserRegMapper;
	private final AuthenticationManager authenticationManager;
	private final DtoAddressMappper dtoAddressMappper;
	private final MapperAddress mapperAddress;

	private final UserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncoder;


	//TODO CAMBIAR IMPLEMENTACION, DE ESTA FORMA NO DEVUELVE EXCEPCION
	@Override
	public List<UserRegDto> getAll() {

		return Optional.ofNullable(this.mapperUserReg.getAll())
				.orElseThrow(NoDataFoundException::new)
				.stream()
				.map(this.dtoUserRegMapper::TuserRegToUserRegDto)
				.toList();
	}

	@Override
	public UserRegDto getById(Long id) {
		return Optional.ofNullable(id)
				.map(this.mapperUserReg::getById)
				.map(this.dtoUserRegMapper::TuserRegToUserRegDto)
				.orElseThrow(() -> new NoDataFoundException(id));
	}

	@Transactional
	@Override
	public Integer update(UserRegDto userRegDto) {

		if(userRegDto==null || userRegDto.getId()==null) {
			throw  new UserNotUpdateException("Datos no validos ");
		}


		if(!userRegDto.getClass().equals(UserRegUpdateDto.class)) throw new UserNotUpdateException("Datos no validos");

		Taddress address = this.dtoAddressMappper.userRegUpdateDtoToTaddres((UserRegUpdateDto) userRegDto);

		try {
			Optional.ofNullable(address)
					.map(this.mapperAddress::update)
					.orElseThrow(()-> new AddressNotUpdateException("Datos de direccion no son validos"));
		}catch (Exception e){
			log.info(e.getMessage());
			throw new AddressNotUpdateException("Datos de direccion no validos", userRegDto);
		}

		try {

			return Optional.of(userRegDto)
					.map(this.dtoUserRegMapper::userRegDtoToTuserReg)
					.map(tuserReg -> {
						tuserReg.setAddressId(new Taddress(address.getId()));
						return tuserReg;
					})
					.map(this.mapperUserReg::update)
					.orElseThrow(()-> new UserNotUpdateException("Datos no validos de usuario"));

		}catch (Exception e){
			throw new UserNotUpdateException("Datos no validos de usuario",(UserRegUpdateDto) userRegDto);
		}
		
	}

	//TODO HACER TEST SI O SI
	@Transactional
	@Override
	public Integer save(UserRegDto user)  {

		if( user == null || !user.getClass().equals(UserRegSaveDto.class)) throw  new UserNotSaveException("Datos de usuario no guardado", user);


		Taddress taddress = this.dtoAddressMappper.userRegSaveDtoToTaddres((UserRegSaveDto) user);


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
					.map(this.dtoUserRegMapper::userRegDtoToTuserReg)
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
			  .map(this.dtoUserRegMapper::TuserRegToUserRegDto)
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
				.map(this.dtoUserRegMapper::TuserRegToUserRegDto)
				.orElseThrow(() -> new UsernameInvalid("Datos no valido"));
	}

	@Override
	public Integer updatePassword( UserUpdatePassDto user) {
		
		if(user==null ){
			throw new PasswordNotUpdateException("Datos no enviados");
		}

		List<String> properties = null;

		try {
			 properties = Arrays.asList(user.getOldPassword(), user.getIndeCard().toString(), user.getNewPassword());
		}catch (RuntimeException e){
			throw new PasswordNotUpdateException("Datos mo validos", user);
		}

		long count = properties.stream()
				.filter(p -> p != null && !p.trim().equals(""))
				.count();


		if(count<properties.size()) throw new PasswordNotUpdateException(" Cantidad Datos no son validos", user);

		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getIndeCard().toString());

		UserUpdatePassDto userValid;
		if(passwordEncoder.matches(user.getOldPassword(), userDetails.getPassword())){

			log.info("Funciona el emparejamiento de contraseña");
			String newPassEncode = passwordEncoder.encode(user.getNewPassword());
			String oldPassEncode = passwordEncoder.encode(user.getOldPassword());
			userValid = UserUpdatePassDto.builder()
					.indeCard(user.getIndeCard())
					.oldPassword(oldPassEncode)
					.newPassword(newPassEncode)
					.build();
		}else {
			throw new UsernameInvalid("Contraseña de usuario no valida");
		}

		return Optional.of(user)
					.map(this.dtoUserRegMapper::userUpdatePassToTuserReg)
					.map(tuserReg -> this.mapperUserReg.updatePassword(tuserReg,userValid.getNewPassword()))
					.orElseThrow(()-> new PasswordNotUpdateException("Error al actualizar usuario, datos de usuario no validos", user));

	}

}
