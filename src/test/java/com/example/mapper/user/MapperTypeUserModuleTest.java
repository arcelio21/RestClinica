package com.example.mapper.user;

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

import com.example.entity.modules.TmodulePrivilege;
import com.example.entity.user.TtypeUser;
import com.example.entity.user.TtypeUserModule;
import com.example.mapper.modules.MapperModulePrivilege;

@DisplayName("ACCEDER Y/O MODIFICAR DATOS QUE SE ENCUENTRAN EN"
		+ " LA ENTIDAD QUE GUARDA LOS PRIVILEGIOS A MODULOS DE CADA TIPO DE USUARIO")
@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperTypeUserModuleTest {

	@Autowired
	private MapperTypeUserModule mapperTypeUserModule;
	
	@Autowired
	private MapperModulePrivilege mapperModulePrivilege;
	
	@Autowired
	private MapperTypeUser mapperTypeUser;
	
	@Test
	void getAll() {
		
		List<TtypeUserModule> typeUserModules=this.mapperTypeUserModule.getAll();
		assertNotNull(typeUserModules);
		assertTrue(typeUserModules.size()>0);
		assertNotNull(typeUserModules.get(0).getTypeUser());
		assertNotNull(typeUserModules.get(0).getModulePrivilegeId());
		typeUserModules.forEach(System.out::println);
	}
	
	@Test
	void getPrivTypeUser() {
		List<TtypeUserModule> typeUserModules=this.mapperTypeUserModule.getPrivTypeUser(1);
		assertNotNull(typeUserModules);
		assertTrue(typeUserModules.size()>0);
		assertNotNull(typeUserModules.get(0).getTypeUser());
		assertNotNull(typeUserModules.get(0).getModulePrivilegeId());
		typeUserModules.forEach(System.out::println);
	}
	
	@Test
	void getModulePriv() {
		List<TtypeUserModule> typeUserModules=this.mapperTypeUserModule.getModulePriv(1);
		assertNotNull(typeUserModules);
		assertTrue(typeUserModules.size()>0);
		assertNotNull(typeUserModules.get(0).getTypeUser());
		assertNotNull(typeUserModules.get(0).getModulePrivilegeId());
		typeUserModules.forEach(System.out::println);
	}

	@Test
	void update() {
		TmodulePrivilege privilege=this.mapperModulePrivilege.getById(1);
		assertNotNull(privilege);
		
		TtypeUser ttypeUser=this.mapperTypeUser.getById(1);
		assertNotNull(ttypeUser);
		
		TtypeUserModule ttypeUserModule=new TtypeUserModule();
		ttypeUserModule.setModulePrivilegeId(privilege);
		ttypeUserModule.setTypeUser(ttypeUser);
		
		Integer idModuloPrivBef=1;
		Integer rowAffected=this.mapperTypeUserModule.update(ttypeUserModule, idModuloPrivBef);
		assertEquals(1, rowAffected);
	}
	@Test
	void save() {
		TmodulePrivilege privilege=this.mapperModulePrivilege.getById(1);
		assertNotNull(privilege);
		
		TtypeUser ttypeUser=this.mapperTypeUser.getById(1);
		assertNotNull(ttypeUser);
		
		TtypeUserModule ttypeUserModule=new TtypeUserModule();
		ttypeUserModule.setModulePrivilegeId(privilege);
		ttypeUserModule.setTypeUser(ttypeUser);
		
		Integer rowAffected=this.mapperTypeUserModule.save(ttypeUserModule);
		assertEquals(1, rowAffected);
	}
}
