package com.example.service.address;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressRequestDto;

import java.util.List;

public interface IServiceAddress{


    List<AddressGetDto> getAll();
    AddressGetDto getById(Integer id);
    Integer update(AddressRequestDto requestDto);
    Integer save(AddressRequestDto requestDto);

    List<AddressGetDto> getAddressByVillage(Integer idVillage);
    List<AddressGetDto> getAddressByDistrict(Integer idDistrict);
    List<AddressGetDto> getAddressByProvince(Integer idProvince);
}
