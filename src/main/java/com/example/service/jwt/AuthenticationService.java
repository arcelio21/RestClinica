package com.example.service.jwt;

import com.example.security.JwtUtils;
import com.example.dto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.dto.RegisterRequest;
import com.example.entity.address.Taddress;
import com.example.entity.address.Tvillage;
import com.example.entity.user.TuserReg;
import com.example.mapper.address.MapperAddress;
import com.example.mapper.user.MapperUserReg;
import lombok.RequiredArgsConstructor;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    /**
     * TODO CREO QUE SE DEBE CAMBIAR LO QUE DEVUELVE EL METODO POR UN MENSAJE
     * Y AL VALIDARSE ENTONCES DEVOLVER EL TOKEN, PUESTO QUE HABRA UN ADMINISTRADOR QUE CREAR LOS USUARIOS
     * TAMBIEN VER LOS TIPOS DE USUARIO
     * @param request
     * @return
     */
    @Transactional
    public AuthenticationResponse register(RegisterRequest request){

        var addressAuth = Taddress.builder()
                .specificAddress(request.getDirecSpecific())
                .villageId(new Tvillage(request.getIdVillage()))
                .build();

        Long rowAffected = this.mapperAddress.save(addressAuth);
        if(rowAffected==1){
            var userAuth = new TuserReg();
            userAuth.setIdenCard(request.getIdenCard());
            userAuth.setEmail(request.getEmail());
            userAuth.setName(request.getName());
            userAuth.setLastName(request.getLastName());
            userAuth.setContact(request.getContact());
            userAuth.setBirthday(request.getBirthday());
            userAuth.setPassword(passwordEncoder.encode(request.getPassword()));
            userAuth.setAddressId(addressAuth);

            var response = this.mapperUserReg.save(userAuth);
            if(response!=null && response==1){
                var jwt = this.jwtUtils.generateToken(userAuth);
                return AuthenticationResponse.builder()
                        .token(jwt)
                        .build();
            }else {
                return null;
            }
        }else {
            return  null;
        }
    }

    /**
     * Primero se le pasa la info de usuario al autenticador proporcionado por spring
     * que se encargara de verificar que el usuario exista, de lo contrario se genera una excepcion.
     * Luego se busca la info del usuario para despues generar el token y devolverlo
     * @param request
     * @return
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request){
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
}
