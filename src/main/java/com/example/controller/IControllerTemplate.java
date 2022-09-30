package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IControllerTemplate<T extends Object> {

	ResponseEntity<List<T>> getAll();
	
	ResponseEntity<T> getById(@PathVariable("id") Integer id);
	
	ResponseEntity<Integer> save(@RequestBody T t);
	
	ResponseEntity<Integer> update(@RequestBody T t);
}
