package com.example.dtomapper.address;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressRequestDto;
import com.example.entity.address.Taddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface AddressMappper {

    @Mappings({
            @Mapping(source = "taddress.id", target = "id", defaultValue = "0"),
            @Mapping(source = "taddress.villageId.id", target = "villageId", defaultValue = "0"),
            @Mapping(source = "taddress.villageId.name", target = "villageName", defaultValue = ""),
            @Mapping(source = "taddress.villageId.district.name", target = "districtName", defaultValue = ""),
            @Mapping(source = "taddress.villageId.district.province.name", target = "provinceName", defaultValue = ""),
            @Mapping(source = "taddress.specificAddress", target = "specificAddress", defaultValue = "")
    })
    AddressGetDto taddressToAddressGetDto(Taddress taddress);

    @Mappings({
            @Mapping(source = "dto.id", target = "id"),
            @Mapping(source = "dto.villageId", target = "villageId.id"),
            @Mapping(source = "dto.specificAddress", target = "specificAddress"),
            @Mapping(target = "villageId", ignore = true)
    })
    Taddress AddressRequestDtoToTaddress(AddressRequestDto dto);
}
