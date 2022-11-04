package com.example.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.service.ServiceTemplateCrud;

public abstract class ControllerTemplateImpl<T, S extends ServiceTemplateCrud<T, Integer>>
		implements IControllerTemplate<T> {

	protected S service;

	protected ControllerTemplateImpl(S service) {

		this.service = service;
	}

	@GetMapping
	@Override
	public ResponseEntity<List<T>> getAll() {

		List<T> data = null;
		try {
			data = this.service.getAll();
			return ResponseEntity.status(HttpStatus.OK).body(data);
		} catch (Exception e) {
			data = Collections.emptyList();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
		}
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<T> getById(@PathVariable("id") Integer id) {

		try {
			T data= this.service.getById(id);
			if(data==null){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
			}else{
				return ResponseEntity.ok(data);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping
	@Override
	public ResponseEntity<Integer> save(@RequestBody T t) {

		if(t==null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}

		try{
			Integer rowAffected = this.service.save(t);

			if(rowAffected==1){
				return ResponseEntity.status(HttpStatus.CREATED).body(rowAffected);
			}else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rowAffected);
			}
	}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(0);
		}
	}

	@PutMapping
	@Override
	public ResponseEntity<Integer> update(@RequestBody T t) {
		return null;
	}

}