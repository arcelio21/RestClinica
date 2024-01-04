package com.example.service.speciality;

import com.example.dto.speciality.userspeciality.UserSpecialityGetDto;
import com.example.dtomapper.speciality.DtoUserSpecialityMapper;
import com.example.entity.speciality.Tspeciality;
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TtypeUser;
import com.example.entity.user.TuserReg;
import com.example.entity.user.TuserTypeReg;
import com.example.exception.NoDataFoundException;
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


    @Test
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

    @Test
    void givenThrowError_when_getById_then_IDNotValid(){
        
        Integer idUserSpeciality = 0;

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getById(idUserSpeciality));

        then(this.mapperUserSpeciality).shouldHaveNoInteractions();
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }

    @Test
    void givenThrowError_when_getById_then_dataNotExist(){
        
        Integer idUserSpeciality = 2;

        given(this.mapperUserSpeciality.getById(idUserSpeciality)).willReturn(null);

        assertThrows(NoDataFoundException.class, ()-> this.serviceUserSpiciality.getById(idUserSpeciality));

        then(this.mapperUserSpeciality).should(times(1)).getById(idUserSpeciality);
        then(this.dtoUserSpecialityMapper).shouldHaveNoInteractions();
    }
}