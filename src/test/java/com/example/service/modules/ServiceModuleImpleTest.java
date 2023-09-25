package com.example.service.modules;


import com.example.dto.modules.ModulesDto;
import com.example.dtomapper.modules.DtoModulesMapper;
import com.example.entity.modules.Tmodule;
import com.example.exception.NoDataFoundException;
import com.example.exception.modules.modules.ModulesNotSaveException;
import com.example.exception.modules.modules.ModulesNotUpdateException;
import com.example.mapper.modules.MapperModules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

/**
 * Clase de prueba para ServiceModuleImple.
 * Prueba las funcionalidades del servicio ServiceModuleImple.
 */
@DisplayName("Test para metodos de ServiceModuleImple")
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
        assertThrows(NoDataFoundException.class, () -> this.serviceModuleImple.getAll());
        then(this.mapperModules).should().getAll();
        then(this.dtoModulesMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método getById() del servicio Module cuando se encuentra un módulo existente.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se simula la obtención de un módulo válido a través del método getById() del objeto simulado mapperModules.</li>
     *   <li>Se simula la conversión del módulo válido a un objeto ModulesDto válido a través del método TmoduleToModulesDto() del objeto simulado dtoModulesMapper.</li>
     *   <li>Se verifica que al llamar al método getById() se obtenga un objeto ModulesDto no nulo y coincidente con el objeto ModulesDto válido esperado.</li>
     *   <li>Se verifica que se haya interactuado una vez con el objeto simulado mapperModules al llamar al método getById().</li>
     *   <li>Se verifica que se haya interactuado una vez con el objeto simulado dtoModulesMapper al llamar al método TmoduleToModulesDto().</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulo existente por ID")
    public void testGetById_ExistingModule_ReturnsModulesDto() {
        // Arrange

        long moduleId = 1;

        given(this.mapperModules.getById(moduleId)).willReturn(this.tmoduleValid);
        given(this.dtoModulesMapper.TmoduleToModulesDto(this.tmoduleValid)).willReturn(this.modulesDtoValid);

        // Act
        ModulesDto result = this.serviceModuleImple.getById(moduleId);

        // Assert
        assertNotNull(result);
        assertEquals(this.modulesDtoValid, result);
        then(this.mapperModules).should().getById(moduleId);
        then(this.dtoModulesMapper).should().TmoduleToModulesDto(this.tmoduleValid);
    }

    /**
     * Prueba unitaria para el método getById() del servicio Module cuando se proporciona un ID no válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getById() con un ID no válido se lance una excepción de tipo NoDataFoundException.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado mapperModules al llamar al método getById().</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoModulesMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulo con ID no válido")
    public void testGetById_InvalidId_ThrowsNoDataFoundException() {
        // Arrange


        long moduleId = -1;

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> this.serviceModuleImple.getById(moduleId));
        then(this.mapperModules).shouldHaveNoInteractions();
        then(this.dtoModulesMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método getById() del servicio Module cuando se proporciona un ID de un módulo que no existe.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getById() con un ID de un módulo que no existe se lance una excepción de tipo NoDataFoundException.</li>
     *   <li>Se verifica que se haya interactuado con el objeto simulado mapperModules al llamar al método getById().</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoModulesMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulo no existente")
    public void testGetById_NonExistingModule_ThrowsNoDataFoundException() {
        // Arrange

        long moduleId = 1;

        given(this.mapperModules.getById(moduleId)).willReturn(null);

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> this.serviceModuleImple.getById(moduleId));
        then(this.mapperModules).should().getById(moduleId);
        then(this.dtoModulesMapper).shouldHaveNoInteractions();
    }


    /**
     * Prueba unitaria para el método update() del servicio Module cuando se proporciona un objeto ModulesDto válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto ModulesDto válido se retorne el ID del módulo actualizado.</li>
     *   <li>Se verifica que se haya interactuado con el objeto simulado dtoModulesMapper al llamar al método modulesDtoToTmodule().</li>
     *   <li>Se verifica que se haya interactuado con el objeto simulado mapperModules al llamar al método update().</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de módulo válido")
    public void testUpdate_ValidModulesDto_ReturnsUpdatedModuleId() {
        // Arrange

        given(this.dtoModulesMapper.modulesDtoToTmodule(this.modulesDtoValid)).willReturn(this.tmoduleValid);
        given(this.mapperModules.update(this.tmoduleValid)).willReturn(1);

        // Act
        Integer result = this.serviceModuleImple.update(this.modulesDtoValid);

        // Assert
        assertEquals(Integer.valueOf(1), result);
        then(this.dtoModulesMapper).should().modulesDtoToTmodule(this.modulesDtoValid);
        then(this.mapperModules).should().update(this.tmoduleValid);
    }

    /**
     * Prueba unitaria para el método update() del servicio Module cuando se proporciona un objeto ModulesDto nulo.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto ModulesDto nulo se lance una excepción del tipo ModulesNotUpdateException.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoModulesMapper.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado mapperModules.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de módulo con objeto nulo")
    public void testUpdate_NullModulesDto_ThrowsModulesNotUpdateException() {
        // Arrange

        ModulesDto modulesDto = null;

        // Act & Assert
        assertThrows(ModulesNotUpdateException.class, () -> this.serviceModuleImple.update(modulesDto));
        then(this.dtoModulesMapper).shouldHaveNoInteractions();
        then(this.mapperModules).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método update() del servicio Module cuando se proporciona un objeto ModulesDto inválido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto ModulesDto inválido se lance una excepción del tipo ModulesNotUpdateException.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoModulesMapper.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado mapperModules.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de módulo con objeto inválido")
    public void testUpdate_InvalidModulesDto_ThrowsModulesNotUpdateException() {
        // Act & Assert
        assertThrows(ModulesNotUpdateException.class, () -> this.serviceModuleImple.update(this.modulesDtoNotValid));
        then(this.dtoModulesMapper).shouldHaveNoInteractions();
        then(this.mapperModules).shouldHaveNoInteractions();
    }


    /**
     * Prueba unitaria para el método save() del servicio Module cuando se proporciona un objeto ModulesDto válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() con un objeto ModulesDto válido se retorne el ID del módulo guardado.</li>
     *   <li>Se verifica que se haya llamado al método modulesDtoToTmodule() del objeto simulado dtoModulesMapper.</li>
     *   <li>Se verifica que se haya llamado al método insert() del objeto simulado mapperModules.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado de módulo con objeto válido")
    public void testSave_ValidModulesDto_ReturnsSavedModuleId() {
        // Arrange

        given(this.dtoModulesMapper.modulesDtoToTmodule(this.modulesDtoValid)).willReturn(this.tmoduleValid);
        given(this.mapperModules.insert(this.tmoduleValid)).willReturn(1);

        // Act
        Integer result = this.serviceModuleImple.save(this.modulesDtoValid);

        // Assert
        assertEquals(Integer.valueOf(1), result);
        then(this.dtoModulesMapper).should().modulesDtoToTmodule(this.modulesDtoValid);
        then(this.mapperModules).should().insert(this.tmoduleValid);
    }

    /**
     * Prueba unitaria para el método save() del servicio Module cuando se proporciona un objeto ModulesDto nulo.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() con un objeto ModulesDto nulo se lance una excepción del tipo ModulesNotSaveException.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoModulesMapper.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado mapperModules.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado de módulo con objeto nulo")
    public void testSave_NullModulesDto_ThrowsModulesNotSaveException() {
        // Arrange
        ModulesDto modulesDto = null;

        // Act & Assert
        assertThrows(ModulesNotSaveException.class, () -> this.serviceModuleImple.save(modulesDto));
        then(this.dtoModulesMapper).shouldHaveNoInteractions();
        then(this.mapperModules).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método save() del servicio Module cuando se proporciona un objeto ModulesDto inválido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() con un objeto ModulesDto inválido se lance una excepción del tipo ModulesNotSaveException.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoModulesMapper.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado mapperModules.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado de módulo con objeto inválido")
    public void testSave_InvalidModulesDto_ThrowsModulesNotSaveException() {
        // Act & Assert
        assertThrows(ModulesNotSaveException.class, () -> this.serviceModuleImple.save(this.modulesDtoNotValid));
        then(this.dtoModulesMapper).shouldHaveNoInteractions();
        then(this.mapperModules).shouldHaveNoInteractions();
    }



}