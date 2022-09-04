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

import com.example.entity.modules.Tprivilege;

@DisplayName("TPRIVILEGE: TEST DE ACCESO A REGISTRO")
@MybatisTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperPrivilegeTest {

	@Autowired
	private MapperPrivilege mapperPrivilege;
	
	@Test
	void getAll() {
		List<Tprivilege> privileges=this.mapperPrivilege.getAll();
		assertNotNull(privileges);
		assertTrue(privileges.size()>0);
		assertNotNull(privileges.get(0).getNamePrivilege());
		privileges.forEach(System.out::println);
		
	}
	
	@Test
	void getById() {
		Tprivilege tprivilege=this.mapperPrivilege.getByid(1);
		assertNotNull(tprivilege);
		assertNotNull(tprivilege.getNamePrivilege());
		System.out.println(tprivilege);
	}

	@Test
	void update() {
		Tprivilege priv=this.mapperPrivilege.getByid(1);
		assertNotNull(priv);
		assertNotNull(priv.getNamePrivilege());
		priv.setNamePrivilege("LOAD");
		
		Integer rowAffected=this.mapperPrivilege.update(priv);
		assertEquals(1, rowAffected);
		
	}
	
	@Test
	void insert() {
		Tprivilege tprivilege=new Tprivilege();
		tprivilege.setNamePrivilege("LOAD");
		Integer rowAffected =this.mapperPrivilege.save(tprivilege);
		assertEquals(1, rowAffected);
	}
}
