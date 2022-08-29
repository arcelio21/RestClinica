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

import com.example.entity.address.Tdistrict;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperDistrictTest {

	@Autowired
	private MapperDistrict mapperDistrict;
	
	@Test
	void getAll() {
		//fail("Not yet implemented");
		List<Tdistrict> districts=this.mapperDistrict.getAll();
		districts.forEach(x->System.out.println(x));
		assertTrue(districts.size()>0);
	}
	
	@Test
	void getById() {
		Tdistrict district=this.mapperDistrict.getById(1);
		System.out.println(district);
		assertNotNull(district);
	}
	
	/*
	 * @Test void getByProvince(){ List<Tdistrict>
	 * districtProv=this.mapperDistrict.getByProvince(1);
	 * districtProv.forEach(x->System.out.println(x.getName()));
	 * assertNotNull(districtProv); }
	 */
	
	@Test
	void getDistrictAllById() {
		Tdistrict distrito=this.mapperDistrict.getDistrictAllById(4);
		System.out.println(distrito.getName()+"provincia: "+distrito.getProvince().getName());
		assertNotNull(distrito);
	}
	
	

}
