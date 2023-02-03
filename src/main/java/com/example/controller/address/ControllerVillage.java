package com.example.controller.address;

import com.example.mapper.address.MapperDistrict;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/district")
@RequiredArgsConstructor
public class ControllerVillage {

    private final MapperDistrict mapperDistrict;
}
