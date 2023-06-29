package com.example.service.modules;

import com.example.dto.modules.privileges.PrivilegeDto;
import com.example.dtomapper.modules.DtoPrivilegeMapper;
import com.example.entity.modules.Tprivilege;
import com.example.exception.NoDataFoundException;
import com.example.mapper.modules.MapperPrivilege;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@DisplayName("Test de metodos implementados en ServicePrivileges")
@ExtendWith(MockitoExtension.class)
class ServicePrivilegeImplTest {

    @Mock
    private MapperPrivilege mapperPrivilege;

    @Mock
    private DtoPrivilegeMapper dtoPrivilegeMapper;

    @InjectMocks
    private ServicePrivilegeImpl servicePrivilege;

    private PrivilegeDto privilegeDtoValid;
    private Tprivilege tprivilegeValid;

    @BeforeEach
    void setUp() {

        privilegeDtoValid = PrivilegeDto.builder()
                .id(1)
                .name("READ")
                .build();

        tprivilegeValid = new Tprivilege(1,"READ");
    }

    @Test
    void getAll_Privileges_Exists() {

        given(this.mapperPrivilege.getAll()).willReturn(List.of(tprivilegeValid));
        given(this.dtoPrivilegeMapper.TprivilegeToPrivilegDto(any(Tprivilege.class))).willReturn(privilegeDtoValid);

        List<PrivilegeDto> privilegeDtoList = this.servicePrivilege.getAll();
        assertNotNull(privilegeDtoList);
        assertEquals(1,privilegeDtoList.size());
        assertInstanceOf(PrivilegeDto.class,privilegeDtoList.get(0));

        then(this.mapperPrivilege).should(times(1)).getAll();
        then(this.dtoPrivilegeMapper).should(times(1)).TprivilegeToPrivilegDto(any(Tprivilege.class));
    }

    @Test
    void getAll_Privileges_isEmpty(){
        given(this.mapperPrivilege.getAll()).willReturn(Collections.emptyList());

        assertThrows(NoDataFoundException.class, ()-> this.servicePrivilege.getAll());

        then(this.mapperPrivilege).should(times(1)).getAll();
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }

    @Test
    void save() {
    }
}