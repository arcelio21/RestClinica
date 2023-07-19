package com.example.service.user;

import com.example.dto.user.typeuser_module.TypeUserModuleGetDto;
import com.example.dtomapper.user.DtoTypeUserModuleMapper;
import com.example.entity.user.TtypeUserModule;
import com.example.exception.NoDataFoundException;
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

    @BeforeEach
    void setUp() {
        dataValid = TypeUserModuleGetDto.builder()
                .id(1L)
                .nameModule("User")
                .nameTypeUser("ADMIN")
                .namePrivilege("WRITE")
                .nameStatus("ACTIVATED")
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


}