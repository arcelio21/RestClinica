package com.example.service.address;

import com.example.dto.address.AddressGetDto;
import com.example.dto.address.AddressRequestDto;

import java.util.List;

public interface IServiceAddress{


    List<AddressGetDto> getAll();
    AddressGetDto getById(Integer id);
    Integer update(AddressRequestDto requestDto);
    Integer save(AddressRequestDto requestDto);
}
