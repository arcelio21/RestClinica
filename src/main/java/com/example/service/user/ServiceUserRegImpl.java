package com.example.service.user;

import com.example.dto.AuthenticationRequest;
import com.example.dto.user.user_reg.UserRegDto;
import com.example.dto.user.user_reg.UserRegSaveDto;
import com.example.dto.user.user_reg.UserRegUpdateDto;
import com.example.dto.user.user_reg.UserUpdatePassDto;
import com.example.dtomapper.address.DtoAddressMappper;
import com.example.dtomapper.user.DtoUserRegMapper;
import com.example.entity.address.Taddress;
import com.example.entity.user.TuserReg;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceUserRegImpl implements IServiceUserReg<UserRegDto, Long, UserRegUpdateDto, UserRegSaveDto>{

	private final MapperUserReg mapperUserReg;
	private final DtoUserRegMapper dtoUserRegMapper;
	private final AuthenticationManager authenticationManager;
	private final DtoAddressMappper dtoAddressMappper;
	private final MapperAddress mapperAddress;

	private final UserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncoder;


	/*-------------------------METODO DE OBTENCION DE DATOS ----------------*/
	@Override
	public List<UserRegDto> getAll() {

		Optional<List<TuserReg>> optionalTuserRegs = Optional.ofNullable(this.mapperUserReg.getAll());

		if(optionalTuserRegs.isPresent() && !optionalTuserRegs.get().isEmpty()){

			return optionalTuserRegs
					.get()
					.stream()
					.map(this.dtoUserRegMapper::TuserRegToUserRegDto)
					.collect(Collectors.toList());
		}

		throw new NoDataFoundException();
	}

	@Override
	public UserRegDto getById(Long id) {

		if(id==null || id<=0){
			throw new NoDataFoundException(id);

		}

		return Optional.of(id)
				.map(this.mapperUserReg::getById)
				.map(this.dtoUserRegMapper::TuserRegToUserRegDto)
				.orElseThrow(() -> new NoDataFoundException(id));
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
				.collect(Collectors.toList());
	}

	//TODO CREO QUE AGREGO LOS USUARIOS ANTES DE VALIDAR, ARREGLAR O VERIFICAR ESO
	@Override
	public UserRegDto authenticateUserReg(AuthenticationRequest user) {

		if(user==null || user.getIdenCard()==null || user.getPassword()==null || user.getPassword().trim().equals("")) {

			throw new UsernameInvalid("Datos no validos");
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

	/*-------------------------------METODOS DE PERSISTENCIA ---------------------------------------------*/

	@Transactional
	@Override
	public Integer update(UserRegUpdateDto user) {

		this.validateNullFieldUpdate(user);

		Integer rowUpdateAddress ;

		rowUpdateAddress = Optional.of(user)
				.map(this.dtoAddressMappper::userRegUpdateDtoToTaddres)
				.map(this.mapperAddress::update)
				.orElseThrow(()-> new AddressNotUpdateException("Datos de direccion no son validos"));

		if (rowUpdateAddress==0) throw  new AddressNotUpdateException("Datos de direccion no son validos");



		return Optional.of(user)
				.map(this.dtoUserRegMapper::userRegUpdateDtoToTuserReg)
				.map(tuserReg -> {

					if(tuserReg.getPassword()!=null && !tuserReg.getPassword().trim().isEmpty()){
						tuserReg.setPassword(passwordEncoder.encode(tuserReg.getPassword()));
					}
					return tuserReg;
				})
				.map(this.mapperUserReg::update)
				.orElseThrow(()-> new UserNotUpdateException("Datos no validos de usuario"));

	}

	/**
	 * ESTE METODO APLICARA PARA CUALQUIER USUARIO QUE SE VAYA A GUARDAR USUARIOS, EN CASO DE QUE EL USUARIO SEA UN CLIENTE,
	 * SE LE DARA LA OPCION DE ENVIO DE LINK PARA CONTRASEÑA Y UNA PREGUNTA PARA VALIDAR IDENTIDAD
	 * @param user
	 * @return
	 */
	//TODO HACER TEST SI O SI | SEPARAR CODIGO DE VALIDACION EN UN METODO
	@Transactional
	@Override
	public Integer save(UserRegSaveDto user)  {

		this.validateNullFieldSave(user);

		Long idAddress;

		idAddress=Optional.of(user)
				.map(this.dtoAddressMappper::userRegSaveDtoToTaddres)
				.map(this.mapperAddress::save)
				.orElseThrow(()-> new AddressNotSaveException("Datos de direccion no son validos"));

		if(idAddress==null || idAddress==0) throw new AddressNotSaveException("Datos de direccion no son validos");


		return Optional.of(user)
				.map(this.dtoUserRegMapper::userRegSaveDtoToTuserReg)
				.map(tuserReg -> {
					tuserReg.setAddressId(new Taddress(idAddress));

					if(tuserReg.getPassword()!=null && !tuserReg.getPassword().trim().isEmpty()){
						tuserReg.setPassword(passwordEncoder.encode(tuserReg.getPassword()));
					}
					return tuserReg;
				})
				.map(this.mapperUserReg::save)
				.orElseThrow(()-> new UserNotSaveException("Datos no son validos para ser guardados"));


	}


	//TODO VERIFICAR SI ES NECESARIO LA CONTRASEÑA OLD, EN CASO DE QUE NO SE HAYA AGREGADO AL USUARIO
	@Override
	public Integer updatePassword( UserUpdatePassDto user) {
		
		if(user==null || user.getNewPassword()==null || user.getNewPassword().trim().isEmpty()
			|| user.getIndeCard()==null || user.getIndeCard()<=0
			){
			throw new PasswordNotUpdateException("Datos no validos");
		}


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


	/* -------------------- METODOS DE VALIDACION DE DATOS DE LOS CAMPOS -------------------*/
	private void validateNullFieldUpdate(UserRegUpdateDto user) throws UserNotUpdateException{

		if(user.getId()==null || user.getId()<=0
				|| user.getAddressId()==null || user.getAddressId()<=0
				|| user.getEmail()==null || user.getEmail().trim().isEmpty()
				|| user.getDirecSpecific()==null || user.getDirecSpecific().trim().isEmpty()
				|| user.getName()==null || user.getName().trim().isEmpty()
				|| user.getLastName()==null || user.getLastName().trim().isEmpty()
				|| user.getIdenCard()==null || user.getIdenCard()<=0
				|| user.getBirthday()==null
				|| user.getVillageId()==null || user.getVillageId()<=0
		) {
			throw  new UserNotUpdateException("Datos no validos", user);
		}

	}

	private void validateNullFieldSave(UserRegSaveDto user){
		if(user.getEmail()==null || user.getEmail().trim().isEmpty()
				|| user.getDirecSpecific()==null || user.getDirecSpecific().trim().isEmpty()
				|| user.getName()==null || user.getName().trim().isEmpty()
				|| user.getLastName()==null || user.getLastName().trim().isEmpty()
				|| user.getIdenCard()==null || user.getIdenCard()<=0
				|| user.getBirthday()==null
				|| user.getVillageId()==null || user.getVillageId()<=0
		) {

			throw  new UserNotSaveException("Datos no validos");
		}
	}

}
