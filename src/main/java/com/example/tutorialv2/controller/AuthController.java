package com.example.tutorialv2.controller;

import com.example.tutorialv2.data.vo.v1.FornecedorVO;
import com.example.tutorialv2.data.vo.v1.security.AccountCredentialsVO;
import com.example.tutorialv2.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Token", description = "Serviço de Autorização da aplicação")
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



    @PostMapping(value = "/signin",
             produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

    @Operation(summary = "Realizar Login",description = "Auth da aplicação",
            tags = {"Token"},
            responses = {
                    @ApiResponse(description = "Sucesso" , responseCode = "200", content = { @Content(schema = @Schema(implementation = AccountCredentialsVO.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )

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
