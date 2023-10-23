package com.example.tutorialv2.controller;


import com.example.tutorialv2.data.vo.v1.ProdutoVO;
import com.example.tutorialv2.data.vo.v1.TutorialVO;
import com.example.tutorialv2.data.vo.v1.UsuarioVO;
import com.example.tutorialv2.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "Produto", description = "Produto Controller")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping(value = "/produto", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista Todos os produtos", tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Sucesso",  responseCode = "200",
                            content = {
                                    @Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = ProdutoVO.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }

    )
    public ResponseEntity<PagedModel<EntityModel<ProdutoVO>>> getAllProdutos
         (@RequestParam(value = "page", defaultValue = "0") Integer page,
          @RequestParam(value = "limit", defaultValue = "10") Integer limit,
          @RequestParam(value = "direction", defaultValue = "asc") String direction)
        {

        var sortDirection = "desc".equalsIgnoreCase(direction)
                ? Sort.Direction.DESC : Sort.Direction.ASC;


        Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection, "idProduto"));
        return ResponseEntity.ok(produtoService.findAll(pageable));
    }

    @GetMapping(value = "/buscarProduto/{buscar}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar produtos Pelo nome", tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Sucesso",  responseCode = "200",
                            content = {
                                    @Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = ProdutoVO.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }

    )
    public ResponseEntity<List<ProdutoVO>> buscarByNome
            (@RequestParam(value = "buscar") String buscar) throws Exception {
        //Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection, "idProduto"));
        return ResponseEntity.ok(produtoService.buscarByNome(buscar));
    }

    @GetMapping(value = "/produto/{idProduto}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Produtos por ID", description = "get By ID",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Sucesso",  responseCode = "200",
                            content = {
                                    @Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = ProdutoVO.class)))
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<ProdutoVO> getProdutoById(@PathVariable("idProduto") long idProduto) throws Exception {
        return ResponseEntity.ok(produtoService.findById(idProduto));
    }

    @PostMapping(value = "/produto", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Incluir Produtos",description = "Inclusão de novo Produtos",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Sucesso" , responseCode = "200", content = { @Content(schema = @Schema(implementation = ProdutoVO.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<ProdutoVO> postProduto(@Valid @RequestBody ProdutoVO produto) throws Exception {
        return ResponseEntity.ok(produtoService.createProduto(produto));
    }

    @PutMapping(value = "/produto/{idProduto}", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Editar Produtos",description = "Editar Produtos",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Sucesso" , responseCode = "200", content = { @Content(schema = @Schema(implementation = ProdutoVO.class))
                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<ProdutoVO> updateProduto(@PathVariable("idProduto") long idProduto,
                                                   @RequestBody ProdutoVO produto ) throws Exception {
            return ResponseEntity.ok(produtoService.updateProduto(produto));
    }

    @DeleteMapping("/produto/{idProduto}")
    @Operation(summary = "Excluir Produto",description = "Delete Produto",
            tags = {"Produto"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<?> deleteProduto (@PathVariable("idProduto") long idProduto) throws  Exception {
        produtoService.deleteProduto(idProduto);
            return ResponseEntity.noContent().build();
    }


}
