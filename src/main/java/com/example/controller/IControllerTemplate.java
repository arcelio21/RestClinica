package com.example.controller;

import java.util.List;

import com.example.dto.RequestResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IControllerTemplate<T> {

	ResponseEntity<RequestResponseDTO> getAll();
	
	ResponseEntity<RequestResponseDTO> getById( Integer id);


    ResponseEntity<RequestResponseDTO> save(T t);
	
	ResponseEntity<RequestResponseDTO> update( T t);
}
