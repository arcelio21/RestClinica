package com.example.mapper.status;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.status.Tstatus;

@MybatisTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperStatusTest {

	@Autowired
	private MapperStatus status;
	
	@Test
	void getAll() {
		List<Tstatus> status=this.status.getAll();
		assertNotNull(status);
		assertNotNull(status.get(0).getName());
		assertTrue(status.size()>0);
		status.forEach(x->System.out.println(x));
		
	}
	
	@Test
	void getById() {
		Integer id=1;
		Tstatus status=this.status.getById(id);
		assertNotNull(status);
		assertNotNull(status.getName());
		System.out.println(status);
		
	}
	
	@Test
	void getByName() {
		List<Tstatus> status=this.status.getByName("a");
		assertNotNull(status);
		assertTrue(status.size()>0);
		status.forEach(System.out::println);
	}
	
	@Test
	void update() {
		Integer id=1;
		Tstatus status=new Tstatus();
		status.setId(id);
		status.setName("DESPEDIDO");
		Integer rowAffected=this.status.update(status);
		assertEquals(1, rowAffected);
		
	}
	
	@Test
	void insert() {
		Tstatus status=new Tstatus();
		status.setName("ANTENDIENDO");
		Integer rowAffected=this.status.save(status);
		assertEquals(1, rowAffected);
	}

}
