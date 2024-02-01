package com.example.tutorialv2.controller;

import com.example.tutorialv2.data.vo.v1.VendaResponse;
import com.example.tutorialv2.data.vo.v1.VendaVo;
import com.example.tutorialv2.mapper.DozerMapper;
import com.example.tutorialv2.model.Venda;
import com.example.tutorialv2.service.VendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
@Tag(name = "Vendas", description = "Vendas Controller")
public class VendaController {

    @Autowired
    VendaService vendaService;

    @PostMapping(value = "/venda")
    public ResponseEntity realizarVenda (@RequestBody VendaVo venda) throws Exception{

        return ResponseEntity.ok(vendaService.realizarVenda(venda));
    }
}
