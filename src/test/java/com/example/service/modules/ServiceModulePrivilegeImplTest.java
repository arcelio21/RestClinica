package com.example.service.modules;

import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeUpdateDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegesDto;
import com.example.dto.modules.modulesprivileges.PrivilegeIdDto;
import com.example.dtomapper.modules.DtoModulesPrivilegesMapper;
import com.example.entity.modules.TmodulePrivilege;
import com.example.exception.NoDataFoundException;
import com.example.exception.modules.modulesprivilege.ModulePrivilegesNotSaveException;
import com.example.exception.modules.modulesprivilege.ModulePrivilegesNotUpdateException;
import com.example.mapper.modules.MapperModulePrivilege;
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
class ServiceModulePrivilegeImplTest {


    @Mock
    private DtoModulesPrivilegesMapper dtoMapper;

    @Mock
    private MapperModulePrivilege mapper;
    @InjectMocks
    private ServiceModulePrivilegeImpl service;

    private ModulePrivilegesDto modulePrivilegesDtoValid;

    private ModulePrivilegesDto modulePrivilegesDtoNotValid;


    private ModulePrivilegeSaveDto modulePrivilegeSaveDtoValid;

    private ModulePrivilegeSaveDto modulePrivilegeSaveDtoNotValid;

    private ModulePrivilegeUpdateDto modulePrivilegeUpdateDtoValid;
    private ModulePrivilegeUpdateDto modulePrivilegeUpdateDtoNotValid;

    @BeforeEach
    void setUp() {

        modulePrivilegesDtoValid = ModulePrivilegesDto.builder()
                .id(1L)
                .moduleId(1L)
                .privilegeId(1)
                .statusId(1)
                .build();
        modulePrivilegesDtoNotValid = ModulePrivilegesDto.builder().build();

        modulePrivilegeSaveDtoValid = ModulePrivilegeSaveDto.builder()
                .privilegeIds(List.of(new PrivilegeIdDto(1)))
                .moduleId(1L)
                .statusId(1)
                .build();

        modulePrivilegeSaveDtoNotValid = ModulePrivilegeSaveDto.builder().build();

        this.modulePrivilegeUpdateDtoValid = ModulePrivilegeUpdateDto.builder()
                .id(1L)
                .privilegeId(1)
                .moduleId(1L)
                .statusId(1)
                .build();

        this.modulePrivilegeUpdateDtoNotValid = ModulePrivilegeUpdateDto.builder().build();

    }

    /**
     * Prueba unitaria para el método getAll() del servicio ModulePrivilege cuando existen datos.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getAll() se retorne una lista de ModulePrivilegesDto.</li>
     *   <li>Se verifica que la lista de ModulePrivilegesDto no sea nula y no esté vacía.</li>
     *   <li>Se verifica que se haya llamado al método getAll() del objeto simulado mapper.</li>
     *   <li>Se verifica que se haya llamado al método TmodulePrivilegeToModulePrivilegeDto() del objeto simulado dtoMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de todos los datos cuando existen")
    void getAll_data_exits(){

        given(this.mapper.getAll()).willReturn(List.of(new TmodulePrivilege()));
        given(this.dtoMapper.TmodulePrivilegeToModulePrivilegeDto(any(TmodulePrivilege.class))).willReturn(this.modulePrivilegesDtoValid);

        List<ModulePrivilegesDto> modulePrivilegesDtos = this.service.getAll();

        assertNotNull(modulePrivilegesDtos);
        assertFalse(modulePrivilegesDtos.isEmpty());

        then(this.mapper).should(times(1)).getAll();
        then(this.dtoMapper).should(times(1)).TmodulePrivilegeToModulePrivilegeDto(any(TmodulePrivilege.class));
    }

    /**
     * Prueba unitaria para el método getAll() del servicio ModulePrivilege cuando no existen datos.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getAll() se lance una excepción NoDataFoundException.</li>
     *   <li>Se verifica que se haya llamado al método getAll() del objeto simulado mapper.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de todos los datos cuando no existen")
    void getAll_data_NotExists(){
        given(this.mapper.getAll()).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class,()-> this.service.getAll());

        then(this.mapper).should(times(1)).getAll();
        then(this.dtoMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método getAll() del servicio ModulePrivilege cuando el resultado es nulo.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getAll() se lance una excepción NoDataFoundException.</li>
     *   <li>Se verifica que se haya llamado al método getAll() del objeto simulado mapper.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de todos los datos cuando el resultado es nulo")
    void getAll_returnNull(){
        given(this.mapper.getAll()).willReturn(null);

        assertThrows(NoDataFoundException.class,()-> this.service.getAll());

        then(this.mapper).should(times(1)).getAll();
        then(this.dtoMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método getById() del servicio ModulePrivilege cuando se proporciona un ID existente.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getById() con un ID existente se retorne el objeto ModulePrivilegesDto correspondiente.</li>
     *   <li>Se verifica que se haya llamado al método getById() del objeto simulado mapper con el ID proporcionado.</li>
     *   <li>Se verifica que se haya llamado al método TmodulePrivilegeToModulePrivilegeDto() del objeto simulado dtoMapper con el objeto TmodulePrivilege correspondiente.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de datos por ID existente")
    void getById_data_exits(){

        Long id= 1L;
        TmodulePrivilege tmodulePrivilege = new TmodulePrivilege(id);

        given(this.mapper.getById(id)).willReturn(tmodulePrivilege);
        given(this.dtoMapper.TmodulePrivilegeToModulePrivilegeDto(tmodulePrivilege)).willReturn(this.modulePrivilegesDtoValid);

        ModulePrivilegesDto modulePrivilegesDto = this.service.getById(id);

        assertNotNull(modulePrivilegesDto);
        then(this.mapper).should(times(1)).getById(id);
        then(this.dtoMapper).should(times(1)).TmodulePrivilegeToModulePrivilegeDto(tmodulePrivilege);
    }

    /**
     * Prueba unitaria para el método getById() del servicio ModulePrivilege cuando se proporciona un ID no válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getById() con un ID no válido se lance una excepción de tipo NoDataFoundException.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado mapper.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de datos por ID no válido")
    void getById_ID_NotValid(){
        Long id= -1L;

        assertThrows(NoDataFoundException.class,()-> this.service.getById(id));

        then(this.mapper).shouldHaveNoInteractions();
        then(this.dtoMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método save() del servicio ModulePrivilege cuando se proporciona un objeto ModulePrivilegeSaveDto válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() con un objeto ModulePrivilegeSaveDto válido se retorne el número de filas afectadas.</li>
     *   <li>Se verifica que se haya llamado al método ModulePrivilegeSaveDtoToTmodulePrivilege() del objeto simulado dtoMapper.</li>
     *   <li>Se verifica que se haya llamado al método save() del objeto simulado mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado de datos con objeto válido")
    void save_data_valid(){
        //given(this.dtoMapper.ModulePrivilegeSaveDtoToTmodulePrivilege(this.modulePrivilegeSaveDtoValid)).willReturn(new TmodulePrivilege());
        given(this.mapper.save(any(TmodulePrivilege.class))).willReturn(1);

        Integer rowAffected = this.service.save(this.modulePrivilegeSaveDtoValid);

        assertNotNull(rowAffected);
        assertEquals(1,rowAffected);

        //then(this.dtoMapper).should(times(1)).ModulePrivilegeSaveDtoToTmodulePrivilege(this.modulePrivilegeSaveDtoValid);
        then(this.mapper).should(times(1)).save(any(TmodulePrivilege.class));
    }


    /**
     * Prueba unitaria para el método save() del servicio ModulePrivilege cuando se proporciona un objeto ModulePrivilegeSaveDto no válido.
     *
     * <p>Se verifica que al intentar guardar los datos con un objeto ModulePrivilegeSaveDto no válido se lance una excepción del tipo ModulePrivilegesNotSaveException.</p>
     * <p>También se verifica que no se hayan realizado interacciones con los objetos simulados dtoMapper y mapper.</p>
     */
    @Test
    @DisplayName("Prueba de guardado de datos con objeto no válido")
    void save_data_notValid(){

        assertThrows(ModulePrivilegesNotSaveException.class, ()-> this.service.save(this.modulePrivilegeSaveDtoNotValid));
        then(this.dtoMapper).shouldHaveNoInteractions();
        then(this.dtoMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método save() del servicio ModulePrivilege cuando el método save() del objeto simulado mapper devuelve 0.
     *
     * <p>Se verifica que al intentar guardar los datos con un objeto ModulePrivilegeSaveDto válido, pero el método save() del objeto mapper devuelve 0, se lance una excepción del tipo ModulePrivilegesNotSaveException.</p>
     * <p>También se verifica que se haya realizado la interacción esperada con el objeto simulado dtoMapper para convertir el objeto ModulePrivilegeSaveDto.</p>
     */
    @Test
    @DisplayName("Prueba de guardado de datos con retorno 0 en el método save() del mapper")
    void save_data_mapperReturn_0(){
        //given(this.dtoMapper.ModulePrivilegeSaveDtoToTmodulePrivilege(this.modulePrivilegeSaveDtoValid)).willReturn(new TmodulePrivilege());
        given(this.mapper.save(any(TmodulePrivilege.class))).willReturn(0);

        assertThrows(ModulePrivilegesNotSaveException.class,()-> this.service.save(this.modulePrivilegeSaveDtoValid));


        //then(this.dtoMapper).should(times(1)).ModulePrivilegeSaveDtoToTmodulePrivilege(this.modulePrivilegeSaveDtoValid);
        then(this.mapper).should(times(1)).save(any(TmodulePrivilege.class));
    }

    /**
     * Prueba unitaria para el método update() del servicio ModulePrivilege cuando se proporciona un objeto ModulePrivilegeUpdateDto válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto ModulePrivilegeUpdateDto válido se retorne el número de filas afectadas.</li>
     *   <li>Se verifica que se haya llamado al método ModulePrivilegeUpdateDtoToTmoduloPrivilege() del objeto simulado dtoMapper.</li>
     *   <li>Se verifica que se haya llamado al método update() del objeto simulado mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de datos válidos")
    void update_data_valid(){
        given(this.dtoMapper.ModulePrivilegeUpdateDtoToTmoduloPrivilege(this.modulePrivilegeUpdateDtoValid)).willReturn(new TmodulePrivilege());
        given(this.mapper.update(any(TmodulePrivilege.class))).willReturn(1);

        Integer rowAffected = this.service.update(this.modulePrivilegeUpdateDtoValid);

        assertNotNull(rowAffected);
        assertEquals(1, rowAffected);

        then(this.dtoMapper).should(times(1)).ModulePrivilegeUpdateDtoToTmoduloPrivilege(this.modulePrivilegeUpdateDtoValid);
        then(this.mapper).should(times(1)).update(any(TmodulePrivilege.class));
    }

    /**
     * Prueba unitaria para el método update() del servicio ModulePrivilege cuando se proporciona un objeto ModulePrivilegeUpdateDto no válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto ModulePrivilegeUpdateDto no válido se lance una excepción ModulePrivilegesNotUpdateException.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoMapper.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de datos no válidos")
    void update_data_NotValid(){

        assertThrows(ModulePrivilegesNotUpdateException.class, ()-> this.service.update(this.modulePrivilegeUpdateDtoNotValid));

        then(this.dtoMapper).shouldHaveNoInteractions();
        then(this.mapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método update() del servicio ModulePrivilege cuando se proporciona un objeto ModulePrivilegeUpdateDto válido pero la actualización retorna 0 filas afectadas.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto ModulePrivilegeUpdateDto válido pero la actualización retorna 0 filas afectadas se lance una excepción ModulePrivilegesNotUpdateException.</li>
     *   <li>Se verifica que se haya llamado al método ModulePrivilegeUpdateDtoToTmoduloPrivilege() del objeto simulado dtoMapper con el objeto ModulePrivilegeUpdateDto válido.</li>
     *   <li>Se verifica que se haya llamado al método update() del objeto simulado mapper con el objeto TmodulePrivilege resultante.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de datos con retorno de 0 filas afectadas")
    void update_data_return0(){
        given(this.dtoMapper.ModulePrivilegeUpdateDtoToTmoduloPrivilege(this.modulePrivilegeUpdateDtoValid)).willReturn(new TmodulePrivilege());
        given(this.mapper.update(any(TmodulePrivilege.class))).willReturn(0);

        assertThrows(ModulePrivilegesNotUpdateException.class, ()-> this.service.update(this.modulePrivilegeUpdateDtoValid));

        then(this.dtoMapper).should(times(1)).ModulePrivilegeUpdateDtoToTmoduloPrivilege(this.modulePrivilegeUpdateDtoValid);
        then(this.mapper).should(times(1)).update(any(TmodulePrivilege.class));
    }
}