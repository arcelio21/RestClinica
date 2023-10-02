package com.example.mapper.user;

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
import com.example.entity.user.TtypeUser;
import com.example.entity.user.TuserReg;
import com.example.entity.user.TuserTypeReg;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperUserTypeRegTest {

	@Autowired
	private MapperUserTypeReg mapperUserTypeReg;
	
	@Test
	void getAll() {
		List<TuserTypeReg> users=this.mapperUserTypeReg.getAll();
		assertNotNull(users);
        assertFalse(users.isEmpty());
		assertNotNull(users.get(0).getStatusId());
		assertNotNull(users.get(0).getTypeUser());
		assertNotNull(users.get(0).getUserRegId());
		users.forEach((s) -> System.out.println(s.getId()+", "+s.getUserRegId().getName()+" "+ s.getUserRegId().getLastName()+", "+s.getTypeUser().getNameTypeUser()));
		
	}

	@Test
	void getById() {
		TuserTypeReg user=this.mapperUserTypeReg.getById(1L);
		assertNotNull(user);
		assertNotNull(user.getStatusId().getId());
		assertNotNull(user.getTypeUser().getId());
		assertNotNull(user.getUserRegId().getId());
		
		System.out.println(user);
	}

	@Test
	void getByIdUserReg(){
		List<TuserTypeReg> users=this.mapperUserTypeReg.getByIdUserReg(1);
		assertNotNull(users);
		assertTrue(users.size()>0);
		assertNotNull(users.get(0).getStatusId());
		assertNotNull(users.get(0).getTypeUser());
		assertNotNull(users.get(0).getUserRegId());
		users.forEach(System.out::println);
	}
	
	@Test
	void getByIdTypeUser() {
		List<TuserTypeReg> users=this.mapperUserTypeReg.getByIdTypeUser(1);
		assertNotNull(users);
		assertTrue(users.size()>0);
		assertNotNull(users.get(0).getStatusId());
		assertNotNull(users.get(0).getTypeUser());
		assertNotNull(users.get(0).getUserRegId());
		users.forEach(System.out::println);
	}
	
	@Test
	void getByIdStatus() {
		List<TuserTypeReg> users=this.mapperUserTypeReg.getByIdStatus(1);
		assertNotNull(users);
		assertTrue(users.size()>0);
		assertNotNull(users.get(0).getStatusId());
		assertNotNull(users.get(0).getTypeUser());
		assertNotNull(users.get(0).getUserRegId());
		users.forEach(System.out::println);
	}
	
	@Test
	void update() {
		TuserTypeReg user=this.mapperUserTypeReg.getById(1L);
		assertNotNull(user);
		
		user.setStatusId(new Tstatus(2));
		
		Integer rowAffected=this.mapperUserTypeReg.update(user);
		assertEquals(1, rowAffected);
	}
	
	@Test
	void save() {
		TuserTypeReg user=new TuserTypeReg();
		user.setTypeUser(new TtypeUser(2));
		user.setUserRegId(new TuserReg(1L));
		user.setStatusId(new Tstatus(1));
		
		Integer rowAffected=this.mapperUserTypeReg.save(user);
		assertEquals(1, rowAffected);
	}
	
}
