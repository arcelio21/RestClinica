package com.example.exception;

import com.example.dto.ErrorResponseDto;
import com.example.dto.address.AddressRequestDto;
import com.example.dto.address.district.DistrictDto;
import com.example.dto.address.province.ProvinceDto;
import com.example.dto.address.village.VillageDto;
import com.example.dto.modules.ModulesDto;
import com.example.dto.modules.modulesprivileges.ModulePrivilegeSaveDto;
import com.example.dto.modules.privileges.PrivilegeSaveDto;
import com.example.dto.modules.privileges.PrivilegeUpdateDto;
import com.example.dto.user.type_user.TypeUserDto;
import com.example.dto.user.user_reg.UserRegSaveDto;
import com.example.dto.user.user_reg.UserRegUpdateDto;
import com.example.dto.user.user_reg.UserUpdatePassDto;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.address.AddressNotUpdateException;
import com.example.exception.address.district.DistrictNotSaveException;
import com.example.exception.address.district.DistrictNotUpdateException;
import com.example.exception.address.province.ProvinceNotSaveException;
import com.example.exception.address.province.ProvinceNotUpdateException;
import com.example.exception.address.village.VillageNotSaveException;
import com.example.exception.address.village.VillageNotUpdateException;
import com.example.exception.modules.modules.ModulesNotSaveException;
import com.example.exception.modules.modules.ModulesNotUpdateException;
import com.example.exception.modules.privilege.PrivilegeNotSaveException;
import com.example.exception.modules.privilege.PrivilegeNotUpdateException;
import com.example.exception.user.type_user.TypeUserNotSaveException;
import com.example.exception.user.type_user.TypeUserNotUpdateException;
import com.example.exception.user.user_reg.PasswordNotUpdateException;
import com.example.exception.user.user_reg.UserNotSaveException;
import com.example.exception.user.user_reg.UserNotUpdateException;
import com.example.exception.user.user_reg.UsernameInvalid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNoDatFoundException(NoDataFoundException ex){

        var error = ErrorResponseDto.builder()
                .fecha(LocalDate.now())
                .messageError(ex.getMessage())
                .build();

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameInvalid.class)
    public ResponseEntity<ErrorResponseDto> handleUsernameInvalid(UsernameInvalid ex){

        var error = ErrorResponseDto.builder()
                .fecha(LocalDate.now())
                .messageError(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotSaveException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotSave(UserNotSaveException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null){
            UserRegSaveDto user = (UserRegSaveDto) ex.getData();
            data = datUserToDataError(user.getIdenCard(), user.getName(), user.getLastName(), user.getEmail(), user.getContact(), user.getBirthday(), user.getVillageId(), user.getDirecSpecific());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data(
                        (data==null)?"":data
                )
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotUpdate(UserNotUpdateException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null){
            UserRegUpdateDto user = (UserRegUpdateDto) ex.getData();
            data = datUserToDataError(user.getIdenCard(), user.getName(), user.getLastName(), user.getEmail(), user.getContact(), user.getBirthday(), user.getVillageId(), user.getDirecSpecific());
            data.put("addressId", user.getAddressId());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data(
                        (data==null)?"":data
                )
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> datUserToDataError(Long idenCard, String name, String lastName, String email, String contact, LocalDate birthday, Long villageId, String direcSpecific) {
        Map<String, Object> data;
        data= new HashMap<>();
        data.put("idenCard", idenCard);
        data.put("name", name);
        data.put("lastName", lastName);
        data.put("email", email);
        data.put("contact", contact);
        data.put("birthday", birthday);
        data.put("villageId", villageId);
        data.put("direcSpecific", direcSpecific);
        return data;
    }

    /**
     * EXCEPCIONES DE ADDRESS
     */

    @ExceptionHandler(AddressNotSaveException.class)
    public ResponseEntity<ErrorResponseDto> handleAddressNotSave(AddressNotSaveException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null){
            AddressRequestDto user = (AddressRequestDto) ex.getData();
            data= new HashMap<>();
            data.put("villageId", user.getVillageId());
            data.put("direcSpecific", user.getSpecificAddress());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data(
                        (data==null)?"":data
                )
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressNotUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleAddressNotUpdate(AddressNotUpdateException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null) {

            AddressRequestDto address = (AddressRequestDto) ex.getData();
            data= new HashMap<>();
            data.put("idAddress", address.getId());
            data.put("villageId", address.getVillageId());
            data.put("direcSpecific", address.getSpecificAddress());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data(
                        (data==null)?"":data
                )
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * EXCEPCIONES DE DISTRITOS
     */
    @ExceptionHandler(DistrictNotUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleDistrictNotUpdate(DistrictNotUpdateException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null) {

            DistrictDto district = (DistrictDto) ex.getData();
            data= new HashMap<>();
            data.put("idDistrict", district.getId());
            data.put("name", district.getName());
            data.put("provinceId", district.getProvinceId());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data(
                        (data==null)?"":data
                )
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DistrictNotSaveException.class)
    public ResponseEntity<ErrorResponseDto> handleDistrictNotSave(DistrictNotUpdateException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null){
            DistrictDto district = (DistrictDto) ex.getData();
            data= new HashMap<>();
            data.put("name", district.getName());
            data.put("provinceId", district.getProvinceId());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data((data==null)?"":data)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * EXCEPCIONES DE VILLAGE
     */
    @ExceptionHandler(VillageNotUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleVillageNotUpdate(VillageNotUpdateException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null) {

            VillageDto villageDto = (VillageDto) ex.getData();
            data= new HashMap<>();
            data.put("idVillage", villageDto.getId());
            data.put("name", villageDto.getName());
            data.put("districtId", villageDto.getDistrictId());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data(
                        (data==null)?"":data
                )
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VillageNotSaveException.class)
    public ResponseEntity<ErrorResponseDto> handleDistrictNotSave(VillageNotSaveException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null){
            VillageDto district = (VillageDto) ex.getData();
            data= new HashMap<>();
            data.put("name", district.getName());
            data.put("districtId", district.getDistrictId());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data((data==null)?"":data)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * EXCEPCIONES DE PROVINCE
     */
    @ExceptionHandler(ProvinceNotUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handleVillageNotUpdate(ProvinceNotUpdateException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null) {

            ProvinceDto provinceDto = (ProvinceDto) ex.getData();
            data= new HashMap<>();
            data.put("idProvince", provinceDto.getId());
            data.put("name", provinceDto.getName());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data(
                        (data==null)?"":data
                )
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProvinceNotSaveException.class)
    public ResponseEntity<ErrorResponseDto> handleDistrictNotSave(ProvinceNotSaveException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null){
            ProvinceDto province = (ProvinceDto) ex.getData();
            data= new HashMap<>();
            data.put("name", province.getName());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data((data==null)?"":data)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(PasswordNotUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handlePaawordNotUpdate(PasswordNotUpdateException ex){

        Map<String, Object> info = null;

        if (ex.getData()!=null){
            UserUpdatePassDto data = (UserUpdatePassDto) ex.getData();
            info= new HashMap<>();

            info.put("IndenCard", data.getIndeCard());
            info.put("oldPass", data.getOldPassword());
            info.put("newPass", data.getNewPassword());
        }

        var error = ErrorResponseDto.builder()
                .messageError(ex.getMessage())
                .fecha(LocalDate.now())
                .data((info==null)?"No data":info)
                .build();

        return ResponseEntity.badRequest().body(error);
    }


    /**
     * PARA CAPTURAR ERRORES DE VALIDACION DE DATOS EN ENTIDADES
     * @param ex
     * @return
     */
    public ResponseEntity<Object> handleArgumentNotValid(MethodArgumentNotValidException ex){

        Map<String, Object> data = new HashMap<>();


        data.put("Datos enviados", ex.getBindingResult().getTarget());
        Map<String, Object>  errorDeCampo = new HashMap<>(); ;
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()){
            errorDeCampo.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        data.put("Errores", errorDeCampo);

        var error = ErrorResponseDto.builder()
                .messageError("Error en validacion de valor de atributos")
                .data(data)
                .fecha(LocalDate.now())
                .build();

        return ResponseEntity.unprocessableEntity().body(error);
    }

    /**
     * LO SOBRESCRIBI PARA QUE EN CASO DE QUE EL ERROR FUERA POR CAMPO CON DATOS NO VALIDO
     * EJECUTAR EL METODO PERSONALIZADO QUE CREE
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {


        if(ex.getClass().equals(MethodArgumentNotValidException.class)) {
            return this.handleArgumentNotValid((MethodArgumentNotValidException) ex);
        }else {
            return super.handleExceptionInternal(ex, body, headers, status, request);
        }

    }

    @ExceptionHandler({TypeUserNotUpdateException.class})
    public ResponseEntity<ErrorResponseDto> handleTypeUserNotUpdate(TypeUserNotUpdateException ex){

        Map<String, Object> data = null;

        if(ex.getData()!=null){
            TypeUserDto typeUserDto = (TypeUserDto) ex.getData();
            data = this.typeUserDtoToMap(typeUserDto);
        }

        var error = ErrorResponseDto.builder()
                .messageError("Datos enviados por cliente no validos")
                .data((ex.getData()==null)?"":data)
                .fecha(LocalDate.now())
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({TypeUserNotSaveException.class})
    public ResponseEntity<ErrorResponseDto> handleTypeUserNotUpdate(TypeUserNotSaveException ex){

        Map<String, Object> data = null;

        if(ex.getData()!=null){
            TypeUserDto typeUserDto = (TypeUserDto) ex.getData();
            data = this.typeUserDtoToMap(typeUserDto);
        }

        var error = ErrorResponseDto.builder()
                .messageError("Datos enviados por cliente no validos")
                .data((ex.getData()==null)?"":data)
                .fecha(LocalDate.now())
                .build();
        return ResponseEntity.badRequest().body(error);
    }
    
    private Map<String,Object> typeUserDtoToMap(TypeUserDto typeUserDto){
        Map<String,Object> data = new HashMap<>();
        data.put("id", typeUserDto.getId());
        data.put("name", typeUserDto.getName());
        return data;
    }


    //EXCEPTION MODULES
    @ExceptionHandler(ModulesNotUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handlerModulesNotUpdate(ModulesNotUpdateException ex){

        Map<String,Object> data = null;

        if(ex.getData()!=null){

            data = new HashMap<>();
            ModulesDto modulesDto = (ModulesDto)ex.getData();

            data.put("id", modulesDto.getId());
            data.put("name_module", modulesDto.getName());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponseDto.builder()
                .fecha(LocalDate.now())
                .messageError(ex.getMessage())
                .data((data!=null)?data:"")
                .build()
        );
    }


    @ExceptionHandler(ModulesNotSaveException.class)
    public ResponseEntity<ErrorResponseDto> handlerModulesNotSave(ModulesNotSaveException ex){

        Map<String,Object> data = null;

        if(ex.getData()!=null){

            data = new HashMap<>();

            ModulesDto modulesDto = (ModulesDto)ex.getData();

            data.put("name_module", modulesDto.getName());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponseDto.builder()
                .fecha(LocalDate.now())
                .data((data!=null)?data:"")
                .messageError(ex.getMessage())
                .build()
        );
    }

    //EXCEPCION PRIVILEGES

    @ExceptionHandler(PrivilegeNotUpdateException.class)
    public ResponseEntity<ErrorResponseDto> handlerPrivilegesNotUpdate(PrivilegeNotUpdateException ex){

        Map<String, Object> data =null;

        if(ex.getData()!=null){
            PrivilegeUpdateDto privilege = (PrivilegeUpdateDto) ex.getData();
            data = new HashMap<>();
            data.put("ID", privilege.getId());
            data.put("Name", privilege.getName());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          ErrorResponseDto.builder()
                  .data( (data==null)
                          ?""
                          :data)
                  .messageError(ex.getMessage())
                  .fecha(LocalDate.now())
                  .build()
        );
    }

    @ExceptionHandler(PrivilegeNotSaveException.class)
    public ResponseEntity<ErrorResponseDto> handlerPrivilegesNotUpdate(PrivilegeNotSaveException ex){

        Map<String, Object> data =null;

        if(ex.getData()!=null){
            PrivilegeSaveDto privilege = (PrivilegeSaveDto) ex.getData();
            data = new HashMap<>();
            data.put("Name", privilege.getName());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponseDto.builder()
                        .data( (data==null)
                                ?""
                                :data)
                        .messageError(ex.getMessage())
                        .fecha(LocalDate.now())
                        .build()
        );
    }

    // EXCEPTION MODULEPRIVILEGES

    @ExceptionHandler(ModulesNotSaveException.class)
    public ResponseEntity<ErrorResponseDto>  handlerModulesPrivilegeNotSave(ModulesNotSaveException ex){

        Map<String, Object> data = null;

        if(ex.getData()!=null){
            data = new HashMap<>();
            ModulePrivilegeSaveDto privilege = (ModulePrivilegeSaveDto) ex.getData();
            data.put("privilegeId", privilege.getPrivilegeId());
            data.put("modulesId", privilege.getModuleId());
            data.put("statusId", privilege.getStatusId());
        }

        return ResponseEntity.badRequest().body(
                ErrorResponseDto.builder()
                        .fecha(LocalDate.now())
                        .messageError(ex.getMessage())
                        .data(
                                (ex.getData()!=null)
                                ?ex.getData()
                                :""
                        )
                        .build()
        );

    }
    //EXCEPCION SQL

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String,Object>> handlerSqlException(SQLException ex){
        log.error(ex.getMessage()+ ", SqlState: "+ex.getSQLState());
        Map<String,Object> response = Map.of("message", "Datos no válidos", "Fecha", LocalDate.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    //EXCEPTION AUTHORIZATION
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String,Object>> handlerAuthenticationEntryPointException(AuthenticationException ex){

        Map<String,Object> responseError = Map.of("MessageError", ex.getMessage(),"Fecha", LocalDate.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                responseError
        );
    }
}
