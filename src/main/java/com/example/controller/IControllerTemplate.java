package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IControllerTemplate<T extends Object> {

	ResponseEntity<List<T>> getAll();
	
	ResponseEntity<T> getById( Integer id);
	
	ResponseEntity<Integer> save( T t);
	
	ResponseEntity<Integer> update( T t);
}
