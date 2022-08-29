package com.example.mapper.address;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.address.Tprovince;
import java.util.List;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MapperProvinceTest {

	
	@Autowired
	private MapperProvince provinceDAO;
	
	
	@Test
	void getAll() {
		//fail("Not yet implemented");
		List<Tprovince> provinces=provinceDAO.getAll();
		provinces.forEach(x->System.out.println(x));
		assertTrue(provinces.size()>0);
	}
	
	@Test
	void getById() {
		Tprovince chiriqui=this.provinceDAO.getById(4);
		System.out.println(chiriqui);
		assertTrue(chiriqui.getName().equals("Chiriqui"));
	}
	
	@Test
	void save() {
		Tprovince newProv=new Tprovince();
		newProv.setName("Kuna yala");
		Integer rowAffected=this.provinceDAO.save(newProv);
		System.out.println(rowAffected);
		assertTrue(rowAffected==1||rowAffected>0 );
	}
	
	@Test
	void update() {
		Tprovince updateProv=this.provinceDAO.getById(4);
		assertNotNull(updateProv);
		
		String newName="Cara";
		updateProv.setName(newName);
		Integer rowAffected=this.provinceDAO.update(updateProv);
		assertTrue(rowAffected==1);
	}
	
	/*
	 * @Test void getAllProvincesById() { List<Tprovince>
	 * provinces=this.provinceDAO.getAllProvincesById(1);
	 * provinces.forEach(x->System.out.println(x));
	 * assertTrue(provinces.get(1).getDistricts()instanceof List<Tdistrict>); }
	 */
	
	
	
}
