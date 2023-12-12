package com.example.service.speciality;

import com.example.dto.speciality.speciality.SpecialityGetDto;
import com.example.dtomapper.speciality.DtoSpecialityMapper;
import com.example.entity.speciality.Tspeciality;
import com.example.exception.NoDataFoundException;
import com.example.mapper.speciality.MapperSpeciality;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ServiceSpecialityImplTest {

    @Mock
    private MapperSpeciality mapperSpeciality;

    @Mock
    private DtoSpecialityMapper dtoSpecialityMapper;

    @InjectMocks
    private ServiceSpecialityImpl serviceSpeciality;

    private final Integer ID_SPECIALITY = 1;

    @BeforeEach
    void setUp() {

    }

    /**
     * Prueba unitaria para el método `getAll` del servicio Speciality cuando la
     * operación es exitosa.
     *
     * <p>
     * Descripción de la Prueba:
     * </p>
     * <ul>
     * <li>Verifica que cuando se llama al método `getAll` del servicio Speciality y
     * la operación es exitosa,
     * devuelve una lista no nula de objetos SpecialityGetDto.</li>
     * <li>Confirma que el método `dtoSpecialityMapper` se llama exactamente una vez
     * con cada objeto Tspeciality devuelto por el mapper,
     * y que el método `mapperSpeciality` también se llama exactamente una vez.</li>
     * <li>Realiza aserciones específicas sobre la estructura y tipo de datos de los
     * objetos SpecialityGetDto en la lista resultante.</li>
     * </ul>
     *
     * <p>
     * Simula el comportamiento esperado:
     * </p>
     * <ul>
     * <li>La prueba simula que se llama al método `getAll` del servicio Speciality,
     * y el método devuelve una lista de objetos Tspeciality
     * que luego se mapean a objetos SpecialityGetDto. Se verifican las
     * interacciones con los mappers y se realizan aserciones sobre la lista
     * resultante.</li>
     * </ul>
     */
    @DisplayName("Prueba exitosa de getAll en SpecialityService")
    @Test
    void getAll_Success() {

        given(this.mapperSpeciality.getAll()).willReturn(List.of(new Tspeciality(1, "Odontologo")));
        given(this.dtoSpecialityMapper.TspecialityToSpecialityGet(any(Tspeciality.class)))
                .willReturn(new SpecialityGetDto(1, "Odontologo"));

        List<SpecialityGetDto> specialityGetDtoList = this.serviceSpeciality.getAll();

        assertNotNull(specialityGetDtoList);
        assertEquals(1, specialityGetDtoList.size());
        assertInstanceOf(SpecialityGetDto.class, specialityGetDtoList.get(0));
        assertInstanceOf(Integer.class, specialityGetDtoList.get(0).id());
        assertInstanceOf(String.class, specialityGetDtoList.get(0).name());

        then(this.dtoSpecialityMapper).should(times(1)).TspecialityToSpecialityGet(any(Tspeciality.class));
        then(this.mapperSpeciality).should(times(1)).getAll();

    }

    /**
     * Prueba unitaria para el método `getAll` del servicio Speciality cuando la
     * lista está vacía.
     *
     * <p>
     * Descripción de la Prueba:
     * </p>
     * <ul>
     * <li>Verifica que cuando se llama al método `getAll` del servicio Speciality y
     * la lista de especialidades está vacía,
     * arroja una excepción `NoDataFoundException`.</li>
     * <li>Confirma que el método `mapperSpeciality` se llama exactamente una vez
     * para obtener la lista,
     * y que el método `dtoSpecialityMapper` no tiene interacciones.</li>
     * </ul>
     *
     * <p>
     * Simula el comportamiento esperado:
     * </p>
     * <ul>
     * <li>La prueba simula que se llama al método `getAll` del servicio Speciality,
     * y el método devuelve una lista vacía,
     * lo que provoca que el método arroje una excepción
     * `NoDataFoundException`.</li>
     * <li>Se verifica que el método `mapperSpeciality` se llama exactamente una vez
     * para obtener la lista de especialidades,
     * y que el método `dtoSpecialityMapper` no tiene interacciones, ya que no hay
     * datos para mapear.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getAll con lista vacía en SpecialityService")
    void getAll_isEmpty() {

        given(this.mapperSpeciality.getAll()).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> this.serviceSpeciality.getAll());

        then(this.dtoSpecialityMapper).shouldHaveNoInteractions();
        then(this.mapperSpeciality).should(times(1)).getAll();

    }

    /**
     * Prueba unitaria para el método `getById` del servicio Speciality cuando se obtienen datos exitosos.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getById` del servicio Speciality con un ID de especialidad válido,
     *       y se obtienen datos exitosos, devuelve un objeto `SpecialityGetDto` no nulo.</li>
     *   <li>Confirma que el método `mapperSpeciality` se llama exactamente una vez con el ID de especialidad válido,
     *       y que el método `dtoSpecialityMapper` se llama exactamente una vez para convertir los datos obtenidos.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getById` del servicio Speciality con un ID de especialidad válido,
     *       y el método devuelve datos exitosos, lo que provoca que se devuelva un objeto `SpecialityGetDto` no nulo.</li>
     *   <li>Se verifica que el método `mapperSpeciality` se llama exactamente una vez con el ID de especialidad válido
     *       y que el método `dtoSpecialityMapper` se llama exactamente una vez para convertir los datos obtenidos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getById con éxito en SpecialityService")
    void getById_Success(){


        given(this.mapperSpeciality.getById(this.ID_SPECIALITY)).willReturn(new Tspeciality(1,"Odontologo"));
        given(this.dtoSpecialityMapper.TspecialityToSpecialityGet(any(Tspeciality.class))).willReturn(new SpecialityGetDto(1,"Odontologo"));

        SpecialityGetDto specialityGetDto = this.serviceSpeciality.getById(this.ID_SPECIALITY);

        assertNotNull(specialityGetDto);
        assertInstanceOf(SpecialityGetDto.class,specialityGetDto);
        assertInstanceOf(Integer.class,specialityGetDto.id());
        assertInstanceOf(String.class,specialityGetDto.name());

        then(this.mapperSpeciality).should(times(1)).getById(this.ID_SPECIALITY);
        then(this.dtoSpecialityMapper).should(times(1)).TspecialityToSpecialityGet(any(Tspeciality.class));
    }

    /**
     * Prueba unitaria para el método `getById` del servicio Speciality cuando no existen datos para el ID proporcionado.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getById` del servicio Speciality con un ID de especialidad válido,
     *       y no existen datos para ese ID, se arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperSpeciality` se llama exactamente una vez con el ID de especialidad válido,
     *       y que el método `dtoSpecialityMapper` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getById` del servicio Speciality con un ID de especialidad válido,
     *       pero no existen datos para ese ID, lo que provoca que se arroje una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que el método `mapperSpeciality` se llama exactamente una vez con el ID de especialidad válido
     *       y que el método `dtoSpecialityMapper` no tiene interacciones, ya que no hay datos para convertir.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getById cuando no existen datos en SpecialityService")
    void getById_NotExits(){

        given(this.mapperSpeciality.getById(this.ID_SPECIALITY)).willReturn(null);

        assertThrows(NoDataFoundException.class, ()-> this.serviceSpeciality.getById(this.ID_SPECIALITY));

        then(this.mapperSpeciality).should(times(1)).getById(this.ID_SPECIALITY);
        then(this.dtoSpecialityMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getById` del servicio Speciality cuando se proporciona un ID no válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getById` del servicio Speciality con un ID no válido,
     *       se arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que tanto el método `mapperSpeciality` como el método `dtoSpecialityMapper` no tienen interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getById` del servicio Speciality con un ID no válido,
     *       lo que provoca que se arroje una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que tanto el método `mapperSpeciality` como el método `dtoSpecialityMapper` no tienen interacciones,
     *       ya que no se debería haber llamado a estos métodos con un ID no válido.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getById con ID no válido en SpecialityService")
    void getById_ID_NotValid(){

        Integer id_notvalid=-1;

        assertThrows(NoDataFoundException.class, ()-> this.serviceSpeciality.getById(id_notvalid));

        then(this.mapperSpeciality).shouldHaveNoInteractions();
        then(this.dtoSpecialityMapper).shouldHaveNoInteractions();
    }


}