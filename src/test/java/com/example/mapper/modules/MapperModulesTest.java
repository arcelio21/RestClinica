package com.example.mapper.modules;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.modules.Tmodule;

@DisplayName("PARA ACCEDER, MODIFICAR E INGRESAR GEISTRO A LA TABLA TMODULE")
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ExtendWith(SpringExtension.class)
class MapperModulesTest {

	@Autowired
	private MapperModules mapperModules;
	

	
	@Test
	void getAll() {
		List<Tmodule> modules=this.mapperModules.getAll();
		assertNotNull(modules); //AL MOMENTO DE HACER EL TEST, LA TABLA NO TENIA REGISTRO
		assertTrue(modules.size()>0);
		assertNotNull(modules.get(0).getNameModule());
		modules.forEach(System.out::println);
	}
	
	@Test
	void getById(){
		//AL MOMENTO DE HACER EL TEST, LA TABLA NO TENIA REGISTRO
		Tmodule module=this.mapperModules.getById(1L);
		assertNotNull(module);
		assertNotNull(module.getNameModule());
		System.out.println(module);
	}
	
	@Test
	void update() {
		Tmodule module=this.mapperModules.getById(1L);
		assertNotNull(module);
		module.setNameModule("Login");
		Integer rowAffected=this.mapperModules.update(module);
		assertEquals(1, rowAffected);
		
	}
	
	@Test
	void insert() {
		Tmodule module=new Tmodule();
		module.setNameModule("Login");
		Integer rowAffected=this.mapperModules.insert(module);
		assertEquals(1, rowAffected);
	}

}
