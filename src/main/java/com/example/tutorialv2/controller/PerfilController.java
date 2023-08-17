package com.example.tutorialv2.controller;

import com.example.tutorialv2.data.vo.v1.PerfilVO;
import com.example.tutorialv2.data.vo.v1.UsuarioVO;
import com.example.tutorialv2.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "Perfil", description = "Perfil Controller")
public class PerfilController {



    @Autowired
    private PerfilService perfilService;

    @GetMapping(value = "/perfil" , produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista de Perfis",
            tags = {"Perfil"},
            responses = {
                    @ApiResponse(description = "Sucesso",  responseCode = "200",
                            content = {
                                    @Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = PerfilVO.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<List<PerfilVO>> getAllPerfil(@RequestParam(required = false) String nome) throws Exception {
            return ResponseEntity.ok(perfilService.findAll(nome));
    }

    @GetMapping(value = "/perfil/{idPerfil}",
            produces = MediaType.APPLICATION_JSON_VALUE) //GET BY ID
    @Operation(summary = "Pegar perfil pelo ID", description = "get By ID",
            tags = {"Perfil"},
            responses = {
                    @ApiResponse(description = "Sucesso",  responseCode = "200",
                            content = {
                                    @Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = PerfilVO.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity <PerfilVO>getPerfilById(@PathVariable("idPerfil") long idPerfil) throws Exception{
            return ResponseEntity.ok(perfilService.findById(idPerfil));
    }

    @PostMapping(value = "/perfil",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Incluir Perfil",description = "Inclusão de novos Perfis",
            tags = {"Perfil"},
            responses = {
                    @ApiResponse(description = "Sucesso" , responseCode = "200", content = { @Content(schema = @Schema(implementation = PerfilVO.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<PerfilVO> postPerfil(@Valid @RequestBody PerfilVO perfil) throws Exception{
            return ResponseEntity.ok(perfilService.createPerfil(perfil));

    }

    @DeleteMapping(value = "/perfil/{idPerfil}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Deletar Perfil",description = "Deletar Perfil da base",
            tags = {"Perfil"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> deletePerfil(@PathVariable("idPerfil") long idPerfil) throws Exception{
            perfilService.deletePerfil(idPerfil);
            return ResponseEntity.noContent().build();
    }


}
