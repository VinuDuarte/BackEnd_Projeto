package com.example.tutorialv2.controller;

import com.example.tutorialv2.data.vo.v1.FornecedorVO;
import com.example.tutorialv2.data.vo.v1.PerfilVO;
import com.example.tutorialv2.service.FornecedorService;
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
@RequestMapping("api/v1")
@Tag(name = "Fornecedor" , description = "Fornecedor Controller")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    @PostMapping(value = "/fornecedor",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Incluir Fornecedor",description = "Inclusão de novos Fornecedor",
            tags = {"Fornecedor"},
            responses = {
                    @ApiResponse(description = "Sucesso" , responseCode = "200", content = { @Content(schema = @Schema(implementation = FornecedorVO.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity <FornecedorVO> createFornecedor(@Valid @RequestBody FornecedorVO fornecedor) throws Exception {
        return ResponseEntity.ok(fornecedorService.createFornecedor(fornecedor));
    }

    @GetMapping(value = "/fornecedor")
    @Operation(summary = "Lista de Fornecedores",
            tags = {"Fornecedor"},
            responses = {
                    @ApiResponse(description = "Sucesso",  responseCode = "200",
                            content = {
                               @Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = FornecedorVO.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity <List<FornecedorVO>> getAllFornecedor(@RequestParam(required = false) String nome) throws Exception {
            return ResponseEntity.ok(fornecedorService.findAll(nome));
    }

    @GetMapping(value = "/fornecedor/{idFornecedor}", produces = MediaType.APPLICATION_JSON_VALUE) //GET BY ID
    @Operation(summary = "Pegar Fornecedor pelo ID", description = "get By ID",
            tags = {"Fornecedor"},
            responses = {
                    @ApiResponse(description = "Sucesso",  responseCode = "200",
                            content = {
                                    @Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = FornecedorVO.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity <FornecedorVO> getFornecedorById(@PathVariable("idFornecedor") long idFornecedor) throws Exception {
                    return ResponseEntity.ok(fornecedorService.findById(idFornecedor));
    }

    @PutMapping("/fornecedor/{idFornecedor}")
    @Operation(summary = "Atualizar Fornecedor",description = "Atualizar Fornecedor",
            tags = {"Fornecedor"},
            responses = {
                    @ApiResponse(description = "Sucesso" , responseCode = "200", content = { @Content(schema = @Schema(implementation = FornecedorVO.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity <FornecedorVO> updateFornecedor(@PathVariable("idFornecedor") long idFornecedor,
                             @RequestBody FornecedorVO fornecedor) throws  Exception {

                    return ResponseEntity.ok(fornecedorService.updateFornecedor(fornecedor));
    }

    @DeleteMapping("/fornecedor/{idFornecedor}")
    @Operation(summary = "Excluir Fornecedor",description = "Atualizar Fornecedor",
            tags = {"Fornecedor"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<?> deleteFornecedor (@PathVariable("idFornecedor") long idFornecedor) throws Exception {
            fornecedorService.deleteFornecedor(idFornecedor);
                return ResponseEntity.noContent().build();
    }


}
