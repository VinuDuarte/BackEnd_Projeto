package com.example.tutorialv2.data.vo.v1;

import lombok.Data;


@Data
public class ProdutoVO {


    private long idProduto;

    private String nome;

    private String marca;

    private int tamanho; //PP = 1 // P = 2 // M = 3 // G = 4 // GG = 5 // Unissex = 0

    private double preco;

    private String imagem;
    private int status;

    private int qtd;

    private long idFornecedor;

    public ProdutoVO() {
    }

    public ProdutoVO(long idProduto, String nome, String marca, int tamanho, double preco, String imagem, int status, int qtd, long idFornecedor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.marca = marca;
        this.tamanho = tamanho;
        this.preco = preco;
        this.imagem = imagem;
        this.status = status;
        this.qtd = qtd;
        this.idFornecedor = idFornecedor;
    }
}
