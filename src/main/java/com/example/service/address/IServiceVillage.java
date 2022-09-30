package com.example.service.address;

import java.util.List;

import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tvillage;
import com.example.service.ServiceTemplateCrud;

public interface IServiceVillage<T,I> extends ServiceTemplateCrud<T, I>{

	List<Tvillage> getByDistrictId( Tdistrict tdistrict);
	Tvillage getDistrictAllById( I id);
}
