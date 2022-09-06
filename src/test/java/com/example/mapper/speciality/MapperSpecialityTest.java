package com.example.mapper.speciality;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.speciality.Tspeciality;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperSpecialityTest {

	@Autowired
	private  MapperSpeciality mapperSpeciality;
	
	@Test
	void getAll() {
		
		List<Tspeciality> specialties =this.mapperSpeciality.getAll();
		assertNotNull(specialties);
		assertTrue(specialties.size()>0);
		assertNotNull(specialties.get(0).getName());
		specialties.forEach(System.out::println);
	}
	
	@Test
	void getById() {
		Tspeciality tspeciality=this.mapperSpeciality.getById(1);
		assertNotNull(tspeciality);
		assertNotNull(tspeciality.getName());
		
		System.out.println(tspeciality);
	}
	
	@Test
	void update() {
		Tspeciality tspeciality=this.mapperSpeciality.getById(1);
		assertNotNull(tspeciality);
		
		tspeciality.setName("Patologia");
		Integer rowAffected=this.mapperSpeciality.update(tspeciality);
		assertEquals(1, rowAffected);
	}
	
	@Test
	void save() {
		Tspeciality tspeciality=new Tspeciality(null, "Patologia");
		
		Integer rowAffected=this.mapperSpeciality.save(tspeciality);
		assertEquals(1, rowAffected);
	}

}
