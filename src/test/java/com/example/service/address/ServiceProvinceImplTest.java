package com.example.service.address;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.entity.address.Tvillage;
import com.example.mapper.address.MapperProvince;

@MybatisTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ServiceProvinceImplTest {

	private ServiceProvinceImpl serviceProvinceImpl;
	

	@Autowired
	private MapperProvince mapperProvince;
	
	@BeforeEach
	void setUp()throws Exception {
		this.serviceProvinceImpl=new ServiceProvinceImpl(mapperProvince);
	}
	
	
	@Test
	void getAll() {
		List<Tprovince> province=this.serviceProvinceImpl.getAll();
		assertNotNull(province);
		for( Tprovince prov: province ) {
			System.out.println(prov );
			System.out.println("	DISTRITOS DE "+prov.getName());
			for(Tdistrict dis: prov.getDistricts()) {
				
				System.out.println("	ID: "+dis.getId()+" Nombre: "+dis.getName());
				System.out.println("		CORREGIMIENTOS DE "+dis.getName());
				for(Tvillage vill:dis.getVillages()) {
					
					System.out.println("		ID: "+vill.getId()+" Nombre: "+vill.getName());
				}
			}
		}
		
	}
	
	@Test
	void getById() {
		Tprovince prov=this.serviceProvinceImpl.getById(1);
		assertNotNull(prov);
		System.out.println(prov );
		System.out.println("	DISTRITOS DE "+prov.getName());
		for(Tdistrict dis: prov.getDistricts()) {
			
			System.out.println("	ID: "+dis.getId()+" Nombre: "+dis.getName());
			System.out.println("		CORREGIMIENTOS DE "+dis.getName());
			for(Tvillage vill:dis.getVillages()) {
				
				System.out.println("		ID: "+vill.getId()+" Nombre: "+vill.getName());
			}
		}
	}

}
