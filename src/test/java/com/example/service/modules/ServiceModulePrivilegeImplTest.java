package com.example.service.modules;

import com.example.dto.modules.modulesprivileges.ModulePrivilegesDto;
import com.example.dtomapper.modules.DtoModulesPrivilegesMapper;
import com.example.entity.modules.TmodulePrivilege;
import com.example.exception.NoDataFoundException;
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
    @BeforeEach
    void setUp() {

        modulePrivilegesDtoNotValid = ModulePrivilegesDto.builder()
                .id(1L)
                .moduleId(1L)
                .privilegeId(1)
                .statusId(1)
                .build();
        modulePrivilegesDtoNotValid = ModulePrivilegesDto.builder().build();

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
}