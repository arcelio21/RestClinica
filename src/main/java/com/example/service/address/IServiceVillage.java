package com.example.service.address;

import java.util.List;

import com.example.dto.address.village.VillageDistrictDto;
import com.example.service.ServiceTemplateCrud;

public interface IServiceVillage<T,I> extends ServiceTemplateCrud<T, I>{

	List<T> getByDistrictId(I id);
	VillageDistrictDto getDistrictAllById( I id);

	List<T> getAllIdName();

	T getByIdName(I id);

}
