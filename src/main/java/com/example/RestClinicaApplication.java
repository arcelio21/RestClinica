package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;




@SpringBootApplication
@EnableConfigurationProperties
@MapperScan("com.example.mapper.*")
@EnableAspectJAutoProxy
@CrossOrigin(origins = "http://localhost:4200")
public class RestClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestClinicaApplication.class, args);
	}

}
