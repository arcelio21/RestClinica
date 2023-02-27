package com.example.mapper.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.address.Taddress;
import com.example.entity.user.TuserReg;

@MybatisTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperUserRegTest {

	@Autowired
	private MapperUserReg mapperUserReg;
	
	@Test
	void getAll() {
		//fail("Not yet implemented");
		List<TuserReg> users=this.mapperUserReg.getAll();
		assertNotNull(users);
		assertTrue(users.size()>0);
		users.forEach(System.out::println);
	}
	
	@Test
	void getById() {
		TuserReg user=this.mapperUserReg.getById(1L);
		assertNotNull(user);
		System.out.println(user); 
		LocalDate fecha=LocalDate.of(2000, 9, 2);
		assertEquals(fecha, user.getFechaNacimiento());
		System.out.println(fecha+" "+user.getFechaNacimiento());
	}
	
	@Test
	void save() {
		TuserReg user=new TuserReg();
		user.setIdenCard(4000894000123L);
		user.setName("Carlos");
		user.setLastName("Montenegro");
		user.setContact("67238192");
		user.setEmail("carloMon12@gmail.com");
		user.setPassword("hola");
		user.setFechaNacimiento(LocalDate.of(2000, 12, 12));
		Taddress address=new Taddress(1L);
		user.setAddressId(address);
		Integer rowAffected=this.mapperUserReg.save(user);
		assertEquals(1,rowAffected);
	}
	
	@Test
	void update() {
		TuserReg user=this.mapperUserReg.getById(1L);
		user.setContact("67289102");
		Integer rowAffected=this.mapperUserReg.update(user);
		assertEquals(1, rowAffected);
	}
	
	@Test
	void updatePassword() {
		TuserReg user=this.mapperUserReg.getById(1L);
		user.setPassword("hola");
		System.out.println(user);
		Integer rowAffected=this.mapperUserReg.updatePassword(user, "");
		assertEquals(1, rowAffected);
	}
	

	
	@Test
	void getByNmae() {
		List<TuserReg> users=this.mapperUserReg.getByName("arce");
		assertNotNull(users);
		assertTrue(users.size()>0);
		
		for(TuserReg user:users) {
			System.out.println(user);
		}
	}

}
