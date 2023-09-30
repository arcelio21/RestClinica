package com.example.dto.user.typeuser_module;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ModuleRoute {

    private String module;
    private String privilege;
    private String typeUser;
}
