package com.example.exception;

import com.example.dto.ErrorResponseDto;
import com.example.dto.user.user_reg.UserRegSaveDto;
import com.example.dto.user.user_reg.UserRegUpdateDto;
import com.example.dto.user.user_reg.UserUpdatePassDto;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.address.AddressNotUpdateException;
import com.example.exception.user.PasswordNotUpdateException;
import com.example.exception.user.UserNotSaveException;
import com.example.exception.user.UserNotUpdateException;
import com.example.exception.user.UsernameInvalid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleNoDatFoundException(NoDataFoundException ex){

        var error = ErrorResponseDto.builder()
                .fecha(LocalDate.now())
                .messageError(ex.getMessage())
                .build();

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameInvalid.class)
    public ResponseEntity<Object> handleUsernameInvalid(UsernameInvalid ex){

        var error = ErrorResponseDto.builder()
                .fecha(LocalDate.now())
                .messageError(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotSaveException.class)
    public ResponseEntity<Object> handleUserNotSave(UserNotSaveException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null){
            UserRegSaveDto user = (UserRegSaveDto) ex.getData();
            data = datUserToDataError(user.getIdenCard(), user.getName(), user.getLastName(), user.getEmail(), user.getContact(), user.getFechaNacimiento(), user.getVillageId(), user.getDirecSpecific());
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
    public ResponseEntity<Object> handleUserNotUpdate(UserNotUpdateException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null){
            UserRegUpdateDto user = (UserRegUpdateDto) ex.getData();
            data = datUserToDataError(user.getIdenCard(), user.getName(), user.getLastName(), user.getEmail(), user.getContact(), user.getFechaNacimiento(), user.getVillageId(), user.getDirecSpecific());
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

    private Map<String, Object> datUserToDataError(Long idenCard, String name, String lastName, String email, String contact, LocalDate fechaNacimiento, Long villageId, String direcSpecific) {
        Map<String, Object> data;
        data= new HashMap<>();
        data.put("idenCard", idenCard);
        data.put("name", name);
        data.put("lastName", lastName);
        data.put("email", email);
        data.put("contact", contact);
        data.put("fechaNacimiento", fechaNacimiento);
        data.put("villageId", villageId);
        data.put("direcSpecific", direcSpecific);
        return data;
    }

    @ExceptionHandler(AddressNotSaveException.class)
    public ResponseEntity<Object> handleUserNotSave(AddressNotSaveException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null){
            UserRegSaveDto user = (UserRegSaveDto) ex.getData();
            data= new HashMap<>();
            data.put("villageId", user.getVillageId());
            data.put("direcSpecific", user.getDirecSpecific());
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
    public ResponseEntity<Object> handleUserNotSave(AddressNotUpdateException ex){

        Map<String, Object> data=null;
        if(ex.getData()!=null) {

            UserRegUpdateDto user = (UserRegUpdateDto) ex.getData();
            data= new HashMap<>();
            data.put("villageId", user.getVillageId());
            data.put("direcSpecific", user.getDirecSpecific());
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

    @ExceptionHandler(PasswordNotUpdateException.class)
    public ResponseEntity<Object> handlePaawordNotUpdate(PasswordNotUpdateException ex){

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
}
