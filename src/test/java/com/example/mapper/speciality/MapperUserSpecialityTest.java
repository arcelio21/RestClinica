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
import com.example.entity.speciality.TuserSpeciality;
import com.example.entity.status.Tstatus;
import com.example.entity.user.TuserTypeReg;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MapperUserSpecialityTest {

	@Autowired
	private MapperUserSpeciality mapperUserSpeciality;
	
	@Test
	void getAll() {
		List<TuserSpeciality> users=this.mapperUserSpeciality.getAll();
		
		assertNotNull(users);
        assertFalse(users.isEmpty());
		assertNotNull(users.get(0).getSpecialityId());
		assertNotNull(users.get(0).getStatusId());
		assertNotNull(users.get(0).getUserTypeRegId());
		
		users.forEach(System.out::println);
	}
	
	@Test
	void getById() {
		TuserSpeciality users=this.mapperUserSpeciality.getById(1);
		
		assertNotNull(users);
		assertNotNull(users.getSpecialityId());
		assertNotNull(users.getStatusId());
		assertNotNull(users.getUserTypeRegId());
		
		System.out.println(users);
	}
	
	@Test
	void getByIdSpeciality() {
		List<TuserSpeciality> users=this.mapperUserSpeciality.getByIdSpeciality(1);
		
		assertNotNull(users);
        assertFalse(users.isEmpty());
		assertNotNull(users.get(0).getSpecialityId());
		assertNotNull(users.get(0).getStatusId());
		assertNotNull(users.get(0).getUserTypeRegId());
		
		users.forEach(System.out::println);
	}
	
	
	@Test
	void getByIdUserTypeReg() {
		List<TuserSpeciality> users=this.mapperUserSpeciality.getByIdUserTypeReg(1);
		
		assertNotNull(users);
		assertNotNull(users.get(0).getSpecialityId());
		assertNotNull(users.get(0).getStatusId());
		assertNotNull(users.get(0).getUserTypeRegId());
		
		System.out.println(users);
	}
	
	@Test
	void getByIdStatus() {
		List<TuserSpeciality> users=this.mapperUserSpeciality.getByIdStatus(1);
		
		assertNotNull(users);
        assertFalse(users.isEmpty());
		assertNotNull(users.get(0).getSpecialityId());
		assertNotNull(users.get(0).getStatusId());
		assertNotNull(users.get(0).getUserTypeRegId());
		
		users.forEach(System.out::println);
	}
	
	@Test
	void update() {
		List<TuserSpeciality> users=this.mapperUserSpeciality.getByIdUserTypeReg(1);
		assertNotNull(users);
		
		users.get(0).setSpecialityId(new Tspeciality(1));
		Integer rowAffected=this.mapperUserSpeciality.update(users.get(0));
		assertEquals(1, rowAffected);
		
	}
	
	@Test
	void save() {
		TuserSpeciality speciality=new TuserSpeciality();
		speciality.setSpecialityId(new Tspeciality(1));
		speciality.setUserTypeRegId(new TuserTypeReg(1L));
		speciality.setStatusId(new Tstatus(1));
		
		Integer rowAffected=this.mapperUserSpeciality.save(speciality);
		assertEquals(1, rowAffected);
	}
	
	

}
