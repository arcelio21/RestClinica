package com.example.service.speciality;

import com.example.dto.speciality.userspeciality.UserSpecialityGetDto;
import com.example.dto.speciality.userspeciality.UserSpecialityUpdateDto;
import com.example.dtomapper.speciality.DtoUserSpecialityMapper;
import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TtypeUser;
import com.example.entity.user.TuserReg;
import com.example.entity.user.TuserTypeReg;
import com.example.exception.NoDataFoundException;
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
}