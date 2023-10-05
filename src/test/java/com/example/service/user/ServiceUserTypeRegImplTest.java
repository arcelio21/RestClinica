package com.example.service.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.dto.user.type_user_reg.TypeUserOfUserRegGetDto;
import com.example.dto.user.type_user_reg.UserRegOfTypeUserGetDto;
import com.example.dto.user.type_user_reg.UserTypeRegGetDto;
import com.example.dto.user.type_user_reg.UserTypeRegUpdateDto;
import com.example.dtomapper.user.DtoUserTypeRegMapper;
import com.example.entity.user.TuserTypeReg;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.type_user_reg.UserTypeRegNotUpdateException;
import com.example.mapper.user.MapperUserTypeReg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceUserTypeRegImplTest {

    @Mock
    private MapperUserTypeReg mapperUserTypeReg;

    @Mock
    private DtoUserTypeRegMapper dtoMapperUserTypeReg;

    @InjectMocks
    private ServiceUserTypeRegImpl service;

    /**
     * Prueba unitaria para el método `getAll` del servicio UserTypeReg.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que el método `getAll` recupere una lista de datos de registro de tipo de usuario.</li>
     *   <li>Confirma que los datos recuperados no son nulos y no están vacíos.</li>
     *   <li>Confirma que el método `mapperUserTypeReg.getAll` se llama exactamente una vez.</li>
     *   <li>Confirma que el método `dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getAll` recupere y mapee con éxito los datos de registro de tipo de usuario.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba del método getAll para UserTypeReg")
    void getAll() {

        given(this.mapperUserTypeReg.getAll())
                .willReturn(List.of(new TuserTypeReg()));

        given(this.dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class)))
                .willReturn(new UserTypeRegGetDto(1L, "Arcelio Montezuma", 12000704001435L, "ADMIN", "Activivated"));

        List<UserTypeRegGetDto> userTypeRegGetDtos = service.getAll();

        assertNotNull(userTypeRegGetDtos);
        assertFalse(userTypeRegGetDtos.isEmpty());

        then(this.mapperUserTypeReg).should(times(1)).getAll();
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class));
    }

    /**
     * Prueba unitaria para el método `getAll` del servicio UserTypeReg.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que el método `getAll` recupere una lista de datos de registro de tipo de usuario.</li>
     *   <li>Confirma que cuando el método `getAll` devuelve null, se lanza una excepción NoDataFoundException.</li>
     *   <li>Confirma que el método `mapperUserTypeReg.getAll` se llama exactamente una vez.</li>
     *   <li>Confirma que el método `dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getAll` maneje correctamente una situación en la que no se encuentren datos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba del método getAll con datos no válidos")
    void getAll_DataNotValid() {
        given(this.mapperUserTypeReg.getAll())
                .willReturn(null);

        assertThrows(NoDataFoundException.class, () -> this.service.getAll());

        then(this.mapperUserTypeReg).should(times(1)).getAll();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getAll` del servicio UserTypeReg.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que el método `getAll` recupere una lista de datos de registro de tipo de usuario.</li>
     *   <li>Confirma que cuando el método `getAll` devuelve una lista vacía, se lanza una excepción NoDataFoundException.</li>
     *   <li>Confirma que el método `mapperUserTypeReg.getAll` se llama exactamente una vez.</li>
     *   <li>Confirma que el método `dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getAll` maneje correctamente una situación en la que no se encuentren datos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba del método getAll con datos vacíos")
    void getAll_DataIsEmpty() {

        given(this.mapperUserTypeReg.getAll())
                .willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> this.service.getAll());

        then(this.mapperUserTypeReg).should(times(1)).getAll();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    // ---------------------------------------------------------------------------------------------------------

    /**
     * Prueba unitaria para el método `getById` del servicio UserTypeReg.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que el método `getById` recupere un registro de tipo de usuario por su ID.</li>
     *   <li>Confirma que cuando se llama al método `getById` con un ID válido, se devuelve un DTO de tipo de usuario.</li>
     *   <li>Confirma que el método `mapperUserTypeReg.getById` se llama exactamente una vez con el ID proporcionado.</li>
     *   <li>Confirma que el método `dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto` se llama exactamente una vez para convertir el resultado en un DTO.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getById` funcione correctamente para recuperar un registro de tipo de usuario por su ID.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba del método getById")
    void getById() {

        Long idValid = 9L;
        given(this.mapperUserTypeReg.getById(idValid)).willReturn(new TuserTypeReg());

        given(this.dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class)))
                .willReturn(new UserTypeRegGetDto(1L, "Arcelio Montezuma", 12000704001435L, "ADMIN", "Activivated"));

        UserTypeRegGetDto userTypeRegGetDto = this.service.getById(idValid);

        assertNotNull(userTypeRegGetDto);
        assertNotNull(userTypeRegGetDto.id());

        then(this.mapperUserTypeReg).should(times(1)).getById(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class));
    }

    /**
     * Prueba unitaria para el método `getById` del servicio UserTypeReg cuando se proporciona un ID no válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getById` con un ID no válido (cero o negativo), se lance una excepción de tipo NoDataFoundException.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` y el método `dtoMapperUserTypeReg` no tienen interacciones cuando se proporciona un ID no válido.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getById` maneja adecuadamente un ID no válido y lanza una excepción.</li>
     *   <li>Confirma que no se realizan llamadas no deseadas a los métodos del servicio y el mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba del método getById con ID no válido")
    void getById_NotValid() {

        Long idNotValid = 0L;

        assertThrows(NoDataFoundException.class, () -> this.service.getById(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getById` del servicio UserTypeReg cuando el ID proporcionado no se encuentra en la base de datos.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getById` con un ID que no existe en la base de datos, se lance una excepción de tipo NoDataFoundException.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` obtiene nulo (no encuentra datos) y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getById` maneja adecuadamente la falta de datos para un ID y lanza una excepción.</li>
     *   <li>Confirma que se llama al método `getById` del servicio exactamente una vez con el ID proporcionado.</li>
     *   <li>Confirma que no se realizan interacciones no deseadas con los métodos del servicio y el mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba del método getById con datos no encontrados")
    void getById_DataNotFound() {

        Long idNotFound = 32L;

        given(this.mapperUserTypeReg.getById(idNotFound)).willReturn(null);

        assertThrows(NoDataFoundException.class, () -> this.service.getById(idNotFound));

        then(this.mapperUserTypeReg).should(times(1)).getById(idNotFound);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    // --------------------------------------------------------------------------------------------

    /**
     * Prueba unitaria para el método `getByIdUserReg` del servicio UserTypeReg cuando se obtiene información de tipos de usuario registrados por un ID de usuario.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdUserReg` con un ID de usuario válido, se obtiene una lista de tipos de usuario registrados.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de usuario proporcionado y que el método `dtoMapperUserTypeReg` convierte los resultados en DTOs.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getByIdUserReg` obtiene con éxito información de tipos de usuario registrados para un usuario específico y la convierte en DTOs.</li>
     *   <li>Confirma que se llama al método `getByIdUserReg` del servicio exactamente una vez con el ID de usuario proporcionado.</li>
     *   <li>Confirma que el método `dtoMapperUserTypeReg` convierte los resultados en DTOs y se llama exactamente una vez para cada resultado.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba del método getByIdUserReg")
    void getByIdUserReg(){

        Long idValid = 1L;

        given(this.mapperUserTypeReg.getByIdUserReg(idValid)).willReturn(List.of(new TuserTypeReg()));

        given(this.dtoMapperUserTypeReg.tuserTypeRegToTypeUserOfUserRegGet(any(TuserTypeReg.class)))
                .willReturn(new TypeUserOfUserRegGetDto(3L, "ADMIN", "Activated"));
        
        List<TypeUserOfUserRegGetDto> typeUserOfUserRegGetDtos = this.service.getByIdUserReg(idValid);

        assertNotNull(typeUserOfUserRegGetDtos);
        assertFalse(typeUserOfUserRegGetDtos.isEmpty());
        
        then(this.mapperUserTypeReg).should(times(1)).getByIdUserReg(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToTypeUserOfUserRegGet(any(TuserTypeReg.class));
    }

    /**
     * Prueba unitaria para el método `getByIdUserReg` del servicio UserTypeReg cuando se proporciona un ID de usuario no válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdUserReg` con un ID de usuario no válido (por ejemplo, cero), se lanza una excepción NoDataFoundException.</li>
     *   <li>Confirma que los métodos `mapperUserTypeReg` y `dtoMapperUserTypeReg` no tienen interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getByIdUserReg` del servicio arroja una excepción NoDataFoundException cuando se proporciona un ID de usuario no válido.</li>
     *   <li>Confirma que no hay interacciones con los métodos `mapperUserTypeReg` y `dtoMapperUserTypeReg` cuando se proporciona un ID de usuario no válido.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdUserReg con ID no válido")
    void getByIdUserReg_IdNotValid(){

        Long idNotValid = 0L;

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserReg(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdUserReg` del servicio UserTypeReg cuando no se encuentra ningún dato.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdUserReg` con un ID de usuario válido pero no se encuentra ningún dato,
     *       se lanza una excepción NoDataFoundException.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama exactamente una vez y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getByIdUserReg` del servicio arroja una excepción NoDataFoundException cuando no se encuentra ningún dato para el ID de usuario proporcionado.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama una vez con el ID de usuario y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdUserReg con datos no válidos")
    void getByIdUserReg_DataNotValid(){

        Long idNotValid = 3L;

        given(this.mapperUserTypeReg.getByIdUserReg(idNotValid)).willReturn(null);

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserReg(idNotValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdUserReg(idNotValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    /**
     * Prueba unitaria para el método `getByIdUserReg` del servicio UserTypeReg cuando no se encuentra ningún dato.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdUserReg` con un ID de usuario válido pero no se encuentra ningún dato,
     *       se lanza una excepción NoDataFoundException.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama exactamente una vez y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getByIdUserReg` del servicio arroja una excepción NoDataFoundException cuando no se encuentra ningún dato para el ID de usuario proporcionado.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama una vez con el ID de usuario y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdUserReg con datos no encontrados")
    void getByIdUserReg_DataNotFound(){

        Long idNotValid = 3L;

        given(this.mapperUserTypeReg.getByIdUserReg(idNotValid)).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserReg(idNotValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdUserReg(idNotValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }


    //------------------------------------------------------------------------------------------------------------------------

   /**
     * Prueba unitaria para el método `getByIdUserRegActivated` del servicio UserTypeReg cuando se obtiene una lista de usuarios registrados activados.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdUserRegActivated` con un ID de usuario válido,
     *       y se obtiene una lista de usuarios registrados activados, la lista no está vacía.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de usuario y que el método `dtoMapperUserTypeReg` se llama una vez para cada elemento en la lista.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Verifica que el método `getByIdUserRegActivated` del servicio devuelve una lista de usuarios registrados activados.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama una vez con el ID de usuario y que el método `dtoMapperUserTypeReg` se llama una vez para cada elemento en la lista.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdUserRegActivated")
    void getByIdUserRegActivated(){

        Long idValid = 1L;

        given(this.mapperUserTypeReg.getByIdUserRegActivated(idValid)).willReturn(List.of(new TuserTypeReg()));

        given(this.dtoMapperUserTypeReg.tuserTypeRegToTypeUserOfUserRegGet(any(TuserTypeReg.class)))
                .willReturn(new TypeUserOfUserRegGetDto(3L, "ADMIN", "Activated"));
        
        List<TypeUserOfUserRegGetDto> typeUserOfUserRegGetDtos = this.service.getByIdUserRegActivated(idValid);

        assertNotNull(typeUserOfUserRegGetDtos);
        assertFalse(typeUserOfUserRegGetDtos.isEmpty());
        
        then(this.mapperUserTypeReg).should(times(1)).getByIdUserRegActivated(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToTypeUserOfUserRegGet(any(TuserTypeReg.class));
    }

    /**
    * Prueba unitaria para el método `getByIdUserRegActivated` del servicio UserTypeReg cuando se proporciona un ID de usuario no válido.
    *
    * <p>Descripción de la Prueba:</p>
    * <ul>
    *   <li>Verifica que cuando se llama al método `getByIdUserRegActivated` con un ID de usuario no válido (cero o negativo),
    *       se lanza una excepción NoDataFoundException.</li>
    *   <li>Confirma que el método `mapperUserTypeReg` y el método `dtoMapperUserTypeReg` no tienen interacciones.</li>
    * </ul>
    *
    * <p>Simula el comportamiento esperado:</p>
    * <ul>
    *   <li>La prueba simula que se llama al método `getByIdUserRegActivated` del servicio con un ID de usuario no válido y que se lanza una excepción NoDataFoundException.</li>
    *   <li>Se verifica que el método `mapperUserTypeReg` y el método `dtoMapperUserTypeReg` no tienen interacciones.</li>
    * </ul>
    */
    @Test
    @DisplayName("Prueba de getByIdUserRegActivated con ID no válido")
    void getByIdUserRegActivated_IdNotValid(){

        Long idNotValid = 0L;

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserRegActivated(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdUserRegActivated` del servicio UserTypeReg cuando el método devuelve datos no válidos (nulos).
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdUserRegActivated` con un ID de usuario válido, pero el método devuelve datos no válidos (nulos),
     *       se lanza una excepción NoDataFoundException.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de usuario proporcionado,
     *       y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdUserRegActivated` del servicio con un ID de usuario válido,
     *       pero el método devuelve datos no válidos (nulos) y que se lanza una excepción NoDataFoundException.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de usuario proporcionado,
     *       y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdUserRegActivated con datos no válidos")
    void getByIdUserRegActivated_DataNotValid(){

        Long idNotValid = 3L;

        given(this.mapperUserTypeReg.getByIdUserRegActivated(idNotValid)).willReturn(null);

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserRegActivated(idNotValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdUserRegActivated(idNotValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    /**
     * Prueba unitaria para el método `getByIdUserRegActivated` del servicio UserTypeReg cuando el método devuelve una lista vacía.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdUserRegActivated` con un ID de usuario válido, pero el método devuelve una lista vacía,
     *       se lanza una excepción NoDataFoundException.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de usuario proporcionado,
     *       y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdUserRegActivated` del servicio con un ID de usuario válido,
     *       pero el método devuelve una lista vacía y que se lanza una excepción NoDataFoundException.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de usuario proporcionado,
     *       y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdUserRegActivated con datos no encontrados")
    void getByIdUserRegActivated_DataNotFound(){

        Long idNotValid = 3L;

        given(this.mapperUserTypeReg.getByIdUserRegActivated(idNotValid)).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserRegActivated(idNotValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdUserRegActivated(idNotValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    //--------------------------------------------------------------------------------------------------------

    /**
     * Prueba unitaria para el método `getByIdTypeUser` del servicio UserTypeReg.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdTypeUser` con un ID de tipo de usuario válido,
     *       el método devuelve una lista de usuarios relacionados con ese tipo de usuario.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de tipo de usuario proporcionado,
     *       y que el método `dtoMapperUserTypeReg` se llama para convertir los resultados a DTOs.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdTypeUser` del servicio con un ID de tipo de usuario válido,
     *       y que el método devuelve una lista de usuarios relacionados con ese tipo de usuario.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de tipo de usuario proporcionado,
     *       y que el método `dtoMapperUserTypeReg` se llama para convertir los resultados a DTOs.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdTypeUser")
    void getByIdTypeUser(){
        Integer idValid = 1;
        
        given(this.mapperUserTypeReg.getByIdTypeUser(idValid)).willReturn(List.of(new TuserTypeReg()));
        given(this.dtoMapperUserTypeReg.tuserTypeRegToUserRegOfTypeUserGet(any(TuserTypeReg.class)))
                .willReturn(new UserRegOfTypeUserGetDto(1L, "Arcelio Montezuma", 12000704001435L, "Activated"));
        
        List<UserRegOfTypeUserGetDto> userGetDtos = this.service.getByIdTypeUser(idValid);

        assertNotNull(userGetDtos);
        assertFalse(userGetDtos.isEmpty());

        then(this.mapperUserTypeReg).should(times(1)).getByIdTypeUser(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToUserRegOfTypeUserGet(any(TuserTypeReg.class));

    }

    /**
     * Prueba unitaria para el método `getByIdTypeUser` del servicio UserTypeReg.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdTypeUser` con un ID de tipo de usuario no válido (cero o negativo),
     *       el método arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` y el método `dtoMapperUserTypeReg` no tienen interacciones cuando se proporciona
     *       un ID de tipo de usuario no válido.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdTypeUser` del servicio con un ID de tipo de usuario no válido (cero o negativo),
     *       y que el método arroja una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg` y el método `dtoMapperUserTypeReg` no tienen interacciones cuando se proporciona
     *       un ID de tipo de usuario no válido.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdTypeUser con ID no válido")
    void getByIdTypeUser_idNotValid(){

        Integer idNotValid = 0;

        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdTypeUser(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdTypeUser` del servicio UserTypeReg.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdTypeUser` con un ID de tipo de usuario válido,
     *       pero el método devuelve un resultado nulo, el método arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama una vez con el ID de tipo de usuario válido, 
     *       y el método `dtoMapperUserTypeReg` no tiene interacciones cuando el método devuelve un resultado nulo.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdTypeUser` del servicio con un ID de tipo de usuario válido,
     *       pero el método devuelve un resultado nulo, y que el método arroja una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg` se llama una vez con el ID de tipo de usuario válido y 
     *       que el método `dtoMapperUserTypeReg` no tiene interacciones cuando el método devuelve un resultado nulo.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdTypeUser con datos no válidos")
    void getByIdTypeUser_DataNotValid(){
        Integer idValid = 1;

        given(this.mapperUserTypeReg.getByIdTypeUser(idValid)).willReturn(null);
        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdTypeUser(idValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdTypeUser(idValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    /**
     * Prueba unitaria para el método `getByIdTypeUser` del servicio UserTypeReg.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdTypeUser` con un ID de tipo de usuario válido,
     *       pero el método devuelve una lista vacía, el método arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama una vez con el ID de tipo de usuario válido, 
     *       y el método `dtoMapperUserTypeReg` no tiene interacciones cuando el método devuelve una lista vacía.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdTypeUser` del servicio con un ID de tipo de usuario válido,
     *       pero el método devuelve una lista vacía, y que el método arroja una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg` se llama una vez con el ID de tipo de usuario válido y 
     *       que el método `dtoMapperUserTypeReg` no tiene interacciones cuando el método devuelve una lista vacía.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdTypeUser con datos vacíos")
    void getByIdTypeUser_DataIsEmpty(){
        Integer idValid = 1;

        given(this.mapperUserTypeReg.getByIdTypeUser(idValid)).willReturn(Collections.emptyList());
        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdTypeUser(idValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdTypeUser(idValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    //------------------------------------------------------------------------------------------------------

    /**
     * Prueba unitaria para el método `getByIdStatus` del servicio UserTypeReg.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdStatus` con un ID de estado válido,
     *       el método devuelve una lista de UserTypeReg y no está vacía.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama una vez con el ID de estado válido, 
     *       y el método `dtoMapperUserTypeReg` se llama una vez para convertir cada elemento de UserTypeReg en UserTypeRegDto.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdStatus` del servicio con un ID de estado válido,
     *       y que el método devuelve una lista de UserTypeReg que no está vacía.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg` se llama una vez con el ID de estado válido y 
     *       que el método `dtoMapperUserTypeReg` se llama una vez para convertir cada elemento de UserTypeReg en UserTypeRegDto.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdStatus con datos válidos")
    void getByIdStatus(){
        Integer idValid = 1;
        
        given(this.mapperUserTypeReg.getByIdStatus(idValid)).willReturn(List.of(new TuserTypeReg()));
        given(this.dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class)))
                .willReturn(new UserTypeRegGetDto(1L, "Arcelio Montezuma", 12000704001435L, "ADMIN", "Activated"));
        
        List<UserTypeRegGetDto> userGetDtos = this.service.getByIdStatus(idValid);

        assertNotNull(userGetDtos);
        assertFalse(userGetDtos.isEmpty());

        then(this.mapperUserTypeReg).should(times(1)).getByIdStatus(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class));

    }

    /**
     * Prueba unitaria para el método `getByIdStatus` del servicio UserTypeReg con un ID no válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdStatus` con un ID de estado no válido,
     *       el método arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` y el método `dtoMapperUserTypeReg` no tienen interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdStatus` del servicio con un ID de estado no válido,
     *       y que el método arroja una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que ni el método `mapperUserTypeReg` ni el método `dtoMapperUserTypeReg` tienen interacciones,
     *       lo que significa que no se deben llamar cuando se proporciona un ID no válido.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdStatus con ID no válido")
    void getByIdStatus_idNotValid(){

        Integer idNotValid = 0;

        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdStatus(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método `getByIdStatus` del servicio UserTypeReg cuando no se encuentra ningún dato válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdStatus` con un ID de estado válido,
     *       y el método no encuentra ningún dato válido, arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de estado válido,
     *       y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdStatus` del servicio con un ID de estado válido,
     *       pero no se encuentra ningún dato válido, lo que provoca que el método arroje una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de estado válido
     *       y que el método `dtoMapperUserTypeReg` no tiene interacciones, lo que significa que no debe haber datos válidos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdStatus con datos no válidos")
    void getByIdStatus_DataNotValid(){
        Integer idValid = 1;

        given(this.mapperUserTypeReg.getByIdStatus(idValid)).willReturn(null);
        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdStatus(idValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdStatus(idValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    /**
     * Prueba unitaria para el método `getByIdStatus` del servicio UserTypeReg cuando los datos están vacíos.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `getByIdStatus` con un ID de estado válido,
     *       y el método devuelve una lista vacía, arroja una excepción `NoDataFoundException`.</li>
     *   <li>Confirma que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de estado válido,
     *       y que el método `dtoMapperUserTypeReg` no tiene interacciones.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `getByIdStatus` del servicio con un ID de estado válido,
     *       pero la lista de datos está vacía, lo que provoca que el método arroje una excepción `NoDataFoundException`.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg` se llama exactamente una vez con el ID de estado válido
     *       y que el método `dtoMapperUserTypeReg` no tiene interacciones, lo que significa que no debe haber datos válidos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getByIdStatus con datos vacíos")
    void getByIdStatus_DataIsEmpty(){
        Integer idValid = 1;

        given(this.mapperUserTypeReg.getByIdStatus(idValid)).willReturn(Collections.emptyList());
        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdStatus(idValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdStatus(idValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    //UPDATE--------------------------------------------------------------------------------------------------------

    /**
     * Prueba unitaria para el método `update` del servicio UserTypeReg cuando se actualiza un registro válido.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `update` con un objeto `UserTypeRegUpdateDto` válido,
     *       y la operación de actualización tiene éxito (retorna 1), la prueba unitaria sea exitosa.</li>
     *   <li>Confirma que el método `dtoMapperUserTypeReg.userTypeRegUpdateToTuserTypeReg` se llama exactamente una vez
     *       para convertir el DTO en una entidad de base de datos.</li>
     *   <li>Confirma que el método `mapperUserTypeReg.update` se llama exactamente una vez para realizar la actualización
     *       en la base de datos.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `update` del servicio con un objeto `UserTypeRegUpdateDto` válido,
     *       y se simula que la conversión de DTO a entidad y la operación de actualización en la base de datos son exitosas.</li>
     *   <li>Se verifica que se devuelve un valor no nulo (cantidad de filas afectadas) y que es igual a 1, lo que indica
     *       que la actualización fue exitosa.</li>
     *   <li>Se verifica que el método `dtoMapperUserTypeReg.userTypeRegUpdateToTuserTypeReg` se llama exactamente una vez
     *       para realizar la conversión de DTO a entidad.</li>
     *   <li>Se verifica que el método `mapperUserTypeReg.update` se llama exactamente una vez para realizar la actualización
     *       en la base de datos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de UserTypeReg")
    void update(){

        UserTypeRegUpdateDto updateDtoValid = new UserTypeRegUpdateDto(1L, 1L, 1, 1);

        given(this.dtoMapperUserTypeReg.userTypeRegUpdateToTuserTypeReg(updateDtoValid)).willReturn(new TuserTypeReg());

        given(this.mapperUserTypeReg.update(any(TuserTypeReg.class))).willReturn(1);

        Integer rowAffected = this.service.update(updateDtoValid);

        assertNotNull(rowAffected);
        assertEquals(1, rowAffected);

        then(this.dtoMapperUserTypeReg).should(times(1)).userTypeRegUpdateToTuserTypeReg(updateDtoValid);
        then(this.mapperUserTypeReg).should(times(1)).update(any(TuserTypeReg.class));
    }


    /**
     * Prueba unitaria para el método `update` del servicio UserTypeReg cuando se intenta actualizar con datos no válidos.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `update` con un objeto `UserTypeRegUpdateDto` no válido (null),
     *       el servicio arroja una excepción `UserTypeRegNotUpdateException`.</li>
     *   <li>Confirma que el método `dtoMapperUserTypeReg` y el método `mapperUserTypeReg` no tienen interacciones,
     *       ya que no deberían ser llamados con datos no válidos.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `update` del servicio con un objeto `UserTypeRegUpdateDto` no válido (null),
     *       lo que debería arrojar una excepción `UserTypeRegNotUpdateException`.</li>
     *   <li>Se verifica que tanto el método `dtoMapperUserTypeReg` como el método `mapperUserTypeReg` no tienen interacciones,
     *       ya que no deben ser llamados con datos no válidos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de UserTypeReg con datos no válidos")
    void update_dataNotValid(){

        UserTypeRegUpdateDto  updateNotValid = null;

        assertThrows(UserTypeRegNotUpdateException.class, ()-> this.service.update(updateNotValid));

        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
        then(this.mapperUserTypeReg).shouldHaveNoInteractions();

    }

    /**
     * Prueba unitaria para el método `update` del servicio UserTypeReg cuando el servicio no puede actualizar los datos.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `update` con datos válidos, pero el servicio no puede actualizar los datos
     *       (retorna null), arroja una excepción `UserTypeRegNotUpdateException`.</li>
     *   <li>Confirma que el método `dtoMapperUserTypeReg` se llama exactamente una vez con los datos de actualización,
     *       y el método `mapperUserTypeReg` se llama exactamente una vez para actualizar los datos en el servicio.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `update` del servicio con datos válidos, pero el servicio no puede actualizar los datos
     *       (retorna null), lo que debería arrojar una excepción `UserTypeRegNotUpdateException`.</li>
     *   <li>Se verifica que el método `dtoMapperUserTypeReg` se llama exactamente una vez con los datos de actualización proporcionados,
     *       y que el método `mapperUserTypeReg` se llama exactamente una vez para intentar actualizar los datos en el servicio.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de UserTypeReg con datos de actualización no válidos")
    void update_returnDataNotValid(){

        UserTypeRegUpdateDto updateDto = new UserTypeRegUpdateDto(1L, 1L, 1, 1);

        given(this.dtoMapperUserTypeReg.userTypeRegUpdateToTuserTypeReg(updateDto)).willReturn(new TuserTypeReg());

        given(this.mapperUserTypeReg.update(any(TuserTypeReg.class))).willReturn(null);

        assertThrows(UserTypeRegNotUpdateException.class, ()-> this.service.update(updateDto));


        then(this.dtoMapperUserTypeReg).should(times(1)).userTypeRegUpdateToTuserTypeReg(updateDto);
        then(this.mapperUserTypeReg).should(times(1)).update(any(TuserTypeReg.class));
    }

    /**
     * Prueba unitaria para el método `update` del servicio UserTypeReg cuando no se afecta ninguna fila.
     *
     * <p>Descripción de la Prueba:</p>
     * <ul>
     *   <li>Verifica que cuando se llama al método `update` con datos válidos, pero la actualización no afecta ninguna fila
     *       (retorna 0), arroja una excepción `UserTypeRegNotUpdateException`.</li>
     *   <li>Confirma que el método `dtoMapperUserTypeReg` se llama exactamente una vez con los datos de actualización,
     *       y el método `mapperUserTypeReg` se llama exactamente una vez para intentar actualizar los datos en el servicio.</li>
     * </ul>
     *
     * <p>Simula el comportamiento esperado:</p>
     * <ul>
     *   <li>La prueba simula que se llama al método `update` del servicio con datos válidos, pero la actualización no afecta
     *       ninguna fila en la base de datos (retorna 0), lo que debería arrojar una excepción `UserTypeRegNotUpdateException`.</li>
     *   <li>Se verifica que el método `dtoMapperUserTypeReg` se llama exactamente una vez con los datos de actualización proporcionados,
     *       y que el método `mapperUserTypeReg` se llama exactamente una vez para intentar actualizar los datos en el servicio.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de UserTypeReg con cero filas afectadas")
    void update_returnZeroRowAffected(){

        UserTypeRegUpdateDto updateDto = new UserTypeRegUpdateDto(1L, 1L, 1, 1);

        given(this.dtoMapperUserTypeReg.userTypeRegUpdateToTuserTypeReg(updateDto)).willReturn(new TuserTypeReg());

        given(this.mapperUserTypeReg.update(any(TuserTypeReg.class))).willReturn(0);

        assertThrows(UserTypeRegNotUpdateException.class, ()-> this.service.update(updateDto));

        then(this.dtoMapperUserTypeReg).should(times(1)).userTypeRegUpdateToTuserTypeReg(updateDto);
        then(this.mapperUserTypeReg).should(times(1)).update(any(TuserTypeReg.class));
    }
}
