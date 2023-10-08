package com.example.service.jwt;

import com.example.exception.NoDataFoundException;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.user.type_user_reg.UserTypeRegNotSaveException;
import com.example.exception.user.user_reg.UserNotSaveException;
import com.example.exception.user.user_reg.UsernameInvalid;
import com.example.security.JwtUtils;
import com.example.dto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.dto.RegisterRequest;
import com.example.dto.user.type_user.TypeUserSaveDto;
import com.example.dto.user.type_user_reg.UserTypeRegSaveDto;
import com.example.dtomapper.user.DtoUserTypeRegMapper;
import com.example.entity.address.Taddress;
import com.example.entity.address.Tvillage;
import com.example.entity.user.TuserReg;
import com.example.mapper.address.MapperAddress;
import com.example.mapper.user.MapperUserReg;
import com.example.mapper.user.MapperUserTypeReg;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final MapperUserReg mapperUserReg;
    private final MapperAddress mapperAddress;
    private final MapperUserTypeReg mapperUserTypeReg;

    private final DtoUserTypeRegMapper dtoUserTypeRegMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    /**
     * Y AL VALIDARSE ENTONCES DEVOLVER EL TOKEN, PUESTO QUE HABRA UN ADMINISTRADOR QUE CREAR LOS USUARIOS
     * TAMBIEN VER LOS TIPOS DE USUARIO
     * @param request
     * @return
     */
    @Transactional
    public AuthenticationResponse register(RegisterRequest request){

        Long idAddress = this.saveAddress(request.getDirecSpecific(), request.getIdVillage());
        
        Long idUserReg = this.saveUserReg(request, idAddress);
        
        this.saveUserTypeReg(request.getRoles(), idUserReg);
        
        return this.generateToken(request.getIdenCard());
    }

    /**
     * Primero se le pasa la info de usuario al autenticador proporcionado por spring
     * que se encargara de verificar que el usuario exista, de lo contrario se genera una excepcion.
     * Luego se busca la info del usuario para despues generar el token y devolverlo
     * @param request
     * @return
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request){

        if(request==null || request.getIdenCard()==null || request.getPassword()==null || request.getPassword().trim().equals("")) {

            throw new UsernameInvalid("Datos no validos");
        }

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getIdenCard(),
                        request.getPassword()
                )
        );

        var user = this.mapperUserReg.getByIdenCard(request.getIdenCard()).orElseThrow();

        var jwt = this.jwtUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();

    }

    /**
     * Guarda una dirección de usuario con la dirección específica y el ID de la localidad proporcionados.
     *
     * @param direcSpecific La dirección específica que se va a guardar.
     * @param idVillage El ID de la localidad a la que se asociará la dirección.
     * @return El ID de la dirección guardada.
     * @throws AddressNotSaveException si no se puede guardar la dirección o si los datos de la dirección no son válidos.
     */
    private Long saveAddress(String direcSpecific, Integer idVillage){
        
        var addressAuth = Taddress.builder()
                .specificAddress(direcSpecific)
                .villageId(new Tvillage(idVillage))
                .build();

        Long rowAffected = this.mapperAddress.save(addressAuth);

        if(rowAffected==null || rowAffected <= 0 || addressAuth.getId()==null || addressAuth.getId()<=0){
            throw new AddressNotSaveException("Data Address Not Valid");
        }

        return addressAuth.getId();
    }

    /**
     * Guarda un registro de usuario con los datos proporcionados en la solicitud y el ID de la dirección asociada.
     *
     * @param request La solicitud que contiene los datos del registro de usuario.
     * @param idAddress El ID de la dirección asociada al registro de usuario.
     * @return El ID del registro de usuario guardado.
     * @throws UserNotSaveException si no se puede guardar el registro de usuario o si los datos del registro no son válidos.
     */
    private Long saveUserReg(RegisterRequest request, Long idAddress){
        
        var userAuth = new TuserReg();
        userAuth.setIdenCard(request.getIdenCard());
        userAuth.setEmail(request.getEmail());
        userAuth.setName(request.getName());
        userAuth.setLastName(request.getLastName());
        userAuth.setContact(request.getContact());
        userAuth.setBirthday(request.getBirthday());
        userAuth.setPassword(passwordEncoder.encode(request.getPassword()));
        userAuth.setAddressId(new Taddress(idAddress));

        var response = this.mapperUserReg.save(userAuth);

        if(response == null || response<=0 || userAuth.getId() == null || userAuth.getId()<=0){
            throw new UserNotSaveException("Data User Not Valid");
        }

        return userAuth.getId();
    }

    /**
     * Guarda los roles de tipo de usuario asociados a un registro de usuario.
     *
     * @param roles La lista de roles de tipo de usuario a guardar.
     * @param idUserReg El ID del registro de usuario al que se asocian los roles.
     * @throws UserTypeRegNotSaveException si no se pueden guardar los roles de tipo de usuario o si los datos de los roles no son válidos.
     */
    private void saveUserTypeReg(List<TypeUserSaveDto> roles, Long idUserReg){

        if(roles==null ||  roles.isEmpty()){
            throw new UserTypeRegNotSaveException("Data Role Not Valid");
        }
        
        for( TypeUserSaveDto userType:roles){
            UserTypeRegSaveDto userTypeRegSaveDto = new UserTypeRegSaveDto(idUserReg, userType.id(), 1);
            Integer rowAffect = this.mapperUserTypeReg.save(this.dtoUserTypeRegMapper.userTypeRegSaveToTuserTypeReg(userTypeRegSaveDto));

            if(rowAffect == null || rowAffect<=0){
                throw new UserTypeRegNotSaveException("Data Role Not Valid");
            }
        }

    }

    /**
     * Genera un token de autenticación para un usuario identificado por su número de identificación.
     *
     * @param idenCard El número de identificación del usuario para el que se generará el token.
     * @return Un objeto AuthenticationResponse que contiene el token generado.
     * @throws NoDataFoundException si el usuario no se encuentra o si los datos no son válidos.
     */
    private AuthenticationResponse generateToken(Long idenCard){

        TuserReg userValited = this.mapperUserReg.getByIdenCard(idenCard).orElseThrow(() -> new NoDataFoundException("Data Not Valited"));

        var jwt = this.jwtUtils.generateToken(userValited);

        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }
}
