package com.example.service.user;

import com.example.dto.user.typeuser_module.*;
import com.example.dtomapper.user.DtoTypeUserModuleMapper;
import com.example.entity.modules.Tmodule;
import com.example.entity.modules.TmodulePrivilege;
import com.example.entity.user.TtypeUser;
import com.example.entity.user.TtypeUserModule;
import com.example.exception.NoDataFoundException;
import com.example.exception.user.typeuser_module.TypeUserModuleNotSaveException;
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

    //ESTE OBJETO SE UTILIZA PARA GUARDAR DATOS CORRECTAMENTE
    private TypeUserModuleSaveDto typeUserModuleSaveDto;

    //OBJETO PARA DATOS NO VALIDOS AL GUARDAR
    private TypeUserModuleSaveDto typeUserModuleSaveDtoNotValid;


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


        this.typeUserModuleSaveDto = TypeUserModuleSaveDto.builder()
                .typeUser(1)
                .idModulePrivilege(1L)
                .build();

        this.typeUserModuleSaveDtoNotValid = TypeUserModuleSaveDto.builder()
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


    /**
     * Prueba unitaria para el método save() del servicio TypeUserModule cuando se proporciona un objeto TypeUserModuleSaveDto válido.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() con un objeto TypeUserModuleSaveDto válido se retorne el número de filas afectadas.</li>
     *   <li>Se verifica que se haya llamado al método TypeUserModuleSaveDtoToTtypeUserModule() del objeto simulado dtoMapper.</li>
     *   <li>Se verifica que se haya llamado al método save() del objeto simulado mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado con datos válidos")
    void save_dataValid(){
        given(this.dtoMapper.TypeUserModuleSaveDtoToTtypeUserModule(this.typeUserModuleSaveDto)).willReturn(new TtypeUserModule());
        given(this.mapper.save(any(TtypeUserModule.class))).willReturn(1);

        Integer rowAffected = this.service.save(this.typeUserModuleSaveDto);

        assertNotNull(rowAffected);
        assertEquals(1,rowAffected);

        then(this.dtoMapper).should(times(1)).TypeUserModuleSaveDtoToTtypeUserModule(this.typeUserModuleSaveDto);
        then(this.mapper).should(times(1)).save(any(TtypeUserModule.class));
    }


    /**
     * Prueba unitaria para el método save() del servicio TypeUserModule cuando se proporciona un objeto TypeUserModuleSaveDto no válido.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() con un objeto TypeUserModuleSaveDto no válido se lance una excepción de tipo TypeUserModuleNotSaveException.</li>
     *   <li>Se verifica que no haya interacciones con los objetos simulados dtoMapper y mapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado con datos no válidos")
    void save_throwException_dataNoValid(){

        assertThrows(TypeUserModuleNotSaveException.class, ()-> this.service.save(this.typeUserModuleSaveDtoNotValid));

        then(this.dtoMapper).shouldHaveNoInteractions();
        then(this.mapper).shouldHaveNoInteractions();
    }


    /**
     * Prueba unitaria para el método save() del servicio TypeUserModule cuando la operación de guardado devuelve una fila afectada igual a cero.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método save() con un objeto TypeUserModuleSaveDto y la operación de guardado devuelve una fila afectada igual a cero, se lance una excepción de tipo TypeUserModuleNotSaveException.</li>
     *   <li>Se verifica que el objeto simulado dtoMapper se utiliza para convertir el objeto TypeUserModuleSaveDto al tipo TtypeUserModule.</li>
     *   <li>Se verifica que el objeto simulado mapper se utiliza para realizar la operación de guardado.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de guardado con fila afectada igual a cero")
    void save_returnRowAffectedZero(){

        given(this.dtoMapper.TypeUserModuleSaveDtoToTtypeUserModule(this.typeUserModuleSaveDto)).willReturn(new TtypeUserModule());
        given(this.mapper.save(any(TtypeUserModule.class))).willReturn(0);

        assertThrows(TypeUserModuleNotSaveException.class,()-> this.service.save(this.typeUserModuleSaveDto));

        then(this.dtoMapper).should(times(1)).TypeUserModuleSaveDtoToTtypeUserModule(this.typeUserModuleSaveDto);
        then(this.mapper).should(times(1)).save(any(TtypeUserModule.class));

    }


    /**
     * Prueba unitaria para el método getModuleAndTypeUserDistinct() del servicio TypeUserModule cuando la función devuelve datos válidos.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleAndTypeUserDistinct() y la función devuelve datos válidos, se retorne una lista de objetos ModuleTypeUserGetDto.</li>
     *   <li>Se verifica que el objeto simulado mapper se utiliza para obtener los datos.</li>
     *   <li>Se verifica que el objeto simulado dtoMapper se utiliza para convertir los datos del tipo TtypeUserModule a ModuleTypeUserGetDto.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos y tipos de usuario asociados distintos")
    void getModuleAndTypeUserDistinct_DataValid(){
        ModuleTypeUserGetDto moduleOfTypeUserGetDto = new ModuleTypeUserGetDto(1L,"USER",1,"ADMIN");

        TtypeUserModule ttypeUserModule = new TtypeUserModule();
        ttypeUserModule.setModulePrivilegeId(new TmodulePrivilege(0L,null,new Tmodule(1L,"USER"),null));
        ttypeUserModule.setTypeUser(new TtypeUser(1,"ADMIN"));

        given(this.mapper.getModuleAndTypeUserDistinct()).willReturn(List.of(ttypeUserModule));
        given(this.dtoMapper.tTypeUserModuleToModuleTypeUserGetDto(ttypeUserModule)).willReturn(moduleOfTypeUserGetDto);

        List<ModuleTypeUserGetDto> moduleTypeUserGetDtoList = this.service.getModuleAndTypeUserDistinct();

        assertNotNull(moduleTypeUserGetDtoList);
        assertFalse(moduleTypeUserGetDtoList.isEmpty());

        then(this.mapper).should(times(1)).getModuleAndTypeUserDistinct();
        then(this.dtoMapper).should(times(1)).tTypeUserModuleToModuleTypeUserGetDto(ttypeUserModule);
    }


    /**
     * Prueba unitaria para el método getModuleAndTypeUserDistinct() del servicio TypeUserModule cuando la función no devuelve datos válidos.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleAndTypeUserDistinct() y la función no devuelve datos válidos, se lance una excepción del tipo NoDataFoundException.</li>
     *   <li>Se verifica que el objeto simulado mapper se utiliza para obtener los datos.</li>
     *   <li>Se verifica que el objeto simulado dtoMapper no tenga interacciones.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos y tipos de usuario asociados distintos sin datos válidos")
    void getModuleAndTypeUserDistinct_DataNotValid(){
        given(this.mapper.getModuleAndTypeUserDistinct()).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, ()-> this.service.getModuleAndTypeUserDistinct());

        then(this.mapper).should(times(1)).getModuleAndTypeUserDistinct();
        then(this.dtoMapper).shouldHaveNoInteractions();
    }


    /**
     * Prueba unitaria para el método getModuleDistinctByIdTypeUserAndStatusActived() del servicio TypeUserModule cuando la función devuelve datos válidos.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleDistinctByIdTypeUserAndStatusActived() y la función devuelve datos válidos, se obtiene una lista de módulos distintos para un tipo de usuario y estado activo.</li>
     *   <li>Se verifica que el objeto simulado mapper se utiliza para obtener los datos.</li>
     *   <li>Se verifica que el objeto simulado dtoMapper se utiliza para transformar los datos.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos distintos por ID de tipo de usuario y estado activo con datos válidos")
    void getModuleDistinctByIdTypeUserAndStatusActived_dataValid(){

        ModuleOfTypeUserGetDto moduleOfTypeUserGetDto = new ModuleOfTypeUserGetDto(1L,"USERS");

        given(this.mapper.getModuleDistinctByIdTypeUserAndStatusActived(1)).willReturn(List.of(new TtypeUserModule()));
        given(this.dtoMapper.tTypeUserModuleToModuleOfTypeUserGetDto(any(TtypeUserModule.class))).willReturn(moduleOfTypeUserGetDto);

        List<ModuleOfTypeUserGetDto> moduleOfTypeUserGetDtos = this.service.getModuleDistinctByIdTypeUserAndStatusActived(1);

        assertNotNull(moduleOfTypeUserGetDtos);
        assertFalse(moduleOfTypeUserGetDtos.isEmpty());

        then(this.mapper).should(times(1)).getModuleDistinctByIdTypeUserAndStatusActived(1);
        then(this.dtoMapper).should(times(1)).tTypeUserModuleToModuleOfTypeUserGetDto(any(TtypeUserModule.class));
     }

    /**
     * Prueba unitaria para el método getModuleDistinctByIdTypeUserAndStatusActived() del servicio TypeUserModule cuando se proporciona un parámetro no válido.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleDistinctByIdTypeUserAndStatusActived() con un parámetro no válido (typeUserId igual a 0), se lance una excepción de tipo NoDataFoundException.</li>
     *   <li>Se verifica que no haya interacciones con los objetos simulados (mapper y dtoMapper).</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos distintos por ID de tipo de usuario y estado activo con parámetro no válido")
     void getModuleDistinctByIdTypeUserAndStatusActived_ParameterNotValid(){

        Integer typeUserId = 0;

        assertThrows(NoDataFoundException.class, ()-> this.service.getModuleDistinctByIdTypeUserAndStatusActived(typeUserId));

        then(this.mapper).shouldHaveNoInteractions();
        then(this.dtoMapper).shouldHaveNoInteractions();
     }

    /**
     * Prueba unitaria para el método getModuleDistinctByIdTypeUserAndStatusActived() del servicio TypeUserModule cuando el mapper devuelve null.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleDistinctByIdTypeUserAndStatusActived() y el mapper devuelve null, se lance una excepción de tipo NoDataFoundException.</li>
     *   <li>Se verifica que el método getModuleDistinctByIdTypeUserAndStatusActived() del mapper se llama exactamente una vez con el parámetro typeUserId igual a 1.</li>
     *   <li>Se verifica que no haya interacciones con el objeto simulado dtoMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos distintos por ID de tipo de usuario y estado activo con retorno null del mapper")
    void getModuleDistinctByIdTypeUserAndStatusActived_MapperReturnNull(){

         given(this.mapper.getModuleDistinctByIdTypeUserAndStatusActived(1)).willReturn(null);

         assertThrows(NoDataFoundException.class, ()-> this.service.getModuleDistinctByIdTypeUserAndStatusActived(1));

         then(this.mapper).should(times(1)).getModuleDistinctByIdTypeUserAndStatusActived(1);
         then(this.dtoMapper).shouldHaveNoInteractions();
     }

    /**
     * Prueba unitaria para el método getModuleDistinctByIdTypeUserAndStatusActived() del servicio TypeUserModule cuando el mapper devuelve una lista vacía.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleDistinctByIdTypeUserAndStatusActived() y el mapper devuelve una lista vacía, se lance una excepción de tipo NoDataFoundException.</li>
     *   <li>Se verifica que el método getModuleDistinctByIdTypeUserAndStatusActived() del mapper se llama exactamente una vez con el parámetro typeUserId igual a 1.</li>
     *   <li>Se verifica que no haya interacciones con el objeto simulado dtoMapper.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos distintos por ID de tipo de usuario y estado activo con retorno de lista vacía del mapper")
    void getModuleDistinctByIdTypeUserAndStatusActived_MapperReturnListEmpty(){

         given(this.mapper.getModuleDistinctByIdTypeUserAndStatusActived(1)).willReturn(Collections.emptyList());

         assertThrows(NoDataFoundException.class, ()-> this.service.getModuleDistinctByIdTypeUserAndStatusActived(1));

         then(this.mapper).should(times(1)).getModuleDistinctByIdTypeUserAndStatusActived(1);
         then(this.dtoMapper).shouldHaveNoInteractions();
     }

     //-----------------------------------------------------------------------------------------------------------------

    /**
     * Prueba unitaria para el método getModuleDistinctByIdTypeUserAndIdStatus() del servicio TypeUserModule cuando el mapper devuelve datos válidos.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleDistinctByIdTypeUserAndIdStatus() y el mapper devuelve datos válidos, se obtiene una lista de objetos ModuleOfTypeUserGetDto no nula y no vacía.</li>
     *   <li>Se verifica que el método getModuleDistinctByIdTypeUserAndIdStatus() del mapper se llama exactamente una vez con los parámetros idTypeUser igual a 1 e idStatus igual a 1.</li>
     *   <li>Se verifica que el método tTypeUserModuleToModuleOfTypeUserGetDto() del objeto simulado dtoMapper se llama exactamente una vez con cualquier instancia de TtypeUserModule.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos distintos por ID de tipo de usuario y ID de estado con retorno de datos válidos")
    void getModuleDistinctByIdTypeUserAndIdStatus_DataValid(){

        Integer idTypeUser = 1;
        Integer idStatus=1;

        ModuleOfTypeUserGetDto moduleOfTypeUserGetDto = new ModuleOfTypeUserGetDto(1L,"ADMIN");

        given(this.mapper.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser,idStatus)).willReturn(List.of(new TtypeUserModule()));
        given(this.dtoMapper.tTypeUserModuleToModuleOfTypeUserGetDto(any(TtypeUserModule.class))).willReturn(moduleOfTypeUserGetDto);

        List<ModuleOfTypeUserGetDto> moduleOfTypeUserGetDtoList = this.service.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser,idStatus);

        assertNotNull(moduleOfTypeUserGetDtoList);
        assertFalse(moduleOfTypeUserGetDtoList.isEmpty());

        then(this.mapper).should(times(1)).getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser,idStatus);
        then(this.dtoMapper).should(times(1)).tTypeUserModuleToModuleOfTypeUserGetDto(any(TtypeUserModule.class));
     }

    /**
     * Prueba unitaria para el método getModuleDistinctByIdTypeUserAndIdStatus() del servicio TypeUserModule cuando se proporcionan parámetros no válidos.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleDistinctByIdTypeUserAndIdStatus() con parámetros no válidos (idTypeUser igual a -2), se lance una excepción del tipo NoDataFoundException.</li>
     *   <li>Se verifica que el método del mapper y el método del dtoMapper no se hayan llamado en absoluto.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos distintos por ID de tipo de usuario y ID de estado con parámetros no válidos")
    void getModuleDistinctByIdTypeUserAndIdStatus_ParameterNotValid(){

         Integer idTypeUser = -2;
         Integer idStatus=1;

         assertThrows(NoDataFoundException.class,()-> this.service.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser, idStatus));

         then(this.mapper).shouldHaveNoInteractions();
         then(this.dtoMapper).shouldHaveNoInteractions();


     }

    /**
     * Prueba unitaria para el método getModuleDistinctByIdTypeUserAndIdStatus() del servicio TypeUserModule cuando el mapper retorna null.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleDistinctByIdTypeUserAndIdStatus() con parámetros válidos, y el mapper retorna null, se lance una excepción del tipo NoDataFoundException.</li>
     *   <li>Se verifica que el método del mapper se haya llamado una vez y que el método del dtoMapper no se haya llamado en absoluto.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos distintos por ID de tipo de usuario y ID de estado con retorno nulo del mapper")
    void getModuleDistinctByIdTypeUserAndIdStatus_MapperReturnNull(){

         Integer idTypeUser = 2;
         Integer idStatus = 1;

         given(this.mapper.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser,idStatus)).willReturn(null);

         assertThrows(NoDataFoundException.class,()-> this.service.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser, idStatus));

         then(this.mapper).should(times(1)).getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser,idStatus);
         then(this.dtoMapper).shouldHaveNoInteractions();
     }

    /**
     * Prueba unitaria para el método getModuleDistinctByIdTypeUserAndIdStatus() del servicio TypeUserModule cuando el mapper retorna una lista vacía.
     *
     * <p>Se simula el comportamiento esperado:</p>
     * <ul>
     *   <li>Se verifica que al llamar al método getModuleDistinctByIdTypeUserAndIdStatus() con parámetros válidos, y el mapper retorna una lista vacía, se lance una excepción del tipo NoDataFoundException.</li>
     *   <li>Se verifica que el método del mapper se haya llamado una vez y que el método del dtoMapper no se haya llamado en absoluto.</li>
     * </ul>
     */
    @Test
    @DisplayName("Prueba de obtención de módulos distintos por ID de tipo de usuario y ID de estado con lista vacía del mapper")
    void getModuleDistinctByIdTypeUserAndIdStatus_MapperReturnEmptyList(){
         Integer idTypeUser = 2;
         Integer idStatus = 1;

         given(this.mapper.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser,idStatus)).willReturn(Collections.emptyList());

         assertThrows(NoDataFoundException.class,()-> this.service.getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser, idStatus));

         then(this.mapper).should(times(1)).getModuleDistinctByIdTypeUserAndIdStatus(idTypeUser,idStatus);
         then(this.dtoMapper).shouldHaveNoInteractions();
     }

}