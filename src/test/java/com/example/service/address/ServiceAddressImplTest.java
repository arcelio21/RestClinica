package com.example.service.address;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressRequestDto;
import com.example.dtomapper.address.AddressMappper;
import com.example.entity.address.Taddress;
import com.example.entity.address.Tvillage;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.AddressNotUpdateException;
import com.example.mapper.address.MapperAddress;
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
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceAddressImplTest {

	@Mock
	private MapperAddress mapper;

	@Mock
	private AddressMappper dtoMapper;

	@InjectMocks
	private ServiceAddressImpl serviceAddress;

	@Test
	void testGetAllWithValidData() {
		// Datos de prueba
		Taddress taddress = new Taddress();
		List<Taddress> taddresses = new ArrayList<>();
		taddresses.add(taddress);

		// Mockear el comportamiento del mapper
		when(mapper.getAll()).thenReturn(taddresses);
		when(this.dtoMapper.taddressToAddressGetDto(taddress)).thenReturn(AddressGetDto.builder().build());

		// Llamar al método a probar
		List<AddressGetDto> result = serviceAddress.getAll();

		// Verificar el resultado
		assertNotNull(result);
		assertEquals(taddresses.size(), result.size());
		// Aquí puedes agregar más aserciones si hay otros datos que debes verificar

		// Verificar que se llamó al mapper y al dtoMapper
		//verify(dtoMapper, times(taddresses.size())).taddressToAddressGetDto(any(Taddress.class));
	}

	@Test
	void testGetAllWithEmptyData() {
		// Mockear el comportamiento del mapper
		when(mapper.getAll()).thenReturn(Collections.emptyList());

		// Llamar al método a probar y capturar la excepción
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getAll());
	}

	@Test
	void testGetAllWithNullData() {
		// Mockear el comportamiento del mapper
		when(mapper.getAll()).thenReturn(null);

		// Llamar al método a probar y capturar la excepción
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getAll());

	}


	//GET ID

	@Test
	void testGetByIdWithValidId() {
		// Datos de prueba
		Integer id = 1;
		Taddress taddress = new Taddress(/* datos del objeto Taddress */);

		// Mockear el comportamiento del mapper
		when(mapper.getById(id)).thenReturn(taddress);
		when(dtoMapper.taddressToAddressGetDto(taddress)).thenReturn(AddressGetDto.builder().build());

		// Llamar al método a probar
		AddressGetDto result = serviceAddress.getById(id);

		// Verificar el resultado
		assertNotNull(result);
	}

	@Test
	void testGetByIdWithInvalidId() {
		// Datos de prueba
		Integer id = null;

		// Llamar al método a probar y capturar la excepción
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getById(id));

		// Verificar que no se llamó al mapper y al dtoMapper
		verifyNoInteractions(mapper);
		verifyNoInteractions(dtoMapper);
	}

	@Test
	void testGetByIdWithNoDataFound() {
		// Datos de prueba
		Integer id = 1;

		// Mockear el comportamiento del mapper
		when(mapper.getById(id)).thenReturn(null);

		// Llamar al método a probar y capturar la excepción
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getById(id));

		// Verificar que no se llamó al mapper
		verifyNoInteractions(dtoMapper);
	}

	//UPDATE
	@Test
	void testUpdateWithValidData() {
		// Datos de prueba
		AddressRequestDto addressRequestDto = AddressRequestDto.builder()
				.id(1L)
				.villageId(1)
				.specificAddress("Hola")
				.build();

		Taddress taddress = new Taddress(1L);
		taddress.setSpecificAddress("Hola");
		taddress.setVillageId(new Tvillage(1));

		// Mockear el comportamiento del mapper
		when(mapper.update(any(Taddress.class))).thenReturn(1);
		when(dtoMapper.AddressRequestDtoToTaddress(addressRequestDto)).thenReturn(taddress);


		// Verificar el resultado
		assertEquals(1, serviceAddress.update(addressRequestDto));
	}

	@Test
	void testUpdateWithNullData() {
		// Datos de prueba
		AddressRequestDto addressRequestDto = null;

		// Llamar al método a probar y capturar la excepción
		assertThrows(AddressNotUpdateException.class, () -> serviceAddress.update(addressRequestDto));


		// Verificar que no se llamó al mapper ni al dtoMapper
		verifyNoInteractions(mapper);
		verifyNoInteractions(dtoMapper);
	}

	@Test
	void testUpdateWithMapperError() {
		AddressRequestDto addressRequestDto = AddressRequestDto.builder()
				.id(1L)
				.villageId(1)
				.specificAddress("Hola")
				.build();

		//HAY QUE HACER EXECPCION PARA SQL
		//when(mapper.update(any(Taddress.class))).thenThrow(SQLException.class);

		assertThrows(AddressNotUpdateException.class, () -> serviceAddress.update(addressRequestDto));
	}


}
