package com.example.service.address;

import com.example.dto.address.district.DistrictDto;
import com.example.dto.address.village.VillageDistrictDto;
import com.example.dto.address.village.VillageDto;
import com.example.dtomapper.address.DtoVillageMapper;
import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tvillage;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.village.VillageNotSaveException;
import com.example.exception.address.village.VillageNotUpdateException;
import com.example.mapper.address.MapperVillage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceVillageImplTest {


    @Mock
    private MapperVillage mapperVillage;
    @Mock
    private DtoVillageMapper dtoVillageMapper;

    @InjectMocks
    private ServiceVillageImpl serviceVillage;

    private VillageDto villageDtoValid;
    private VillageDto villageDtoNotValid;

    private VillageDistrictDto villageDistrictDtoValid;
    private VillageDistrictDto villageDistrictDtoNotValid;



    @BeforeEach
    void setUp() {
        villageDtoValid = VillageDto.builder()
                .id(1)
                .name("Las lomas")
                .districtId(1)
                .build();

        villageDtoNotValid = VillageDto.builder()
                .id(0)
                .name("  ")
                .districtId(null)
                .build();

        villageDistrictDtoValid = VillageDistrictDto.builder()
                .id(1)
                .name("Las lomas")
                .district(DistrictDto.builder()
                        .id(1)
                        .name("David")
                        .provinceId(1)
                        .build())
                .build();

        villageDistrictDtoNotValid = VillageDistrictDto.builder()
                .id(0)
                .name("  ")
                .district(null)
                .build();
    }

    /**
     * Prueba unitaria para el método getAll() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que existen village disponibles.
     * Se espera que el método devuelva una lista de objetos VillageDto.
     */
    @Test
    void getAll_WithAvailableVillages_ReturnsListOfVillageDtos() {
        // Arrange
        List<Tvillage> villagesList = new ArrayList<>();
        villagesList.add(new Tvillage(1, "Las lomas", new Tdistrict(1)));

        when(mapperVillage.getAll()).thenReturn(villagesList);
        when(dtoVillageMapper.tvillageToVillageDto(any(Tvillage.class))).thenReturn(this.villageDtoValid);

        // Act
        List<VillageDto> result = serviceVillage.getAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals(villageDtoValid, result.get(0));
    }

    /**
     * Prueba unitaria para el método getAll() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que no existen Village disponibles.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getAll_WithNoAvailableVillages_ThrowsNoDataFoundException() {
        // Arrange
        when(mapperVillage.getAll()).thenReturn(null);

        // Act and Assert
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getAll());
    }

    /**
     * Prueba unitaria para el método getAll() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que la lista de pueblos disponibles está vacía.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getAll_WithEmptyVillagesList_ThrowsNoDataFoundException() {
        // Arrange
        when(mapperVillage.getAll()).thenReturn(Collections.emptyList());

        // Act and Assert
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getAll());
    }

    //GET BY ID


    /**
     * Prueba unitaria para el método getById() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se encuentra un pueblo para el identificador especificado.
     * Se espera que el método devuelva un objeto VillageDto.
     */
    @Test
    void getById_WithValidId_ReturnsVillageDto() {
        // Arrange
        int id = 1;
        Tvillage village = new Tvillage(id, "Las lomas", new Tdistrict(1));
        when(mapperVillage.getById(id)).thenReturn(village);
        when(dtoVillageMapper.tvillageToVillageDto(village)).thenReturn(this.villageDtoValid);

        // Act
        VillageDto result = serviceVillage.getById(id);

        // Assert
        assertEquals(villageDtoValid, result);
    }

    /**
     * Prueba unitaria para el método getById() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que no se encuentra ningún pueblo para el identificador especificado.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getById_WithInvalidId_ThrowsNoDataFoundException() {
        // Arrange
        int id = 0;

        // Act and Assert
        // Se espera que al llamar al método getById() con un identificador inválido se lance una excepción del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getById(id));
    }

    //UPDATE

    /**
     * Prueba unitaria para el método update() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se actualiza un pueblo existente con datos válidos.
     * Se espera que el método devuelva el identificador del pueblo actualizado.
     */
    @Test
    void update_WithValidData_ReturnsUpdatedVillageId() {


        Tvillage updatedVillage = new Tvillage(1, "Las lomas", new Tdistrict(1));
        when(dtoVillageMapper.villageDtoToTvillage(this.villageDtoValid)).thenReturn(updatedVillage);
        when(mapperVillage.update(updatedVillage)).thenReturn(1);

        // Act
        Integer result = serviceVillage.update(this.villageDtoValid);

        // Assert
        assertEquals(1, result);
    }

    /**
     * Prueba unitaria para el método update() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se intenta actualizar un pueblo con datos inválidos.
     * Se espera que se lance una excepción del tipo VillageNotUpdateException.
     */
    @Test
    void update_WithInvalidData_ThrowsVillageNotUpdateException() {


        // Act and Assert
        // Se espera que al llamar al método update() con datos inválidos se lance una excepción del tipo VillageNotUpdateException
        assertThrows(VillageNotUpdateException.class, () -> serviceVillage.update(this.villageDtoNotValid));
    }

    //SAVE

    /**
     * Prueba unitaria para el método save() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se guarda un nuevo pueblo con datos válidos.
     * Se espera que el método devuelva la cantidad de registros guardados.
     */
    @Test
    void save_WithValidData_ReturnsNumberOfSavedRecords() {


        Tvillage savedVillage = new Tvillage(1, "Las lomas", new Tdistrict(1));
        when(dtoVillageMapper.villageDtoToTvillage(this.villageDtoValid)).thenReturn(savedVillage);
        when(mapperVillage.save(savedVillage)).thenReturn(1);

        // Act
        Integer result = serviceVillage.save(this.villageDtoValid);

        // Assert
        assertEquals(1, result);
    }

    /**
     * Prueba unitaria para el método save() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se intenta guardar un nuevo pueblo con datos inválidos.
     * Se espera que se lance una excepción del tipo VillageNotSaveException.
     */
    @Test
    void save_WithInvalidData_ThrowsVillageNotSaveException() {

        // Act and Assert
        // Se espera que al llamar al método save() con datos inválidos se lance una excepción del tipo VillageNotSaveException
        assertThrows(VillageNotSaveException.class, () -> serviceVillage.save(this.villageDtoNotValid));
    }

    //GETBYDISTRICTID

    /**
     * Prueba unitaria para el método getByDistrictId() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se obtiene una lista de pueblos por el identificador del distrito,
     * y se encuentran pueblos asociados al distrito.
     * Se espera que el método devuelva una lista de objetos VillageDto.
     */
    @Test
    void getByDistrictId_WithAvailableVillages_ReturnsListOfVillageDtos() {
        // Arrange
        Integer districtId = 1;
        List<Tvillage> villagesList = new ArrayList<>();
        villagesList.add(new Tvillage(1, "Las lomas", new Tdistrict(districtId)));

        when(mapperVillage.getByDistrictId(any(Tdistrict.class))).thenReturn(villagesList);
        when(dtoVillageMapper.tvillageToVillageDto(any(Tvillage.class))).thenReturn(this.villageDtoValid);

        // Act
        List<VillageDto> result = serviceVillage.getByDistrictId(districtId);

        // Assert
        assertEquals(1, result.size());
        assertEquals(villageDtoValid, result.get(0));
    }

    /**
     * Prueba unitaria para el método getByDistrictId() del servicio ServiceVillageImpl.
     * Verifica el escenario donde el ID es nulo o igual menor que 0.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getByDistrictId_WithIdNotValid_ThrowsNoDataFoundException() {
        // Arrange
        Integer districtId = 0;


        // Act and Assert
        // Se espera que al llamar al método getByDistrictId() con ID 0  lance una excepción del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getByDistrictId(districtId));
    }

    /**
     * Prueba unitaria para el método getByDistrictId() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se obtiene una lista de pueblos por el identificador del distrito,
     * pero no se encuentran pueblos asociados al distrito.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getByDistrictId_WithNoAvailableVillages_ThrowsNoDataFoundException() {
        // Arrange
        Integer districtId = 1;

        when(mapperVillage.getByDistrictId(any(Tdistrict.class))).thenReturn(null);

        // Act and Assert
        // Se espera que al llamar al método getByDistrictId() sin pueblos asociados al distrito se lance una excepción del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getByDistrictId(districtId));
    }

    //GETDISTRICTALLBYID

    /**
     * Prueba unitaria para el método getDistrictAllById() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se obtiene un objeto VillageDistrictDto por su identificador,
     * y se encuentra un pueblo asociado al identificador.
     * Se espera que el método devuelva un objeto VillageDistrictDto con los datos correctos.
     */
    @Test
    void getDistrictAllById_WithAvailableVillage_ReturnsVillageDistrictDto() {
        // Arrange
        Integer villageId = 1;
        Tvillage village = new Tvillage(villageId, "Las lomas", new Tdistrict(1));

        when(mapperVillage.getDistrictAllById(villageId)).thenReturn(village);
        when(dtoVillageMapper.tvillageToVillageDistritcDto(village)).thenReturn(this.villageDistrictDtoValid);

        // Act
        VillageDistrictDto result = serviceVillage.getDistrictAllById(villageId);

        // Assert
        assertEquals(this.villageDistrictDtoValid, result);
    }

    /**
     * Prueba unitaria para el método getDistrictAllById() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se intenta obtener un objeto VillageDistrictDto por un identificador nulo.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getDistrictAllById_WithNullId_ThrowsNoDataFoundException() {
        // Arrange
        Integer villageId = null;

        // Act and Assert
        // Se espera que al llamar al método getDistrictAllById() con un identificador nulo se lance una excepción del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getDistrictAllById(villageId));
    }

    /**
     * Prueba unitaria para el método getDistrictAllById() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se intenta obtener un objeto VillageDistrictDto por un identificador de pueblo inexistente.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getDistrictAllById_WithNonexistentVillage_ThrowsNoDataFoundException() {
        // Arrange
        Integer villageId = 1;

        when(mapperVillage.getDistrictAllById(villageId)).thenReturn(null);

        // Act and Assert
        // Se espera que al llamar al método getDistrictAllById() con un identificador de pueblo inexistente se lance una excepción del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getDistrictAllById(villageId));
    }

    //GETALLIDNAME

    /**
     * Prueba unitaria para el método getAllIdName() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se obtiene una lista de VillageDto con id y nombre de los pueblos disponibles.
     * Se espera que el método devuelva una lista no vacía con los datos correctos.
     */
    @Test
    void getAllIdName_WithAvailableVillages_ReturnsListOfVillageDtos() {
        // Arrange
        List<Tvillage> villagesList = new ArrayList<>();
        villagesList.add(new Tvillage(1, "Las lomas", new Tdistrict(1)));

        when(mapperVillage.getAllIdName()).thenReturn(villagesList);
        when(dtoVillageMapper.tvillageToVillageDto(any(Tvillage.class))).thenReturn(this.villageDtoValid);

        // Act
        List<VillageDto> result = serviceVillage.getAllIdName();

        // Assert
        assertEquals(1, result.size());
        assertEquals(this.villageDtoValid, result.get(0));
    }

    /**
     * Prueba unitaria para el método getAllIdName() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que no se encuentran datos disponibles.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getAllIdName_WithNoAvailableVillages_ThrowsNoDataFoundException() {
        // Arrange
        when(mapperVillage.getAllIdName()).thenReturn(null);

        // Act and Assert
        // Se espera que al llamar al método getAllIdName() sin datos disponibles se lance una excepción del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getAllIdName());
    }

    /**
     * Prueba unitaria para el método getAllIdName() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se obtiene una lista vacía de pueblos disponibles.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getAllIdName_WithEmptyVillagesList_ThrowsNoDataFoundException() {
        // Arrange
        when(mapperVillage.getAllIdName()).thenReturn(Collections.emptyList());

        // Act and Assert
        // Se espera que al llamar al método getAllIdName() con una lista vacía de pueblos disponibles se lance una excepción del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getAllIdName());
    }


    //GETBYIDNAME

    /**
     * Prueba unitaria para el método getByIdName() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que se obtiene un objeto VillageDto por su identificador.
     * Se espera que el método devuelva un VillageDto con el id y el nombre correctos.
     */
    @Test
    void getByIdName_WithValidId_ReturnsVillageDto() {
        // Arrange
        int villageId = 1;
        Tvillage tvillage = new Tvillage(villageId, "Las lomas", new Tdistrict(1));

        when(mapperVillage.getByIdName(villageId)).thenReturn(tvillage);
        when(dtoVillageMapper.tvillageToVillageDto(tvillage)).thenReturn(this.villageDtoValid);

        // Act
        VillageDto result = serviceVillage.getByIdName(villageId);

        // Assert
        assertEquals(this.villageDtoValid, result);
    }

    /**
     * Prueba unitaria para el método getByIdName() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que no se encuentra ningún pueblo para el identificador especificado.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getByIdName_WithInvalidId_ThrowsNoDataFoundException() {
        // Arrange
        int villageId = 0;

        // Act and Assert
        // Se espera que al llamar al método getByIdName() con un id inválido se lance una excepción del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getByIdName(villageId));
    }

    /**
     * Prueba unitaria para el método getByIdName() del servicio ServiceVillageImpl.
     * Verifica el escenario en el que el mapper devuelve un valor nulo.
     * Se espera que se lance una excepción del tipo NoDataFoundException.
     */
    @Test
    void getByIdName_WithEmptyData_ThrowsNoDataFoundException() {
        // Arrange
        int villageId = 1;

        when(mapperVillage.getByIdName(villageId)).thenReturn(null);

        // Act and Assert
        // Se espera que al llamar al método getByIdName() con un objeto VillageDto que tenga datos vacíos se lance una excepción del tipo NoDataFoundException
        assertThrows(NoDataFoundException.class, () -> serviceVillage.getByIdName(villageId));
    }





}