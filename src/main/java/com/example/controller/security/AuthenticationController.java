package com.example.controller.security;

import com.example.controller.ControllerTemplate;
import com.example.dto.AuthenticationRequest;
import com.example.dto.AuthenticationResponse;
import com.example.dto.RegisterRequest;
import com.example.entity.user.TuserReg;
import com.example.service.jwt.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController extends ControllerTemplate {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

        return ResponseEntity.ok(this.authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){

        return  ResponseEntity.ok(this.authenticationService.authenticate(request));
    }

    /**
     * Un ejemplo de como se puede utilizar la anotacion
     * para obtener informacion del usuario autenticado
     * @param tuserReg
     * @return
     */
    @GetMapping(value = "/data")
    public String dataUser(@AuthenticationPrincipal TuserReg tuserReg){
        return tuserReg.getName()+" "+tuserReg.getLastName();
    }
}
