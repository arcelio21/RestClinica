package com.example.dtomapper.user;

import com.example.dto.AuthenticationRequest;
import com.example.dto.user.UserRegDto;
import com.example.dto.user.UserUpdatePassDto;
import com.example.entity.user.TuserReg;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserRegMapper {


    @Mappings({
            @Mapping(source = "user.id", target = "id"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.lastName", target = "lastName"),
            @Mapping(source = "user.addressId.id", target = "addressId"),
            @Mapping(source = "user.contact", target = "contact"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.idenCard",target = "idenCard"),
            @Mapping(source = "user.fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "user.fechaCreacion", target = "fechaCreacion"),
            @Mapping(target = "password", ignore = true)
    })
    UserRegDto TuserRegToUserRegDto(TuserReg user);


    @Mappings({
            @Mapping(source = "user.id", target = "id"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.lastName", target = "lastName"),
            @Mapping(source = "user.idenCard", target = "idenCard"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.contact", target = "contact"),
            @Mapping(source = "user.password", target = "password"),
            @Mapping(source = "user.fechaNacimiento", target = "fechaNacimiento"),
            @Mapping(source = "user.addressId", target = "addressId.id"),
            @Mapping(target = "addressId", ignore = true),
            @Mapping(target = "usersTypesRegs", ignore = true),
            @Mapping(target = "fechaCreacion", ignore = true)
    })
    TuserReg userRegDtoToTuserReg(UserRegDto user);

    @Mappings({
            @Mapping(source = "request.idenCard", target = "idenCard"),
            @Mapping(source = "request.password", target = "password"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "addressId", ignore = true),
            @Mapping(target = "usersTypesRegs", ignore = true),
            @Mapping(target = "fechaCreacion", ignore = true),
            @Mapping(target = "fechaNacimiento", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "contact", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "lastName", ignore = true)
    })
    TuserReg authenticationRequestToTuserReg(AuthenticationRequest request);

    @Mappings({
            @Mapping(source = "user.indeCard", target = "idenCard"),
            @Mapping(source = "user.oldPassword", target = "password"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "addressId", ignore = true),
            @Mapping(target = "usersTypesRegs", ignore = true),
            @Mapping(target = "fechaCreacion", ignore = true),
            @Mapping(target = "fechaNacimiento", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "contact", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "lastName", ignore = true),
    })
    TuserReg userUpdatePassToTuserReg(UserUpdatePassDto user);

}
