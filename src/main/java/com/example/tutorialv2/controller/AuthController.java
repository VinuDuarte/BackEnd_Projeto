package com.example.tutorialv2.controller;

import com.example.tutorialv2.data.vo.v1.security.AccountCredentialsVO;
import com.example.tutorialv2.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth EndPoint")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    AuthService authService;


     private boolean checkIfParamIsNotNull(AccountCredentialsVO data){
           return  data == null
                || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPassword() == null || data.getPassword().isBlank();
     }


    @Operation(summary = "Autenticação do user e retorno do token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin (@RequestBody AccountCredentialsVO data) throws Exception{
        if (checkIfParamIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("CLIENTE DE REQUISIÇÃO INVALIDO");

        var token = authService.signin(data);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;

    }

    private boolean checkIfParamIsNotNull(String username, String refreshToken) {
         return refreshToken == null || refreshToken.isBlank()
                 || username == null || username.isBlank();
    }

    @Operation(summary = "Refresh token")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken (@PathVariable("username") String username,
                             @RequestHeader("Authorization") String refreshToken) throws Exception{

        if (checkIfParamIsNotNull(username,refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("CLIENTE DE REQUISIÇÃO INVALIDO");

        var token = authService.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;

    }



}
