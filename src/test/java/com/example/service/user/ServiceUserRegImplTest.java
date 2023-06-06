package com.example.service.user;

import com.example.dto.user.user_reg.UserRegDto;
import com.example.dto.user.user_reg.UserRegSaveDto;
import com.example.dto.user.user_reg.UserRegUpdateDto;
import com.example.dtomapper.address.DtoAddressMappper;
import com.example.dtomapper.user.DtoUserRegMapper;
import com.example.entity.address.Taddress;
import com.example.entity.address.Tvillage;
import com.example.entity.user.TuserReg;
import com.example.exception.NoDataFoundException;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;

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
     * Prueba unitaria para verificar que el método getAll() devuelve una lista de UserRegDto.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado dtoUserRegMapper para que convierta correctamente un TuserReg en un UserRegDto.</li>
     *   <li>Se configura el objeto simulado mapperUserReg para que devuelva una lista que contiene un TuserReg.</li>
     *   <li>Se realiza la llamada al método getAll() del servicio UserReg.</li>
     *   <li>Se verifica que el resultado sea una lista con un solo elemento.</li>
     *   <li>Se verifica que los métodos simulados hayan sido llamados correctamente.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getAll() devuelve una lista de UserRegDto")
    void getAll_ReturnsListOfUserRegDto() {

        //GIVEN
        given(this.dtoUserRegMapper.TuserRegToUserRegDto(this.tuserReg)).willReturn(this.userRegDtoValid);
        List<TuserReg> tuserRegs = List.of(this.tuserReg);
        given(mapperUserReg.getAll()).willReturn(tuserRegs);

        // Act
        List<UserRegDto> result = serviceUserReg.getAll();

        // Assert
        assertEquals(1, result.size());

        then(this.mapperUserReg).should().getAll();
        then(this.dtoUserRegMapper).should().TuserRegToUserRegDto(this.tuserReg);

    }

    /**
     * Prueba  para verificar que se lance la excepción NoDataFoundException al llamar al método getAll()
     * cuando no hay datos disponibles.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado mapperUserReg para que devuelva una lista vacía de TuserReg.</li>
     *   <li>Se realiza la llamada al método getAll() del servicio UserReg.</li>
     *   <li>Se verifica que se lance la excepción NoDataFoundException.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getAll() lanza NoDataFoundException cuando no hay datos disponibles")

    void getAll_ThrowsNoDataFoundException_WhenNoDataAvailable() {
        // GIVEN
        given(mapperUserReg.getAll()).willReturn(Collections.emptyList());

        // THEN
        assertThrows(NoDataFoundException.class, () -> serviceUserReg.getAll());
    }


    /**
     * Prueba unitaria para verificar que el método getById() devuelve un UserRegDto válido cuando se proporciona un ID válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado mapperUserReg para que devuelva un TuserReg cuando se le pase el ID válido.</li>
     *   <li>Se configura el objeto simulado dtoUserRegMapper para que convierta correctamente un TuserReg en un UserRegDto.</li>
     *   <li>Se realiza la llamada al método getById() del servicio UserReg con el ID válido.</li>
     *   <li>Se verifica que el resultado sea igual al UserRegDto válido esperado.</li>
     *   <li>Se verifica que los métodos simulados hayan sido llamados correctamente.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getById() devuelve un UserRegDto válido cuando se proporciona un ID válido")
    public void testGetById_ValidId_ReturnsUserRegDto() {
        // Arrange
        Long id = 1L;

        given(mapperUserReg.getById(id)).willReturn(this.tuserReg);
        given(dtoUserRegMapper.TuserRegToUserRegDto(any(TuserReg.class))).willReturn(this.userRegDtoValid);

        // Act
        UserRegDto result = this.serviceUserReg.getById(id);

        // Assert
        assertEquals(this.userRegDtoValid, result);
        then(mapperUserReg).should().getById(id);
        then(dtoUserRegMapper).should().TuserRegToUserRegDto(this.tuserReg);
    }


    /**
     * Prueba unitaria para verificar que el método getById() lance una excepción NoDataFoundException cuando se proporciona un ID no válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se inicializa el ID con un valor no válido (null).</li>
     *   <li>Se llama al método getById() del servicio UserReg pasando el ID no válido.</li>
     *   <li>Se verifica que se lance una excepción NoDataFoundException.</li>
     *   <li>Se verifica que los métodos simulados no hayan sido llamados.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getById() lanza NoDataFoundException cuando se proporciona un ID no válido")
    public void testGetById_NotValidId_ThrowsNoDataFoundException() {
        // Arrange
        Long id = null;

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> this.serviceUserReg.getById(id));
        then(mapperUserReg).should(never()).getById(anyLong());
        then(dtoUserRegMapper).should(never()).TuserRegToUserRegDto(any(TuserReg.class));
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