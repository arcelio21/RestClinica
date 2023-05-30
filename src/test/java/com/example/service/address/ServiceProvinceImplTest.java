package com.example.service.address;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import com.example.dto.address.province.ProvinceDto;
import com.example.dtomapper.address.DtoProvinceMapper;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.province.ProvinceNotSaveException;
import com.example.exception.address.province.ProvinceNotUpdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.entity.address.Tvillage;
import com.example.mapper.address.MapperProvince;

@ExtendWith(SpringExtension.class)
class ServiceProvinceImplTest {


	@Mock
	private MapperProvince mapperProvince;
	@Mock
	private DtoProvinceMapper dtoProvinceMapper;
	@InjectMocks
	private ServiceProvinceImpl serviceProvinceImpl;

	private ProvinceDto provinceDtoValid;
	private ProvinceDto provinceDtoNOtValid;

	private Tprovince tprovinceValid;


	
	@BeforeEach
	void setUp() {

		provinceDtoValid = ProvinceDto.builder()
				.id(1)
				.name("Chiriqui")
				.build();

		provinceDtoNOtValid = ProvinceDto.builder()
				.id(1)
				.name(" ")
				.build();

		tprovinceValid = new Tprovince(1,"Chiriqui");
	}


	/**
	 * Prueba el método getAll cuando se tienen provincias en la base de datos.
	 * Debe devolver una lista de objetos ProvinceDto que representan todas las provincias encontradas.
	 *
	 * @throws NoDataFoundException si no se encuentran provincias en la base de datos
	 */
	@Test
	void testGetAll_ProvincesExist_ReturnsProvinceDtoList() {
		List<Tprovince> tprovinceList = Collections.singletonList(new Tprovince());

		when(mapperProvince.getAll()).thenReturn(tprovinceList);
		when(dtoProvinceMapper.tprovinceToProvinceDto(any(Tprovince.class))).thenReturn(ProvinceDto.builder().build());

		// Act
		List<ProvinceDto> result = serviceProvinceImpl.getAll();

		// Assert
		assertFalse(result.isEmpty());
	}

	/**
	 * Prueba el método getAll cuando no se encuentran provincias en la base de datos.
	 * Debe lanzar una excepción NoDataFoundException.
	 *
	 * @throws NoDataFoundException si no se encuentran provincias en la base de datos
	 */
	@Test
	void testGetAll_NoProvincesExist_ThrowsNoDataFoundException() {

		when(mapperProvince.getAll()).thenReturn(null);

		// Act & Assert
		assertThrows(NoDataFoundException.class, () -> serviceProvinceImpl.getAll());
	}

	// Agrega más casos de prueba para cubrir otros escenarios, como cuando no se encuentran provincias en la base de datos.

	/**
	 * Prueba el método getById cuando se proporciona un ID de provincia válido.
	 * Debe devolver un objeto ProvinceDto que representa la provincia encontrada.
	 *
	 * @throws NoDataFoundException si no se encuentra ninguna provincia para el identificador especificado
	 */
	@Test
	void testGetById_ValidId_ReturnsProvinceDto() {

		Integer id = 1;

		when(mapperProvince.getById(id)).thenReturn(tprovinceValid);
		when(dtoProvinceMapper.tprovinceToProvinceDto(tprovinceValid)).thenReturn(provinceDtoValid);

		// Act
		ProvinceDto result = serviceProvinceImpl.getById(id);

		// Assert
		assertNotNull(result);
		assertEquals(provinceDtoValid, result);
	}

	/**
	 * Prueba el método getById cuando se proporciona un ID de provincia inválido.
	 * Debe lanzar una excepción NoDataFoundException.
	 *
	 * @throws NoDataFoundException si no se encuentra ninguna provincia para el identificador especificado
	 */
	@Test
	void testGetById_InvalidId_ThrowsNoDataFoundException() {

		Integer id = null;

		// Act & Assert
		assertThrows(NoDataFoundException.class, () -> serviceProvinceImpl.getById(id));
	}


	/**
	 * Prueba el método update cuando se proporciona una provincia válida.
	 * Debe devolver el identificador de la provincia actualizada.
	 *
	 * @throws ProvinceNotUpdateException si los datos de la provincia no son válidos para la actualización
	 */
	@Test
	void testUpdate_ValidProvince_ReturnsUpdatedProvinceId() {
		// Arrange
		when(dtoProvinceMapper.provinceDtoToTprovince(provinceDtoValid)).thenReturn(tprovinceValid);
		when(mapperProvince.update(tprovinceValid)).thenReturn(1);

		// Act
		Integer result = serviceProvinceImpl.update(provinceDtoValid);

		// Assert
		assertNotNull(result);
		assertEquals(1, result);
	}

	/**
	 * Prueba el método update cuando se proporciona una provincia inválida.
	 * Debe lanzar una excepción ProvinceNotUpdateException.
	 *
	 * @throws ProvinceNotUpdateException si los datos de la provincia no son válidos para la actualización
	 */
	@Test
	void testUpdate_InvalidProvince_ThrowsProvinceNotUpdateException() {

		// Act & Assert
		assertThrows(ProvinceNotUpdateException.class, () -> serviceProvinceImpl.update(this.provinceDtoNOtValid));
	}

	// Agrega más casos de prueba para cubrir otros escenarios, como cuando los datos de la provincia no son válidos para la actualización.

	/**
	 * Prueba el método save cuando se proporciona una provincia válida.
	 * Debe devolver la cantidad de registros guardados.
	 *
	 * @throws ProvinceNotSaveException si los datos no son válidos o no se puede guardar la provincia
	 */
	@Test
	void testSave_ValidProvince_ReturnsSavedRecordsCount() {
		// Arrange
		when(dtoProvinceMapper.provinceDtoToTprovince(provinceDtoValid)).thenReturn(tprovinceValid);
		when(mapperProvince.save(tprovinceValid)).thenReturn(1);

		// Act
		Integer result = serviceProvinceImpl.save(provinceDtoValid);

		// Assert
		assertNotNull(result);
		assertEquals(1, result);
	}

	/**
	 * Prueba el método save cuando se proporciona una provincia inválida.
	 * Debe lanzar una excepción ProvinceNotSaveException.
	 *
	 * @throws ProvinceNotSaveException si los datos no son válidos o no se puede guardar la provincia
	 */
	@Test
	void testSave_InvalidProvince_ThrowsProvinceNotSaveException() {

		// Act & Assert
		assertThrows(ProvinceNotSaveException.class, () -> serviceProvinceImpl.save(this.provinceDtoNOtValid));
	}

}
