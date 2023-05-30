package com.example.service.address;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressRequestDto;
import com.example.dtomapper.address.DtoAddressMappper;
import com.example.entity.address.Taddress;
import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.entity.address.Tvillage;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.address.AddressNotUpdateException;
import com.example.mapper.address.MapperAddress;
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
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceAddressImplTest {

	@Mock
	private MapperAddress mapper;

	@Mock
	private DtoAddressMappper dtoMapper;

	@InjectMocks
	private ServiceAddressImpl serviceAddress;

	private AddressRequestDto addressRequestDtoValid;

	private AddressRequestDto addressRequestDtoNotValid;

	@BeforeEach
	void setUp(){
		 addressRequestDtoValid = AddressRequestDto.builder()
				.id(1L)
				.villageId(1)
				.specificAddress("Hola")
				.build();

		 addressRequestDtoNotValid = AddressRequestDto.builder()
				 .id(null)
				 .villageId(null)
				 .specificAddress("  ")
				 .build();
	}

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


		Taddress taddress = new Taddress(1L);
		taddress.setSpecificAddress("Hola");
		taddress.setVillageId(new Tvillage(1));

		// Mockear el comportamiento del mapper
		when(mapper.update(any(Taddress.class))).thenReturn(1);
		when(dtoMapper.AddressRequestDtoToTaddress(this.addressRequestDtoValid)).thenReturn(taddress);


		// Verificar el resultado
		assertEquals(1, serviceAddress.update(this.addressRequestDtoValid));
	}

	@Test
	void testUpdateWithNullData() {

		// Llamar al método a probar y capturar la excepción
		assertThrows(AddressNotUpdateException.class, () -> serviceAddress.update(this.addressRequestDtoNotValid));


		// Verificar que no se llamó al mapper ni al dtoMapper
		verifyNoInteractions(mapper);
		verifyNoInteractions(dtoMapper);
	}

	@Test
	void testUpdateWithMapperError() {


		//TODO HAY QUE HACER EXECPCION PARA SQL
		//when(mapper.update(any(Taddress.class))).thenThrow(SQLException.class);

		assertThrows(AddressNotUpdateException.class, () -> serviceAddress.update(this.addressRequestDtoValid));
	}

	//SAVE

	/**
	 * Prueba el método save cuando se proporciona un AddressRequestDto válido.
	 * Debe devolver el ID obtenido después de guardar la dirección.
	 */
	@Test
	void testSave_ValidAddressRequestDto_ReturnsRowAffected() {

		when(dtoMapper.AddressRequestDtoToTaddress(this.addressRequestDtoValid)).thenReturn(new Taddress());
		when(mapper.save(any(Taddress.class))).thenReturn(1);

		// Act
		Integer result = serviceAddress.save(this.addressRequestDtoValid);

		// Assert
		assertEquals(1, result);
	}

	/**
	 * Prueba el método save cuando se proporciona un AddressRequestDto nulo.
	 * Debe lanzar una excepción AddressNotSaveException.
	 */
	@Test
	void testSave_NullAddressRequestDto_ThrowsAddressNotSaveException() {


		// Act & Assert
		assertThrows(AddressNotSaveException.class, () -> serviceAddress.save(this.addressRequestDtoNotValid));
	}


	//GETADDRESSBYVILLAGE

	/**
	 * Prueba el método getAddressByVillage cuando se proporciona un ID de Village válido.
	 * Debe devolver una lista de objetos AddressGetDto que representan las direcciones asociadas al pueblo.
	 * En este caso, se espera que la lista contenga elementos.
	 *
	 * @throws NoDataFoundException si no se encuentran direcciones para el pueblo especificado
	 */
	@Test
	void testGetAddressByVillage_ValidId_ReturnsAddressGetDtoList() {
		// Arrange
		Integer idVillage = 1;
		List<Taddress> taddressList = Collections.singletonList(new Taddress());

		when(mapper.getAddressByVillage(any(Tvillage.class))).thenReturn(taddressList);
		when(dtoMapper.taddressToAddressGetDto(any(Taddress.class))).thenReturn(AddressGetDto.builder().build());

		// Act
		List<AddressGetDto> result = serviceAddress.getAddressByVillage(idVillage);

		// Assert
		assertFalse(result.isEmpty());

	}

	/**
	 * Prueba el método getAddressByVillage cuando se proporciona un ID de pueblo inválido.
	 * Debe lanzar una excepción NoDataFoundException.
	 *
	 * @throws NoDataFoundException si no se encuentran direcciones para el pueblo especificado
	 */
	@Test
	void testGetAddressByVillage_InvalidId_ThrowsNoDataFoundException() {
		// Arrange
		Integer idVillage = null;

		// Act & Assert
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getAddressByVillage(idVillage));
	}

	/**
	 * Prueba el método getAddressByVillage cuando se proporciona un ID de village Valido y no existe direcciones.
	 * Debe lanzar una excepción NoDataFoundException.
	 *
	 * @throws NoDataFoundException si no se encuentran direcciones para la village especificada
	 */
	@Test
	void testGetAddressByVillage_ValidId_NotExistsAddress_ThrowsNoDataFoundException() {
		// Arrange
		Integer idVillage = 1;

		when(this.mapper.getAddressByVillage(any(Tvillage.class))).thenReturn(Collections.emptyList());

		// Act & Assert
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getAddressByVillage(idVillage));
	}

	//GETADDRESSBYDISTRICT

	/**
	 * Prueba el método getAddressByDistrict cuando se proporciona un ID de distrito válido.
	 * Debe devolver una lista de objetos AddressGetDto que representan las direcciones asociadas al distrito.
	 * En este caso, se espera que la lista contenga elementos.
	 *
	 * @throws NoDataFoundException si no se encuentran direcciones para el distrito especificado
	 */
	@Test
	void testGetAddressByDistrict_ValidId_ReturnsAddressGetDtoList() {
		// Arrange
		Integer idDistrict = 1;
		List<Taddress> taddressList = Collections.singletonList(new Taddress());

		when(mapper.getAddressByDistrict(any(Tdistrict.class))).thenReturn(taddressList);
		when(dtoMapper.taddressToAddressGetDto(any(Taddress.class))).thenReturn(AddressGetDto.builder().build());

		// Act
		List<AddressGetDto> result = serviceAddress.getAddressByDistrict(idDistrict);

		// Assert
		assertFalse(result.isEmpty());

	}

	/**
	 * Prueba el método getAddressByDistrict cuando se proporciona un ID de distrito inválido.
	 * Debe lanzar una excepción NoDataFoundException.
	 *
	 * @throws NoDataFoundException si no se encuentran direcciones para el distrito especificado
	 */
	@Test
	void testGetAddressByDistrict_InvalidId_ThrowsNoDataFoundException() {
		// Arrange
		Integer idDistrict = null;

		// Act & Assert
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getAddressByDistrict(idDistrict));
	}

	/**
	 * Prueba el método getAddressByDistrict cuando se proporciona un ID de district Valido y no existe direcciones.
	 * Debe lanzar una excepción NoDataFoundException.
	 *
	 * @throws NoDataFoundException si no se encuentran direcciones para la distrito especificada
	 */
	@Test
	void testGetAddressByDistrict_ValidId_NotExistsAddress_ThrowsNoDataFoundException() {
		// Arrange
		Integer idDistrict = 1;

		when(this.mapper.getAddressByDistrict(any(Tdistrict.class))).thenReturn(Collections.emptyList());

		// Act & Assert
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getAddressByDistrict(idDistrict));
	}


	//GETADDRESSBYPROVINCE

	/**
	 * Prueba el método getAddressByProvince cuando se proporciona un ID de provincia válido.
	 * Debe devolver una lista de objetos AddressGetDto que representan las direcciones asociadas a la provincia.
	 * En este caso, se espera que la lista contenga elementos.
	 *
	 * @throws NoDataFoundException si no se encuentran direcciones para la provincia especificada
	 */
	@Test
	void testGetAddressByProvince_ValidId_ReturnsAddressGetDtoList() {
		// Arrange
		Integer idProvince = 1;
		List<Taddress> taddressList = Collections.singletonList(new Taddress());

		when(mapper.getAddressByProvince(any(Tprovince.class))).thenReturn(taddressList);
		when(dtoMapper.taddressToAddressGetDto(any(Taddress.class))).thenReturn(AddressGetDto.builder().build());

		// Act
		List<AddressGetDto> result = serviceAddress.getAddressByProvince(idProvince);

		// Assert
		assertFalse(result.isEmpty());
	}

	/**
	 * Prueba el método getAddressByProvince cuando se proporciona un ID de provincia Valido y no existe direcciones.
	 * Debe lanzar una excepción NoDataFoundException.
	 *
	 * @throws NoDataFoundException si no se encuentran direcciones para la provincia especificada
	 */
	@Test
	void testGetAddressByProvince_ValidId_NotExistsAddress_ThrowsNoDataFoundException() {
		// Arrange
		Integer idProvince = 1;

		when(this.mapper.getAddressByProvince(any(Tprovince.class))).thenReturn(Collections.emptyList());

		// Act & Assert
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getAddressByProvince(idProvince));
	}

	/**
	 * Prueba el método getAddressByProvince cuando se proporciona un ID de provincia inválido.
	 * Debe lanzar una excepción NoDataFoundException.
	 *
	 * @throws NoDataFoundException si no se encuentran direcciones para la provincia especificada
	 */
	@Test
	void testGetAddressByProvince_InvalidId_ThrowsNoDataFoundException() {
		// Arrange
		Integer idProvince = null;

		// Act & Assert
		assertThrows(NoDataFoundException.class, () -> serviceAddress.getAddressByProvince(idProvince));
	}



}
