package com.example.mapper.address;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.example.entity.address.Tprovince;
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
		districts.forEach(System.out::println);
        assertFalse(districts.isEmpty());
	}
	
	@Test
	void getById() {
		Tdistrict district=this.mapperDistrict.getById(1);
		System.out.println(district);
		assertNotNull(district);
	}
	

	@Test
  	void getByProvinceWithIdNotValid(){
		List<Tdistrict> districtProv=this.mapperDistrict.getByProvinceId(new Tprovince(23));
		assertNotNull(districtProv);
		assertTrue(districtProv.isEmpty());
	}

	@Test
	void save(){
		Tdistrict tdistrict = new Tdistrict(1);
		tdistrict.setName("Bocas");
		tdistrict.setProvince(new Tprovince(1));
		System.out.println(this.mapperDistrict.save(tdistrict));
	}

	@Test
	void saveNotValid(){
		Tdistrict tdistrict = new Tdistrict(1);
		tdistrict.setName("Bocas");
		tdistrict.setProvince(new Tprovince(67));
		System.out.println(this.mapperDistrict.save(tdistrict));
	}

	@Test
	void getByProvinceId(){

		List<Tdistrict> tdistricts = this.mapperDistrict.getByProvinceId(new Tprovince(4));
        assertFalse(tdistricts.isEmpty());

		for (Tdistrict tdistrict:
			  tdistricts) {

			System.out.println(tdistrict.getId() + " "+tdistrict.getName());
		}

	}

	

	
	

}
