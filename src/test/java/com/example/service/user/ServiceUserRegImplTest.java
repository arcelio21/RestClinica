package com.example.service.user;

import com.example.dto.user.user_reg.UserRegDto;
import com.example.dto.user.user_reg.UserRegSaveDto;
import com.example.dto.user.user_reg.UserRegUpdateDto;
import com.example.dtomapper.address.DtoAddressMappper;
import com.example.dtomapper.user.DtoUserRegMapper;
import com.example.entity.address.Taddress;
import com.example.entity.address.Tvillage;
import com.example.entity.user.TuserReg;
import com.example.exception.user.user_reg.UserNotSaveException;
import com.example.exception.user.user_reg.UserNotUpdateException;
import com.example.mapper.address.MapperAddress;
import com.example.mapper.user.MapperUserReg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

@DisplayName("Test para metodos de la clase ServiceUserRegImpl.class")
@ExtendWith(MockitoExtension.class)
class ServiceUserRegImplTest {

    @Mock
    private MapperUserReg mapperUserReg;
    @Mock
    private DtoUserRegMapper dtoUserRegMapper;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private DtoAddressMappper dtoAddressMappper;
    @Mock
    private MapperAddress mapperAddress;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ServiceUserRegImpl serviceUserReg;


    private UserRegDto userRegDtoValid;
    //private UserRegDto userRegDtoNotValid;
    private UserRegUpdateDto userRegUpdateDtoValid;
    private UserRegUpdateDto userRegUpdateDtoNotValid;
    private UserRegSaveDto userRegSaveDtoValid;
    private UserRegSaveDto userRegSaveDtoNotValid;

    private TuserReg tuserReg;


    @BeforeEach
    void setUp() {

        userRegDtoValid = UserRegDto.builder()
                .id(1L)
                .addressId(1L)
                .email("arcelio@gmail.com")
                .contact("65723832")
                .name("Arcelio")
                .birthday(LocalDate.of(2000, 9, 2))
                .creationDate(LocalDateTime.now())
                .idenCard(12000704001435L)
                .build();

        userRegUpdateDtoValid = UserRegUpdateDto.userUpdateBuilder()
                .id(1L)
                .addressId(1L)
                .email("arcelio@gmail.com")
                .contact("65723832")
                .name("Arcelio")
                .lastName("Montezuma")
                .birthday(LocalDate.of(2000, 9, 2))
                .idenCard(12000704001435L)
                .direcSpecific("San jose")
                .villageId(1L)
                .build();
        tuserReg = new TuserReg();
        tuserReg.setId(1L);
        tuserReg.setAddressId(new Taddress(1L, new Tvillage(1), "San jose"));
        tuserReg.setPassword("holaCOMO");
        tuserReg.setContact("65723832");
        tuserReg.setIdenCard(12000704001435L);
        tuserReg.setBirthday(LocalDate.of(2000, 9, 2));
        tuserReg.setEmail("arcelio@gmail.com");
        tuserReg.setLastName("MOntezuma");
        tuserReg.setName("Arcelio");

        userRegUpdateDtoNotValid = UserRegUpdateDto.userUpdateBuilder()
                .id(1L)
                .addressId(0L)
                .birthday(LocalDate.of(2000, 9, 2))
                .idenCard(12000704001435L)
                .direcSpecific("San jose")
                .villageId(1L)
                .build();

        userRegSaveDtoValid = UserRegSaveDto.userSaveBUilder()
                .id(1L)
                .email("arcelio@gmail.com")
                .contact("65723832")
                .name("Arcelio")
                .lastName("Montezuma")
                .birthday(LocalDate.of(2000, 9, 2))
                .idenCard(12000704001435L)
                .villageId(1L)
                .direcSpecific("SAN JOSE")
                .password("Hola")
                .build();

        userRegSaveDtoNotValid = UserRegSaveDto.userSaveBUilder()
                .id(1L)
                .addressId(1L)
                .email("arcelio@gmail.com")
                .direcSpecific("SAN JOSE")
                .password("Hola")
                .build();

    }

    /**
     * Prueba unitaria para el método de actualización de datos válidos.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configuran los objetos simulados para que devuelvan los valores esperados.</li>
     *   <li>Se llama al método de actualización con los datos válidos.</li>
     *   <li>Se verifica que el número de filas afectadas sea igual a 1.</li>
     *   <li>Se verifica que los métodos simulados hayan sido llamados correctamente.</li>
     * </ul>
     */
    @DisplayName("Prueba de actualización con datos válidos")
    @Test
    void update_data_valid(){

        //GIVEN
        given(this.dtoAddressMappper.userRegUpdateDtoToTaddres(this.userRegUpdateDtoValid)).willReturn(Taddress.builder()
                        .id(1L)
                        .villageId(new Tvillage(1))
                        .specificAddress("San jose")
                .build());

        given(this.mapperAddress.update(any(Taddress.class))).willReturn(1);

        given(this.dtoUserRegMapper.userRegUpdateDtoToTuserReg(this.userRegUpdateDtoValid)).willReturn(this.tuserReg);
        given(this.mapperUserReg.update(any(TuserReg.class))).willReturn(1);



        //WHEN
        Integer rowAffected = this.serviceUserReg.update(this.userRegUpdateDtoValid);

        //THEN
        assertEquals(1, rowAffected);

        then(this.dtoAddressMappper).should().userRegUpdateDtoToTaddres(this.userRegUpdateDtoValid);
        then(this.mapperAddress).should().update(any(Taddress.class));

        then(this.dtoUserRegMapper).should().userRegUpdateDtoToTuserReg(this.userRegUpdateDtoValid);
        then(this.mapperUserReg).should().update(any(TuserReg.class));

    }

    /**
     * Prueba unitaria para el método de actualización con datos no válidos.
     *
     * <p>Se espera que se lance una excepción UserNotUpdateException al intentar actualizar con datos no válidos.</p>
     */
    @Test
    @DisplayName("Prueba de actualización con datos no válidos")
    void update_data_notValid(){

        assertThrows(UserNotUpdateException.class,()-> this.serviceUserReg.update(this.userRegUpdateDtoNotValid));
    }

    /**
     * Prueba unitaria para el método de guardado de datos válidos.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configuran los objetos simulados para que devuelvan los valores esperados.</li>
     *   <li>Se llama al método de guardado con los datos válidos.</li>
     *   <li>Se verifica que el número de filas afectadas sea igual a 1.</li>
     *   <li>Se verifica que los métodos simulados hayan sido llamados correctamente.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado con datos válidos")
    void save_Data_Valid(){
        given(this.dtoAddressMappper.userRegSaveDtoToTaddres(this.userRegSaveDtoValid)).willReturn(Taddress.builder()
                .villageId(new Tvillage(1))
                .specificAddress("San jose")
                .build());
        given(this.dtoUserRegMapper.userRegSaveDtoToTuserReg(this.userRegSaveDtoValid)).willReturn( this.tuserReg);

        given(this.mapperAddress.save((any(Taddress.class)))).willReturn(1L);
        given(this.mapperUserReg.save(any(TuserReg.class))).willReturn(1);

        Integer rowAffected = this.serviceUserReg.save(this.userRegSaveDtoValid);

        assertEquals(1,rowAffected);

        then(this.dtoUserRegMapper).should().userRegSaveDtoToTuserReg(this.userRegSaveDtoValid);
        then(this.dtoAddressMappper).should().userRegSaveDtoToTaddres(this.userRegSaveDtoValid);
        then(this.mapperAddress).should().save(any(Taddress.class));
        then(this.mapperUserReg).should().save(any(TuserReg.class));
    }


    /**
     * Prueba unitaria para el método de guardado con datos no válidos.
     *
     * <p>Se espera que se lance una excepción UserNotSaveException al intentar guardar datos no válidos.</p>
     */
    @Test
    @DisplayName("Prueba de guardado con datos no válidos")
    void save_data_NotValid(){
        assertThrows(UserNotSaveException.class, ()-> this.serviceUserReg.save(this.userRegSaveDtoNotValid));
    }

}