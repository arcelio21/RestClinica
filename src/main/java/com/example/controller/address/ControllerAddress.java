package com.example.controller.address;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Addresses Controller", description = "Realiza operaciones sobre los registros disponibles de Address")
@RequestMapping("api/v1/address")
public class ControllerAddress {
}
