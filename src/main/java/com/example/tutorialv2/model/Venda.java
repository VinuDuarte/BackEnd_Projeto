package com.example.tutorialv2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venda")
@SequenceGenerator(name = "venda_seq", sequenceName = "venda_seq",
        initialValue = 1, allocationSize = 1)
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
    private long idVenda;

    private Date dataVenda;

    private long idUsuario;

    private boolean exibir;

    private long idProduto;

    private int quantidadeVendida;

    private BigDecimal valorVenda;





}
