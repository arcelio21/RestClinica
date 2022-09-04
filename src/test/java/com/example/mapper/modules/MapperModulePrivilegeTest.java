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
import com.example.entity.modules.TmodulePrivilege;
import com.example.entity.modules.Tprivilege;
import com.example.entity.status.Tstatus;
import com.example.mapper.status.MapperStatus;

@DisplayName("TmodulePrivilege: PARA ACCEDER Y MODIFICAR TODA A INFORMACION QUE HAY EN LA TABLA")
@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperModulePrivilegeTest {

	@Autowired
	private MapperModulePrivilege mapperModulePrivilege;
	
	@Autowired
	private MapperModules mapperModules;
	
	@Autowired
	private MapperPrivilege mapperPrivilege;
	
	@Autowired
	private MapperStatus mapperStatus;
	
	@Test
	void getAll() {
		List<TmodulePrivilege> modulPrivs=this.mapperModulePrivilege.getAll();
		assertNotNull(modulPrivs);
		assertTrue(modulPrivs.size()>0);
		assertNotNull(modulPrivs.get(0).getModule().getId());
		assertNotNull(modulPrivs.get(0).getPrivilege().getId());
		assertNotNull(modulPrivs.get(0).getStatus().getId());
		modulPrivs.forEach(System.out::println);
	}
	
	@Test
	void getById() {
		TmodulePrivilege tmodulePrivilege=this.mapperModulePrivilege.getById(1);
		assertNotNull(tmodulePrivilege);
		assertNotNull(tmodulePrivilege.getModule().getId());
		assertNotNull(tmodulePrivilege.getPrivilege().getId());
		assertNotNull(tmodulePrivilege.getStatus().getId());
		System.out.println(tmodulePrivilege);
	}
	
	@Test
	void update() {
		TmodulePrivilege tmodulePrivilege=this.mapperModulePrivilege.getById(1);
 		tmodulePrivilege.setModule(new Tmodule(1));
		
		Integer rowAffected=this.mapperModulePrivilege.update(tmodulePrivilege);
		assertEquals(1, rowAffected);
		
	}
	
	@DisplayName("BUSCAMOS LA INFORMACION DE ESTADO, PRIVILEGIOS, Y MODULOS PARA INSERTARLO EN ESTA TABLA")
	@Test
	void save() {
		TmodulePrivilege tmodulePrivilege= new TmodulePrivilege();
		Tprivilege tprivilege=this.mapperPrivilege.getByid(1);
		assertNotNull(tprivilege);
		
		Tmodule tmodule=this.mapperModules.getById(1);
		assertNotNull(tmodule);
		
		Tstatus tstatus=this.mapperStatus.getById(1);
		assertNotNull(tstatus);
		
		tmodulePrivilege.setPrivilege(tprivilege);
		tmodulePrivilege.setModule(tmodule);
		tmodulePrivilege.setStatus(tstatus);
		
		Integer rowAffected=this.mapperModulePrivilege.save(tmodulePrivilege);
		assertEquals(1, rowAffected);
		
	}

}
