package com.example.service.address;

import com.example.dto.address.district.DistrictAllDto;
import com.example.dto.address.district.DistrictDto;
import com.example.dto.address.province.ProvinceDto;
import com.example.dtomapper.address.DtoDistrictMapper;
import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.exception.NoDataFoundException;
import com.example.exception.address.district.DistrictNotSaveException;
import com.example.exception.address.district.DistrictNotUpdateException;
import com.example.mapper.address.MapperDistrict;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@MybatisTest
@ExtendWith(MockitoExtension.class) //ES MEJOR UTILIZAR LA ANOTACION
class ServiceDistrictImplTest {
	@Mock
	private MapperDistrict mapper;
	@Mock
	private DtoDistrictMapper dtoMapper;

	@InjectMocks
	private ServiceDistrictImpl service;
	@BeforeEach
	void setUp() {
		//MockitoAnnotations.openMocks(this); ES MEJOR NO UTILIZAR ESTE METODO
	}

	@Test
	void getAllWithData(){
		Tdistrict tdistrict1= new Tdistrict(1,"David", new Tprovince(4));
		Tdistrict tdistrict2 = new Tdistrict(2,"Las lomas", new Tprovince(4));
		List<Tdistrict> tdistricts = new ArrayList<>();
		tdistricts.add(tdistrict1);
		tdistricts.add(tdistrict2);

		//DEFINIENDO COMPORTAMIENTO
		when(this.mapper.getAll()).thenReturn(tdistricts);
		when(this.dtoMapper.tdistrictToDistrictDto(tdistrict1)).thenReturn(DistrictDto.builder()
						.id(1)
						.name("David")
						.provinceId(4)
				.build());

		when(this.dtoMapper.tdistrictToDistrictDto(tdistrict2)).thenReturn(DistrictDto.builder()
				.id(2)
				.name("Las lomas")
				.provinceId(4)
				.build());

		//EJECUTAR METODOS
		List<DistrictDto> districtDtos = this.service.getAll();

		assertNotNull(districtDtos);
		assertFalse(districtDtos.isEmpty());

		//VERIFICAR RESULTADO
		verify(this.mapper,times(1)).getAll();
		verify(this.dtoMapper,times(1)).tdistrictToDistrictDto(tdistrict1);
		verify(this.dtoMapper,times(1)).tdistrictToDistrictDto(tdistrict2);


	}

	@Test
	void getAllWithOutData(){
		List<Tdistrict> tdistrictList = new ArrayList<>();
		when(this.mapper.getAll()).thenReturn(tdistrictList);

		assertThrows(NoDataFoundException.class,() -> this.service.getAll());
	}

	@Test
	void getByIdWithIdValidAndDistrictExits(){

		Tdistrict tdistrict = new Tdistrict(1,"Bocas",new Tprovince(1));
		Integer id = 1;
		when(this.mapper.getById(id)).thenReturn(tdistrict);
		when(this.dtoMapper.tdistrictToDistrictDto(tdistrict)).thenReturn(DistrictDto.builder()
						.id(1)
						.name("Bocas")
						.provinceId(1)
				.build());

		DistrictDto districtDto = this.service.getById(1);

		assertNotNull(districtDto);

	}

	@Test
	void getByIdWithIdValidAndDistrictNotExits(){
		Integer id = 1;
		when(this.mapper.getById(id)).thenReturn(null);

		assertThrows(NoDataFoundException.class,()-> this.service.getById(id));
	}

	@Test
	void getByIdWithIdNotValidAndDistrictNotExits(){
		Tdistrict tdistrict = new Tdistrict(1,"Bocas",new Tprovince(1));
		Integer id = null;

		assertThrows(NoDataFoundException.class,()-> this.service.getById(id));
	}


	/**
	 * PROBARA SI LA VALIDACION DE DATOS NULOS O VACIOS FUNCIONA
	 */
	@Test
	void updateDistrictNotValid(){
		DistrictDto districtDto = DistrictDto.builder()
				.id(1)
				.name("  ")
				.provinceId(null)
				.build();


		assertThrows(DistrictNotUpdateException.class,()->this.service.update(districtDto));
	}

	/**
	 * PROBARA SU AL DEVOLVER UN ERROR EN LA CONVERSION DE OBJETOS LANZARA EXCEPCION
	 */
	@Test
	void updateDistrictConverterNotValid(){
		DistrictDto districtDto = DistrictDto.builder()
				.id(1)
				.name("Bocas")
				.provinceId(1)
				.build();

		when(this.dtoMapper.districtDtoToTdistrict(districtDto)).thenReturn(null);

		assertThrows(DistrictNotUpdateException.class,()->this.service.update(districtDto));
	}

	@Test
	void updateDistrictValid(){
		DistrictDto districtDto = DistrictDto.builder()
				.id(1)
				.name("Bocas")
				.provinceId(1)
				.build();

		Tdistrict tdistrict = new Tdistrict(1,"Bocas",new Tprovince(1));

		when(this.dtoMapper.districtDtoToTdistrict(districtDto)).thenReturn(tdistrict);
		when(this.mapper.update(tdistrict)).thenReturn(1);

		assertEquals(1,this.service.update(districtDto));
	}

	/**
	 * PROBARA QUE DEVUELVA UNA EXCEPCION SI LOS DATOS NO SON VALIDOS
	 */
	@Test
	void saveDistrictNotValid(){
		DistrictDto districtDto = DistrictDto.builder()
				.name("  ")
				.provinceId(1)
				.build();
		assertThrows(DistrictNotSaveException.class,()->this.service.save(districtDto));
	}


	/**
	 * PROBARA QUE UNA CONVERSION ERRONEA DEVUELVA UNA EXCEPCION
	 */
	@Test
	void saveDistrictConverterNotValid(){
		DistrictDto districtDto = DistrictDto.builder()
				.name("David")
				.provinceId(1)
				.build();
		when(this.dtoMapper.districtDtoToTdistrict(districtDto)).thenReturn(null);

		assertThrows(DistrictNotSaveException.class,()->this.service.save(districtDto));
	}

	/**
	 * PROBARA QUE DEVUELVA EL VALOR ESPARADO SI SE GUARDA CORRECTAMENTE
	 */
	@Test
	void saveDistrictValid(){
		DistrictDto districtDto = DistrictDto.builder()
				.id(1)
				.name("Bocas")
				.provinceId(1)
				.build();

		Tdistrict tdistrict = new Tdistrict(1,"Bocas",new Tprovince(1));

		when(this.dtoMapper.districtDtoToTdistrict(districtDto)).thenReturn(tdistrict);
		when(this.mapper.save(tdistrict)).thenReturn(1);

		assertEquals(1,this.service.save(districtDto));
	}


	/**
	 * PROBARA SI EL METODO FUNCIONA CORRECTAMENTE TENIENDO DATOS DISPONIBLES
	 */
	@Test
	void getAllIdNameWithData(){
		Tdistrict tdistrict1= new Tdistrict(1);
		tdistrict1.setName("David");
		Tdistrict tdistrict2 = new Tdistrict(2);
		tdistrict2.setName("Las lomas");

		List<Tdistrict> tdistricts = new ArrayList<>();
		tdistricts.add(tdistrict1);
		tdistricts.add(tdistrict2);

		//DEFINIENDO COMPORTAMIENTO
		when(this.mapper.getAllIdName()).thenReturn(tdistricts);
		when(this.dtoMapper.tdistrictToDistrictDto(tdistrict1)).thenReturn(DistrictDto.builder()
				.id(1)
				.name("David")
				.build());

		when(this.dtoMapper.tdistrictToDistrictDto(tdistrict2)).thenReturn(DistrictDto.builder()
				.id(2)
				.name("Las lomas")
				.build());

		//EJECUTAR METODOS
		List<DistrictDto> districtDtos = this.service.getAllIdName();

		assertNotNull(districtDtos);
		assertFalse(districtDtos.isEmpty());

	}

	/**
	 * PROBARA SI EL METODO DEVUELVE UNA EXCEPCION SI NO TIENE DATOS
	 */
	@Test
	void getAllIdNameWithOutData(){
		List<Tdistrict> tdistrictList = new ArrayList<>();
		when(this.mapper.getAllIdName()).thenReturn(tdistrictList);

		assertThrows(NoDataFoundException.class,() -> this.service.getAllIdName());
	}


	@Test
	void getByIdNameWithIdValidAndDistrictExits(){

		Tdistrict tdistrict = new Tdistrict(1,"Bocas",new Tprovince(1));
		Integer id = 1;
		when(this.mapper.getByIdName(id)).thenReturn(tdistrict);
		when(this.dtoMapper.tdistrictToDistrictDto(tdistrict)).thenReturn(DistrictDto.builder()
				.id(1)
				.name("Bocas")
				.provinceId(1)
				.build());

		DistrictDto districtDto = this.service.getByIdName(1);

		assertNotNull(districtDto);

	}

	@Test
	void getByIdNameWithIdValidAndDistrictNotExits(){
		Integer id = 1;
		when(this.mapper.getByIdName(id)).thenReturn(null);

		assertThrows(NoDataFoundException.class,()-> this.service.getByIdName(id));
	}

	@Test
	void getByIdNameWithIdNoValidA(){
		Integer id = null;

		assertThrows(NoDataFoundException.class,()-> this.service.getByIdName(id));
	}


	/**
	 * PROBARA SI LANZA EXCEPCION AL ENVIAR ID NO VALIDO
	 */
	@Test
	void getByProvinceIdWhenIdNotValid(){
		Integer id=0;

		assertThrows(NoDataFoundException.class,()-> this.service.getByProvinceId(id));
	}

	/**
	 * PROBARA SI LANZA UNA EXCEPCION SI NO HAY DISTRITO REGISTRADO CON EL ID DE PROVINCIA ENVIADO
	 */
	@Test
	void getByProvinceIdWhenIdProvinceNotExits(){

		Integer id=0;

		assertThrows(NoDataFoundException.class,()-> this.service.getByProvinceId(id));
	}

	@Test
	void getByProvinceIdWhenDataValid(){

		Integer id=4;

		Tdistrict tdistrict1= new Tdistrict(1);
		tdistrict1.setName("David");

		Tdistrict tdistrict2 = new Tdistrict(2);
		tdistrict2.setName("Las lomas");

		List<Tdistrict> tdistricts = new ArrayList<>();
		tdistricts.add(tdistrict1);
		tdistricts.add(tdistrict2);

		//DEFINIENDO COMPORTAMIENTO
		when(this.mapper.getByProvinceId(any(Tprovince.class))).thenReturn(tdistricts); //PARA QUE ACEPTE CUALQUIR INSTANCIA DE UN OBJETO EN UN METODO
		when(this.dtoMapper.tdistrictToDistrictDto(tdistrict1)).thenReturn(DistrictDto.builder()
				.id(1)
				.name("David")
				.build());

		when(this.dtoMapper.tdistrictToDistrictDto(tdistrict2)).thenReturn(DistrictDto.builder()
				.id(2)
				.name("Las lomas")
				.build());

		//EJECUTAR METODOS
		List<DistrictDto> districtDtos = this.service.getByProvinceId(id);

		assertNotNull(districtDtos);
		assertFalse(districtDtos.isEmpty());
	}

	/**
	 * VALIDA QUE DEVUELVA UNA EXCEPCION SI NO EXISTE DISTRITOS ASOCIADOS AL DI DE PROVINCIA
	 */
	@Test
	void getByProvinceIdWhenIdProvinceValidAndNotExistDistrict(){

		Integer id=1;

		when(this.mapper.getByProvinceId(any(Tprovince.class))).thenReturn(new ArrayList<>());

		assertThrows(NoDataFoundException.class,()-> this.service.getByProvinceId(id));
	}

	@Test
	void getDistrictAndProvinceById_When_IdNotValid(){
		Integer id=0;

		assertThrows(NoDataFoundException.class, ()-> this.service.getDistrictAndProvinceById(id));
	}

	@Test
	void getDistrictAndProvinceById_When_IdValid_And_NotExistData(){
		Integer id=1;

		when(this.mapper.getDistrictAndProvinceById(id)).thenReturn(null);
		assertThrows(NoDataFoundException.class, ()-> this.service.getDistrictAndProvinceById(id));
	}

	@Test
	void getDistrictAndProvinceById_When_IdValid_And_DataValid_But_Converter_NoValid(){

		Integer id=1;
		Tdistrict tdistrict = new Tdistrict(id);
		when(this.mapper.getDistrictAndProvinceById(id)).thenReturn(tdistrict);
		when(this.dtoMapper.tdistrictToDistrictAll(tdistrict)).thenReturn(null);
		assertThrows(NoDataFoundException.class, ()-> this.service.getDistrictAndProvinceById(id));
	}

	@Test
	void getDistrictAndProvinceById_When_IdValid_And_DataValid(){
		Integer id=1;
		Tdistrict tdistrict = new Tdistrict(id);
		tdistrict.setName("David");
		tdistrict.setProvince(new Tprovince(4, "Chiriqui"));

		when(this.mapper.getDistrictAndProvinceById(id)).thenReturn(tdistrict);
		when(this.dtoMapper.tdistrictToDistrictAll(tdistrict)).thenReturn(DistrictAllDto.builder()
						.id(id)
						.name("David")
						.tprovince(ProvinceDto.builder()
								.id(4)
								.name("Chiriqui")
								.build())
				.build());

		assertNotNull(this.service.getDistrictAndProvinceById(id));

	}





}
