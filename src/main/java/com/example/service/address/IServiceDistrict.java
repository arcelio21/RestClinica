package com.example.service.address;

import java.util.List;

import com.example.dto.address.district.DistrictAllDto;
import com.example.service.ServiceTemplateCrud;

public interface IServiceDistrict<GET, ID, UPDATE, SAVE> extends ServiceTemplateCrud<GET, ID, UPDATE, SAVE>{

	GET getByIdName(ID id);
	List<GET> getAllIdName();
	List<GET> getByProvinceId(ID id);
	DistrictAllDto getDistrictAndProvinceById(ID id);
}
