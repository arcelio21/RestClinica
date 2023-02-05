package com.example.service;

import com.example.dto.address.AddressGetDto;

import java.util.List;

public interface ServiceTemplateCrud<T,I> {

	List<T> getAll();
	
	T getById(I id);
	
	Integer update(T t);
	
	Integer save(T t);
	
}
