package com.example.service.user;

import com.example.dto.user.type_user.TypeUserDto;
import com.example.dtomapper.user.DtoTypeUserMapper;
import com.example.entity.user.TtypeUser;
import com.example.exception.NoDataFoundException;
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
    private TypeUserDto typeUserDtoNotValid;

    private TtypeUser ttypeUserValid;
    private TtypeUser ttypeUserNotValid;

    @BeforeEach
    void setUp() {
        typeUserDtoValid = TypeUserDto.builder()
                .id(1)
                .name("ADMIN")
                .build();

        typeUserDtoNotValid = TypeUserDto.builder()
                .name(" ")
                .build();

        ttypeUserValid = new TtypeUser(1,"ADMIN");
        ttypeUserNotValid = new TtypeUser(null,"");

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


    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void save() {
    }
}