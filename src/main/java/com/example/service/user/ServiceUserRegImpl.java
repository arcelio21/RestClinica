package com.example.service.user;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceUserRegImpl implements IServiceUserReg<UserRegDto, Long, UserRegUpdateDto, UserRegSaveDto>{

	private final MapperUserReg mapperUserReg;
	private final DtoUserRegMapper dtoUserRegMapper;
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


	/*-------------------------------METODOS DE PERSISTENCIA ---------------------------------------------*/

	/**
	 * Actualiza los datos de un usuario en la base de datos.
	 * Se lleva a cabo dentro de una transacción.
	 *
	 * @param user Objeto UserRegUpdateDto con los datos de actualización del usuario.
	 * @return El número de filas afectadas por la actualización.
	 * @throws UserNotUpdateException si los datos de usuario no son válidos.
	 */
	@Transactional
	@Override
	public Integer update(UserRegUpdateDto user) {

		this.validateNullFieldUpdate(user);

		this.updateAddress(user);

		return Optional.of(user)
				.map(this.dtoUserRegMapper::userRegUpdateDtoToTuserReg)
				.map(this.mapperUserReg::update)
				.orElseThrow(()-> new UserNotUpdateException("Datos no validos de usuario"));

	}

	/**
	 * Guarda los datos de registro de usuario.
	 *
	 * @param user Objeto UserRegSaveDto que contiene los datos de registro del usuario.
	 * @return El identificador del usuario guardado.
	 * @throws UserNotSaveException si los datos no son válidos para ser guardados.
	 */
	@Transactional
	@Override
	public Integer save(UserRegSaveDto user)  {

		this.validateNullFieldSave(user);

		Long idAddress = this.saveAddress(user);


		return Optional.of(user)
				.map(this.dtoUserRegMapper::userRegSaveDtoToTuserReg)
				.map((tuserReg) -> this.addDataAddress(tuserReg,idAddress))
				.map(this::encrypPaswwordUserSave)
				.map(this.mapperUserReg::save)
				.orElseThrow(()-> new UserNotSaveException("Datos no son validos para ser guardados"));


	}


	/**
	 * Actualiza la contraseña del usuario.
	 *
	 * @param user Objeto UserUpdatePassDto que contiene los datos de actualización de contraseña.
	 * @return El número de filas afectadas por la actualización.
	 * @throws PasswordNotUpdateException Si hay un error al actualizar la contraseña del usuario.
	 * @throws UsernameInvalid Si la contraseña actual no es válida.
	 */
	@Override
	public Integer updatePassword( UserUpdatePassDto user) {
		
		this.validateNullDataUpdatePassword(user);


		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getIndeCard().toString());


		if(this.isPasswordNewEqualpasswordOld(user.getOldPassword(), userDetails.getPassword())){

			UserUpdatePassDto userMap = UserUpdatePassDto.builder()
					.indeCard(user.getIndeCard())
					.newPassword(user.getNewPassword())
					.oldPassword(userDetails.getPassword())
					.build();

			UserUpdatePassDto userValid = this.newPasswordEncode(userMap);

			return Optional.of(userValid)
					.map(this.dtoUserRegMapper::userUpdatePassToTuserReg)
					.map(tuserReg -> this.mapperUserReg.updatePassword(tuserReg,userValid.getNewPassword()))
					.orElseThrow(()-> new PasswordNotUpdateException("Error al actualizar usuario, datos de usuario no validos", user));

		}else {
			throw new UsernameInvalid("Contraseña de usuario no valida");
		}


	}

	/* --------------------METODOS RELACIONADO CON DIRECCIONES ----------------------- */

	/**
	 * Actualiza la dirección de un usuario en la base de datos.
	 *
	 * @param user Objeto UserRegUpdateDto con los datos de actualización de la dirección.
	 * @throws AddressNotUpdateException si los datos de la dirección no son válidos.
	 */
	private void updateAddress(UserRegUpdateDto user){
		 Optional.of(user)
				.map(this.dtoAddressMappper::userRegUpdateDtoToTaddres)
				.map(this.mapperAddress::update)
				.orElseThrow(()-> new AddressNotUpdateException("Datos de direccion no son validos"));
	}

	/**
	 * Guarda la dirección del usuario.
	 *
	 * @param user El objeto UserRegSaveDto que contiene los datos de la dirección.
	 * @return El identificador de la dirección guardada.
	 * @throws AddressNotSaveException Si los datos de la dirección no son válidos para ser guardados.
	 */
	private Long saveAddress(UserRegSaveDto user){
		return Optional.of(user)
				.map(this.dtoAddressMappper::userRegSaveDtoToTaddres)
				.map(this.mapperAddress::save)
				.orElseThrow(()-> new AddressNotSaveException("Datos de direccion no son validos"));
	}

	/**
	 * Agrega la dirección del usuario a la entidad TuserReg.
	 *
	 * @param user      La entidad TuserReg a la que se agregará la dirección.
	 * @param idAddress El identificador de la dirección.
	 * @return La entidad TuserReg con la dirección agregada.
	 */
	private TuserReg addDataAddress(TuserReg user, Long idAddress){
		user.setAddressId(new Taddress(idAddress));
		return user;
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

	private void validateNullFieldSave(UserRegSaveDto user) throws UserNotSaveException{
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

	private void validateNullDataUpdatePassword(UserUpdatePassDto user) throws PasswordNotUpdateException{
		if(user==null || user.getNewPassword()==null || user.getNewPassword().trim().isEmpty()
				|| user.getIndeCard()==null || user.getIndeCard()<=0
		){
			throw new PasswordNotUpdateException("Datos no validos");
		}
	}

	private boolean isPasswordNewEqualpasswordOld(String newPassword, String oldPassword){
		return this.passwordEncoder.matches(newPassword, oldPassword);
	}

	private UserUpdatePassDto newPasswordEncode(UserUpdatePassDto user){

		return UserUpdatePassDto.builder()
				.indeCard(user.getIndeCard())
				.oldPassword(user.getOldPassword())
				.newPassword(passwordEncoder.encode(user.getNewPassword()))
				.build();
	}



	/**
	 * Encripta la contraseña del usuario en la entidad TuserReg.
	 *
	 * @param user La entidad TuserReg a la que se encriptará la contraseña.
	 * @return La entidad TuserReg con la contraseña encriptada.
	 */
	private TuserReg encrypPaswwordUserSave(TuserReg user){
		if(user.getPassword()!=null && !user.getPassword().trim().isEmpty()){
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		return user;
	}


}
