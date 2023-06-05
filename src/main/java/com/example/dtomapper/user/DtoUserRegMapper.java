package com.example.dtomapper.user;

import com.example.dto.AuthenticationRequest;
import com.example.dto.user.user_reg.UserRegDto;
import com.example.dto.user.user_reg.UserRegSaveDto;
import com.example.dto.user.user_reg.UserRegUpdateDto;
import com.example.dto.user.user_reg.UserUpdatePassDto;
import com.example.entity.user.TuserReg;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoUserRegMapper {

    DtoUserRegMapper INSTANCE = Mappers.getMapper(DtoUserRegMapper.class);

    @Mappings({
            @Mapping(source = "user.id", target = "id"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.lastName", target = "lastName"),
            @Mapping(source = "user.addressId.id", target = "addressId"),
            @Mapping(source = "user.contact", target = "contact"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.idenCard",target = "idenCard"),
            @Mapping(source = "user.birthday", target = "birthday"),
            @Mapping(source = "user.creationDate", target = "creationDate"),
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
            @Mapping(source = "user.birthday", target = "birthday"),
            @Mapping(source = "user.addressId", target = "addressId.id", resultType = Long.class),
            @Mapping(target = "addressId", ignore = true),
            @Mapping(target = "usersTypesRegs", ignore = true),
            @Mapping(target = "creationDate", ignore = true),
            @Mapping(target = "authorities", ignore = true)
    })
    TuserReg userRegDtoToTuserReg(UserRegDto user);

    @Mappings({
            @Mapping(source = "request.idenCard", target = "idenCard"),
            @Mapping(source = "request.password", target = "password"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "addressId", ignore = true),
            @Mapping(target = "usersTypesRegs", ignore = true),
            @Mapping(target = "creationDate", ignore = true),
            @Mapping(target = "birthday", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "contact", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "lastName", ignore = true),
            @Mapping(target = "authorities", ignore = true)
    })
    TuserReg authenticationRequestToTuserReg(AuthenticationRequest request);

    @Mappings({
            @Mapping(source = "user.indeCard", target = "idenCard"),
            @Mapping(source = "user.oldPassword", target = "password"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "addressId", ignore = true),
            @Mapping(target = "usersTypesRegs", ignore = true),
            @Mapping(target = "creationDate", ignore = true),
            @Mapping(target = "birthday", ignore = true),
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "contact", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "lastName", ignore = true),
            @Mapping(target = "authorities", ignore = true)
    })
    TuserReg userUpdatePassToTuserReg(UserUpdatePassDto user);

    @Mappings({
            @Mapping(source = "userUpdate.id", target = "id"),
            @Mapping(source = "userUpdate.name", target = "name"),
            @Mapping(source = "userUpdate.contact", target = "contact"),
            @Mapping(source = "userUpdate.idenCard", target = "idenCard"),
            @Mapping(source = "userUpdate.email", target = "email"),
            @Mapping(source = "userUpdate.birthday", target = "birthday"),
            @Mapping(source = "userUpdate.password", target = "password"),
            @Mapping(source = "userUpdate.lastName", target = "lastName"),
            @Mapping(target = "addressId", ignore = true),
            @Mapping(source = "userUpdate.addressId", target = "addressId.id"),
            @Mapping(source = "userUpdate.villageId", target = "addressId.villageId.id"),
            @Mapping(source = "userUpdate.direcSpecific", target = "addressId.specificAddress"),
            @Mapping(target = "usersTypesRegs", ignore = true)
    })
    TuserReg userRegUpdateDtoToTuserReg(UserRegUpdateDto userUpdate);

 

}
