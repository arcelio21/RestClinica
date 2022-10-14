package com.example.mapper.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.user.TtypeUser;

@MybatisTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperTypeUserTest {

	@Autowired
	private MapperTypeUser typeUser;
	
	@Test
	void getAll() {
		//fail("Not yet implemented");
		Map<Integer,TtypeUser> typeUsers=this.typeUser.getAll();
		assertNotNull(typeUsers);
		assertTrue(typeUsers.size()>0);
		typeUsers.forEach((k,v)->System.out.println("ID: "+k+"| VALUE: "+v));
		
	}
	
	@Test
	void getById() {
		TtypeUser typeUser=this.typeUser.getById(11);
		assertNotNull(typeUser);
		assertNotNull(typeUser.getId());
		assertNotNull(typeUser.getNameTypeUser());
	} 
	
	@Test
	void update() {
		TtypeUser tuser=this.typeUser.getById(1);
		tuser.setNameTypeUser("hola");
		int rowAffected=this.typeUser.update(tuser);
		assertEquals(1,rowAffected);
	}
	
	@Test
	void save() {
		TtypeUser tuser=new TtypeUser();
		tuser.setNameTypeUser("INFORMATICO");
		int rowAffected=this.typeUser.save(tuser);
		assertEquals(1, rowAffected);
	}

}
