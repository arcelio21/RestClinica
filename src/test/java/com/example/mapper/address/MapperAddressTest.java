package com.example.mapper.address;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.address.Taddress;

@MybatisTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperAddressTest {

	@Autowired
	private MapperAddress addressDao;

	@Test
	void getAll() {
		List<Taddress> allAddress=this.addressDao.getAll();
		assertNotNull(allAddress);
		assertTrue(allAddress.size()>0);
		
	}
	
	@Test
	void getById() {
		/*Taddress address=this.addressDao.getById(1);
		assertNotNull(address);
		System.out.println(address);*/
	}
	
	@Test
	void getVillageAllById() {
		/*Taddress add=this.addressDao.getVillageAllById(1);
		assertNotNull(add);
		System.out.println(add);*/
	}

}
