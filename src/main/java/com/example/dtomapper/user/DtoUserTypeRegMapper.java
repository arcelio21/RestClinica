package com.example.dtomapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.example.dto.user.type_user_reg.UserTypeRegGetDto;
import com.example.entity.user.TuserTypeReg;

@Mapper
public interface DtoUserTypeRegMapper {
    
    DtoUserTypeRegMapper INSTANCE = Mappers.getMapper(DtoUserTypeRegMapper.class);

    @Mappings(value = {
        @Mapping(target = "id", source = "user.id"),
        @Mapping(target = "fullName", expression = "java(user.getUserRegId().getName() + ' '+ user.getUserRegId().getLastName())"),
        @Mapping(target = "idenCard", source = "user.userRegId.idenCard"),
        @Mapping(target = "typeUser", source = "user.typeUser.nameTypeUser"),
        @Mapping(target = "status", source = "user.statusId.name")
    })
    UserTypeRegGetDto tuserTypeRegToUserTypeRegDto(TuserTypeReg user);
}
