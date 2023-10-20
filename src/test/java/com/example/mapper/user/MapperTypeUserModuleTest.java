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

import com.example.entity.user.TtypeUserModule;

@DisplayName("ACCEDER Y/O MODIFICAR DATOS QUE SE ENCUENTRAN EN"
		+ " LA ENTIDAD QUE GUARDA LOS PRIVILEGIOS A MODULOS DE CADA TIPO DE USUARIO")
@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperTypeUserModuleTest {

	@Autowired
	private MapperTypeUserModule mapperTypeUserModule;

	
	@Test
	void getAll() {
		
		List<TtypeUserModule> typeUserModules=this.mapperTypeUserModule.getAll();
		assertNotNull(typeUserModules);
		assertTrue(typeUserModules.size()>0);
		//assertNotNull(typeUserModules.get(0).getTypeUser());
		//assertNotNull(typeUserModules.get(0).getModulePrivilegeId());
		typeUserModules.forEach((data)->{
			StringBuilder sb = new StringBuilder();
			sb.append(data.getId()).append(" | ");
			sb.append(data.getModulePrivilegeId().getModule().getNameModule()).append(" | ");
			sb.append(data.getModulePrivilegeId().getPrivilege().getNamePrivilege()).append(" | ");
			sb.append(data.getTypeUser().getNameTypeUser()).append(" |");
			System.out.println(sb);
		});
	}

	@Test
	void getTypeUserDistinctByIdModuleAndIdStatus(){
		Long idModule = 3L;
		Integer idStatus = 1;
		List<TtypeUserModule> ttypeUserModules = this.mapperTypeUserModule.getTypeUserDistinctByIdModuleAndIdStatus(idModule,idStatus);

		assertNotNull(ttypeUserModules);

		ttypeUserModules
				.forEach((data)-> System.out.println(data.getTypeUser().getNameTypeUser()+" | "+data.getTypeUser().getId()));
	}

	@Test
	void getTypeUserDistinctByIdModuleAndStatusActivated(){
		Long idModule = 3L;
		List<TtypeUserModule> ttypeUserModules = this.mapperTypeUserModule.getTypeUserDistinctByIdModuleAndStatusActivated(idModule);

		assertNotNull(ttypeUserModules);

		ttypeUserModules
				.forEach((data)-> System.out.println(data.getTypeUser().getNameTypeUser()+" | "+data.getTypeUser().getId()));
	}

}
