package com.example.service.user;

import com.example.dto.user.typeuser_module.TypeUserModuleGetDto;
import com.example.dto.user.typeuser_module.TypeUserModuleUpdateDto;
import com.example.dtomapper.user.DtoTypeUserModuleMapper;
import com.example.entity.user.TtypeUserModule;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.typeuser_module.TypeUserModuleNotUpdateException;
import com.example.mapper.user.MapperTypeUserModule;
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
class ServiceTypeUserModuleImplTest {

    @Mock
    private MapperTypeUserModule mapper;

    @Mock
    private DtoTypeUserModuleMapper dtoMapper;

    @InjectMocks
    private ServiceTypeUserModuleImpl service;

    private TypeUserModuleGetDto dataValid;
    private TypeUserModuleUpdateDto typeUserModuleUpdateValid;

    @BeforeEach
    void setUp() {
        dataValid = TypeUserModuleGetDto.builder()
                .id(1L)
                .nameModule("User")
                .nameTypeUser("ADMIN")
                .namePrivilege("WRITE")
                .nameStatus("ACTIVATED")
                .build();

        this.typeUserModuleUpdateValid = TypeUserModuleUpdateDto.builder()
                .id(1L)
                .idTypeUser(1)
                .idModulePrivilege(1L)
                .build();
    }


    /**
     * Prueba unitaria para el método getAll() del servicio TypeUserModule cuando existen datos válidos.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getAll() se retorne una lista de TypeUserModuleGetDto con datos válidos.</li>
     *   <li>Se verifica que se haya llamado al método tTypeUserModuleToTypeUserModuleGetDto() del objeto simulado dtoMapper con cualquier objeto TtypeUserModule.</li>
     *   <li>Se verifica que se haya llamado al método getAll() del objeto simulado mapper una vez.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getAll() con datos válidos")
    void getAllValidData(){

        given(this.mapper.getAll()).willReturn(List.of(new TtypeUserModule()));
        given(this.dtoMapper.tTypeUserModuleToTypeUserModuleGetDto(any(TtypeUserModule.class))).willReturn(dataValid);

        List<TypeUserModuleGetDto> typeUserModuleGetDtoList = this.service.getAll();

        assertNotNull(typeUserModuleGetDtoList);
        assertEquals(1,typeUserModuleGetDtoList.size());

        then(this.mapper).should(times(1)).getAll();
        then(this.dtoMapper).should(times(1)).tTypeUserModuleToTypeUserModuleGetDto(any(TtypeUserModule.class));

    }


    /**
     * Prueba unitaria para el método getAll() del servicio TypeUserModule cuando la lista está vacía.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getAll() con una lista vacía se lance la excepción NoDataFoundException.</li>
     *   <li>Se verifica que se haya llamado al método getAll() del objeto simulado mapper una vez.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getAll() con lista vacía")
    void getAll_ListEmty(){
        given(this.mapper.getAll()).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, ()-> this.service.getAll());

        then(this.mapper).should(times(1)).getAll();

    }

    /**
     * Prueba unitaria para el método getById() del servicio TypeUserModule cuando se proporciona un ID válido.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getById() con un ID válido se retorne el objeto TypeUserModuleGetDto correspondiente.</li>
     *   <li>Se verifica que se haya llamado al método getById() del objeto simulado mapper con el ID proporcionado.</li>
     *   <li>Se verifica que se haya llamado al método tTypeUserModuleToTypeUserModuleGetDto() del objeto simulado dtoMapper con el objeto TtypeUserModule correspondiente.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getById() con datos válidos")
    void getById_DataValid(){
        Long id = 1L;
        given(this.mapper.getById(id)).willReturn(new TtypeUserModule());
        given(this.dtoMapper.tTypeUserModuleToTypeUserModuleGetDto(any(TtypeUserModule.class))).willReturn(this.dataValid);

        TypeUserModuleGetDto data = this.service.getById(id);

        assertNotNull(data);

        then(this.mapper).should(times(1)).getById(id);
        then(this.dtoMapper).should(times(1)).tTypeUserModuleToTypeUserModuleGetDto(any(TtypeUserModule.class));
    }

    /**
     * Prueba unitaria para el método getById() del servicio TypeUserModule cuando se proporciona un ID no válido.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getById() con un ID no válido se lance una excepción del tipo NoDataFoundException.</li>
     *   <li>Se verifica que no se haya llamado al método getById() del objeto simulado mapper.</li>
     *   <li>Se verifica que no se haya llamado al método tTypeUserModuleToTypeUserModuleGetDto() del objeto simulado dtoMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getById() con ID no válido")
    void getById_IdNotValid(){
        Long idInvalid = 0L;

        assertThrows(NoDataFoundException.class,()-> this.service.getById(idInvalid));

        then(this.mapper).shouldHaveNoInteractions();
        then(this.dtoMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método getById() del servicio TypeUserModule cuando el resultado es nulo.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getById() y el resultado es nulo se lance una excepción del tipo NoDataFoundException.</li>
     *   <li>Se verifica que se haya llamado al método getById() del objeto simulado mapper.</li>
     *   <li>Se verifica que no se haya llamado al método tTypeUserModuleToTypeUserModuleGetDto() del objeto simulado dtoMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getById() con resultado nulo")
    void getById_ReturnNull(){
        Long id = 1L;
        given(this.mapper.getById(id)).willReturn(null);


        assertThrows(NoDataFoundException.class,()-> this.service.getById(id));

        then(this.mapper).should(times(1)).getById(id);
        then(this.dtoMapper).shouldHaveNoInteractions();
    }


    /**
     * Prueba unitaria para el método update() del servicio TypeUserModule cuando se proporciona un objeto TypeUserModuleUpdateDto válido.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto TypeUserModuleUpdateDto válido se retorne el número de filas afectadas (1).</li>
     *   <li>Se verifica que se haya llamado al método TypeUserModuleUpdateDtoToTtypeUserModule() del objeto simulado dtoMapper.</li>
     *   <li>Se verifica que se haya llamado al método update() del objeto simulado mapper con el objeto TtypeUserModule generado.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de datos válidos")
    void update_ValidData(){

        given(this.dtoMapper.TypeUserModuleUpdateDtoToTtypeUserModule(this.typeUserModuleUpdateValid)).willReturn(new TtypeUserModule());
        given(this.mapper.update(any(TtypeUserModule.class))).willReturn(1);

        Integer rowAffected = this.service.update(this.typeUserModuleUpdateValid);

        assertNotNull(rowAffected);
        assertEquals(1,rowAffected);

        then(this.dtoMapper).should(times(1)).TypeUserModuleUpdateDtoToTtypeUserModule(this.typeUserModuleUpdateValid);
        then(this.mapper).should(times(1)).update(any(TtypeUserModule.class));
    }


    /**
     * Prueba unitaria para el método update() del servicio TypeUserModule cuando se proporciona un objeto TypeUserModuleUpdateDto no válido.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto TypeUserModuleUpdateDto no válido se lance una excepción del tipo TypeUserModuleNotUpdateException.</li>
     *   <li>Se verifica que no se haya llamado a ningún método del objeto simulado dtoMapper.</li>
     *   <li>Se verifica que no se haya llamado a ningún método del objeto simulado mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de datos no válidos")
    void update_NotValidData(){

        TypeUserModuleUpdateDto updateNotValid = TypeUserModuleUpdateDto.builder().build();

        assertThrows(TypeUserModuleNotUpdateException.class, ()-> this.service.update(updateNotValid));

        then(this.dtoMapper).shouldHaveNoInteractions();
        then(this.mapper).shouldHaveNoInteractions();

    }

    /**
     * Prueba unitaria para el método update() del servicio TypeUserModule cuando el método de actualización devuelve 0 filas afectadas.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto TypeUserModuleUpdateDto válido y el método de actualización devuelve 0 filas afectadas, se lance una excepción del tipo TypeUserModuleNotUpdateException.</li>
     *   <li>Se verifica que se haya llamado al método TypeUserModuleUpdateDtoToTtypeUserModule() del objeto simulado dtoMapper.</li>
     *   <li>Se verifica que se haya llamado al método update() del objeto simulado mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización con 0 filas afectadas")
    void updtae_rowAffectedZero(){
        given(this.dtoMapper.TypeUserModuleUpdateDtoToTtypeUserModule(this.typeUserModuleUpdateValid)).willReturn(new TtypeUserModule());
        given(this.mapper.update(any(TtypeUserModule.class))).willReturn(0);

        assertThrows(TypeUserModuleNotUpdateException.class,()-> this.service.update(this.typeUserModuleUpdateValid));

        then(this.dtoMapper).should(times(1)).TypeUserModuleUpdateDtoToTtypeUserModule(this.typeUserModuleUpdateValid);
        then(this.mapper).should(times(1)).update(any(TtypeUserModule.class));
    }


}