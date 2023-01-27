package com.example.controller.address;

import com.example.controller.ControllerTemplateImpl;
import com.example.entity.address.Tdistrict;
import com.example.entity.address.Tprovince;
import com.example.service.address.ServiceDistrictImpl;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("api/v1/district")
@Tag(name = "Districts Controller ", description = "Realiza operaciones sobre los registros de los distritos disponibles")
public class ControllerDistrictImpl extends ControllerTemplateImpl<Tdistrict,ServiceDistrictImpl> {

	public ControllerDistrictImpl(ServiceDistrictImpl service) {
		super(service);
	}


	@GetMapping(value = "/byprovince/{id}")
	public ResponseEntity<List<Tdistrict>> getByProvinceId(@PathVariable(value = "province") Tprovince tprovince){

		return  ResponseEntity.ok(this.service.getByProvinceId(tprovince));
	}
}
