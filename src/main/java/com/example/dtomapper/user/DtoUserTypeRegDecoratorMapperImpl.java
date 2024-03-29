package com.example.dtomapper.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dto.user.type_user_reg.UserRegOfTypeUserGetDto;
import com.example.dto.user.type_user_reg.UserTypeRegGetDto;
import com.example.entity.user.TuserTypeReg;

public abstract class DtoUserTypeRegDecoratorMapperImpl implements DtoUserTypeRegMapper{
    
    @Autowired
    private DtoUserTypeRegMapper mapper;

    @Override
    public UserTypeRegGetDto tuserTypeRegToUserTypeRegDto(TuserTypeReg user){

        this.isFullNameValited(user);
        return this.mapper.tuserTypeRegToUserTypeRegDto(user);
    }

    @Override
    public UserRegOfTypeUserGetDto tuserTypeRegToUserRegOfTypeUserGet(TuserTypeReg user){
        
        this.isFullNameValited(user);
        return this.mapper.tuserTypeRegToUserRegOfTypeUserGet(user);
    }

    private void isFullNameValited(TuserTypeReg user){

        if(user==null 
            || user.getUserRegId().getName() == null || user.getUserRegId().getName().isEmpty()
            || user.getUserRegId().getLastName() == null || user.getUserRegId().getLastName().isEmpty()){

            throw new RuntimeException("ERROR DE MAPEO DE FULLNAME");
        }
    }

}
