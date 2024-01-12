package com.example.service.speciality;

import com.example.dto.speciality.userspeciality.*;
import com.example.dtomapper.speciality.DtoUserSpecialityMapper;
import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TtypeUser;
import com.example.entity.user.TuserReg;
import com.example.entity.user.TuserTypeReg;
import com.example.exception.NoDataFoundException;
import com.example.exception.speciality.user_speciality.UserSpecialityNotSaveException;
import com.example.exception.speciality.user_speciality.UserSpecialityNotUpdateException;
import com.example.mapper.speciality.MapperUserSpeciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ServiceUserSpecialityTest {

    @Mock
    private MapperUserSpeciality mapperUserSpeciality;
    @Mock
    private DtoUserSpecialityMapper dtoUserSpecialityMapper;
    @InjectMocks
    private ServiceUserSpeciality serviceUserSpiciality;

    private TuserSpeciality tuserSpeciality;
    private UserSpecialityGetDto userSpecialityGetDto;
    private UserSpecialityUpdateDto userSpecialityUpdateDto;
    private UserSpecialitySaveDto userSpecialitySaveDto;

    @BeforeEach
    void setUp() {
        Tspeciality tspeciality = new Tspeciality(1,"Odontologo");

        TuserReg  tuserReg = new TuserReg();
        tuserReg.setName("Arcelio");
        tuserReg.setLastName("Montezuma");

        TuserTypeReg tuserTypeReg = new TuserTypeReg();
        tuserTypeReg.setId(1L);
        tuserTypeReg.setUserRegId(tuserReg);
        tuserTypeReg.setTypeUser(new TtypeUser(1,"Admin"));

        Tstatus tstatus = new Tstatus(1,"Activated");

        tuserSpeciality = new TuserSpeciality(1L,tspeciality,tuserTypeReg,tstatus);

        userSpecialityGetDto = new UserSpecialityGetDto(1L,"Odontologo","Activated","Admin","Montezuma","Arcelio");
        
        this.userSpecialityUpdateDto = new UserSpecialityUpdateDto(1L, 1, 1L, 1);
        this.userSpecialitySaveDto = new UserSpecialitySaveDto(1, 1L, 1);
    }

    /**
     * Prueba unitaria para el método `getAll` del servicio UserSpeciality cuando hay datos disponibles.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getAll`, y hay datos disponibles,
     *       recupera y mapea correctamente los datos a UserSpecialityGetDto.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez,
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez para mapear los datos recuperados.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `getAll` del servicio, y hay datos disponibles.
     *       Verifica que el servicio recupera y mapea correctamente los datos a UserSpecialityGetDto.</li>
     *   <li>El método `mapperUserSpeciality` debería llamarse una vez, y el método `dtoUserSpecialityMapper`
     *       debería llamarse una vez para mapear los datos recuperados.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba getAll con datos disponibles")
    void givenListUserSpeciality_when_getAll_thenSuccess(){

        given(this.mapperUserSpeciality.getAll()).willReturn(List.of(this.tuserSpeciality));
        given(this.dtoUserSpecialityMapper.userSpecialityToUserSpecialityGetDto(this.tuserSpeciality)).willReturn(this.userSpecialityGetDto);

        List<UserSpecialityGetDto> listUserSpeciality = this.serviceUserSpiciality.getAll();
        assertNotNull(listUserSpeciality);
        assertFalse(listUserSpeciality.isEmpty());
        assertNotNull(listUserSpeciality.get(0));

        then(this.mapperUserSpeciality).should(times(1)).getAll();
        then(this.dtoUserSpecialityMapper).should(times(1)).userSpecialityToUserSpecialityGetDto(this.tuserSpeciality);
    }

    /**
     * Prueba unitaria para el método `getAll` del servicio UserSpeciality cuando la lista de datos está vacía.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getAll`, y la lista de datos está vacía,
     *       arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez,
     *       y que el método `dtoUserSpecialityMapper` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `getAll` del servicio, pero la lista de datos está vacía,
     *       lo que provoca que el método arroje una excepción `NoDataFoundException`.</li>
     *   <li>El método `mapperUserSpeciality` debería llamarse una vez, y el método `dtoUserSpecialityMapper`
     *       no debería tener interacciones, ya que no hay datos para mapear.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba getAll con lista vacía")
    void givenListUserSpeciality_when_getAll_then_ListEmpty(){

        given(this.mapperUserSpeciality.getAll()).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getAll());

        then(this.mapperUserSpeciality).should(times(1)).getAll();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();

    }

    /**
     * Prueba unitaria para el método `getAll` del servicio UserSpeciality cuando la lista de datos es nula.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getAll` y la lista de datos es nula,
     *       arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez,
     *       y que el método `dtoUserSpecialityMapper` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `getAll` del servicio,
     *       pero la lista de datos es nula, lo que provoca que el método arroje una excepción `NoDataFoundException`.</li>
     *   <li>El método `mapperUserSpeciality` debería llamarse una vez, y el método `dtoUserSpecialityMapper`
     *       no debería tener interacciones, ya que no hay datos para mapear.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba getAll con lista nula")
    void givenListUserSpeciality_when_getAll_then_null(){

        given(this.mapperUserSpeciality.getAll()).willReturn(null);

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getAll());

        then(this.mapperUserSpeciality).should(times(1)).getAll();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();

    }


    /**
     * Prueba unitaria para el método `getById` del servicio UserSpeciality cuando el dato existe.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getById` con un ID válido y el dato existe,
     *       se obtiene el resultado esperado.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con el ID proporcionado,
     *       y que el método `dtoUserSpecialityMapper` también se llama exactamente una vez para mapear el resultado.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `getById` del servicio con un ID válido,
     *       y el dato correspondiente existe en la base de datos.</li>
     *   <li>Se verifica que los mapeadores y el servicio se llamen según lo esperado y que se obtenga el resultado deseado.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba getById con dato existente")
    void givenOneUserSpeciality_when_getById_then_exist(){

        Integer idUserSpeciality = 1;

        given(this.mapperUserSpeciality.getById(idUserSpeciality)).willReturn(this.tuserSpeciality);
        given(this.dtoUserSpecialityMapper.userSpecialityToUserSpecialityGetDto(this.tuserSpeciality)).willReturn(this.userSpecialityGetDto);
        
        UserSpecialityGetDto userSpecialityGet = this.serviceUserSpiciality.getById(idUserSpeciality);

        assertNotNull(userSpecialityGet);
        assertNotNull(userSpecialityGet.id());
        assertNotNull(userSpecialityGet.lastNameUser());
        assertNotNull(userSpecialityGet.nameSpeciality());
        assertNotNull(userSpecialityGet.nameStatus());
        assertNotNull(userSpecialityGet.nameTypeUser());
        assertNotNull(userSpecialityGet.nameUser());

        then(this.mapperUserSpeciality).should(times(1)).getById(idUserSpeciality);
        then(this.dtoUserSpecialityMapper).should(times(1)).userSpecialityToUserSpecialityGetDto(this.tuserSpeciality);
    }

    
    /**
     * Prueba unitaria para el método `getById` del servicio UserSpeciality cuando el ID no es válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getById` con un ID no válido,
     *       se lance una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que ni el método `mapperUserSpeciality` ni el método `dtoUserSpecialityMapper` tienen interacciones.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `getById` del servicio con un ID que no es válido.</li>
     *   <li>Se espera que se lance una excepción y que no haya interacciones con los mapeadores o el servicio.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba getById con ID no válido")
    void givenThrowError_when_getById_then_IDNotValid(){
        
        Integer idUserSpeciality = 0;

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getById(idUserSpeciality));

        then(this.mapperUserSpeciality).shouldHaveNoInteractions();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getById` del servicio UserSpeciality cuando los datos no existen.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getById` con un ID válido pero no existente,
     *          se lance una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con el ID válido,
     *       y que el método `dtoUserSpecialityMapper` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `getById` del servicio con un ID que existe,
     *       pero los datos no están presentes.</li>
     *   <li>Se espera que se lance una excepción y que haya una única interacción con el método `mapperUserSpeciality`.</li>
     *   <li>El método `dtoUserSpecialityMapper` no debe tener interacciones ya que no hay datos para mapear.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba getById con datos que no existen")
    void givenThrowError_when_getById_then_dataNotExist(){
        
        Integer idUserSpeciality = 2;

        given(this.mapperUserSpeciality.getById(idUserSpeciality)).willReturn(null);

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getById(idUserSpeciality));

        then(this.mapperUserSpeciality).should(times(1)).getById(idUserSpeciality);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `update` del servicio UserSpeciality cuando se actualiza con éxito.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `update` y la actualización tiene éxito,
     *       se devuelve un valor entero que indica la cantidad de filas afectadas, y no se lanza ninguna excepción.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`,
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialityUpdateDto`.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `update` del servicio y la actualización tiene éxito.
     *       Se espera que se devuelva un valor entero que indica la cantidad de filas afectadas, y que no se lance ninguna excepción.</li>
     *   <li>Se verifica que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialityUpdateDto`.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba update exitoso")
    void givenRowAffected_when_update_then_success(){

        given(this.mapperUserSpeciality.update(this.tuserSpeciality)).willReturn(1);
        given(this.dtoUserSpecialityMapper.UserSpecialityUpdateDtoTouserSpeciality(this.userSpecialityUpdateDto)).willReturn(this.tuserSpeciality);

        Integer rowAffected = this.serviceUserSpiciality.update(this.userSpecialityUpdateDto);

        assertNotNull(rowAffected);
        assertEquals(1,rowAffected);

        then(this.mapperUserSpeciality).should(times(1)).update(this.tuserSpeciality);
        then(this.dtoUserSpecialityMapper).should(times(1)).UserSpecialityUpdateDtoTouserSpeciality(this.userSpecialityUpdateDto);
    }

    /**
     * Prueba unitaria para el método `update` del servicio UserSpeciality cuando ocurre un error y retorna un valor nulo.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `update` y ocurre un error, y retorna un valor nulo,
     *       se lance una excepción `UserSpecialityNotUpdateException`.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`,
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialityUpdateDto`.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `update` del servicio,
     *       pero ocurre un error y retorna un valor nulo. Se espera que se lance una excepción.</li>
     *   <li>Se verifica que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialityUpdateDto`.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba update con error y retorno nulo")
    void givenThrowError_when_update_then_errorUpdate(){

        given(this.mapperUserSpeciality.update(this.tuserSpeciality)).willReturn(null);
        given(this.dtoUserSpecialityMapper.UserSpecialityUpdateDtoTouserSpeciality(this.userSpecialityUpdateDto)).willReturn(this.tuserSpeciality);

        assertThrows(UserSpecialityNotUpdateException.class, ()-> this.serviceUserSpiciality.update(this.userSpecialityUpdateDto));

        then(this.mapperUserSpeciality).should(times(1)).update(this.tuserSpeciality);
        then(this.dtoUserSpecialityMapper).should(times(1)).UserSpecialityUpdateDtoTouserSpeciality(this.userSpecialityUpdateDto);
    }

    /**
     * Prueba unitaria para el método `update` del servicio UserSpeciality cuando ocurre un error y retorna 0 filas afectadas.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `update` y ocurre un error, y retorna 0 filas afectadas,
     *       se lance una excepción `UserSpecialityNotUpdateException`.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`,
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialityUpdateDto`.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `update` del servicio,
     *       pero ocurre un error y retorna 0 filas afectadas. Se espera que se lance una excepción.</li>
     *   <li>Se verifica que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialityUpdateDto`.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba update con error y retorno de 0 filas afectadas")
    void givenThrowError_when_update_then_errorUpdateReturnRowAffectedZero(){

        given(this.mapperUserSpeciality.update(this.tuserSpeciality)).willReturn(0);
        given(this.dtoUserSpecialityMapper.UserSpecialityUpdateDtoTouserSpeciality(this.userSpecialityUpdateDto)).willReturn(this.tuserSpeciality);

        assertThrows(UserSpecialityNotUpdateException.class, ()-> this.serviceUserSpiciality.update(this.userSpecialityUpdateDto));

        then(this.mapperUserSpeciality).should(times(1)).update(this.tuserSpeciality);
        then(this.dtoUserSpecialityMapper).should(times(1)).UserSpecialityUpdateDtoTouserSpeciality(this.userSpecialityUpdateDto);
    }

    /**
     * Prueba unitaria para el método `update` del servicio UserSpeciality cuando los datos no son válidos.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `update` con datos no válidos,
     *       se lance una excepción `UserSpecialityNotUpdateException`.</li>
     *   <li>Confirma que ni el método `mapperUserSpeciality` ni el método `dtoUserSpecialityMapper`
     *       tengan interacciones.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `update` del servicio con datos que no son válidos.</li>
     *   <li>Se espera que se lance una excepción y que ni el método `mapperUserSpeciality`
     *       ni el método `dtoUserSpecialityMapper` tengan interacciones.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba update con datos no válidos")
    void givenThrowError_when_update_then_dataNotValid(){
        UserSpecialityUpdateDto userSpecialityUpdateNotValid = new UserSpecialityUpdateDto(1L, -1, 1L, 0);
        
        assertThrows(UserSpecialityNotUpdateException.class,()-> this.serviceUserSpiciality.update(userSpecialityUpdateNotValid));

        then(this.mapperUserSpeciality).shouldHaveNoInteractions();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `save` del servicio UserSpeciality cuando se guarda con éxito.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `save` y el guardado tiene éxito,
     *       se devuelve un valor entero que indica la cantidad de filas afectadas, y no se lanza ninguna excepción.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`,
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialitySaveDto`.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `save` del servicio y el guardado tiene éxito.
     *       Se espera que se devuelva un valor entero que indica la cantidad de filas afectadas, y que no se lance ninguna excepción.</li>
     *   <li>Se verifica que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialitySaveDto`.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba save exitoso")
    void given_rowAffected_when_saveUserSpeciality_then_success(){
        
        given(this.mapperUserSpeciality.save(this.tuserSpeciality)).willReturn(1);
        given(this.dtoUserSpecialityMapper.UserSpecialitySaveDtoToUserSpeciality(this.userSpecialitySaveDto)).willReturn(this.tuserSpeciality);

        Integer rowAffected = this.serviceUserSpiciality.save(this.userSpecialitySaveDto);

        assertNotNull(rowAffected);
        assertEquals(1, rowAffected);

        then(this.mapperUserSpeciality).should(times(1)).save(this.tuserSpeciality);
        then(this.dtoUserSpecialityMapper).should(times(1)).UserSpecialitySaveDtoToUserSpeciality(this.userSpecialitySaveDto);
    }

    /**
     * Prueba unitaria para el método `save` del servicio UserSpeciality cuando se produce un error al guardar.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `save` y el guardado produce un error,
     *       se lanza la excepción `UserSpecialityNotSaveException` y no se devuelve ningún valor.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`,
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialitySaveDto`.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `save` del servicio y el guardado produce un error.
     *       Se espera que se lance la excepción `UserSpecialityNotSaveException` y no se devuelva ningún valor.</li>
     *   <li>Se verifica que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialitySaveDto`.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de error al guardar")
    void given_throwError_when_saveUserSpeciality_then_returnNullMapper(){
        given(this.mapperUserSpeciality.save(this.tuserSpeciality)).willReturn(null);
        given(this.dtoUserSpecialityMapper.UserSpecialitySaveDtoToUserSpeciality(this.userSpecialitySaveDto)).willReturn(this.tuserSpeciality);

        assertThrows(UserSpecialityNotSaveException.class, ()-> this.serviceUserSpiciality.save(this.userSpecialitySaveDto));

        then(this.mapperUserSpeciality).should(times(1)).save(this.tuserSpeciality);
        then(this.dtoUserSpecialityMapper).should(times(1)).UserSpecialitySaveDtoToUserSpeciality(this.userSpecialitySaveDto);
    
    }

    /**
     * Prueba unitaria para el método `save` del servicio UserSpeciality cuando se produce un error y devuelve 0 filas afectadas.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `save` y el guardado produce un error (devolviendo 0 filas afectadas),
     *       se lanza la excepción `UserSpecialityNotSaveException` y no se devuelve ningún valor.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`,
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialitySaveDto`.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `save` del servicio y el guardado produce un error
     *       devolviendo 0 filas afectadas. Se espera que se lance la excepción `UserSpecialityNotSaveException` y no se devuelva ningún valor.</li>
     *   <li>Se verifica que el método `mapperUserSpeciality` se llama exactamente una vez con la entidad `tuserSpeciality`
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez con el DTO `userSpecialitySaveDto`.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de error al guardar con 0 filas afectadas")
    void given_throwError_when_saveUserSpeciality_then_returnZeroMapper(){
        given(this.mapperUserSpeciality.save(this.tuserSpeciality)).willReturn(0);
        given(this.dtoUserSpecialityMapper.UserSpecialitySaveDtoToUserSpeciality(this.userSpecialitySaveDto)).willReturn(this.tuserSpeciality);

        assertThrows(UserSpecialityNotSaveException.class, ()-> this.serviceUserSpiciality.save(this.userSpecialitySaveDto));

        then(this.mapperUserSpeciality).should(times(1)).save(this.tuserSpeciality);
        then(this.dtoUserSpecialityMapper).should(times(1)).UserSpecialitySaveDtoToUserSpeciality(this.userSpecialitySaveDto);
    
    }

    /**
     * Prueba unitaria para el método `save` del servicio UserSpeciality cuando se produce un error debido a datos no válidos.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `save` y los datos proporcionados no son válidos,
     *       se lanza la excepción `UserSpecialityNotSaveException` y no se produce ninguna interacción con los mappers ni el repositorio.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `save` del servicio con datos no válidos.
     *       Se espera que se lance la excepción `UserSpecialityNotSaveException` y que no haya interacciones con los mappers ni el repositorio.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de error al guardar con datos no válidos")
    void given_throwError_when_saveUserSpeciality_then_dataNotValid(){
        UserSpecialitySaveDto userSpecialitySaveNotValid = new UserSpecialitySaveDto(0, 1L, null);
        
        assertThrows(UserSpecialityNotSaveException.class,()-> this.serviceUserSpiciality.save(userSpecialitySaveNotValid));

        then(this.mapperUserSpeciality).shouldHaveNoInteractions();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdSpeciality` del servicio UserSpeciality al filtrar por especialidad con éxito.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdSpeciality` con un ID de especialidad válido,
     *       el método devuelve una lista no vacía de DTOs `UserSpecialityBySpecialityGetDto` y
     *       que las interacciones con el repositorio y el mapper son las esperadas.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `getByIdSpeciality` del servicio con un ID de especialidad válido.
     *       Se espera que el método devuelva una lista no vacía de DTOs y que haya interacciones específicas con el repositorio y el mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba exitosa al filtrar por especialidad")
    void given_listUserSpeciality_when_filterBySpeciality_then_success(){

        Integer idSpeciality = 1;

        UserSpecialityBySpecialityGetDto userSpecialityBySpecialityGet = new UserSpecialityBySpecialityGetDto(1L, "Activated", "Arcelio", "Montezuma", "ADMIN");


        given(this.mapperUserSpeciality.getByIdSpeciality(idSpeciality)).willReturn(List.of(this.tuserSpeciality));
        given(this.dtoUserSpecialityMapper.userSpecialityToUserSpecialityBySpecialityGetDto(this.tuserSpeciality)).willReturn(userSpecialityBySpecialityGet);

        List<UserSpecialityBySpecialityGetDto>  userSpecialityBySpecialityGetList = this.serviceUserSpiciality.getByIdSpeciality(idSpeciality);

        assertNotNull(userSpecialityBySpecialityGetList);
        assertFalse(userSpecialityBySpecialityGetList.isEmpty());
        assertNotNull(userSpecialityBySpecialityGetList.get(0));

        then(this.mapperUserSpeciality).should(times(1)).getByIdSpeciality(idSpeciality);
        then(this.dtoUserSpecialityMapper).should(times(1)).userSpecialityToUserSpecialityBySpecialityGetDto(this.tuserSpeciality);
    }

    /**
     * Prueba unitaria para el método `getByIdSpeciality` del servicio UserSpeciality al filtrar por especialidad con ID no válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdSpeciality` con un ID de especialidad no válido,
     *       el método arroja una excepción `NoDataFoundException` y que no hay interacciones con el repositorio y el mapper.</li>
     * </ul>
     *
     * <p>Comportamiento Esperado:</p>
     * <ul>
     *   <li>La prueba simula un escenario donde se llama al método `getByIdSpeciality` del servicio con un ID de especialidad no válido.
     *       Se espera que el método arroje una excepción y que no haya interacciones con el repositorio y el mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Error al filtrar por especialidad con ID no válido")
    void given_ThrowError_when_filterBySpeciality_then_idSpecialityNotValid(){
       
        Integer idSpeciality = 0;

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getByIdSpeciality(idSpeciality));

        then(this.mapperUserSpeciality).shouldHaveNoInteractions();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdSpeciality` del servicio UserSpeciality al filtrar por especialidad con lista vacía.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdSpeciality` con un ID de especialidad válido,
     *       y el método devuelve una lista vacía, arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de especialidad válido,
     *       y que el método `dtoUserSpecialityMapper` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdSpeciality` del servicio con un ID de especialidad válido,
     *       pero la lista de datos está vacía, lo que provoca que el método arroje una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de especialidad válido
     *       y que el método `dtoUserSpecialityMapper` no tiene interacciones, lo que significa que no debe haber datos válidos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Error al filtrar por especialidad con lista vacía")
    void given_ThrowError_when_filterBySpeciality_then_listEmpty(){
       
        Integer idSpeciality = 2;

        given(this.mapperUserSpeciality.getByIdSpeciality(idSpeciality)).willReturn(Collections.emptyList());


        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getByIdSpeciality(idSpeciality));

        then(this.mapperUserSpeciality).should(times(1)).getByIdSpeciality(idSpeciality);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }
    
    /**
     * Prueba unitaria para el método `getByIdSpeciality` del servicio UserSpeciality al filtrar por especialidad con lista nula.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdSpeciality` con un ID de especialidad válido,
     *       y el método devuelve una lista nula, arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de especialidad válido,
     *       y que el método `dtoUserSpecialityMapper` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdSpeciality` del servicio con un ID de especialidad válido,
     *       pero la lista de datos es nula, lo que provoca que el método arroje una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de especialidad válido
     *       y que el método `dtoUserSpecialityMapper` no tiene interacciones, lo que significa que no debe haber datos válidos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Error al filtrar por especialidad con lista nula")
    void given_ThrowError_when_filterBySpeciality_then_listNull(){
       
        Integer idSpeciality = 2;

        given(this.mapperUserSpeciality.getByIdSpeciality(idSpeciality)).willReturn(null);


        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getByIdSpeciality(idSpeciality));

        then(this.mapperUserSpeciality).should(times(1)).getByIdSpeciality(idSpeciality);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdUserTypeReg` del servicio UserSpeciality al filtrar por ID de Tipo de Usuario de Registro.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdUserTypeReg` con un ID de Tipo de Usuario de Registro válido,
     *       y el método devuelve una lista válida, se procesa correctamente y no se arroja ninguna excepción.</li>
     *   <li>Confirma que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de Tipo de Usuario de Registro válido,
     *       y que el método `dtoUserSpecialityMapper` también se llama exactamente una vez para mapear los datos.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdUserTypeReg` del servicio con un ID de Tipo de Usuario de Registro válido,
     *       y que el método devuelve una lista válida con al menos un elemento.</li>
     *   <li>Se verifica que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de Tipo de Usuario de Registro válido
     *       y que el método `dtoUserSpecialityMapper` también se llama exactamente una vez para mapear los datos, lo que significa que hay datos válidos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por ID de Tipo de Usuario de Registro con lista válida")
    void given_ListUserSpeciality_when_filterByIdUserTypeReg_then_success(){

        Integer idUserTypeReg = 1;
        UserSpecialityByUserTypeRegGetDto userSpecialityByUserTypeRegGet = new UserSpecialityByUserTypeRegGetDto(1L,"Odontologo","Activated");

        given(this.mapperUserSpeciality.getByIdUserTypeReg(idUserTypeReg)).willReturn(List.of(this.tuserSpeciality));
        given(this.dtoUserSpecialityMapper.userSpecialityToUserSpecialityByUserTypeRegGetDto(this.tuserSpeciality)).willReturn(userSpecialityByUserTypeRegGet);

        List<UserSpecialityByUserTypeRegGetDto> userSpecialityByUserTypeRegGetDtos = this.serviceUserSpiciality.getByIdUserTypeReg(idUserTypeReg);

        assertNotNull(userSpecialityByUserTypeRegGetDtos);
        assertFalse(userSpecialityByUserTypeRegGetDtos.isEmpty());
        assertNotNull(userSpecialityByUserTypeRegGetDtos.get(0));

        then(this.mapperUserSpeciality).should(times(1)).getByIdUserTypeReg(idUserTypeReg);
        then(this.dtoUserSpecialityMapper).should(times(1)).userSpecialityToUserSpecialityByUserTypeRegGetDto(this.tuserSpeciality);
    }

    /**
     * Prueba unitaria para el método `getByIdUserTypeReg` del servicio UserSpeciality al filtrar por ID de Tipo de Usuario de Registro.
     * Verifica el manejo de errores cuando se proporciona un ID de Tipo de Usuario de Registro no válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdUserTypeReg` con un ID de Tipo de Usuario de Registro no válido,
     *       se lanza una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` y `dtoUserSpecialityMapper` no tienen interacciones,
     *       ya que no se espera ninguna llamada cuando el ID de Tipo de Usuario de Registro no es válido.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por ID de Tipo de Usuario de Registro con ID no válido")
    void given_ThrowError_when_filterByIdUserTypeReg_then_IdUserTypeRegNotValid(){

        Integer idUserTypeReg = 0;

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getByIdUserTypeReg(idUserTypeReg));

        then(this.mapperUserSpeciality).shouldHaveNoInteractions();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdUserTypeReg` del servicio UserSpeciality al filtrar por ID de Tipo de Usuario de Registro.
     * Verifica el manejo de errores cuando el mapper devuelve datos nulos.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdUserTypeReg` con un ID de Tipo de Usuario de Registro válido,
     *       pero el mapper devuelve datos nulos, se lanza una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de Tipo de Usuario de Registro válido,
     *       y que el método `dtoUserSpecialityMapper` no tiene interacciones, ya que no se espera ninguna llamada cuando el mapper devuelve datos nulos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por ID de Tipo de Usuario de Registro con datos nulos")
    void given_ThrowError_when_filterByIdUserTypeReg_then_MapperReturnDataNull(){

        Integer idUserTypeReg = 2;

        given(this.mapperUserSpeciality.getByIdUserTypeReg(idUserTypeReg)).willReturn(null);

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getByIdUserTypeReg(idUserTypeReg));

        then(this.mapperUserSpeciality).should(times(1)).getByIdUserTypeReg(idUserTypeReg);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdUserTypeReg` del servicio UserSpeciality al filtrar por ID de Tipo de Usuario de Registro.
     * Verifica el manejo de errores cuando el mapper devuelve una lista de datos vacía.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdUserTypeReg` con un ID de Tipo de Usuario de Registro válido,
     *       pero el mapper devuelve una lista vacía, se lanza una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de Tipo de Usuario de Registro válido,
     *       y que el método `dtoUserSpecialityMapper` no tiene interacciones, ya que no se espera ninguna llamada cuando el mapper devuelve una lista vacía.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por ID de Tipo de Usuario de Registro con lista vacía")
    void given_ThrowError_when_filterByIdUserTypeReg_then_MapperReturnDataIsEmpty(){

        Integer idUserTypeReg = 2;

        given(this.mapperUserSpeciality.getByIdUserTypeReg(idUserTypeReg)).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getByIdUserTypeReg(idUserTypeReg));

        then(this.mapperUserSpeciality).should(times(1)).getByIdUserTypeReg(idUserTypeReg);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdStatus` del servicio UserSpeciality al filtrar por ID de Estado.
     * Verifica el éxito del método cuando se proporciona un ID de Estado válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdStatus` con un ID de Estado válido,
     *       el servicio devuelve una lista de datos.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de Estado válido,
     *       y que el método `dtoUserSpecialityMapper` se llama exactamente una vez para cada elemento en la lista de datos devuelta por el mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por ID de Estado con datos exitosos")
    void given_listUserSpeciality_when_getUserSpecialityByStatus_then_dataSuccess(){

        Integer idStatus = 1;

        UserSpecialityByStatusGetDto userSpecialityByStatusGet = new UserSpecialityByStatusGetDto(1L,"Odontologo","Arcelio","Montezuma","Admin");

        given(this.mapperUserSpeciality.getByIdStatus(idStatus)).willReturn(List.of(this.tuserSpeciality));
        given(this.dtoUserSpecialityMapper.userSpecialityToUserSpecialityByStatusGetDto(this.tuserSpeciality)).willReturn(userSpecialityByStatusGet);

        List<UserSpecialityByStatusGetDto> userSpecialityByStatusList = this.serviceUserSpiciality.getByIdStatus(idStatus);

        assertNotNull(userSpecialityByStatusList);
        assertFalse(userSpecialityByStatusList.isEmpty());
        assertNotNull(userSpecialityByStatusList.stream().findFirst());

        then(this.mapperUserSpeciality).should(times(1)).getByIdStatus(idStatus);
        then(this.dtoUserSpecialityMapper).should(times(1)).userSpecialityToUserSpecialityByStatusGetDto(this.tuserSpeciality);
    }

    /**
     * Prueba unitaria para el método `getByIdStatus` del servicio UserSpeciality al filtrar por un ID de Estado no válido.
     * Verifica que el método lance una excepción `NoDataFoundException` cuando se proporciona un ID de Estado no válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdStatus` con un ID de Estado no válido,
     *       el servicio lanza una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que ni el método `mapperUserSpeciality` ni el método `dtoUserSpecialityMapper` se llaman, ya que la excepción se lanza antes de llegar a esos métodos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por ID de Estado con ID no válido")
    void given_throwError_when_getUserSpecialityByStatus_then_idNotValid(){

        Integer idStatus = 0;

        assertThrows(NoDataFoundException.class,()-> this.serviceUserSpiciality.getByIdStatus(idStatus));

        then(this.mapperUserSpeciality).shouldHaveNoInteractions();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdStatus` del servicio UserSpeciality al filtrar por un ID de Estado,
     * donde el mapeo desde el repositorio devuelve un resultado nulo.
     * Verifica que el método lance una excepción `NoDataFoundException` cuando el mapeo desde el repositorio devuelve un resultado nulo.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdStatus` y el mapeo desde el repositorio devuelve un resultado nulo,
     *       el servicio lanza una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de Estado proporcionado.</li>
     *   <li>Confirma que el método `dtoUserSpecialityMapper` no se llama, ya que la excepción se lanza antes de llegar a ese método.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por ID de Estado con resultado nulo en el mapeo")
    void given_throwError_when_getUserSpecialityByStatus_then_mapperReturnNull(){

        Integer idStatus = 2;

        given(this.mapperUserSpeciality.getByIdStatus(idStatus)).willReturn(null);

        assertThrows(NoDataFoundException.class,()-> this.serviceUserSpiciality.getByIdStatus(idStatus));

        then(this.mapperUserSpeciality).should(times(1)).getByIdStatus(idStatus);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdStatus` del servicio UserSpeciality al filtrar por un ID de Estado,
     * donde el mapeo desde el repositorio devuelve una lista vacía.
     * Verifica que el método lance una excepción `NoDataFoundException` cuando el mapeo desde el repositorio devuelve una lista vacía.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdStatus` y el mapeo desde el repositorio devuelve una lista vacía,
     *       el servicio lanza una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` se llama exactamente una vez con el ID de Estado proporcionado.</li>
     *   <li>Confirma que el método `dtoUserSpecialityMapper` no se llama, ya que la excepción se lanza antes de llegar a ese método.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por ID de Estado con mapeo devolviendo lista vacía")
    void given_throwError_when_getUserSpecialityByStatus_then_mapperReturnEmpty(){

        Integer idStatus = 3;

        given(this.mapperUserSpeciality.getByIdStatus(idStatus)).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class,()-> this.serviceUserSpiciality.getByIdStatus(idStatus));

        then(this.mapperUserSpeciality).should(times(1)).getByIdStatus(idStatus);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdStatusActivated` del servicio UserSpeciality al filtrar por usuarios activados,
     * donde el mapeo desde el repositorio devuelve una lista con al menos un elemento.
     * Verifica que el método devuelva una lista no vacía de objetos `UserSpecialityByStatusGetDto`.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdStatusActivated` y el mapeo desde el repositorio devuelve una lista no vacía,
     *       el servicio devuelve una lista no vacía de objetos `UserSpecialityByStatusGetDto`.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` se llama exactamente una vez para obtener la lista de usuarios activados.</li>
     *   <li>Verifica que el método `dtoUserSpecialityMapper` se llama exactamente una vez para cada elemento en la lista devuelta por el mapeo.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por usuarios activados con mapeo devolviendo lista no vacía")
    void given_listUserSpeciality_when_getUserSpecialityByStatusActivated_then_success(){

        UserSpecialityByStatusGetDto userSpecialityByStatusGet = new UserSpecialityByStatusGetDto(1L,"Odontologo","Arcelio","Montezuma","Admin");

        given(this.mapperUserSpeciality.getByIdStatusActivated()).willReturn(List.of(this.tuserSpeciality));
        given(this.dtoUserSpecialityMapper.userSpecialityToUserSpecialityByStatusGetDto(this.tuserSpeciality)).willReturn(userSpecialityByStatusGet);

        List<UserSpecialityByStatusGetDto> userSpecialityByStatusList = this.serviceUserSpiciality.getByIdStatusActivated();

        assertNotNull(userSpecialityByStatusList);
        assertFalse(userSpecialityByStatusList.isEmpty());
        assertNotNull(userSpecialityByStatusList.stream().findFirst());

        then(this.mapperUserSpeciality).should(times(1)).getByIdStatusActivated();
        then(this.dtoUserSpecialityMapper).should(times(1)).userSpecialityToUserSpecialityByStatusGetDto(this.tuserSpeciality);
    }

    /**
     * Prueba unitaria para el método `getByIdStatusActivated` del servicio UserSpeciality al filtrar por usuarios activados,
     * donde el mapeo desde el repositorio devuelve `null`.
     * Verifica que el método arroje una excepción `NoDataFoundException`.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdStatusActivated` y el mapeo desde el repositorio devuelve `null`,
     *       el servicio arroja una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` se llama exactamente una vez para obtener la lista de usuarios activados.</li>
     *   <li>Verifica que el método `dtoUserSpecialityMapper` no se llama en este caso.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por usuarios activados con mapeo devolviendo null")
    void given_throwError_when_getUserSpecialityByStatusActivated_then_mapperReturnNull(){

        given(this.mapperUserSpeciality.getByIdStatusActivated()).willReturn(null);

        assertThrows(NoDataFoundException.class,()-> this.serviceUserSpiciality.getByIdStatusActivated());

        then(this.mapperUserSpeciality).should(times(1)).getByIdStatusActivated();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();

    }

    /**
     * Prueba unitaria para el método `getByIdStatusActivated` del servicio UserSpeciality al filtrar por usuarios activados,
     * donde el mapeo desde el repositorio devuelve una lista vacía.
     * Verifica que el método arroje una excepción `NoDataFoundException`.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByIdStatusActivated` y el mapeo desde el repositorio devuelve una lista vacía,
     *       el servicio arroja una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` se llama exactamente una vez para obtener la lista de usuarios activados.</li>
     *   <li>Verifica que el método `dtoUserSpecialityMapper` no se llama en este caso.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por usuarios activados con mapeo devolviendo lista vacía")
    void given_throwError_when_getUserSpecialityByStatusActivated_then_mapperReturnEmpty(){

        given(this.mapperUserSpeciality.getByIdStatusActivated()).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class,()-> this.serviceUserSpiciality.getByIdStatusActivated());

        then(this.mapperUserSpeciality).should(times(1)).getByIdStatusActivated();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();

    }

    /**
     * Prueba unitaria para el método `getByTypeUserId` del servicio UserSpeciality al filtrar por usuarios de un tipo específico,
     * donde el mapeo desde el repositorio devuelve una lista con al menos un elemento.
     * Verifica que la lista obtenida no esté vacía y que el mapeo de la entidad al DTO sea exitoso.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que cuando se llama al método `getByTypeUserId` con un id de tipo de usuario válido y el mapeo desde el repositorio devuelve
     *       una lista con al menos un elemento, el servicio devuelve una lista de DTOs no vacía.</li>
     *   <li>Verifica que el método `mapperUserSpeciality` se llama exactamente una vez para obtener la lista de usuarios por tipo.</li>
     *   <li>Verifica que el método `dtoUserSpecialityMapper` se llama exactamente una vez para convertir la entidad a DTO.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por usuarios de un tipo específico con mapeo devolviendo lista no vacía")
    void given_listUserSpeciality_when_getUserSpecialityByTypeUser_then_success(){

        Integer idTypeUser = 1;

        UserSpecialityByTypeUserGetDto userSpecialityByTypeUser = new UserSpecialityByTypeUserGetDto(1L,"Odontologo","Activated","Arcelio","Montezuma");

        given(this.mapperUserSpeciality.getByTypeUserId(idTypeUser)).willReturn(List.of(this.tuserSpeciality));
        given(this.dtoUserSpecialityMapper.userSpecialityToUserSpecialityByTypeUserGetDto(this.tuserSpeciality)).willReturn(userSpecialityByTypeUser);

        List<UserSpecialityByTypeUserGetDto> userSpecialityByTypeUserList = this.serviceUserSpiciality.getByTypeUserId(idTypeUser);

        assertNotNull(userSpecialityByTypeUserList);
        assertFalse(userSpecialityByTypeUserList.isEmpty());
        assertNotNull(userSpecialityByTypeUserList.stream().findFirst());

        then(this.mapperUserSpeciality).should(times(1)).getByTypeUserId(idTypeUser);
        then(this.dtoUserSpecialityMapper).should(times(1)).userSpecialityToUserSpecialityByTypeUserGetDto(this.tuserSpeciality);
    }

    /**
     * Prueba unitaria para el método `getByTypeUserId` del servicio UserSpeciality al intentar filtrar por usuarios de un tipo específico
     * con un ID de tipo de usuario no válido.
     * Verifica que se lance una excepción `NoDataFoundException` y que no haya interacciones con los mapeadores ni el repositorio.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que al llamar al método `getByTypeUserId` con un ID de tipo de usuario no válido, el servicio lanza una excepción
     *       `NoDataFoundException`.</li>
     *   <li>Verifica que no haya interacciones con el repositorio y que el mapeador de DTOs tampoco sea invocado.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por usuarios de un tipo específico con ID no válido")
    void given_throwError_when_getUserSpecialityByTypeUser_then_IdNotValid(){

        Integer idTypeUser = 0;

        assertThrows(NoDataFoundException.class,()-> this.serviceUserSpiciality.getByTypeUserId(idTypeUser));

        then(this.mapperUserSpeciality).shouldHaveNoInteractions();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByTypeUserId` del servicio UserSpeciality al intentar filtrar por usuarios de un tipo específico
     * cuando el mapeador retorna null.
     * Verifica que se lance una excepción `NoDataFoundException` y que haya una única interacción con el repositorio.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que al llamar al método `getByTypeUserId` con un ID de tipo de usuario válido, pero el mapeador devuelve null,
     *       el servicio lanza una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que haya una única interacción con el repositorio y que el mapeador de DTOs no sea invocado.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por usuarios de un tipo específico con mapeador retornando null")
    void given_throwError_when_getUserSpecialityByTypeUser_then_mapperReturnNull(){

        Integer idTypeUser = 1;

        given(this.mapperUserSpeciality.getByTypeUserId(idTypeUser)).willReturn(null);

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getByTypeUserId(idTypeUser));

        then(this.mapperUserSpeciality).should(times(1)).getByTypeUserId(idTypeUser);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByTypeUserId` del servicio UserSpeciality al intentar filtrar por usuarios de un tipo específico
     * cuando el repositorio retorna una lista vacía.
     * Verifica que se lance una excepción `NoDataFoundException` y que haya una única interacción con el repositorio.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Confirma que al llamar al método `getByTypeUserId` con un ID de tipo de usuario válido, pero el repositorio retorna una lista vacía,
     *       el servicio lanza una excepción `NoDataFoundException`.</li>
     *   <li>Verifica que haya una única interacción con el repositorio y que el mapeador de DTOs no sea invocado.</li>
     * </ul>
     */
    @Test
    @DisplayName("Filtrar por usuarios de un tipo específico con repositorio retornando lista vacía")
    void given_throwError_when_getUserSpecialityByTypeUser_then_mapperReturnEmpty(){

        Integer idTypeUser = 1;

        given(this.mapperUserSpeciality.getByTypeUserId(idTypeUser)).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getByTypeUserId(idTypeUser));

        then(this.mapperUserSpeciality).should(times(1)).getByTypeUserId(idTypeUser);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }
}