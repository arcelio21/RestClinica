package com.example.controller.address;

import com.example.service.address.ServiceDistrictImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/district")
@RequiredArgsConstructor
@Tag(name = "Controller District", description = "Se podra realizar todas las operaciones disponibles para district")
public class ControllerDistrict {

    private final ServiceDistrictImpl serviceDistrict;


}
