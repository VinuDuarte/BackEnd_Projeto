package com.example.tutorialv2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "datavenda")
    private LocalDate dataVenda;

    @Column(name = "id_usuario")
    private long idUsuario;

    @Column(name = "exibir")
    private boolean exibir;

    @Column(name = "id_produto")
    private long idProduto;

    @Column(name = "quantidade_produto")
    private int quantidadeVendida;

    @Column(name = "valor_venda")
    private BigDecimal valorVenda;

    @PrePersist
    private void onCreate() {
        this.dataVenda = LocalDate.now();
    }




}
