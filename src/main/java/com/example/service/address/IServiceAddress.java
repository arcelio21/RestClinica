package com.example.service.address;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressSaveDto;
import com.example.dto.address.AddressUpdatetDto;

import java.util.List;

public interface IServiceAddress{


    List<AddressGetDto> getAll();
    AddressGetDto getById(Integer id);
    Integer update(AddressUpdatetDto requestDto);
    Integer save(AddressSaveDto requestDto);

    List<AddressGetDto> getAddressByVillage(Integer idVillage);
    List<AddressGetDto> getAddressByDistrict(Integer idDistrict);
    List<AddressGetDto> getAddressByProvince(Integer idProvince);
}
