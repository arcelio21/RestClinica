package com.example.controller.address;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controller.ControllerTemplateImpl;
import com.example.entity.address.Taddress;
import com.example.service.address.ServiceAddressImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/address")
public class ControllerAddressImpl extends ControllerTemplateImpl<Taddress, ServiceAddressImpl>{

	
	public ControllerAddressImpl(ServiceAddressImpl service) {
		super(service);
	}

}
