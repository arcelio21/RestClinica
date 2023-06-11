package com.example.service.modules;


import com.example.dto.modules.ModulesDto;
import com.example.dtomapper.modules.DtoModulesMapper;
import com.example.entity.modules.Tmodule;
import com.example.exception.NoDataFoundException;
import com.example.mapper.modules.MapperModules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ServiceModuleImpleTest {

    @Mock
    private MapperModules mapperModules;
    @Mock
    private DtoModulesMapper dtoModulesMapper;
    @InjectMocks
    private ServiceModuleImple serviceModuleImple;

    private ModulesDto modulesDtoValid;
    private ModulesDto modulesDtoNotValid;

    private Tmodule tmoduleValid;
    private Tmodule tmoduleNotValid;

    @BeforeEach
    void setUp() {

        this.modulesDtoValid = ModulesDto.builder()
                .id(1L)
                .name("/api/address")
                .build();
        this.modulesDtoNotValid = ModulesDto.builder().build();

        this.tmoduleValid = new Tmodule(1L,"/api/address");
        this.tmoduleNotValid = new Tmodule(null, "");

    }

    //GET
    @Test
     void testGetAll_ExistingModules_ReturnsListOfModulesDto() {
        // Arrange

        given(this.mapperModules.getAll()).willReturn(List.of(this.tmoduleValid));
        given(this.dtoModulesMapper.TmoduleToModulesDto(any(Tmodule.class))).willAnswer(invocation -> {
            Tmodule tmodule = invocation.getArgument(0);

            return ModulesDto.builder().name(tmodule.getNameModule()).id(tmodule.getId()).build();
        });

        // Act
        List<ModulesDto> result = this.serviceModuleImple.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        then(this.mapperModules).should().getAll();
        then(this.dtoModulesMapper).should(times(1)).TmoduleToModulesDto(any(Tmodule.class));
    }

    @Test
    public void testGetAll_NoModules_ThrowsNoDataFoundException() {
        // Arrange

        given(this.mapperModules.getAll()).willReturn(List.of());

        // Act & Assert
        Assertions.assertThrows(NoDataFoundException.class, () -> this.serviceModuleImple.getAll());
        then(this.mapperModules).should().getAll();
        then(this.dtoModulesMapper).shouldHaveNoInteractions();
    }



}