package com.example.controller.address;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.controller.ControllerTemplateImpl;
import com.example.entity.address.Tprovince;
import com.example.service.address.ServiceProvinceImpl;

@Tag(name = "Provinces Controller", description = "Realiza operaciones sobre las provincias disponibles")
@RestController
@RequestMapping("/api/v1/province")
public class ControllerProvinceImpl extends ControllerTemplateImpl<Tprovince, ServiceProvinceImpl>{

	protected ControllerProvinceImpl(ServiceProvinceImpl service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

}
