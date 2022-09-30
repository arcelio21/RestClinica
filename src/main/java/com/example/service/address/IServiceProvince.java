package com.example.service.address;

import java.util.List;

import com.example.entity.address.Tprovince;
import com.example.service.ServiceTemplateCrud;

public interface IServiceProvince<T,I> extends ServiceTemplateCrud<T, I> {

	List<T> getAllSimple();
	Tprovince getByIdSimple( I id);
}
