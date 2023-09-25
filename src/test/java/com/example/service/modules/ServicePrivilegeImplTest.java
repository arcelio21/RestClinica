package com.example.service.modules;

import com.example.dto.modules.privileges.PrivilegeDto;
import com.example.dto.modules.privileges.PrivilegeSaveDto;
import com.example.dto.modules.privileges.PrivilegeUpdateDto;
import com.example.dtomapper.modules.DtoPrivilegeMapper;
import com.example.entity.modules.Tprivilege;
import com.example.exception.NoDataFoundException;
import com.example.exception.modules.privilege.PrivilegeNotSaveException;
import com.example.exception.modules.privilege.PrivilegeNotUpdateException;
import com.example.mapper.modules.MapperPrivilege;
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

@DisplayName("Test de metodos implementados en ServicePrivileges")
@ExtendWith(MockitoExtension.class)
class ServicePrivilegeImplTest {

    @Mock
    private MapperPrivilege mapperPrivilege;

    @Mock
    private DtoPrivilegeMapper dtoPrivilegeMapper;

    @InjectMocks
    private ServicePrivilegeImpl servicePrivilege;

    private PrivilegeDto privilegeDtoValid;
    private Tprivilege tprivilegeValid;

    private PrivilegeUpdateDto privilegeUpdateDtoValid;
    private PrivilegeUpdateDto privilegeUpdateDtoNotValid;

    private PrivilegeSaveDto privilegeSaveDtoValid;
    private PrivilegeSaveDto privilegeSaveDtoNotValid;


    @BeforeEach
    void setUp() {

        privilegeDtoValid = PrivilegeDto.builder()
                .id(1)
                .name("READ")
                .build();

        tprivilegeValid = new Tprivilege(1,"READ");

        privilegeUpdateDtoValid = PrivilegeUpdateDto.builder()
                .id(1)
                .name("WRITE")
                .build();
        privilegeUpdateDtoNotValid = PrivilegeUpdateDto.builder().build();

        this.privilegeSaveDtoValid = PrivilegeSaveDto.builder()
                .name("READ")
                .build();

        this.privilegeSaveDtoNotValid = PrivilegeSaveDto.builder().build();
    }

    /**
     * Prueba unitaria para el método getAll() del servicio Privilege cuando existen privilegios.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getAll() se retorne una lista de PrivilegeDto no nula.</li>
     *   <li>Se verifica que la lista de PrivilegeDto tenga un tamaño de 1.</li>
     *   <li>Se verifica que el primer elemento de la lista de PrivilegeDto sea una instancia de PrivilegeDto.</li>
     *   <li>Se verifica que se haya llamado al método getAll() del objeto simulado mapperPrivilege.</li>
     *   <li>Se verifica que se haya llamado al método TprivilegeToPrivilegDto() del objeto simulado dtoPrivilegeMapper al menos una vez.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de privilegios existentes")
    void getAll_Privileges_Exists() {

        given(this.mapperPrivilege.getAll()).willReturn(List.of(tprivilegeValid));
        given(this.dtoPrivilegeMapper.TprivilegeToPrivilegDto(any(Tprivilege.class))).willReturn(privilegeDtoValid);

        List<PrivilegeDto> privilegeDtoList = this.servicePrivilege.getAll();
        assertNotNull(privilegeDtoList);
        assertEquals(1,privilegeDtoList.size());
        assertInstanceOf(PrivilegeDto.class,privilegeDtoList.get(0));

        then(this.mapperPrivilege).should(times(1)).getAll();
        then(this.dtoPrivilegeMapper).should(times(1)).TprivilegeToPrivilegDto(any(Tprivilege.class));
    }

    /**
     * Prueba unitaria para el método getAll() del servicio Privilege cuando no existen privilegios.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getAll() se lance una excepción NoDataFoundException.</li>
     *   <li>Se verifica que se haya llamado al método getAll() del objeto simulado mapperPrivilege.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de privilegios vacía")
    void getAll_Privileges_isEmpty(){
        given(this.mapperPrivilege.getAll()).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, ()-> this.servicePrivilege.getAll());

        then(this.mapperPrivilege).should(times(1)).getAll();
    }

    @Test
    void getById_Exist() {
        Integer id=1;
        given(this.mapperPrivilege.getByid(id)).willReturn(this.tprivilegeValid);
        given(this.dtoPrivilegeMapper.TprivilegeToPrivilegDto(this.tprivilegeValid)).willReturn(this.privilegeDtoValid);

        PrivilegeDto privilegeDto = this.servicePrivilege.getById(id);

        assertNotNull(privilegeDto);
        assertNotNull(privilegeDto.getId());
        assertNotNull(privilegeDto.getName());

        then(this.mapperPrivilege).should(times(1)).getByid(id);
        then(this.dtoPrivilegeMapper).should(times(1)).TprivilegeToPrivilegDto(this.tprivilegeValid);
    }

    /**
     * Prueba unitaria para el método getById() del servicio Privilege cuando se proporciona un ID nulo.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getById() con un ID nulo se lance una excepción NoDataFoundException.</li>
     *   <li>Se verifica que no se haya interactuado con las dependencias mapperPrivilege y dtoPrivilegeMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de privilegio con ID nulo")
    public void getById_NullId_ThrowsNoDataFoundException() {

        Integer id = null;

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> this.servicePrivilege.getById(id));
        then(this.mapperPrivilege).shouldHaveNoInteractions();
        then(this.dtoPrivilegeMapper).shouldHaveNoInteractions();
    }


    /**
     * Prueba unitaria para el método getById() del servicio Privilege cuando el dato no existe.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getById() con un ID de un dato inexistente se lance una excepción NoDataFoundException.</li>
     *   <li>Se verifica que se haya llamado al método getByid() del objeto simulado mapperPrivilege.</li>
     *   <li>Se verifica que no se haya interactuado con la dependencia dtoPrivilegeMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de privilegio con dato inexistente")
    public void getById_DataNotExist() {

        Integer id = 34;

        given(this.mapperPrivilege.getByid(id)).willReturn(null);
        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> this.servicePrivilege.getById(id));
        then(this.mapperPrivilege).should(times(1)).getByid(id);
        then(this.dtoPrivilegeMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método update() del servicio Privilege cuando se proporciona un objeto PrivilegeUpdateDto válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto PrivilegeUpdateDto válido se retorne el número de filas afectadas.</li>
     *   <li>Se verifica que se haya llamado al método privilegeUpdateDtoToTprivilege() del objeto simulado dtoPrivilegeMapper.</li>
     *   <li>Se verifica que se haya llamado al método update() del objeto simulado mapperPrivilege.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de privilegio con objeto válido")
    void update_with_DataValid(){

        given(this.dtoPrivilegeMapper.privilegeUpdateDtoToTprivilege(this.privilegeUpdateDtoValid)).willReturn(new Tprivilege());
        given(this.mapperPrivilege.update(any(Tprivilege.class))).willReturn(1);

        Integer rowAffected = this.servicePrivilege.update(this.privilegeUpdateDtoValid);
        assertNotNull(rowAffected);
        assertEquals(1,rowAffected);

        then(this.mapperPrivilege).should(times(1)).update(any(Tprivilege.class));
        then(this.dtoPrivilegeMapper).should(times(1)).privilegeUpdateDtoToTprivilege(this.privilegeUpdateDtoValid);
    }


    /**
     * Prueba unitaria para el método update() del servicio Privilege cuando se proporciona un objeto PrivilegeUpdateDto no válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un objeto PrivilegeUpdateDto no válido se lance la excepción PrivilegeNotUpdateException.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado mapperPrivilege.</li>
     *   <li>Se verifica que no se haya interactuado con el objeto simulado dtoPrivilegeMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de privilegio con objeto no válido")
    void update_With_Data_NotValid() {
        assertThrows(PrivilegeNotUpdateException.class, ()-> this.servicePrivilege.update(this.privilegeUpdateDtoNotValid));
        then(this.mapperPrivilege).shouldHaveNoInteractions();
        then(this.dtoPrivilegeMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método update() del servicio Privilege cuando se proporciona un ID que no existe.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método update() con un ID que no existe se lance la excepción PrivilegeNotUpdateException.</li>
     *   <li>Se verifica que se haya llamado al método privilegeUpdateDtoToTprivilege() del objeto simulado dtoPrivilegeMapper.</li>
     *   <li>Se verifica que se haya llamado al método update() del objeto simulado mapperPrivilege.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de actualización de privilegio con ID inexistente")
    void update_with_ID_notExist(){
        given(this.dtoPrivilegeMapper.privilegeUpdateDtoToTprivilege(this.privilegeUpdateDtoValid)).willReturn(new Tprivilege());
        given(this.mapperPrivilege.update(any(Tprivilege.class))).willReturn(0);

        assertThrows(PrivilegeNotUpdateException.class,()-> this.servicePrivilege.update(this.privilegeUpdateDtoValid));

        then(this.mapperPrivilege).should(times(1)).update(any(Tprivilege.class));
        then(this.dtoPrivilegeMapper).should(times(1)).privilegeUpdateDtoToTprivilege(this.privilegeUpdateDtoValid);
    }

    /**
     * Prueba unitaria para el método save() del servicio Privilege cuando se proporciona un objeto PrivilegeSaveDto válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() con un objeto PrivilegeSaveDto válido se retorne el número de filas afectadas.</li>
     *   <li>Se verifica que se haya llamado al método privilegeSaveDtoToTprivilege() del objeto simulado dtoPrivilegeMapper.</li>
     *   <li>Se verifica que se haya llamado al método save() del objeto simulado mapperPrivilege.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado de privilegio con datos válidos")
    void save_data_valid() {
        given(this.dtoPrivilegeMapper.privilegeSaveDtoToTprivilege(this.privilegeSaveDtoValid)).willReturn(new Tprivilege());
        given(this.mapperPrivilege.save(any(Tprivilege.class))).willReturn(1);

        Integer rowAffected = this.servicePrivilege.save(this.privilegeSaveDtoValid);

        assertNotNull(rowAffected);
        assertEquals(1,rowAffected);

        then(this.mapperPrivilege).should(times(1)).save(any(Tprivilege.class));
        then(this.dtoPrivilegeMapper).should(times(1)).privilegeSaveDtoToTprivilege(this.privilegeSaveDtoValid);
    }

    /**
     * Prueba unitaria para el método save() del servicio Privilege cuando se proporciona un objeto PrivilegeSaveDto no válido.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() con un objeto PrivilegeSaveDto no válido se lance una excepción de tipo PrivilegeNotSaveException.</li>
     *   <li>Se verifica que no se haya llamado al método save() del objeto simulado mapperPrivilege.</li>
     *   <li>Se verifica que no se haya llamado al método privilegeSaveDtoToTprivilege() del objeto simulado dtoPrivilegeMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado de privilegio con datos no válidos")
    void save_data_notValid(){
        assertThrows(PrivilegeNotSaveException.class, ()-> this.servicePrivilege.save(this.privilegeSaveDtoNotValid));

        then(this.mapperPrivilege).shouldHaveNoInteractions();
        then(this.dtoPrivilegeMapper).shouldHaveNoInteractions();
    }

    /**
     * Prueba unitaria para el método save() del servicio Privilege cuando ocurre un error al guardar los datos.
     *
     * <p>Se realiza la simulación del comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() se lance una excepción de tipo PrivilegeNotSaveException.</li>
     *   <li>Se verifica que se haya llamado al método save() del objeto simulado mapperPrivilege.</li>
     *   <li>Se verifica que se haya llamado al método privilegeSaveDtoToTprivilege() del objeto simulado dtoPrivilegeMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado de privilegio con error en el guardado")
    void save_Data_ErrorToTheSave(){
        given(this.dtoPrivilegeMapper.privilegeSaveDtoToTprivilege(this.privilegeSaveDtoValid)).willReturn(new Tprivilege());
        given(this.mapperPrivilege.save(any(Tprivilege.class))).willReturn(0);

        assertThrows(PrivilegeNotSaveException.class, ()-> this.servicePrivilege.save(this.privilegeSaveDtoValid));

        then(this.mapperPrivilege).should(times(1)).save(any(Tprivilege.class));
        then(this.dtoPrivilegeMapper).should(times(1)).privilegeSaveDtoToTprivilege(this.privilegeSaveDtoValid);
    }
}