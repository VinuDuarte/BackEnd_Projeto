package com.example.tutorialv2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@SequenceGenerator(name = "venda_seq", sequenceName = "venda_seq",
        initialValue = 10, allocationSize = 1)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendar_seq")
    private long idVenda;

    private Date dataVenda;

    private long idUsuario;

    private boolean exibir;

    private long idProduto;

    private int quantidadeVendida;

    private BigDecimal valorVenda;





}