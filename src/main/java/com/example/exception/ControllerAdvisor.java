package com.example.exception;

import com.example.dto.ErrorResponseDto;
import com.example.dto.user.UserRegSaveDto;
import com.example.exception.address.AddressNotSaveException;
import com.example.exception.user.UserNotSaveException;
import com.example.exception.user.UsernameInvalid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
            data= new HashMap<>();
            data.put("idenCard",user.getIdenCard());
            data.put("name", user.getName());
            data.put("lastName", user.getLastName());
            data.put("email", user.getEmail());
            data.put("contact", user.getContact());
            data.put("fechaNacimiento", user.getFechaNacimiento());
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
}
