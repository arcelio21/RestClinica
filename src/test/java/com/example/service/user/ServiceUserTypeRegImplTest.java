package com.example.service.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.dto.user.type_user_reg.TypeUserOfUserRegGetDto;
import com.example.dto.user.type_user_reg.UserRegOfTypeUserGetDto;
import com.example.dto.user.type_user_reg.UserTypeRegGetDto;
import com.example.dtomapper.user.DtoUserTypeRegMapper;
import com.example.entity.user.TuserTypeReg;
import com.example.exception.NoDataFoundException;
import com.example.mapper.user.MapperUserTypeReg;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceUserTypeRegImplTest {

    @Mock
    private MapperUserTypeReg mapperUserTypeReg;

    @Mock
    private DtoUserTypeRegMapper dtoMapperUserTypeReg;

    @InjectMocks
    private ServiceUserTypeRegImpl service;

    @Test
    void getAll() {

        given(this.mapperUserTypeReg.getAll())
                .willReturn(List.of(new TuserTypeReg()));

        given(this.dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class)))
                .willReturn(new UserTypeRegGetDto(1L, "Arcelio Montezuma", 12000704001435L, "ADMIN", "Activivated"));

        List<UserTypeRegGetDto> userTypeRegGetDtos = service.getAll();

        assertNotNull(userTypeRegGetDtos);
        assertFalse(userTypeRegGetDtos.isEmpty());

        then(this.mapperUserTypeReg).should(times(1)).getAll();
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class));
    }

    @Test
    void getAll_DataNotValid() {
        given(this.mapperUserTypeReg.getAll())
                .willReturn(null);

        assertThrows(NoDataFoundException.class, () -> this.service.getAll());

        then(this.mapperUserTypeReg).should(times(1)).getAll();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    @Test
    void getAll_DataIsEmpty() {

        given(this.mapperUserTypeReg.getAll())
                .willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, () -> this.service.getAll());

        then(this.mapperUserTypeReg).should(times(1)).getAll();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    // ---------------------------------------------------------------------------------------------------------

    @Test
    void getById() {

        Long idValid = 9L;
        given(this.mapperUserTypeReg.getById(idValid)).willReturn(new TuserTypeReg());

        given(this.dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class)))
                .willReturn(new UserTypeRegGetDto(1L, "Arcelio Montezuma", 12000704001435L, "ADMIN", "Activivated"));

        UserTypeRegGetDto userTypeRegGetDto = this.service.getById(idValid);

        assertNotNull(userTypeRegGetDto);
        assertNotNull(userTypeRegGetDto.id());

        then(this.mapperUserTypeReg).should(times(1)).getById(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class));
    }

    @Test
    void getById_NotValid() {

        Long idNotValid = 0L;

        assertThrows(NoDataFoundException.class, () -> this.service.getById(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    @Test
    void getById_DataNotFound() {

        Long idNotFound = 32L;

        given(this.mapperUserTypeReg.getById(idNotFound)).willReturn(null);

        assertThrows(NoDataFoundException.class, () -> this.service.getById(idNotFound));

        then(this.mapperUserTypeReg).should(times(1)).getById(idNotFound);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    // --------------------------------------------------------------------------------------------

    @Test
    void getByIdUserReg(){

        Long idValid = 1L;

        given(this.mapperUserTypeReg.getByIdUserReg(idValid)).willReturn(List.of(new TuserTypeReg()));

        given(this.dtoMapperUserTypeReg.tuserTypeRegToTypeUserOfUserRegGet(any(TuserTypeReg.class)))
                .willReturn(new TypeUserOfUserRegGetDto(3L, "ADMIN", "Activated"));
        
        List<TypeUserOfUserRegGetDto> typeUserOfUserRegGetDtos = this.service.getByIdUserReg(idValid);

        assertNotNull(typeUserOfUserRegGetDtos);
        assertFalse(typeUserOfUserRegGetDtos.isEmpty());
        
        then(this.mapperUserTypeReg).should(times(1)).getByIdUserReg(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToTypeUserOfUserRegGet(any(TuserTypeReg.class));
    }

    @Test
    void getByIdUserReg_IdNotValid(){

        Long idNotValid = 0L;

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserReg(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    @Test
    void getByIdUserReg_DataNotValid(){

        Long idNotValid = 3L;

        given(this.mapperUserTypeReg.getByIdUserReg(idNotValid)).willReturn(null);

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserReg(idNotValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdUserReg(idNotValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    @Test
    void getByIdUserReg_DataNotFound(){

        Long idNotValid = 3L;

        given(this.mapperUserTypeReg.getByIdUserReg(idNotValid)).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserReg(idNotValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdUserReg(idNotValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }


    //------------------------------------------------------------------------------------------------------------------------

   @Test
    void getByIdUserRegActivated(){

        Long idValid = 1L;

        given(this.mapperUserTypeReg.getByIdUserRegActivated(idValid)).willReturn(List.of(new TuserTypeReg()));

        given(this.dtoMapperUserTypeReg.tuserTypeRegToTypeUserOfUserRegGet(any(TuserTypeReg.class)))
                .willReturn(new TypeUserOfUserRegGetDto(3L, "ADMIN", "Activated"));
        
        List<TypeUserOfUserRegGetDto> typeUserOfUserRegGetDtos = this.service.getByIdUserRegActivated(idValid);

        assertNotNull(typeUserOfUserRegGetDtos);
        assertFalse(typeUserOfUserRegGetDtos.isEmpty());
        
        then(this.mapperUserTypeReg).should(times(1)).getByIdUserRegActivated(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToTypeUserOfUserRegGet(any(TuserTypeReg.class));
    }

    @Test
    void getByIdUserRegActivated_IdNotValid(){

        Long idNotValid = 0L;

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserRegActivated(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    @Test
    void getByIdUserRegActivated_DataNotValid(){

        Long idNotValid = 3L;

        given(this.mapperUserTypeReg.getByIdUserRegActivated(idNotValid)).willReturn(null);

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserRegActivated(idNotValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdUserRegActivated(idNotValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    @Test
    void getByIdUserRegActivated_DataNotFound(){

        Long idNotValid = 3L;

        given(this.mapperUserTypeReg.getByIdUserRegActivated(idNotValid)).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class,()-> this.service.getByIdUserRegActivated(idNotValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdUserRegActivated(idNotValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    //--------------------------------------------------------------------------------------------------------

    @Test
    void getByIdTypeUser(){
        Integer idValid = 1;
        
        given(this.mapperUserTypeReg.getByIdTypeUser(idValid)).willReturn(List.of(new TuserTypeReg()));
        given(this.dtoMapperUserTypeReg.tuserTypeRegToUserRegOfTypeUserGet(any(TuserTypeReg.class)))
                .willReturn(new UserRegOfTypeUserGetDto(1L, "Arcelio Montezuma", 12000704001435L, "Activated"));
        
        List<UserRegOfTypeUserGetDto> userGetDtos = this.service.getByIdTypeUser(idValid);

        assertNotNull(userGetDtos);
        assertFalse(userGetDtos.isEmpty());

        then(this.mapperUserTypeReg).should(times(1)).getByIdTypeUser(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToUserRegOfTypeUserGet(any(TuserTypeReg.class));

    }

    @Test
    void getByIdTypeUser_idNotValid(){

        Integer idNotValid = 0;

        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdTypeUser(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    @Test
    void getByIdTypeUser_DataNotValid(){
        Integer idValid = 1;

        given(this.mapperUserTypeReg.getByIdTypeUser(idValid)).willReturn(null);
        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdTypeUser(idValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdTypeUser(idValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    @Test
    void getByIdTypeUser_DataIsEmpty(){
        Integer idValid = 1;

        given(this.mapperUserTypeReg.getByIdTypeUser(idValid)).willReturn(Collections.emptyList());
        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdTypeUser(idValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdTypeUser(idValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    //------------------------------------------------------------------------------------------------------

    @Test
    void getByIdStatus(){
        Integer idValid = 1;
        
        given(this.mapperUserTypeReg.getByIdStatus(idValid)).willReturn(List.of(new TuserTypeReg()));
        given(this.dtoMapperUserTypeReg.tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class)))
                .willReturn(new UserTypeRegGetDto(1L, "Arcelio Montezuma", 12000704001435L, "ADMIN", "Activated"));
        
        List<UserTypeRegGetDto> userGetDtos = this.service.getByIdStatus(idValid);

        assertNotNull(userGetDtos);
        assertFalse(userGetDtos.isEmpty());

        then(this.mapperUserTypeReg).should(times(1)).getByIdStatus(idValid);
        then(this.dtoMapperUserTypeReg).should(times(1)).tuserTypeRegToUserTypeRegDto(any(TuserTypeReg.class));

    }

    @Test
    void getByIdStatus_idNotValid(){

        Integer idNotValid = 0;

        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdStatus(idNotValid));

        then(this.mapperUserTypeReg).shouldHaveNoInteractions();
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
    }

    @Test
    void getByIdStatus_DataNotValid(){
        Integer idValid = 1;

        given(this.mapperUserTypeReg.getByIdStatus(idValid)).willReturn(null);
        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdStatus(idValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdStatus(idValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();

    }

    @Test
    void getByIdStatus_DataIsEmpty(){
        Integer idValid = 1;

        given(this.mapperUserTypeReg.getByIdStatus(idValid)).willReturn(Collections.emptyList());
        assertThrows(NoDataFoundException.class, ()-> this.service.getByIdStatus(idValid));

        then(this.mapperUserTypeReg).should(times(1)).getByIdStatus(idValid);
        then(this.dtoMapperUserTypeReg).shouldHaveNoInteractions();
        
    }
}
