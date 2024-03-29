package com.example.service.user;

import com.example.dto.user.type_user.TypeUserDto;
import com.example.dto.user.type_user.TypeUserPostDto;
import com.example.dto.user.type_user.TypeUserUpdateDto;
import com.example.dtomapper.user.DtoTypeUserMapper;
import com.example.entity.user.TtypeUser;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.type_user.TypeUserNotSaveException;
import com.example.exception.user.type_user.TypeUserNotUpdateException;
import com.example.mapper.user.MapperTypeUser;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@DisplayName("Test para metodos de clase ServiceTypeUserImpl")
@ExtendWith(MockitoExtension.class)
class ServiceTypeUserImplTest {

    @Mock
    private MapperTypeUser mapperTypeUser;

    @Mock
    private DtoTypeUserMapper dtoTypeUserMapper;

    @InjectMocks
    private ServiceTypeUserImpl serviceTypeUser;

    private TypeUserDto typeUserDtoValid;

    private TtypeUser ttypeUserValid;

    @BeforeEach
    void setUp() {
        typeUserDtoValid = TypeUserDto.builder()
                .id(1)
                .name("ADMIN")
                .build();

        

        ttypeUserValid = new TtypeUser(1,"ADMIN");

    }


    /**
     * Prueba unitaria para el método getAll() del servicio TypeUser, que devuelve una lista de TypeUserDto.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado del mapper TypeUser para que devuelva una lista de TtypeUser válidos.</li>
     *   <li>Se configura el objeto simulado del mapper dtoTypeUserMapper para que convierta cada TtypeUser en un TypeUserDto.</li>
     *   <li>Se llama al método getAll() del servicio TypeUser.</li>
     *   <li>Se verifica que el resultado tenga el tamaño esperado.</li>
     *   <li>Se verifica que se haya llamado al método getAll() del mapper TypeUser una vez.</li>
     *   <li>Se verifica que se haya llamado al método ttypeUserToTypeUserDto() del mapper dtoTypeUserMapper una vez por cada TtypeUser.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getAll() del servicio TypeUser que devuelve una lista de TypeUserDto")
    public void testGetAll_ReturnsTypeUserDtoList() {
        // Arrange

        given(this.mapperTypeUser.getAll()).willReturn(List.of(this.ttypeUserValid));
        given(this.dtoTypeUserMapper.ttypeUserToTypeUserDto(any(TtypeUser.class))).willAnswer(invocation -> {
            TtypeUser ttypeUser = invocation.getArgument(0);
            int id = ttypeUser.getId();
            String name = ttypeUser.getNameTypeUser();
            return TypeUserDto.builder().id(id).name(name).build();
        });

        // Act
        List<TypeUserDto> result = this.serviceTypeUser.getAll();

        // Assert
        assertEquals(1, result.size());
        then(this.mapperTypeUser).should().getAll();
        then(this.dtoTypeUserMapper).should(times(1)).ttypeUserToTypeUserDto(any(TtypeUser.class));
    }

    /**
     * Prueba unitaria para el método getAll() del servicio TypeUser cuando no se encuentran datos.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado del mapper TypeUser para que devuelva una lista vacía.</li>
     *   <li>Se llama al método getAll() del servicio TypeUser.</li>
     *   <li>Se verifica que se lance una excepción de tipo NoDataFoundException.</li>
     *   <li>Se verifica que se haya llamado al método getAll() del mapper TypeUser una vez.</li>
     *   <li>Se verifica que no se haya llamado al método ttypeUserToTypeUserDto() del mapper dtoTypeUserMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de getAll() del servicio TypeUser cuando no se encuentran datos")
    public void testGetAll_ReturnsTypeUserDtoListEmpty() {
        // Arrange

        given(this.mapperTypeUser.getAll()).willReturn(List.of());


        // Assert

        assertThrows(NoDataFoundException.class,()-> this.serviceTypeUser.getAll());
        then(this.mapperTypeUser).should(times(1)).getAll();
        then(this.dtoTypeUserMapper).should(never()).ttypeUserToTypeUserDto(any(TtypeUser.class));
    }


    //GET_ID


    /**
     * Prueba unitaria para el método getById() del servicio TypeUser cuando se proporciona un ID válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado del mapper TypeUser para que devuelva un objeto TtypeUser válido.</li>
     *   <li>Se configura el objeto simulado del mapper DtoTypeUserMapper para que convierta el objeto TtypeUser en un objeto TypeUserDto válido.</li>
     *   <li>Se llama al método getById() del servicio TypeUser con un ID válido.</li>
     *   <li>Se verifica que el resultado no sea nulo.</li>
     *   <li>Se verifica que los métodos simulados hayan sido llamados correctamente.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de TypeUserDto por ID válido")
    public void testGetById_ValidId_ReturnsTypeUserDto() {
        // Arrange

        int id = 1;

        given(this.mapperTypeUser.getById(id)).willReturn(this.ttypeUserValid);
        given(this.dtoTypeUserMapper.ttypeUserToTypeUserDto(this.ttypeUserValid)).willReturn(this.typeUserDtoValid);

        // Act
        TypeUserDto result = this.serviceTypeUser.getById(id);

        // Assert
        assertNotNull(result);
        then(this.mapperTypeUser).should().getById(id);
        then(this.dtoTypeUserMapper).should().ttypeUserToTypeUserDto(this.ttypeUserValid);
    }

    /**
     * Prueba unitaria para el método getById() del servicio TypeUser cuando se proporciona un ID no válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se llama al método getById() del servicio TypeUser con un ID no válido.</li>
     *   <li>Se verifica que se lance una excepción del tipo NoDataFoundException.</li>
     *   <li>Se verifica que no se hayan realizado interacciones con los objetos simulados.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de TypeUserDto por ID no válido")
    public void testGetById_InvalidId_ThrowsNoDataFoundException() {
        // Arrange

        int id = -1;

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> this.serviceTypeUser.getById(id));
        then(this.mapperTypeUser).shouldHaveNoInteractions();
        then(this.dtoTypeUserMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método getById() del servicio TypeUser cuando se proporciona un ID inexistente.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado para que devuelva null al llamar al método getById() con el ID proporcionado.</li>
     *   <li>Se llama al método getById() del servicio TypeUser con el ID inexistente.</li>
     *   <li>Se verifica que se lance una excepción del tipo NoDataFoundException.</li>
     *   <li>Se verifica que se haya realizado una interacción con el objeto simulado mapperTypeUser, llamando al método getById() con el ID proporcionado.</li>
     *   <li>Se verifica que no se hayan realizado interacciones con el objeto simulado dtoTypeUserMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de TypeUserDto por ID inexistente")
    public void testGetById_NonexistentId_ThrowsNoDataFoundException() {

        int id = 1;

        given(this.mapperTypeUser.getById(id)).willReturn(null);

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> this.serviceTypeUser.getById(id));
        then(this.mapperTypeUser).should().getById(id);
        then(this.dtoTypeUserMapper).shouldHaveNoInteractions();
    }

    //UPDATE

    /**
     * Prueba unitaria para el método update() del servicio TypeUser cuando se proporciona un TypeUserDto válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado dtoTypeUserMapper para que convierta el TypeUserDto válido en un objeto TtypeUser válido.</li>
     *   <li>Se configura el objeto simulado mapperTypeUser para que devuelva 1 al llamar al método update() con el objeto TtypeUser válido.</li>
     *   <li>Se llama al método update() del servicio TypeUser con el TypeUserDto válido.</li>
     *   <li>Se verifica que el resultado retornado sea igual a 1.</li>
     *   <li>Se verifica que se haya realizado una interacción con el objeto simulado dtoTypeUserMapper, llamando al método typeUserDtoToTtypeUser() con el TypeUserDto válido.</li>
     *   <li>Se verifica que se haya realizado una interacción con el objeto simulado mapperTypeUser, llamando al método update() con el objeto TtypeUser válido.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de TypeUserDto válido")
    public void testUpdate_ValidTypeUserDto_ReturnsUpdatedTypeUser() {
        // Arrange
        TypeUserUpdateDto typeUserUpdateDto = new TypeUserUpdateDto(1, "USER");
        given(this.dtoTypeUserMapper.typeUserUpdateDtoToTtypeUser(typeUserUpdateDto)).willReturn(this.ttypeUserValid);
        given(this.mapperTypeUser.update(this.ttypeUserValid)).willReturn(1);

        // Act
        Integer result = this.serviceTypeUser.update(typeUserUpdateDto);

        // Assert
        assertEquals(1, result);
        then(this.dtoTypeUserMapper).should().typeUserUpdateDtoToTtypeUser(typeUserUpdateDto);
        then(this.mapperTypeUser).should().update(this.ttypeUserValid);
    }

    /**
     * Prueba unitaria para el método update() del servicio TypeUser cuando se proporciona un TypeUserDto no válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>No se configuran objetos simulados, ya que se espera que no haya interacciones con ellos.</li>
     *   <li>Se llama al método update() del servicio TypeUser con el TypeUserDto no válido.</li>
     *   <li>Se verifica que se lance una excepción del tipo TypeUserNotUpdateException.</li>
     *   <li>Se verifica que no haya interacciones con los objetos simulados dtoTypeUserMapper y mapperTypeUser.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de TypeUserDto no válido")
    public void testUpdate_InvalidTypeUserDto_ThrowsTypeUserNotUpdateException() {
        // Arrange
        TypeUserUpdateDto typeUserUpdateDto = new TypeUserUpdateDto(1, "");
        // Act & Assert
        assertThrows(TypeUserNotUpdateException.class, () -> this.serviceTypeUser.update(typeUserUpdateDto));
        then(this.dtoTypeUserMapper).shouldHaveNoInteractions();
        then(this.mapperTypeUser).shouldHaveNoInteractions();
    }


    /**
     * Prueba unitaria para el método update() del servicio TypeUser cuando se proporciona un TypeUserDto que no existe.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado dtoTypeUserMapper para que devuelva null al convertir el TypeUserDto válido.</li>
     *   <li>No se configuran otros objetos simulados, ya que se espera que no haya interacciones con ellos.</li>
     *   <li>Se llama al método update() del servicio TypeUser con el TypeUserDto válido.</li>
     *   <li>Se verifica que se lance una excepción del tipo TypeUserNotUpdateException.</li>
     *   <li>Se verifica que se haya llamado al método typeUserDtoToTtypeUser() del objeto simulado dtoTypeUserMapper con el TypeUserDto válido.</li>
     *   <li>Se verifica que no haya interacciones con el objeto simulado mapperTypeUser.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de TypeUserDto no existente")
    public void testUpdate_NonexistentTypeUserDto_ThrowsTypeUserNotUpdateException() {
        
        TypeUserUpdateDto typeUserUpdateDto = new TypeUserUpdateDto(1, "USER");
        // Arrange
        given(this.dtoTypeUserMapper.typeUserUpdateDtoToTtypeUser(typeUserUpdateDto)).willReturn(null);

        // Act & Assert
        assertThrows(TypeUserNotUpdateException.class, () -> this.serviceTypeUser.update(typeUserUpdateDto));
        then(this.dtoTypeUserMapper).should().typeUserUpdateDtoToTtypeUser(typeUserUpdateDto);
        then(this.mapperTypeUser).shouldHaveNoInteractions();
    }

    // SAVE

    /**
     * Prueba unitaria para el método save() del servicio TypeUser cuando se proporciona un TypeUserDto válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se configura el objeto simulado dtoTypeUserMapper para que convierta el TypeUserDto válido en el objeto TtypeUser válido.</li>
     *   <li>Se configura el objeto simulado mapperTypeUser para que devuelva el ID del TtypeUser guardado.</li>
     *   <li>Se llama al método save() del servicio TypeUser con el TypeUserDto válido.</li>
     *   <li>Se verifica que se retorne el ID del TtypeUser guardado.</li>
     *   <li>Se verifica que se haya llamado al método typeUserDtoToTtypeUser() del objeto simulado dtoTypeUserMapper con el TypeUserDto válido.</li>
     *   <li>Se verifica que se haya llamado al método save() del objeto simulado mapperTypeUser con el TtypeUser válido.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado de TypeUserDto válido")
    public void testSave_ValidTypeUserDto_ReturnsSavedTypeUserId() {
        // Arrange
        TypeUserPostDto typeUserPostDto = new TypeUserPostDto("USER");
        given(this.dtoTypeUserMapper.typeUserPostDtoToTtypeUser(typeUserPostDto)).willReturn(this.ttypeUserValid);
        given(this.mapperTypeUser.save(this.ttypeUserValid)).willReturn(1);

        // Act
        Integer result = this.serviceTypeUser.save(typeUserPostDto);

        // Assert
        assertEquals(1, result);
        then(this.dtoTypeUserMapper).should().typeUserPostDtoToTtypeUser(typeUserPostDto);
        then(this.mapperTypeUser).should().save(this.ttypeUserValid);
    }

    /**
     * Prueba unitaria para el método save() del servicio TypeUser cuando se proporciona un TypeUserDto inválido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se lanza una excepción TypeUserNotSaveException al llamar al método save() del servicio TypeUser con el TypeUserDto inválido.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoTypeUserMapper.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado mapperTypeUser.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado de TypeUserDto inválido")
    public void testSave_InvalidTypeUserDto_ThrowsTypeUserNotSaveException() {
        // Arrange
        TypeUserPostDto typeUserPostDto = new TypeUserPostDto("");
        // Act & Assert
        assertThrows(TypeUserNotSaveException.class, () -> this.serviceTypeUser.save(typeUserPostDto));
        then(this.dtoTypeUserMapper).shouldHaveNoInteractions();
        then(this.mapperTypeUser).shouldHaveNoInteractions();
    }




}