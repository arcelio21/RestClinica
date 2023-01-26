package com.example.controller.address;

import com.example.controller.ControllerTemplateImpl;
import com.example.entity.address.Tvillage;
import com.example.service.address.ServiceVillageImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Village Controller",description = "Realizando operacion sobre los village")
@RequestMapping(value = "api/v1/village")
public class ControllerVillageImpl extends ControllerTemplateImpl<Tvillage, ServiceVillageImpl> {
    protected ControllerVillageImpl(ServiceVillageImpl service) {
        super(service);
    }
}
