package com.example.tutorialv2.service;

import com.example.tutorialv2.data.vo.v1.ProdutoVO;
import com.example.tutorialv2.data.vo.v1.VendaVo;
import com.example.tutorialv2.exceptions.NaoEncontradoException;
import com.example.tutorialv2.mapper.DozerMapper;
import com.example.tutorialv2.model.Produto;
import com.example.tutorialv2.model.Venda;
import com.example.tutorialv2.repository.ProdutoRepository;
import com.example.tutorialv2.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class VendaService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    VendaRepository vendaRepository;
    public Venda realizarVenda(VendaVo venda) throws Exception {
        var enity = DozerMapper.parseObject(venda, Venda.class);

        //Validação de produtos
        var produtoRep = produtoRepository.findByIdProduto(enity.getIdProduto());
        var produto = DozerMapper.parseObject(produtoRep, Produto.class);

        if (produto.getQtd() <= 0){
            throw new NaoEncontradoException("Produto em falta");
        }
        var qtd =  enity.getQuantidadeVendida();
        produto.setQtd(produto.getQtd() - qtd);

        enity.setValorVenda(BigDecimal.valueOf(produto.getPreco() * qtd));
        produtoRepository.save(produto);

        return DozerMapper.parseObject(vendaRepository.save(enity), Venda.class);
    }

}
