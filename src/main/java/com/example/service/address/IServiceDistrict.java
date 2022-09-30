package com.example.service.address;

import java.util.List;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.service.ServiceTemplateCrud;

public interface IServiceDistrict<T, I> extends ServiceTemplateCrud<T, I>{
	
	T getDistrictAllSimpleById(I id);
	List<T> getByProvinceId(Tprovince tprovince);
	Tdistrict getByIdSimple(I id);
}
