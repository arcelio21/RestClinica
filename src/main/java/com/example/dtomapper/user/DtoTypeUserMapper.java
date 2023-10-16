package com.example.dtomapper.user;

import com.example.dto.user.type_user.TypeUserDto;
import com.example.dto.user.type_user.TypeUserPostDto;
import com.example.dto.user.type_user.TypeUserUpdateDto;
import com.example.entity.user.TtypeUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoTypeUserMapper {

    DtoTypeUserMapper INSTANCE = Mappers.getMapper(DtoTypeUserMapper.class);

    @Mappings({
            @Mapping(source = "tUser.id", target = "id"),
            @Mapping(source = "tUser.nameTypeUser", target = "name")
    })
    TypeUserDto ttypeUserToTypeUserDto(TtypeUser tUser);

    @Mappings({
            @Mapping(source = "tUserDto.id", target = "id"),
            @Mapping(source = "tUserDto.name", target = "nameTypeUser")
    })
    TtypeUser typeUserDtoToTtypeUser(TypeUserDto tUserDto);

    @Mappings({
            @Mapping(source = "tUserDto.id", target = "id"),
            @Mapping(source = "tUserDto.name", target = "nameTypeUser")
    })
    TtypeUser typeUserUpdateDtoToTtypeUser(TypeUserUpdateDto tUserDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "tUserDto.name", target = "nameTypeUser")
    })
    TtypeUser typeUserPostDtoToTtypeUser(TypeUserPostDto tUserDto);
}
