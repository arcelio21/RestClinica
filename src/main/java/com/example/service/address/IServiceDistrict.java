package com.example.service.address;

import java.util.List;

import com.example.dto.address.district.DistrictAllDto;
import com.example.service.ServiceTemplateCrud;

public interface IServiceDistrict<T, I> extends ServiceTemplateCrud<T, I>{

	T getByIdName(I id);
	List<T> getAllIdName();
	List<T> getByProvinceId(I id);
	DistrictAllDto getDistrictAndProvinceById(I id);
}
