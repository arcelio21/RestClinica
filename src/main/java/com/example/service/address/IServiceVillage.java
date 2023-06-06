package com.example.service.address;

import java.util.List;

import com.example.dto.address.village.VillageDistrictDto;
import com.example.service.ServiceTemplateCrud;

public interface IServiceVillage<GET,ID,UPDATE,SAVE> extends ServiceTemplateCrud<GET,ID,UPDATE,SAVE>{

	List<GET> getByDistrictId(ID id);
	VillageDistrictDto getDistrictAllById( ID id);

	List<GET> getAllIdName();

	GET getByIdName(ID id);

}
