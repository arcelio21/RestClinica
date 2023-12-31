package com.example.dtomapper.speciality;

import com.example.dto.speciality.userspeciality.*;
import com.example.entity.speciality.TuserSpeciality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoUserSpecialityMapper {

    DtoUserSpecialityMapper INSTANCE = Mappers.getMapper(DtoUserSpecialityMapper.class);

    @Mappings(
            value = {
                    @Mapping(source = "save.idSpeciality", target = "specialityId.id"),
                    @Mapping(source = "save.idUserTypeReg", target = "userTypeRegId.id"),
                    @Mapping(source = "save.idStatus", target = "statusId.id"),
                    @Mapping(target = "id", ignore = true),
                    @Mapping(target = "specialityId", ignore = true),
                    @Mapping(target = "statusId", ignore = true),
                    @Mapping(target = "userTypeRegId", ignore = true)
            }
    )
    TuserSpeciality UserSpecialitySaveDtoToUserSpeciality(UserSpecialitySaveDto save);

    @Mappings(
            value = {
                    @Mapping(source = "update.idSpeciality", target = "specialityId.id"),
                    @Mapping(source = "update.idUserTypeReg", target = "userTypeRegId.id"),
                    @Mapping(source = "update.idStatus", target = "statusId.id"),
                    @Mapping(source = "update.id",target = "id"),
                    @Mapping(target = "specialityId", ignore = true),
                    @Mapping(target = "statusId", ignore = true),
                    @Mapping(target = "userTypeRegId", ignore = true)
            }
    )
    TuserSpeciality UserSpecialityUpdateDtoTouserSpeciality(UserSpecialityUpdateDto update);

    @Mappings(
            value = {
                    @Mapping(target = "id", source = "data.id"),
                    @Mapping(target = "lastNameUser", source = "data.userTypeRegId.userRegId.lastName"),
                    @Mapping(target = "nameUser", source = "data.userTypeRegId.userRegId.name"),
                    @Mapping(target = "nameSpeciality", source = "data.specialityId.name"),
                    @Mapping(target = "nameTypeUser", source = "data.userTypeRegId.typeUser.nameTypeUser"),
                    @Mapping(target = "nameStatus", source = "data.statusId.name"),
            }
    )
    UserSpecialityGetDto userSpecialityToUserSpecialityGetDto(TuserSpeciality data);

    @Mappings(
            value = {
                    @Mapping(target = "id", source = "data.id"),
                    @Mapping(target = "lastNameUser", source = "data.userTypeRegId.userRegId.name"),
                    @Mapping(target = "nameUser", source = "data.userTypeRegId.userRegId.lastName"),
                    @Mapping(target = "nameStatus", source = "data.statusId.name"),
                    @Mapping(target = "nameTypeUser", source = "data.userTypeRegId.typeUser.nameTypeUser")
            }
    )
    UserSpecialityBySpecialityGetDto userSpecialityToUserSpecialityBySpecialityGetDto(TuserSpeciality data);

    @Mappings(value = {
            @Mapping(target = "id", source = "data.id"),
            @Mapping(target = "nameUser", source = "data.userTypeRegId.userRegId.name"),
            @Mapping(target = "lastNameUser", source = "data.userTypeRegId.userRegId.lastName"),
            @Mapping(target = "nameTypeUser", source = "data.userTypeRegId.typeUser.nameTypeUser"),
            @Mapping(target = "nameSpeciality", source = "data.specialityId.name")
    })
    UserSpecialityByStatusGetDto userSpecialityToUserSpecialityByStatusGetDto(TuserSpeciality data);

    @Mappings(value = {
            @Mapping(target = "id", source = "data.id"),
            @Mapping(target = "nameSpeciality", source = "data.specialityId.name"),
            @Mapping(target = "nameUser", source = "data.userTypeRegId.userRegId.name"),
            @Mapping(target = "lastNameUser", source = "data.userTypeRegId.userRegId.lastName"),
            @Mapping(target = "nameStatus", source = "data.statusId.name")
    })
    UserSpecialityByTypeUserGetDto userSpecialityToUserSpecialityByTypeUserGetDto(TuserSpeciality data);

    @Mappings(value = {
            @Mapping(target = "id", source = "data.id"),
            @Mapping(target = "nameStatus", source = "data.statusId.name"),
            @Mapping(target = "nameSpeciality", source = "data.specialityId.name")
    })
    UserSpecialityByUserTypeRegGetDto userSpecialityToUserSpecialityByUserTypeRegGetDto(TuserSpeciality data);
}
