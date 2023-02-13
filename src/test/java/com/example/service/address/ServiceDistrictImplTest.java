package com.example.service.address;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.address.Tdistrict;
import com.example.mapper.address.MapperDistrict;

@MybatisTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ServiceDistrictImplTest {
	
	private ServiceDistrictImpl service;
	
	@Autowired
	private MapperDistrict mapper;

	@BeforeEach
	void setUp() throws Exception {
		
		//service = new ServiceDistrictImpl(mapper, );
	}

	@Test
	void getById() {
		/*Tdistrict tdistrict=service.getById(1);
		assertNotNull(tdistrict);
		assertNotNull(tdistrict.getProvince().getName());
		assertNotNull(tdistrict.getVillages());
		assertTrue(tdistrict.getVillages().size()>0);
		System.out.println(tdistrict);*/
		//System.out.println(tdistrict.getProvince().getDistricts().get(0).getId());
		//HACER CAMBIOS EN MAPPER PARA NO CREAR PROBLEMA N+1
	}

}
