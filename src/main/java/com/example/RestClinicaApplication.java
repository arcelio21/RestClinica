package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.tags.Tag;



@SpringBootApplication
@MapperScan("com.example.mapper.*")
@Tag(name = "Clinica", description = "Manejara consultas y citas de una clinica")
public class RestClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestClinicaApplication.class, args);
	}

}
