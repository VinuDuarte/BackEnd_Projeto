package com.example.tutorialv2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@SequenceGenerator(name = "VENDA_SEQUENCE", sequenceName = "SQ_VENDA",
         allocationSize = 1)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VENDA_SEQUENCE")
    private long idVenda;

    private Date dataVenda;

    private long idUsuario;

    private boolean exibir;

    private long idProduto;

    private int quantidadeVendida;

    private BigDecimal valorVenda;





}
