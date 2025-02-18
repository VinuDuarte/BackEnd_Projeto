package com.example.tutorialv2.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoVO {

    @JsonIgnore
    private long idProduto;

    @Schema(description = "nome", example = "Camiseta")
    private String nome;

    @Schema(description = "marca", example = "Adidas")
    private String marca;

    @Schema(description = "tamanho", example = "1")
    private int tamanho; //PP = 1 // P = 2 // M = 3 // G = 4 // GG = 5 // Unissex = 0

    @Schema(description = "preco", example = "10.00")
    private double preco;

    private String imagem;//TODO MultipartFile

    @Schema(description = "status", example = "1")
    private int status;

    @Schema(description = "qtd", example = "1")
    private int qtd;

    @Schema(description = "idFornecedor", example = "1")
    private long idFornecedor;

}
