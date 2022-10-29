package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/village")
public class Village {

	public ResponseEntity<?> getAll(){
		
		return ResponseEntity.ok("Hola mundo");
	}
}
