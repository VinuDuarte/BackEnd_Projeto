package com.example.tutorialv2.controller;


import com.example.tutorialv2.data.vo.v1.ProdutoVO;
import com.example.tutorialv2.data.vo.v1.UsuarioVO;
import com.example.tutorialv2.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Usuarios", description = "Usuario Controller")
public class UsuarioController {

    @Autowired //INJEÇÃO DE DEPENDENCIA
    UsuarioService usuarioService;


    @GetMapping(value = "/usuarios" ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista de todos os Usuarios",
        tags = {"Usuarios"},
        responses = {
            @ApiResponse(description = "Sucesso",  responseCode = "200",
                    content = {
                        @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioVO.class)))
                    }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    public ResponseEntity<List<UsuarioVO>> getAllUsuarios
            (@RequestParam(required = false) String nome) throws Exception{
        return ResponseEntity.ok(usuarioService.findAll(nome));
    }

    @GetMapping(value = "/usuarios/{idUsuario}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Usuarios por ID", description = "get By ID",
            tags = {"Usuarios"},
            responses = {
                    @ApiResponse(description = "Sucesso",  responseCode = "200",
                            content = {
                                    @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioVO.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public  ResponseEntity<UsuarioVO> getUsuariosById(@PathVariable("idUsuario")long idUsuario) throws Exception {
            return ResponseEntity.ok(usuarioService.findById(idUsuario));

    }

    @PostMapping(value = "/usuarios",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Incluir Usuarios",description = "Inclusão de novo usuario",
            tags = {"Usuarios"},
            responses = {
            @ApiResponse(description = "Sucesso" , responseCode = "200", content = { @Content(schema = @Schema(implementation = UsuarioVO.class))
           }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )

    public ResponseEntity<UsuarioVO> postUsuario(@Valid @RequestBody UsuarioVO usuario) throws Exception{
           return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }



    @PutMapping(value = "/usuarios/{idUsuario}", params = "status")
    public ResponseEntity inativarOuAtivarUsuario(@PathVariable ("idUsuario") Long idUsuario,
                                                             @RequestParam("status") int status ,
                                                             UsuarioVO usuario) throws Exception {

         return ResponseEntity.ok(usuarioService.inativarOuAtivarUsuario(usuario));
    }


    @DeleteMapping(value = "/usuarios/{idUsuario}")
    @Operation(summary = "Deletar Usuario",description = "Deletar usuario da base",
        tags = {"Usuarios"},
        responses = {
            @ApiResponse(description = "Sucesso", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        })
    public ResponseEntity<?> deleteUsuario(@PathVariable("idUsuario") long idUsuario) throws Exception{
            usuarioService.deleteUsuario(idUsuario);
            return ResponseEntity.noContent().build();
    }



}
