package com.example.service.modules;


import com.example.dto.modules.ModulesDto;
import com.example.dtomapper.modules.DtoModulesMapper;
import com.example.entity.modules.Tmodule;
import com.example.exception.NoDataFoundException;
import com.example.mapper.modules.MapperModules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    /**
     * Prueba unitaria para el método getAll() del servicio Module cuando existen módulos.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se simula la obtención de una lista de módulos válidos a través del método getAll() del objeto simulado mapperModules.</li>
     *   <li>Se simula la conversión de cada módulo Tmodule a ModulesDto a través del método TmoduleToModulesDto() del objeto simulado dtoModulesMapper.</li>
     *   <li>Se verifica que la lista de módulos obtenida no sea nula.</li>
     *   <li>Se verifica que la lista de módulos obtenida tenga el tamaño esperado.</li>
     *   <li>Se verifica que se haya interactuado una vez con el objeto simulado mapperModules al llamar al método getAll().</li>
     *   <li>Se verifica que se haya interactuado una vez con el objeto simulado dtoModulesMapper al llamar al método TmoduleToModulesDto() por cada módulo.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos existentes")
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

    /**
     * Prueba unitaria para el método getAll() del servicio Module cuando no existen módulos.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se simula la obtención de una lista vacía de módulos a través del método getAll() del objeto simulado mapperModules.</li>
     *   <li>Se verifica que al llamar al método getAll() se lance una excepción del tipo NoDataFoundException.</li>
     *   <li>Se verifica que se haya interactuado una vez con el objeto simulado mapperModules al llamar al método getAll().</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoModulesMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos cuando no existen")
    public void testGetAll_NoModules_ThrowsNoDataFoundException() {
        // Arrange

        given(this.mapperModules.getAll()).willReturn(List.of());

        // Act & Assert
        Assertions.assertThrows(NoDataFoundException.class, () -> this.serviceModuleImple.getAll());
        then(this.mapperModules).should().getAll();
        then(this.dtoModulesMapper).shouldHaveNoInteractions();
    }



}