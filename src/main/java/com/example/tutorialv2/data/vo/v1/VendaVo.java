package com.example.tutorialv2.data.vo.v1;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VendaVo {

    private long idVenda;

    private long idUsuario;

    private boolean exibir;

    private long idProduto;

    private int quantidadeVendida;

}
