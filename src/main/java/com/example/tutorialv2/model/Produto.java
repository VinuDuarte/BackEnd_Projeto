package com.example.tutorialv2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@SequenceGenerator(name = "produto_id_produto_seq", sequenceName = "produto_id_produto_seq",
        initialValue = 1, allocationSize = 1)
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto implements Serializable {



    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =
            GenerationType.SEQUENCE, generator = "produto_id_produto_seq")
    private long idProduto;

    @NotNull(message = "Campo nome é obrigatório.")
    private String nome;

   // @NotEmpty(message = "Campo Marca deve ser preenchido")

    private String marca;

    //@Size(max = 20)

    private int tamanho; //PP = 1 // P = 2 // M = 3 // G = 4 // GG = 5 // Unissex = 0

    //@NotNull(message = "Campo Preço deve ser obrigatório é obrigatório.")
    //@Size(min = 1)

    private double preco;


    private String imagem;



    private int status;


    private int qtd;

    //@NotBlank(message = "Campo fornecedor deve ser preenchido")
    //@Column(name = "id_fornecedor")
    private long idFornecedor;



}