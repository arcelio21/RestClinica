package com.example.controller;

import java.util.List;

import com.example.dto.RequestResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IControllerTemplate<T> {

	ResponseEntity<RequestResponseDTO> getAll();
	
	ResponseEntity<RequestResponseDTO> getById( Integer id);
	
	ResponseEntity<RequestResponseDTO> save( T t);
	
	ResponseEntity<RequestResponseDTO> update( T t);
}
